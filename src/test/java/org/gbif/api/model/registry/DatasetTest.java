package org.gbif.api.model.registry;

import org.gbif.api.vocabulary.DatasetType;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.MaintenanceUpdateFrequency;

import java.net.URI;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.google.common.collect.Sets;
import org.apache.bval.jsr303.ApacheValidationProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DatasetTest {

  @Test
  public void testValidations() {
    ValidatorFactory validatorFactory =
      Validation.byProvider(ApacheValidationProvider.class).configure().buildValidatorFactory();
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
    assertTrue("Violations were expected", !violations.isEmpty());

    // ensure all 6 expected violations are caught
    Set<String> propertiesInViolation =
      Sets.newHashSet("title", "homepage", "logoUrl", "type", "installationKey", "publishingOrganisationKey");
    assertEquals(6, violations.size());
    for (ConstraintViolation<?> cv : violations) {
      propertiesInViolation.contains(cv.getPropertyPath().toString());
    }

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
    assertTrue("No violations were expected", violations.isEmpty());
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
