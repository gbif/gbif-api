/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ImageTest {

  @Test
  public void testEquals() {
    Image i1 = new Image();
    i1.setKey(123);
    i1.setImage("http://www.example.org/image.png");
    i1.setTitle("Puma concolor");

    Image i2 = new Image();
    i2.setKey(123);
    i2.setImage("http://www.example.org/image.png");
    i2.setTitle("Puma concolor");

    assertEquals(i1, i2);

    i2.setKey(124);

    assertFalse(i1.equals(i2));
  }

}
