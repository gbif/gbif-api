package org.gbif.api.util.validators.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

/** {@link ConstraintValidator} that validates emails. */
public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {

  private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();

  @Override
  public void initialize(ValidEmail constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return EMAIL_VALIDATOR.isValid(value);
  }
}
