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
package org.gbif.api.service.common;

import org.gbif.api.model.common.User;

import javax.annotation.Nullable;

/**
 * The identity service provides means to create, update and delete User accounts, and provide the mechanims to
 * authenticate a user with their password.
 * This is a replacement of the deprecated UserService which was a read only service, backed by a managed database
 * (Drupal) and provides a writable option.
 */
public interface IdentityService extends CrudService<User, User, String> {

  /**
   * Authenticates a user.
   * @param password clear text password
   *
   * @return the authenticated user or null if not found or wrong credentials provided
   */
  @Nullable
  User authenticate(String username, String password);

  /**
   * Retrieves a user by a currently open login session.
   * The session name is stored by in a cookie.
   * @param session the session name as found in the cookie
   * @return the user of an existing session or NULL if not found
   */
  @Nullable
  User getBySession(String session);
}
