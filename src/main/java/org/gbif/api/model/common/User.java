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

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A GBIF user account registered in the drupal user database.
 *
 * @Deprecated replaced by {@link GbifUser}
 */
@Deprecated
public class User {

  private static final String EMAIL_PATTERN =
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  private Integer key;
  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private String passwordHash;
  private Set<UserRole> roles = new HashSet<>();
  private Date lastLogin;
  // Note: Settings was introduced in the system developed to replace Drupal
  private Map<String, String> settings = new HashMap<>();

  @NotNull
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @NotNull
  @Pattern(regexp = EMAIL_PATTERN)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Nullable
  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  /**
   * The unique, immutable drupal user account name.
   * This name should be used for referring to a user.
   * The account name is made of ASCII lower case alphanumerics, underscore, dash or dots and is in particular void
   * of whitespace.
   */
  @NotNull
  @Pattern(regexp = "^[a-z0-9_.-]+$")
  @Size(min = 3, max = 64)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the first name of a person
   */
  @NotNull
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the last name of the user
   */
  @NotNull
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the first and last name of the user concatenated with a space
   */
  @NotNull
  @JsonIgnore
  public String getName() {
    return firstName + " " + lastName;
  }

  /**
   * @return the drupal hashed version of the user password.
   */
  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  @NotNull
  public Set<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }

  public void addRole(UserRole role) {
    roles.add(role);
  }

  /**
   * Checks if the user has the given user role.
   *
   * @param role
   * @return true if the user has the requested role
   */
  public boolean hasRole(UserRole role) {
    if (role != null && roles.contains(role)) {
      return true;
    }
    return false;
  }

  public boolean isAdmin() {
    return roles.contains(UserRole.ADMIN);
  }

  /**
   * Gets the settings which may be empty but never null.
   *
   * @return
   */
  @NotNull
  public Map<String, String> getSettings() {
    return settings;
  }

  /**
   * Sets the settings object, setting an empty map if null is provided.
   */
  public void setSettings(Map<String, String> settings) {
    // safeguard against misuse to avoid NPE
    this.settings = settings == null ? new HashMap<>() : settings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(key, user.key) &&
      Objects.equals(userName, user.userName) &&
      Objects.equals(firstName, user.firstName) &&
      Objects.equals(lastName, user.lastName) &&
      Objects.equals(email, user.email) &&
      Objects.equals(passwordHash, user.passwordHash) &&
      Objects.equals(roles, user.roles) &&
      Objects.equals(lastLogin, user.lastLogin) &&
      Objects.equals(settings, user.settings);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(key, userName, firstName, lastName, email, passwordHash, roles, lastLogin, settings);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("userName='" + userName + "'")
      .add("firstName='" + firstName + "'")
      .add("lastName='" + lastName + "'")
      .add("email='" + email + "'")
      .add("roles=" + roles)
      .add("lastLogin=" + lastLogin)
      .add("settings=" + settings)
      .toString();
  }
}
