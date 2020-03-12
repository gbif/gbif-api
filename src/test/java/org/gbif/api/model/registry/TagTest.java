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

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

import com.google.common.collect.Sets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TagTest {

  @Test
  public void testEquals() {
    Tag t1 = new Tag("aa", "aa");
    Tag t2 = new Tag("aa", "aa");
    assertEquals("Tags are equal", t1, t2);
    assertEquals("Tags are equal", t2, t1);
    t2.setKey(1);
    assertFalse("Tags are equal", t2.equals(t1));
  }

  @Test
  public void testValidations() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Tag tag = new Tag("", null);
    Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
    assertFalse("Violations were expected", violations.isEmpty());

    // ensure all expected properties are caught
    Set<String> propertiesInViolation = Sets.newHashSet("value");
    for (ConstraintViolation<?> cv : violations) {
      propertiesInViolation.remove(cv.getPropertyPath().toString());
    }
    assertTrue("Properties incorrectly passed validation " + propertiesInViolation, propertiesInViolation.isEmpty());
  }

}
