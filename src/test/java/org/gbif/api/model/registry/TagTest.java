package org.gbif.api.model.registry;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.google.common.collect.Sets;
import org.apache.bval.jsr303.ApacheValidationProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TagTest {

  @Test
  public void testEquals() {
    Tag t1 = new Tag("aa", "aa");
    Tag t2 = new Tag("aa", "aa");
    assertEquals("Tags are equal", t1, t2);
    assertEquals("Tags are equal", t2, t1);
    t2.setKey(1);
    assertTrue("Tags are equal", !t2.equals(t1));
  }

  @Test
  public void testValidations() {
    ValidatorFactory validatorFactory =
      Validation.byProvider(ApacheValidationProvider.class).configure().buildValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Tag tag = new Tag("", null);
    Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
    assertTrue("Violations were expected", !violations.isEmpty());

    // ensure all expected properties are caught
    Set<String> propertiesInViolation = Sets.newHashSet("value");
    for (ConstraintViolation<?> cv : violations) {
      propertiesInViolation.remove(cv.getPropertyPath().toString());
    }
    assertTrue("Properties incorrectly passed validation " + propertiesInViolation, propertiesInViolation.isEmpty());
  }

}
