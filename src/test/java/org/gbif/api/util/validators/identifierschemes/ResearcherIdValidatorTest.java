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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests the {@link ResearcherIdValidator}. */
public class ResearcherIdValidatorTest {

  private static final ResearcherIdValidator VALIDATOR = new ResearcherIdValidator();

  @Test
  public void researcherIsValidTest() {
    assertTrue(VALIDATOR.isValid("http://www.researcherid.com/rid/M-6306-2017"));
    assertTrue(VALIDATOR.isValid("https://www.researcherid.com/rid/M-6306-2017"));
    assertTrue(VALIDATOR.isValid("https://researcherid.com/rid/M-6306-2017"));
    assertTrue(VALIDATOR.isValid("M-6306-2017"));

    assertFalse(VALIDATOR.isValid("http://www.researcherid.com/rid/M-63063-2017"));
    assertFalse(VALIDATOR.isValid("http://www.researcherid.com/rid/M-A306-2017"));
    assertFalse(VALIDATOR.isValid("http://www.researcherid.com/rid/My Name"));
  }

  @Test
  public void publonsIsValidTest() {
    assertTrue(VALIDATOR.isValid("http://publons.com/researcher/M-5306-2017"));
    assertTrue(VALIDATOR.isValid("https://publons.com/researcher/M-5306-2017"));
    assertTrue(VALIDATOR.isValid("https://www.publons.com/researcher/M-5306-2017"));
    assertTrue(VALIDATOR.isValid("https://publons.com/researcher/1/my-name/"));

    assertFalse(VALIDATOR.isValid("https://publons.com/researcher/M-A306-2017"));
    assertFalse(VALIDATOR.isValid("https://publons.com/researcher/M-55306-2017"));
    assertFalse(VALIDATOR.isValid("https://publons.com/researcher/my-name"));
  }
}
