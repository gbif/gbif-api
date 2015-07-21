package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.vocabulary.DatasetType;

import java.util.Iterator;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Iterator over datasets from paging responses that filters out deleted datasets and adds an optional type filter.
 */
abstract class DatasetBasePager implements Iterable<Dataset> {
    private static final Logger LOG = LoggerFactory.getLogger(DatasetBasePager.class);
    private final DatasetType type;
    private final int pageSize;

    /**
     * @param pageSize to use when talking to the registry
     * @param type the accepted dataset type, null for all
     */
    public DatasetBasePager(@Nullable DatasetType type, int pageSize) {
        this.type = type;
        Preconditions.checkArgument(pageSize > 0, "pageSize must at least be 1");
        this.pageSize = pageSize;
    }

    class ResponseIterator implements Iterator<Dataset>{
        private final PagingRequest page = new PagingRequest(0, pageSize);
        private PagingResponse<Dataset> resp = null;
        private Iterator<Dataset> iter;
        private Dataset next;

        public ResponseIterator() {
            loadPage();
            next = nextDataset();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Dataset next() {
            Dataset d = next;
            next = nextDataset();
            return d;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private Dataset nextDataset() {
            while (true) {
                if (!iter.hasNext()) {
                    if (resp.isEndOfRecords()) {
                        // no more records to load, stop!
                        return null;
                    } else {
                        loadPage();
                    }
                }
                Dataset d = iter.next();
                if (d.getDeleted() != null) {
                    LOG.debug("Ignore deleted dataset {}: {}", d.getKey(), d.getTitle().replaceAll("\n", " "));
                } else if (type != null && d.getType() != type) {
                    LOG.debug("Ignore {} dataset {}: {}", d.getType(), d.getKey(), d.getTitle().replaceAll("\n", " "));
                } else {
                    return d;
                }
            }
        }

        private void loadPage() {
            LOG.info("Load dataset page {}-{}", page.getOffset(), page.getOffset()+page.getLimit());
            resp = nextPage(page);
            iter = resp.getResults().iterator();
            page.nextPage();
        }
    }

    abstract PagingResponse<Dataset> nextPage(PagingRequest page);

    @Override
    public Iterator<Dataset> iterator() {
        return new ResponseIterator();
    }

}
