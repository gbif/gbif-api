/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.registry;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagTest {

  @Test
  public void testEquals() {
    Tag t1 = new Tag("aa", "aa");
    Tag t2 = new Tag("aa", "aa");
    assertEquals(t1, t2, "Tags are equal");
    assertEquals(t2, t1, "Tags are equal");
    t2.setKey(1);
    assertNotEquals(t1, t2, "Tags are equal");
  }

  @Test
  public void testValidations() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Tag tag = new Tag("", null);
    Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
    assertFalse(violations.isEmpty(), "Violations were expected");

    // ensure all expected properties are caught
    Set<String> propertiesInViolation = new HashSet<>();
    propertiesInViolation.add("value");
    for (ConstraintViolation<?> cv : violations) {
      propertiesInViolation.remove(cv.getPropertyPath().toString());
    }
    assertTrue(propertiesInViolation.isEmpty(), "Properties incorrectly passed validation " + propertiesInViolation);
  }
}
