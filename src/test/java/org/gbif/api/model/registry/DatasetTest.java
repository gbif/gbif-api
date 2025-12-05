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

import org.gbif.api.vocabulary.DatasetType;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.MaintenanceUpdateFrequency;

import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatasetTest {

  @Test
  public void testValidations() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Dataset ds = new Dataset();

    // 3 non-mandatory fields that don't validate
    ds.setTitle("B"); // too short
    ds.setHomepage(URI.create("www.gbif.org")); // doesn't start with http or https
    ds.setLogoUrl(URI.create("file:///tmp/aha")); // bad http URI

    // All mandatory fields missing include:
    // ds.setType(?);
    // ds.setInstallationKey(?);
    // ds.setPublishingOrganizationKey(?);

    // perform validation
    Set<ConstraintViolation<Dataset>> violations = validator.validate(ds);
    assertFalse(violations.isEmpty(), "Violations were expected");

    // ensure all 6 expected violations are caught
    Set<String> propertiesInViolation = new HashSet<>();
    propertiesInViolation.add("title");
    propertiesInViolation.add("homepage");
    propertiesInViolation.add("logoUrl");
    propertiesInViolation.add("type");
    propertiesInViolation.add("installationKey");
    propertiesInViolation.add("publishingOrganisationKey");

    assertEquals(6, violations.size());

    // fix non-mandatory fields that don't validate
    ds.setTitle("Rooftop bugs");
    ds.setHomepage(URI.create("http://www.gbif.org"));
    ds.setLogoUrl(URI.create("http://www.gbif.org/logo.png"));

    // add all mandatory fields that were missing
    ds.setType(DatasetType.SAMPLING_EVENT);
    ds.setInstallationKey(UUID.randomUUID());
    ds.setPublishingOrganizationKey(UUID.randomUUID());

    // perform validation again
    violations = validator.validate(ds);
    assertTrue(violations.isEmpty(), "No violations were expected");
  }

  @Test
  public void testLenientEquals() {
    Dataset ds1 = new Dataset();
    ds1.setMaintenanceUpdateFrequency(MaintenanceUpdateFrequency.DAILY);
    ds1.setMaintenanceDescription("Daily, except for holidays");
    ds1.setLicense(License.CC_BY_4_0);
    ds1.setCreated(new Date());

    Dataset ds2 = new Dataset();
    ds2.setMaintenanceUpdateFrequency(MaintenanceUpdateFrequency.DAILY);
    ds2.setMaintenanceDescription("Daily, except for holidays");
    ds2.setLicense(License.CC0_1_0); // different license
    ds1.setCreated(new Date()); // different created date!

    assertTrue(ds1.lenientEquals(ds2)); // true because lenient equals excludes
  }
}
