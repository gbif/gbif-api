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
import org.gbif.api.vocabulary.EndpointType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class EndpointPriorityComparatorTest {

  private static final EndpointPriorityComparator COMP = new EndpointPriorityComparator();

  @Test
  public void testSuccessfulSimpleComparisons() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setType(EndpointType.TAPIR);
    e2.setType(EndpointType.BIOCASE);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.BIOCASE);
    e2.setType(EndpointType.BIOCASE);
    assertEquals(0, COMP.compare(e1, e2));

    e1.setType(EndpointType.BIOCASE);
    e2.setType(EndpointType.TAPIR);
    assertTrue(COMP.compare(e1, e2) < 0);

    e1.setType(EndpointType.BIOCASE);
    e2.setType(EndpointType.DIGIR_MANIS);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.DWC_ARCHIVE);
    e2.setType(EndpointType.TAPIR);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.BIOCASE_XML_ARCHIVE);
    e2.setType(EndpointType.BIOCASE);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.DWC_ARCHIVE);
    e2.setType(EndpointType.BIOCASE_XML_ARCHIVE);
    assertTrue(COMP.compare(e1, e2) > 0);
  }

  @Test
  public void testPriorityComparisons() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setType(EndpointType.TAPIR);
    e2.setType(EndpointType.BIOCASE);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.DIGIR_MANIS);
    e2.setType(EndpointType.DIGIR_MANIS);
    assertEquals(0, COMP.compare(e1, e2));
  }

  @Test
  public void testInvalidComparison1() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setType(EndpointType.TAPIR);
    e2.setType(EndpointType.WMS);
    assertThrows(ClassCastException.class, () -> COMP.compare(e1, e2));
  }

  @Test
  public void testInvalidComparison2() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setType(EndpointType.WMS);
    e2.setType(EndpointType.TAPIR);
    assertThrows(ClassCastException.class, () -> COMP.compare(e1, e2));
  }
}
