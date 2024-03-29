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

import java.util.Objects;
import java.util.regex.Pattern;

public class ResearcherIdValidator implements IdentifierSchemeValidator {

  public static final Pattern FORMAT_PATTERN =
      Pattern.compile(
          "^(?<prefix>http(?:s)?:\\/\\/(?:www.)?.+)?([A-Z]{1,3}-\\d{4}-(19|20)\\d\\d)$");

  private static final Pattern PUBLONS_PATTERN =
      Pattern.compile(
          "^(?<prefix>http(?:s)?:\\/\\/(?:www.)?publons.com\\/researcher\\/)?(([A-Z]{1,3}-\\d{4}-(19|20)\\d\\d)|([0-9]+\\/[a-zA-Z\\-\\_]+\\/?))$");

  @Override
  public boolean isValid(String value) {
    if (value == null || value.isEmpty()) {
      return false;
    }
    return FORMAT_PATTERN.matcher(value).matches() || PUBLONS_PATTERN.matcher(value).matches();
  }

  @Override
  public String normalize(String value) {
    Objects.requireNonNull(value, "Identifier value can't be null");
    String trimmedValue = value.trim();
    if (FORMAT_PATTERN.matcher(trimmedValue).matches()
        || PUBLONS_PATTERN.matcher(trimmedValue).matches()) {
      return trimmedValue;
    }
    throw new IllegalArgumentException(value + " it not a valid ResearcherID");
  }
}
