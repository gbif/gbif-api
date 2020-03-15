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
package org.gbif.api.util;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

/**
 * Analogues of methods from guava's Strings
 */
public final class ApiStringUtils {

  private ApiStringUtils() {
  }

  /**
   * Returns the given string if it is nonempty; {@code null} otherwise.
   *
   * @param string the string to test and possibly return
   * @return {@code string} itself if it is nonempty; {@code null} if it is
   *     empty or null
   */
  @Nullable
  public static String emptyToNull(@Nullable String string) {
    return StringUtils.isEmpty(string) ? null : string;
  }

  /**
   * Returns the given string if it is non-null; the empty string otherwise.
   *
   * @param string the string to test and possibly return
   * @return {@code string} itself if it is non-null; {@code ""} if it is null
   */
  public static String nullToEmpty(@Nullable String string) {
    return (string == null) ? "" : string;
  }
}
