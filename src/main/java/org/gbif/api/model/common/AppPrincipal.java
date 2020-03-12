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
package org.gbif.api.model.common;

import java.util.Objects;

import javax.security.auth.Subject;

/**
 * Similar to {@link GbifUserPrincipal} but represents an application instead of a user.
 * The appKey is used as the unique account name and is exposed as the principal name.
 */
public class AppPrincipal implements ExtendedPrincipal {

  private final String appKey;
  private final String appRole;

  /**
   * {@link AppPrincipal} constructor.
   *
   * @param appKey  mandatory, appKey of the application that is now authenticated.
   * @param appRole optionally, the "role" of the app as {@link String}. Mostly to use jsr250 @RolesAllowed.
   */
  public AppPrincipal(String appKey, String appRole) {
    Objects.requireNonNull(appKey, "appKey shall be provided");
    this.appKey = appKey;
    this.appRole = appRole;
  }

  @Override
  public String getName() {
    return appKey;
  }

  @Override
  public boolean implies(Subject subject) {
    return false;
  }

  @Override
  public boolean hasRole(String role) {
    return appRole != null && appRole.equalsIgnoreCase(role);
  }
}
