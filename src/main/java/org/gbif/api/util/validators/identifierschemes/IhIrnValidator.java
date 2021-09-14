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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IhIrnValidator implements IdentifierSchemeValidator {

  private static final Pattern IH_IRN_PATTERN =
      Pattern.compile("^(?<prefix>http:\\/\\/sweetgum.nybg.org\\/science\\/ih\\/person-details(\\/)?\\?irn=)?([0-9]+)$");

  @Override
  public boolean isValid(String value) {
    if (value == null || value.isEmpty()) {
      return false;
    }
    Matcher matcher = IH_IRN_PATTERN.matcher(value);
    return matcher.matches();
  }

  @Override
  public String normalize(String value) {
    Objects.requireNonNull(value, "Identifier value can't be null");
    String trimmedValue = value.trim();
    Matcher matcher = IH_IRN_PATTERN.matcher(trimmedValue);
    if (matcher.matches()) {
      return trimmedValue;
    }
    throw new IllegalArgumentException(value + " it not a valid IH IRN");
  }
}
