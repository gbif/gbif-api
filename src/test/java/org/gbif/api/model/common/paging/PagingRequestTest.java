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
package org.gbif.api.model.common.paging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PagingRequestTest {

  @Test
  public void testGoodParams() {
    PagingRequest pr = new PagingRequest(0, 0);
    assertEquals(0, pr.getOffset());
    assertEquals(0, pr.getLimit());

    pr = new PagingRequest(10, 2);
    assertEquals(10, pr.getOffset());
    assertEquals(2, pr.getLimit());
  }

  @Test
  public void testInvalidParams() {
    assertThrows(IllegalArgumentException.class, () -> new PagingRequest(-1, 0));
  }

  @Test
  public void testInvalidParams2() {
    assertThrows(IllegalArgumentException.class, () -> new PagingRequest(0, -100));
  }

  @Test
  public void testInvalidParams3() {
    assertThrows(IllegalArgumentException.class, () -> new PagingRequest(-2, -100));
  }
}
