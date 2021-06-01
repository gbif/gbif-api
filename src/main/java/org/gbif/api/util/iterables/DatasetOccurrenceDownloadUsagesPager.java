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

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.DatasetOccurrenceDownloadUsage;
import org.gbif.api.service.registry.OccurrenceDownloadService;

/**
 * Iterates over results of {@link OccurrenceDownloadService#listDatasetUsages(String, Pageable)}.
 */
public class DatasetOccurrenceDownloadUsagesPager extends BasePager<DatasetOccurrenceDownloadUsage> {

  private final OccurrenceDownloadService service;
  private final String downloadKey;

  public DatasetOccurrenceDownloadUsagesPager(OccurrenceDownloadService service,
                                              String downloadKey,
                                              int pageSize) {
    super(pageSize);
    this.downloadKey = downloadKey;
    this.service = service;
  }

  @Override
  PagingResponse<DatasetOccurrenceDownloadUsage> nextPage(PagingRequest page) {
    return service.listDatasetUsages(downloadKey, page);
  }

}
