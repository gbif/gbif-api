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
package org.gbif.api.model.predicate;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.util.RangeValue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RangePredicateTest {

  @Test
  public void testInvalidConstructor() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new RangePredicate<>(
                OccurrenceSearchParameter.YEAR, new RangeValue("a", null, null, null)));
  }

  @Test
  public void testValidConstructor() {
    new RangePredicate<>(OccurrenceSearchParameter.DAY, new RangeValue("20", null, "30", null));
    new RangePredicate<>(OccurrenceSearchParameter.MONTH, new RangeValue("1", null, "12", null));
    new RangePredicate<>(
        OccurrenceSearchParameter.YEAR, new RangeValue("2000", null, "2012", null));
  }
}
