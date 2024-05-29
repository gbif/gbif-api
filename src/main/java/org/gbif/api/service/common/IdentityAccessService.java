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
package org.gbif.api.service.common;

import org.gbif.api.model.common.GbifUser;

import javax.annotation.Nullable;

/**
 * Identity service accessing a single user, in read-only.
 * This is a replacement of the deprecated {@link UserService}.
 */
public interface IdentityAccessService {

  /**
   * Get a {@link GbifUser} by identifier (username or email).
   *
   * @param identifier username or email
   *
   * @return the user or null if the user is not found
   */
  @Nullable
  GbifUser get(String identifier);

  /**
   * Authenticates a user.
   *
   * @param identifier username or email
   * @param password clear text password
   *
   * @return the authenticated user or null if not found or wrong credentials provided
   */
  @Nullable
  GbifUser authenticate(String identifier, String password);

}
