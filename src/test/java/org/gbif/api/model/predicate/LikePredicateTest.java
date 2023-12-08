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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LikePredicateTest {

  @Test
  public void testInvalidConstructor() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new LikePredicate<>(OccurrenceSearchParameter.ELEVATION, "123.2%", null));
  }

  @Test
  public void testInvalidConstructor2() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new LikePredicate<>(OccurrenceSearchParameter.BASIS_OF_RECORD, "%FOSSIL%", null));
  }

  @Test
  public void testInvalidConstructor4() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new LikePredicate<>(OccurrenceSearchParameter.COUNTRY, "D%", null));
  }

  @Test
  public void testValidConstructor() {
    new LikePredicate<>(OccurrenceSearchParameter.SCIENTIFIC_NAME, "Abies%", null);
    new LikePredicate<>(OccurrenceSearchParameter.CATALOG_NUMBER, "kew-%", null);
  }
}
