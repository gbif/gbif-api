package org.gbif.api.service.common;

import org.gbif.api.model.common.GbifUser;

import javax.annotation.Nullable;

/**
 * Identity service accessing a single user.
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
