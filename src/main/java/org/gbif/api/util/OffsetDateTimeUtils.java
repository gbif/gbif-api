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
package org.gbif.api.util;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for null-safe OffsetDateTime operations.
 */
public class OffsetDateTimeUtils {

  private OffsetDateTimeUtils() {}

  /**
   * Compares two OffsetDateTime values in a null-safe manner.
   * Null values are considered to be greater than non-null values (sorted to the end).
   *
   * @param dt1 first OffsetDateTime to compare
   * @param dt2 second OffsetDateTime to compare
   * @return a negative integer, zero, or a positive integer as the first argument
   *         is less than, equal to, or greater than the second
   */
  public static int compareOffsetDateTime(OffsetDateTime dt1, OffsetDateTime dt2) {
    if (dt1 == dt2) return 0;
    if (dt1 == null) return 1;
    if (dt2 == null) return -1;
    return dt1.toInstant().truncatedTo(ChronoUnit.MICROS).compareTo(dt2.toInstant().truncatedTo(ChronoUnit.MICROS));
  }

  /**
   * Checks if two OffsetDateTime values are equal in a null-safe manner.
   *
   * @param dt1 first OffsetDateTime to compare
   * @param dt2 second OffsetDateTime to compare
   * @return true if both are null or both are equal, false otherwise
   */
  public static boolean isEqualOffsetDateTime(OffsetDateTime dt1, OffsetDateTime dt2) {
    if (dt1 == dt2) return true;
    if (dt1 == null || dt2 == null) return false;
    return dt1.toInstant().truncatedTo(ChronoUnit.MICROS).equals(dt2.toInstant().truncatedTo(ChronoUnit.MICROS));
  }
}

