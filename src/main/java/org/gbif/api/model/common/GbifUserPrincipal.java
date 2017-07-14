package org.gbif.api.model.common;

import org.gbif.api.vocabulary.UserRole;

import java.util.Objects;

import com.google.common.base.Strings;

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
    if (!Strings.isNullOrEmpty(role)) {
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
