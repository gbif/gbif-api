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
package org.gbif.api.model.crawler;

/**
 * This enum lists the reasons why a crawl is finished.
 */
public enum FinishReason {
  /**
   * This status means that we were able to finish the crawl without any fatal errors and without exhausting retries.
   * There could still have been intermittent errors but we managed to reach the end of an endpoint or a DwC-A file.
   */
  NORMAL,

  /**
   * This status means that the user requested an abort of the crawl and that is the reason this crawl is done.
   */
  USER_ABORT,

  /**
   * This status means that we had to abort the crawl for any abnormal reason (endpoint down, not a valid archive,
   * ...). To find the exact reason(s) you need to look at the logs to see past errors.
   */
  ABORT,

  /**
   * Crawling has stopped because the source data was not modified since the last crawl.
   */
  NOT_MODIFIED,

  /**
   * This status means that we don't know why the crawl aborted. This is a sign of a programming error and should be
   * investigated.
   */
  UNKNOWN
}
