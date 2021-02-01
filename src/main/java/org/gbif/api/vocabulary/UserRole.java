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
package org.gbif.api.vocabulary;

/**
 * Roles here shall not be defined in {@link AppRole} (validated by unit test).
 */
public enum UserRole {
  /**
   * A regular, registered GBIF drupal user.
   */
  USER,

  /**
   * A drupal system administrator.
   * To be removed when Drupal is decommissioned.
   */
  @Deprecated
  ADMIN,

  /**
   * A drupal news and content editor.
   * To be removed when Drupal is decommissioned.
   */
  @Deprecated
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
  REGISTRY_EDITOR,

  /**
   * A user that has permissions to create data packages.
   */
  DATA_REPO_USER,

  /**
   * A Catalogue of Life administrator with all permissions.
   */
  COL_ADMIN,

  /**
   * A Catalogue of Life editor with permissions to manage datasets and assemblies.
   * Exact permissions are handled by the CoL itself.
   */
  COL_EDITOR,

  /**
   * A vocabulary administrator with all permissions.
   */
  VOCABULARY_ADMIN,

  /**
   * A vocabulary editor with limited permissions.
   */
  VOCABULARY_EDITOR,

  /**
   * A grscicoll administrator with all permissions.
   */
  GRSCICOLL_ADMIN,

  /**
   * A grscicoll editor with limited permissions.
   */
  GRSCICOLL_EDITOR,

  /**
   * A grscicoll editor with permissions for some iDigBio-only operations.
   */
  IDIGBIO_GRSCICOLL_EDITOR
}
