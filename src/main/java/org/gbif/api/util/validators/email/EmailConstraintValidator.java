/*
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
package org.gbif.api.util.validators.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * {@link ConstraintValidator} that validates emails. Uses apache commons {@link EmailValidator}
 * <p>
 * Handles {@link ValidEmail} annotation which is better than default {@link javax.validation.constraints.Email}.
 * Default one finds some values valid like: "mail@mail", "mail@mail.e", but this one not.
 */
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
