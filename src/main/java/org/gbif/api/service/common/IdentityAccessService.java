package org.gbif.api.service.common;

import org.gbif.api.model.common.GbifUser;

import javax.annotation.Nullable;

/**
 * Identity service accessing a single user.
 * This is a replacement of the deprecated {@link UserService}.
 */
public interface IdentityAccessService {

  @Nullable
  GbifUser get(String userName);

  /**
   * Authenticates a user.
   * @param password clear text password
   *
   * @return the authenticated user or null if not found or wrong credentials provided
   */
  @Nullable
  GbifUser authenticate(String username, String password);

}
