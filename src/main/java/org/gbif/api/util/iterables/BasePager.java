/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.gbif.api.util.PreconditionUtils.checkArgument;

/**
 * Iterator over registry entities from paging responses that filters out deleted entities.
 */
abstract class BasePager<T> implements Iterable<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BasePager.class);
    private final int pageSize;

    /**
     * @param pageSize to use when talking to the registry
     */
    public BasePager(int pageSize) {
        checkArgument(pageSize > 0, "pageSize must at least be 1");
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
                if (!exclude(entity)) {
                    return entity;
                }
            }
        }

        private void loadPage() {
            LOG.debug("Loading page {}-{}", page.getOffset(), page.getOffset() + page.getLimit());
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
