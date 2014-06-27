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

public interface UserService {

  /**
   * Authenticates a user.
   *
   * @param password clear text password
   *
   * @return the authenticated user or null if not found or wrong credentials provided
   */
  @Nullable
  User authenticate(String username, String password);

  /**
   * Retrieves a user by its case insensitive username.
   */
  @Nullable
  User get(String username);

  /**
   * Retrieves a user by a currently open Drupal (login) session.
   * The session name is stored by Drupal clientside in a cookie.
   * @param session the drupal session name as found in the drupal cookie
   * @return the user of an existing session or NULL if not found
   */
  @Nullable
  User getBySession(String session);

}
