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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Validator for ISNI numbers. */
public class IsniValidator implements IdentifierSchemeValidator {

  private static final Pattern ISNI_PATTERN =
      Pattern.compile(
          "^(?<prefix>http(?:s)?:\\/\\/isni.org\\/isni\\/)?([\\p{Digit}xX\\p{Pd}\\s]{16,24})$");

  @Override
  public boolean isValid(String value) {
    if (value == null || value.isEmpty()) {
      return false;
    }

    Matcher matcher = ISNI_PATTERN.matcher(value);
    if (!matcher.matches()) {
      return false;
    }

    String prefixGroup = matcher.group("prefix");
    String isniNumber = prefixGroup == null ? value : value.substring(prefixGroup.length());

    return Mod112.hasValidChecksumDigit(isniNumber);
  }

  @Override
  public String normalize(String value) {
    Objects.requireNonNull(value, "Identifier value can't be null");
    return value.trim();
  }
}
