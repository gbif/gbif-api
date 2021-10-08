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
package org.gbif.api.util.validators.identifierschemes;

import java.util.regex.Pattern;

/**
 * Util class that generates a validates Mod11,2 checksums.
 */
public abstract class Mod112 {

  private static final Pattern NORMALIZE_PATTERN = Pattern.compile("-|\\s+");

  /**
   * Private constructor.
   */
  private Mod112() {
    //do nothing
  }

  /**
   * Validates that value contains an valid checksum digit according to ISO 7064 11,2.
   */
  static boolean hasValidChecksumDigit(String value) {
    String normalized = NORMALIZE_PATTERN.matcher(value).replaceAll("");
    return normalized.charAt(normalized.length() - 1) ==
           generateChecksumDigit(normalized.substring(0, normalized.length() - 1));
  }

  /**
   * Generates check digit as per ISO 7064 11,2.
   *
   */
  static char generateChecksumDigit(String baseDigits) {
    int total = 0;
    for (int i = 0; i < baseDigits.length(); i++) {
      int digit = Character.getNumericValue(baseDigits.charAt(i));
      total = (total + digit) << 1;
    }
    int remainder = total % 11;
    int result = (12 - remainder) % 11;
    return result == 10 ? 'X' : Character.forDigit(result, 10);
  }

  public abstract boolean isValid(String value);

  public String normalize(String value) {
    return NORMALIZE_PATTERN.matcher(value).replaceAll("");
  }

}
