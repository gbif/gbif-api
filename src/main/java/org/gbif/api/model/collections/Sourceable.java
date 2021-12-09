package org.gbif.api.model.collections;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.gbif.api.vocabulary.collections.MasterSourceType;

/**
 * Annotation to indicate that a field can be populated from external sources.
 *
 * <p>This is used in GRSciColl to flag fields whose value comes from other sources rather than
 * GRSciColl such as IH, datasets or organizations.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Repeatable(value = Sourceables.class)
public @interface Sourceable {

  MasterSourceType[] masterSources() default {};

  /**
   * Indicates that only some parts are sourceable. If this field is not set it's assumed that the
   * whole field is sourceable.
   *
   * <p>This applies only to lists or similar. For example, a list of identifiers where only the DOI
   * identifiers are sourceable would set this field as {DOI}.
   */
  String[] sourceableParts() default {};

  /**
   * Indicates that a field is initially filled from an external source but it can be overwritten
   * afterwards.
   *
   * <p>E.g.: the names of IH collections.
   */
  boolean overridable() default false;
}
