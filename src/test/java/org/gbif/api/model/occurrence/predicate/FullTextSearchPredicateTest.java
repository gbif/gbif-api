/*
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
package org.gbif.api.model.occurrence.predicate;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FullTextSearchPredicateTest {


  private static final String TEST_VALUE = "bar";
  private static final String COMP_VALUE = "bar ";

  @Test
  public void testEmptyConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new FullTextSearchPredicate(null));
    assertThrows(IllegalArgumentException.class, () -> new FullTextSearchPredicate("   "));
  }

  @Test
  public void testEquals() {
    FullTextSearchPredicate ep1 = new FullTextSearchPredicate(TEST_VALUE);
    FullTextSearchPredicate ep2 = new FullTextSearchPredicate(COMP_VALUE);

    // All of them are equal to themselves
    assertThat(ep1, equalTo(ep2));
  }

}
