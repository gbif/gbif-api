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

import static org.junit.jupiter.api.Assertions.*;

/** Tests the {@link IhIrnValidator}. */
public class IhIrnValidatorTest {

  private static final IhIrnValidator VALIDATOR = new IhIrnValidator();

  @Test
  public void isValidTest() {
    assertTrue(VALIDATOR.isValid("http://sweetgum.nybg.org/science/ih/person-details/?irn=133130"));
    assertTrue(VALIDATOR.isValid("http://sweetgum.nybg.org/science/ih/person-details?irn=133130"));
    assertTrue(VALIDATOR.isValid("133130"));

    assertFalse(VALIDATOR.isValid("https://sweetgum.nybg.org/science/ih/person-details?irn=133130"));
    assertFalse(VALIDATOR.isValid("http://sweetgum.nybg.org/science/ih/person-details?irn=133130er"));
    assertFalse(VALIDATOR.isValid("133130er"));
  }

}
