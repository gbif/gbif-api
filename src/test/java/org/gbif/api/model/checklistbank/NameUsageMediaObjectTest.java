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
package org.gbif.api.model.checklistbank;

import java.net.URI;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameUsageMediaObjectTest {

  @Test
  public void testEquals() {
    NameUsageMediaObject i1 = new NameUsageMediaObject();
    i1.setIdentifier(URI.create("http://www.example.org/image.png"));
    i1.setTitle("Puma concolor");

    NameUsageMediaObject i2 = new NameUsageMediaObject();
    i2.setIdentifier(URI.create("http://www.example.org/image.png"));
    i2.setTitle("Puma concolor");

    assertEquals(i1, i2);

  }

}
