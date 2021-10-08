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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests the {@link OrcidValidator}. */
public class OrcidValidatorTest {

  private static final OrcidValidator VALIDATOR = new OrcidValidator();

  @Test
  public void isValidTest() {
    assertTrue(VALIDATOR.isValid("https://orcid.org/0000-0003-0145-6846"));
    assertTrue(VALIDATOR.isValid("http://orcid.org/0000-0003-0145-6846"));
    assertTrue(VALIDATOR.isValid("0000-0003-0145-6846"));
    assertTrue(VALIDATOR.isValid("https://orcid.org/0000-0003-1093-677X"));
    assertTrue(VALIDATOR.isValid("0000-0003-1093-677X"));

    assertFalse(VALIDATOR.isValid("000d0-0003-1093-677X"));
  }

  @Test
  public void normalizeTest() {
    assertEquals(
        "https://orcid.org/0000-0003-0145-6846", VALIDATOR.normalize("0000-0003-0145-6846"));
  }
}
