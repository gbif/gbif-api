/*
 * Copyright 2021 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.util;

import java.lang.reflect.Field;

/**
 * Utility method to work with annotations.
 */
public final class AnnotationUtils {

  private AnnotationUtils() {
  }

  /**
   * Check if a field is annotated with @Deprecated in the provided class.
   * Mostly used on elements of an Enum but will also work on class {@link Field} (private and public).
   *
   * @return true if the specified field is annotated with @Deprecated on the provided class. False is all
   * other cases (including if the field doesn't exist)
   */
  public static boolean isFieldDeprecated(Class<?> _class, String fieldName) {
    try {
      Field field = _class.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.isAnnotationPresent(Deprecated.class);
    } catch (NoSuchFieldException ignore) {}
    return false;
  }
}
