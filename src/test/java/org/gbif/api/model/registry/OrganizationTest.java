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

import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
    assertTrue("Violations were expected", !violations.isEmpty());

    // ensure all expected properties are caught
    Set<String> propertiesInViolation = new HashSet<>();
    propertiesInViolation.add("title");
    propertiesInViolation.add("logoUrl");

    for (ConstraintViolation<?> cv : violations) {
      propertiesInViolation.remove(cv.getPropertyPath().toString());
    }
    assertTrue("Properties incorrectly passed validation " + propertiesInViolation, propertiesInViolation.isEmpty());

    // fix validation problems
    org.setTitle("Academy of Natural Sciences");
    org.setHomepage(Collections.singletonList(URI.create("http://www.gbif.org")));
    org.setLogoUrl(URI.create("http://www.gbif.org/logo.png"));
    org.setLanguage(Language.ENGLISH);
    org.setEndorsingNodeKey(UUID.randomUUID());

    // perform validation again
    violations = validator.validate(org);
    assertTrue("No violations were expected", violations.isEmpty());
  }
}
