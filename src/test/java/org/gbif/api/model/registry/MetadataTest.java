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

import org.gbif.api.vocabulary.MetadataType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MetadataTest {

  @Test
  public void testValidationsForContentJson() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Metadata metadata = baseMetadata();
    metadata.setContentJson("{a:1}");
    Set<ConstraintViolation<Metadata>> violations = validator.validate(metadata);
    assertFalse(violations.isEmpty(), "Violations were expected for invalid JSON");

    Set<String> propertiesInViolation = new HashSet<>();
    propertiesInViolation.add("contentJsonValid");
    for (ConstraintViolation<?> cv : violations) {
      propertiesInViolation.remove(cv.getPropertyPath().toString());
    }
    assertTrue(propertiesInViolation.isEmpty(), "Properties incorrectly passed validation " + propertiesInViolation);

    metadata.setContentJson("{\"a\":1}");
    violations = validator.validate(metadata);
    assertTrue(violations.isEmpty(), "No violations were expected for valid JSON");

    metadata.setContentJson(null);
    violations = validator.validate(metadata);
    assertTrue(violations.isEmpty(), "No violations were expected for null JSON");

    metadata.setContentJson("   ");
    violations = validator.validate(metadata);
    assertTrue(violations.isEmpty(), "No violations were expected for blank JSON");
  }

  private Metadata baseMetadata() {
    Metadata metadata = new Metadata();
    metadata.setDatasetKey(UUID.randomUUID());
    metadata.setType(MetadataType.EML);
    metadata.setContent("metadata-content");
    metadata.setCreatedBy("usr");
    metadata.setModifiedBy("usr");
    metadata.setCreated(new Date());
    metadata.setModified(new Date());
    return metadata;
  }
}
