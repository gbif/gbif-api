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
