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

import static org.gbif.api.util.PreconditionUtils.checkArgument;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.gbif.api.model.collections.descriptors.Descriptor;
import org.gbif.api.model.collections.request.DescriptorSearchRequest;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.service.collections.DescriptorsService;

/** Iterates over results of {@link DescriptorsService#listDescriptors(DescriptorSearchRequest)}. */
@Slf4j
public class CollectionDescriptorVerbatimPager implements Iterable<Map<String, String>> {

  private final DescriptorsService service;
  private final DescriptorSearchRequest searchRequest;
  private final int pageSize;

  public CollectionDescriptorVerbatimPager(
      DescriptorsService service, DescriptorSearchRequest searchRequest, int pageSize) {
    checkArgument(pageSize > 0, "pageSize must be at least 1");
    this.pageSize = pageSize;
    this.searchRequest = searchRequest;
    this.service = service;
  }

  @Override
  public Iterator<Map<String, String>> iterator() {
    return new ResponseIterator();
  }

  class ResponseIterator implements Iterator<Map<String, String>> {
    private final PagingRequest page = new PagingRequest(0, pageSize);
    private List<Map<String, String>> resp = null;
    private Iterator<Map<String, String>> iter;
    private Map<String, String> next;

    public ResponseIterator() {
      loadPage();
      next = nextEntity();
    }

    @Override
    public boolean hasNext() {
      return next != null && !next.isEmpty();
    }

    @Override
    public Map<String, String> next() {
      Map<String, String> entity = next;
      next = nextEntity();
      return entity;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    private Map<String, String> nextEntity() {
      while (true) {
        if (!iter.hasNext()) {
          if (resp.isEmpty()) {
            // no more records to load, stop!
            return null;
          } else {
            loadPage();
          }
        }
        if (iter.hasNext()) {
          return iter.next();
        }
      }
    }

    private void loadPage() {
      log.debug("Loading page {}-{}", page.getOffset(), page.getOffset() + page.getLimit());
      resp = nextPage(page);
      iter = resp.iterator();
      page.nextPage();
    }
  }

  public List<Map<String, String>> nextPage(PagingRequest page) {
    searchRequest.setLimit(page.getLimit());
    searchRequest.setOffset(page.getOffset());

    List<Map<String, String>> result = new ArrayList<>();
    for (Descriptor d : service.listDescriptors(searchRequest).getResults()) {
      result.add(d.getVerbatim());
    }
    return result;
  }
}
