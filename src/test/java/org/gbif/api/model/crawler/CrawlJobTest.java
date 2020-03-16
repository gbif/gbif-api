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

import org.gbif.api.vocabulary.EndpointType;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CrawlJobTest {

  @Test
  public void testEquals() {
    Map<String, String> m1 = new HashMap<>();
    m1.put("foo", "bar");
    CrawlJob c1 = new CrawlJob(UUID.randomUUID(), EndpointType.BIOCASE, URI.create("http://www.foo.com"), 1, m1);

    Map<String, String> m2 = new HashMap<>();
    m2.put("foo", "bar");
    CrawlJob c2 = new CrawlJob(UUID.fromString(c1.getDatasetKey().toString()),
                               EndpointType.BIOCASE,
                               URI.create("http://www.foo.com"),
                               1,
                               m2);

    assertEquals(c1, c2);
    assertEquals(c1.hashCode(), c2.hashCode());
  }
}
