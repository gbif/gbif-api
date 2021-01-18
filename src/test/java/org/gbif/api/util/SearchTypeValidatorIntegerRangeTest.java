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
package org.gbif.api.util;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class SearchTypeValidatorIntegerRangeTest {

  public static Stream<Arguments> data() {
    return Stream.of(
        Arguments.of("10", null, null),
        Arguments.of("10,20", 10, 20),
        Arguments.of("10.1,20.0", null, null),
        Arguments.of("-1 , 0", -1, 0),
        Arguments.of("*,20", null, 20),
        Arguments.of("10, *", 10, null),
        Arguments.of("peter", null, null),
        Arguments.of("Puma concolor, 1882", null, null)
    );
  }

  @ParameterizedTest
  @MethodSource("data")
  public void testRange(String arg, Integer start, Integer end) {
    try {
      Range<Integer> range = SearchTypeValidator.parseIntegerRange(arg);
      if (start == null && end == null) {
        fail(arg + " supposed to be an invalid range");
      } else {
        // test parse result
        if (range.hasLowerBound()) {
          assertEquals(start, range.lowerEndpoint());
        } else {
          assertNull(start);
        }

        if (range.hasUpperBound()) {
          assertEquals(end, range.upperEndpoint());
        } else {
          assertNull(end);
        }

        assertTrue(SearchTypeValidator.isRange(arg));
      }

    } catch (IllegalArgumentException e) {
      // expected?
      if (start != null || end != null) {
        fail(arg + " supposed to be a valid range");
      }
    }
  }
}
