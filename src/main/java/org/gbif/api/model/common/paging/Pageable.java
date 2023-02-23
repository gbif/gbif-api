/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
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
package org.gbif.api.model.common.paging;

import javax.validation.constraints.Min;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Most simple paging interface for both request and responses.
 */
public interface Pageable {

  /**
   * Maximum number of records to be returned.
   *
   * @return the limit.
   */
  @Parameter(
    name = "limit",
    description = "Controls the number of results in the page.\n\n" +
      "A value above the maximum allowed for the service will be replaced with " +
      "the maximum, which varies depending on the service. Sensible defaults " +
      "are used.",
    schema = @Schema(implementation = Integer.class, minimum = "0"),
    in = ParameterIn.QUERY)
  @Min(0)
  int getLimit();

  /**
   * Defines how many items to skip before beginning to return records.
   *
   * @return the offset with 0 being no offset at all.
   */
  @Parameter(
    name = "offset",
    description = "Determines the offset for the search results.\n\n" +
      "A limit of 20 and offset of 40 will retrieve the third page of 20 " +
      "results. Some services have a maximum offset.",
    schema = @Schema(implementation = Integer.class, minimum = "0"),
    in = ParameterIn.QUERY)
  @Min(0)
  long getOffset();

  /**
   * The usual (search) limit and offset parameters
   */
  @Target({PARAMETER, METHOD, FIELD, ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @io.swagger.v3.oas.annotations.Parameters(
    value = {
      @Parameter(
        name = "limit",
        description = "Controls the number of results in the page. Using too high a value will be overwritten with the default maximum threshold, depending on the service. Sensible defaults are used so this may be omitted.",
        schema = @Schema(implementation = Integer.class, minimum = "0"),
        in = ParameterIn.QUERY),
      @Parameter(
        name = "offset",
        description = "Determines the offset for the search results. A limit of 20 and offset of 40 will get the third page of 20 results. Some services have a maximum offset.",
        schema = @Schema(implementation = Integer.class, minimum = "0"),
        in = ParameterIn.QUERY),
      @Parameter(
        name = "page",
        hidden = true
      )
    }
  )
  @interface OffsetLimitParameters {}
}
