package org.gbif.api.documentation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;

public class CommonParameters {

  /**
   * The usual (search) limit and offset parameters
   */
  @Target({METHOD, ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @Parameter(
    name = "hl",
    description = "Set `hl=true` to highlight terms matching the query when in fulltext search fields. The highlight " +
      "will be an emphasis tag of class `gbifHl`.",
    schema = @Schema(implementation = Boolean.class),
    in = ParameterIn.QUERY)
  public @interface HighlightParameter {}

  /**
   * A normal full-text search parameter.
   */
  @Target({METHOD, ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @Parameter(
    name = "q",
    description = "Simple full text search parameter. The value for this parameter can be a simple word or a phrase. " +
      "Wildcards are not supported",
    schema = @Schema(implementation = String.class),
    in = ParameterIn.QUERY)
  public @interface QParameter {}
}
