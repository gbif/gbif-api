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

import org.gbif.api.vocabulary.UserRole;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * A wrapper class for a GBIF User that exposes the unique account name as the principal name.
 * Replacement for {@link UserPrincipal}
 */
public class GbifUserPrincipal implements ExtendedPrincipal {
  private final GbifUser user;

  public GbifUserPrincipal(GbifUser user) {
    Objects.requireNonNull(user, "user shall be provided");
    this.user = user;
  }

  @Override
  public String getName() {
    return user.getUserName();
  }

  public GbifUser getUser() {
    return user;
  }

  /**
   * Checks if the user has the given string based role.
   * We use strings here and not the enum to facilitate the use of the method with the standard SecurityContext
   * which uses Strings for roles.
   *
   * @param role case insensitive role
   *
   * @return true if the user has the requested role
   */
  public boolean hasRole(String role) {
    if (StringUtils.isNotEmpty(role)) {
      try {
        UserRole r = UserRole.valueOf(role.toUpperCase());
        return user.hasRole(r);
      } catch (IllegalArgumentException e) {
        // ignore
      }
    }
    return false;
  }
}
