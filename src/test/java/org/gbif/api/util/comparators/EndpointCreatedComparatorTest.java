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
package org.gbif.api.util.comparators;

import org.gbif.api.model.registry.Endpoint;

import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndpointCreatedComparatorTest {

  private static final EndpointCreatedComparator COMP = EndpointCreatedComparator.INSTANCE;
  private final long now = System.currentTimeMillis();

  @Test
  public void testSuccessfulSimpleComparisons() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setCreated(new Date(now));
    e2.setCreated(new Date(now));
    assertEquals(0, COMP.compare(e1, e2));

    e2.setCreated(new Date(now-10000));
    assertTrue(COMP.compare(e1, e2) > 0);

    e2.setCreated(new Date(now+10000));
    assertTrue(COMP.compare(e1, e2) < 0);
  }

  @Test
  public void testNullComparisons() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    assertEquals(0, COMP.compare(e1, e2));

    e1.setCreated(new Date(now));
    assertTrue(COMP.compare(e1, e2) < 0);

    e2.setCreated(new Date(now-10000));
    assertTrue(COMP.compare(e1, e2) > 0);

    e2.setCreated(new Date(now+10000));
    assertTrue(COMP.compare(e1, e2) < 0);
  }
}
