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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests the {@link HuhValidator}. */
public class HuhValidatorTest {

  private static final HuhValidator VALIDATOR = new HuhValidator();

  @Test
  public void kikiIsValidTest() {
    assertTrue(
        VALIDATOR.isValid(
            "https://kiki.huh.harvard.edu/databases/rdfgen.php?uuid=fbb65596-0611-4406-b510-bf45d45ec6e2"));
    assertTrue(
        VALIDATOR.isValid(
            "http://kiki.huh.harvard.edu/databases/rdfgen.php?uuid=fbb65596-0611-4406-b510-bf45d45ec6e2"));
    assertTrue(
        VALIDATOR.isValid(
            "https://kiki.huh.harvard.edu/databases/rdfgen.php?uuid=fbb65596-0611-4406-b510-bf45d45ec6e2"));
    assertTrue(
        VALIDATOR.isValid(
            "http://kiki.huh.harvard.edu/databases/rdfgen.php?uuid=fbb65596-0611-4406-b510-bf45d45ec6e2"));
    assertTrue(
        VALIDATOR.isValid(
            "https://kiki.huh.harvard.edu/databases/botanist_search.php?mode=details&id=44949"));
    assertTrue(
        VALIDATOR.isValid("https://kiki.huh.harvard.edu/databases/botanist_search.php?id=44949"));
    assertTrue(
        VALIDATOR.isValid(
            "http://kiki.huh.harvard.edu/databases/botanist_search.php?mode=details&id=44949"));

    assertFalse(
        VALIDATOR.isValid(
            "https://kiki.huh.harvard.edu/databases/rdfgen.php?uuid=fbb65596-0611-4406-b510-bf45d45ec6e"));
    assertFalse(
        VALIDATOR.isValid("http://kiki.huh.harvard.edu/databases/botanist_search.php?id=4d4949"));
  }

  @Test
  public void purlIsValidTest() {
    assertTrue(
        VALIDATOR.isValid(
            "http://purl.oclc.org/net/edu.harvard.huh/guid/uuid/f5ea5399-24c6-4ad9-a04e-c24c3f215bae"));
    assertTrue(
        VALIDATOR.isValid(
            "https://purl.oclc.org/net/edu.harvard.huh/guid/uuid/f5ea5399-24c6-4ad9-a04e-c24c3f215bae"));

    assertFalse(
        VALIDATOR.isValid(
            "https://purl.oclc.org/net/edu.harvard.huh/guid/uuid/f5ea5399-24c6-4ad9-a04e-c24c3f215ba2e"));
  }
}
