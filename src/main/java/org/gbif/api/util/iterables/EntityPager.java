package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.NetworkEntity;

import java.util.Iterator;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Iterator over registry entities from paging responses that filters out deleted entities.
 */
abstract class EntityPager<T extends NetworkEntity> implements Iterable<T> {
    private static final Logger LOG = LoggerFactory.getLogger(EntityPager.class);
    private final int pageSize;

    /**
     * @param pageSize to use when talking to the registry
     */
    public EntityPager(int pageSize) {
        Preconditions.checkArgument(pageSize > 0, "pageSize must at least be 1");
        this.pageSize = pageSize;
    }

    class ResponseIterator implements Iterator<T>{
        private final PagingRequest page = new PagingRequest(0, pageSize);
        private PagingResponse<T> resp = null;
        private Iterator<T> iter;
        private T next;

        public ResponseIterator() {
            loadPage();
            next = nextEntity();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            T entity = next;
            next = nextEntity();
            return entity;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private T nextEntity() {
            while (true) {
                if (!iter.hasNext()) {
                    if (resp.isEndOfRecords()) {
                        // no more records to load, stop!
                        return null;
                    } else {
                        loadPage();
                    }
                }
                T entity = iter.next();
                if (entity.getDeleted() != null) {
                    LOG.debug("Ignore deleted entity {}: {}", entity.getKey(), entity.getTitle().replaceAll("\n", " "));
                } else if (!exclude(entity)) {
                    return entity;
                }
            }
        }

        private void loadPage() {
            LOG.debug("Loading page {}-{}", page.getOffset(), page.getOffset()+page.getLimit());
            resp = nextPage(page);
            iter = resp.getResults().iterator();
            page.nextPage();
        }
    }

    abstract PagingResponse<T> nextPage(PagingRequest page);

    /**
     * Override this method to implement other exclusion filters.
     */
    protected boolean exclude(T entity) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ResponseIterator();
    }

}
