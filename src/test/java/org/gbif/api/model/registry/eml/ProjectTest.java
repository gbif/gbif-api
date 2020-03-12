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
package org.gbif.api.model.registry.eml;

import org.gbif.api.model.registry.Contact;

import org.junit.Test;

import com.google.common.collect.Lists;

import static org.junit.Assert.assertNotEquals;

public class ProjectTest {

  @Test
  public void testEquals() {
    Project p1 = new Project("BioFresh Project", "226874", Lists.newArrayList(new Contact()), "Some Abstract",
      "Funded by the EU under the 7th Framework Programme", "Ran from November 2009 until April 2014.",
      "Established an internet platform bringing together information and data on freshwater biodiversity.");

    // identifier is null
    Project p2 = new Project("BioFresh Project", null, Lists.newArrayList(new Contact()), "Some Abstract",
      "Funded by the EU under the 7th Framework Programme", "Ran from November 2009 until April 2014.",
      "Established an internet platform bringing together information and data on freshwater biodiversity.");

    assertNotEquals(p1, p2);
  }
}
