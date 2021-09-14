/*
 * Copyright 2021 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.util.validators.identifierschemes;

import java.util.Objects;
import java.util.regex.Pattern;

public class HuhValidator implements IdentifierSchemeValidator {

  private static final Pattern HUH_KIKI_PATTERN =
      Pattern.compile(
          "^(?<scheme>http(?:s)?:\\/\\/kiki.huh.harvard.edu\\/)(databases\\/rdfgen.php\\?uuid=)([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})$");

  private static final Pattern HUH_KIKI_SEARCH_PATTERN =
      Pattern.compile(
          "^(?<scheme>http(?:s)?:\\/\\/kiki.huh.harvard.edu\\/)(databases\\/botanist_search.php\\?(mode=details&)?id=)([0-9]+)$");

  private static final Pattern HUH_PURL_PATTERN =
      Pattern.compile(
          "^(?<scheme>http(?:s)?:\\/\\/purl.oclc.org\\/)(net\\/edu.harvard.huh\\/guid\\/uuid\\/)([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})$");

  @Override
  public boolean isValid(String value) {
    if (value == null || value.isEmpty()) {
      return false;
    }
    return HUH_KIKI_PATTERN.matcher(value).matches()
        || HUH_KIKI_SEARCH_PATTERN.matcher(value).matches()
        || HUH_PURL_PATTERN.matcher(value).matches();
  }

  @Override
  public String normalize(String value) {
    Objects.requireNonNull(value, "Identifier value can't be null");
    String trimmedValue = value.trim();
    if (HUH_KIKI_PATTERN.matcher(trimmedValue).matches()
        || HUH_KIKI_SEARCH_PATTERN.matcher(trimmedValue).matches()
        || HUH_PURL_PATTERN.matcher(trimmedValue).matches()) {
      return trimmedValue;
    }
    throw new IllegalArgumentException(value + " it not a valid HUH");
  }
}
