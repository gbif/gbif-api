package org.gbif.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that marks a resource method to throw a NotFoundException (becomes http 404)
 * when null is returned by the method.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NullToNotFound {

  /**
   * Resource URI which was not found.
   */
  String value() default "/";
}
