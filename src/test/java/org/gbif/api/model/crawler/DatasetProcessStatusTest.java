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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gbif.api.vocabulary.EndpointType;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatasetProcessStatusTest {

  @Test
  public void testEquals() throws IOException {
    UUID uuid = UUID.randomUUID();
    Date now = new Date();
    DatasetProcessStatus s1 = DatasetProcessStatus.builder()
      .datasetKey(uuid)
      .startedCrawling(now)
      .crawlJob(new CrawlJob(uuid, EndpointType.BIOCASE, URI.create("http://www.foo.com"), 1, null))
      .crawlContext("foo")
      .processStateOccurrence(ProcessState.RUNNING)
      .processStateChecklist(ProcessState.EMPTY)
      .declaredCount(100L)
      .pagesCrawled(10L)
      .pagesFragmentedSuccessful(11L)
      .pagesFragmentedError(12L)
      .fragmentsEmitted(13L)
      .fragmentsReceived(14L)
      .fragmentsProcessed(15L)
      .rawOccurrencesPersistedNew(16L)
      .rawOccurrencesPersistedUnchanged(17L)
      .rawOccurrencesPersistedUpdated(18L)
      .rawOccurrencesPersistedError(19L)
      .verbatimOccurrencesPersistedSuccessful(20L)
      .verbatimOccurrencesPersistedError(21L)
      .interpretedOccurrencesPersistedSuccessful(22L)
      .interpretedOccurrencesPersistedError(23L)
      .build();

    DatasetProcessStatus s2 = DatasetProcessStatus.builder()
      .datasetKey(uuid)
      .startedCrawling(new Date(now.getTime()))
      .crawlJob(new CrawlJob(uuid, EndpointType.BIOCASE, URI.create("http://www.foo.com"), 1, null))
      .crawlContext("foo")
      .processStateOccurrence(ProcessState.RUNNING)
      .processStateChecklist(ProcessState.EMPTY)
      .declaredCount(100L)
      .pagesCrawled(10L)
      .pagesFragmentedSuccessful(11L)
      .pagesFragmentedError(12L)
      .fragmentsEmitted(13L)
      .fragmentsReceived(14L)
      .fragmentsProcessed(15L)
      .rawOccurrencesPersistedNew(16L)
      .rawOccurrencesPersistedUnchanged(17L)
      .rawOccurrencesPersistedUpdated(18L)
      .rawOccurrencesPersistedError(19L)
      .verbatimOccurrencesPersistedSuccessful(20L)
      .verbatimOccurrencesPersistedError(21L)
      .interpretedOccurrencesPersistedSuccessful(22L)
      .interpretedOccurrencesPersistedError(23L)
      .build();

    assertEquals(s1, s2);
    assertEquals(s1.hashCode(), s2.hashCode());

    ObjectMapper om = new ObjectMapper();

    byte[] bytes = om.writeValueAsBytes(s1);
    DatasetProcessStatus s3 = om.readValue(bytes, DatasetProcessStatus.class);
    assertEquals(s1, s3);
    assertEquals(s1.hashCode(), s3.hashCode());
  }
}
