/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.vocabulary;

public enum UserRole {
  /**
   * A regular, registered GBIF drupal user.
   */
  USER,

  /**
   * A drupal system administrator.
   */
  ADMIN,

  /**
   * A drupal news and content editor.
   */
  EDITOR,

  /**
   * A registry administrator with all permissions, often used by internal GBIF applications.
   */
  REGISTRY_ADMIN,

  /**
   * A registry editor with limited permissions.
   * Exact permissions are handled by the registry itself.
   * Often used for external applications.
   */
  REGISTRY_EDITOR

}
