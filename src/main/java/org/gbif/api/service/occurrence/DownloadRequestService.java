/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.service.occurrence;

import org.gbif.api.model.occurrence.DownloadRequest;

import java.io.InputStream;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Provides services to manage download requests and retrieve the download file. The services provided are:
 * <ul>
 * <li><strong>request</strong> inits a new download and assigns it a key</li> *
 * <li><strong>cancel</strong> cancels an existing download</li>
 * <li><strong>getResult</strong> retrieves the download file, if it is ready</li>
 * </ul>
 */
public interface DownloadRequestService {

  /**
   * Cancels running download. If the download is not running throws an IllegalStateException.
   */
  void cancel(@NotNull String downloadKey);

  /**
   * Creates a download request. If the request is successfully created its key is returned.
   */
  String create(@NotNull DownloadRequest downloadRequest);

  /**
   * @param downloadKey of the corresponding download request
   * @return the input stream of the zipped download result file or null if it's not existing or ready yet
   */
  @Nullable
  InputStream getResult(String downloadKey);

}
