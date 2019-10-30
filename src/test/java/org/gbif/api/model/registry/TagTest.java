package org.gbif.api.model.registry;

import com.google.common.collect.Sets;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

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
