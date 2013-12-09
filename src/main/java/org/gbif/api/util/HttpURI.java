package org.gbif.api.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validates that the URI field is absolute, beginning with either http or https.
 * To use, apply to the URI fields intended for validation.
 * The field must be:
 * <ul>
 * <li>A URI object</li>
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = URIValidator.class)
public @interface HttpURI {
  public abstract Class<?>[] groups() default {};
  public abstract Class<? extends Payload>[] payload() default { };
  public abstract String message() default "";
}
