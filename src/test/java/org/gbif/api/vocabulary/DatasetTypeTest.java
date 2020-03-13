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
package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatasetTypeTest {

  @Test
  public void testFromString() throws Exception {
    assertEquals(DatasetType.SAMPLING_EVENT, DatasetType.fromString("samplingEvent"));
    assertEquals(DatasetType.SAMPLING_EVENT, DatasetType.fromString("sampling-event"));
    assertEquals(DatasetType.SAMPLING_EVENT, DatasetType.fromString("sampling_event"));
    assertEquals(DatasetType.OCCURRENCE, DatasetType.fromString("occurrence"));
    assertEquals(DatasetType.CHECKLIST, DatasetType.fromString("checklist"));
    assertEquals(DatasetType.METADATA, DatasetType.fromString("metadata"));
    assertEquals(DatasetType.METADATA, DatasetType.fromString("METADATA"));
    assertEquals(DatasetType.METADATA, DatasetType.fromString("meta-data"));
  }
}
