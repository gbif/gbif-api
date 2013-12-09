package org.gbif.api.model.common;

import org.gbif.api.vocabulary.UserRole;

import java.security.Principal;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * A wrapper class for a GBIF User that exposes the unique account name as the principal name.
 */
public class UserPrincipal implements Principal {
  private final User user;

  public UserPrincipal(User user) {
    Preconditions.checkNotNull(user);
    this.user = user;
  }

  @Override
  public String getName() {
    return user.getUserName();
  }

  public User getUser() {
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
