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

import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Language;

import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganizationTest {

  @Test
  public void testValidations() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Organization org = new Organization();
    org.setTitle("a");  // too short
    org.setHomepage(Collections.singletonList(URI.create("www.gbif.org"))); // doesn't start with http or https
    org.setLogoUrl(URI.create("file:///tmp/aha")); // bad http URI

    // perform validation
    Set<ConstraintViolation<Organization>> violations = validator.validate(org);
    assertFalse(violations.isEmpty(), "Violations were expected");

    // ensure all expected properties are caught
    Set<String> propertiesInViolation = new HashSet<>();
    propertiesInViolation.add("title");
    propertiesInViolation.add("logoUrl");
    propertiesInViolation.add("country");

    for (ConstraintViolation<?> cv : violations) {
      propertiesInViolation.remove(cv.getPropertyPath().toString());
    }
    assertTrue(propertiesInViolation.isEmpty(), "Properties incorrectly passed validation " + propertiesInViolation);

    // fix validation problems
    org.setTitle("Academy of Natural Sciences");
    org.setHomepage(Collections.singletonList(URI.create("http://www.gbif.org")));
    org.setLogoUrl(URI.create("http://www.gbif.org/logo.png"));
    org.setLanguage(Language.ENGLISH);
    org.setEndorsingNodeKey(UUID.randomUUID());
    org.setCountry(Country.DENMARK);

    // perform validation again
    violations = validator.validate(org);
    assertTrue(violations.isEmpty(), "No violations were expected");
  }
}
