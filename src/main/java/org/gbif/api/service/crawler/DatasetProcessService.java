/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.service.crawler;

import org.gbif.api.model.crawler.DatasetProcessStatus;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;

/**
 * This service exposes information regarding the crawling process and is not intended to provide historical
 * information at the moment. Only information about currently queued and running crawl jobs is exposed.
 * <p/>
 * We distinguish between XML based (BioCASe, DiGIR, TAPIR) and DwC-A datasets. These don't share the same work queues
 * because their processing is different in the beginning (downloading an archive vs. request-response type iterating
 * over the endpoint. They do however share the same pipeline for processing the gathered data.
 */
public interface DatasetProcessService {

  /**
   * Returns the processing status for a particular dataset identified by a {@link UUID} key.
   *
   * @param datasetKey the dataset key
   *
   * @return a consolidated object populated with the crawl status for the specific dataset. Returns null if the
   *         dataset is not currently being processed
   */
  @Nullable
  DatasetProcessStatus getDatasetProcessStatus(UUID datasetKey);

  /**
   * @return the processing status for all datasets that are currently being worked on (XML and DwC-A). These might be
   *         in different states, some can still be crawled on a page by page basis, some may be downloaded in the case
   *         of DwC-A and for some only the interpretation is still running.
   *         <p/>
   *         There is a chance that some processes will be returned that are already finished.
   */
  Set<DatasetProcessStatus> getRunningDatasetProcesses();

  /**
   * @return an ordered list of dataset processing statuses for all XML based datasets that are currently waiting to be
   *         crawled
   */
  List<DatasetProcessStatus> getPendingXmlDatasetProcesses();

  /**
   * @return an ordered list of dataset processing statuses for all DwC-A based datasets that are currently waiting to
   *         be crawled
   */
  List<DatasetProcessStatus> getPendingDwcaDatasetProcesses();
}
