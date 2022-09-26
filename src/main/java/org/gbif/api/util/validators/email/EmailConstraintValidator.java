package org.gbif.api.util.validators.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

/** {@link ConstraintValidator} that validates emails. */
public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {

  private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();

  private ValidEmail annotation;

  @Override
  public void initialize(ValidEmail constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.annotation = constraintAnnotation;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (!annotation.required() && value == null) {
      return true;
    }
    return EMAIL_VALIDATOR.isValid(value);
  }
}
