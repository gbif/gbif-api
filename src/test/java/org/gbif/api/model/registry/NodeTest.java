package org.gbif.api.model.registry;

import java.net.URI;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.google.common.collect.Sets;
import org.apache.bval.jsr303.ApacheValidationProvider;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NodeTest {

  @Test
  public void testValidations() {
    ValidatorFactory validatorFactory =
      Validation.byProvider(ApacheValidationProvider.class).configure().buildValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Node node = new Node();
    node.setTitle("a"); // too short
    node.setLogoUrl(URI.create("file:///tmp/aha")); // bad http URI
    Set<ConstraintViolation<Node>> violations = validator.validate(node);
    assertTrue("Violations were expected", !violations.isEmpty());

    // ensure all expected properties are caught
    Set<String> propertiesInViolation = Sets.newHashSet("title", "logoUrl");
    for (ConstraintViolation<?> cv : violations) {
      propertiesInViolation.remove(cv.getPropertyPath().toString());
    }
    assertTrue("Properties incorrectly passed validation " + propertiesInViolation, propertiesInViolation.isEmpty());
  }

}
