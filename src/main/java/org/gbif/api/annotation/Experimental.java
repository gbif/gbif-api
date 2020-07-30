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
package org.gbif.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Experimental features are available but are not as robustly supported as other features.
 * In particular, experimental features:
 * - Are not tested as well as stable features.
 * - Do not get bugs fixed as quickly as established features.
 * - The APIs could drastically change if we find a different, better way to solve the same problem.
 * - May disappear entirely if the feature is too difficult to support.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
  ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.PACKAGE,
  ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE, ElementType.TYPE_PARAMETER
})
public @interface Experimental {}
