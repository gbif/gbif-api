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

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.gbif.api.util.validators.email.ValidEmail;
import org.gbif.api.vocabulary.UserRole;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * An abstract GBIF user account. The main purpose of this abstraction is to let subclasses handle
 * key and password information only if required. By doing so, it is possible to have classes
 * working for user information without having to carry those information around.
 */
@SuppressWarnings("unused")
public abstract class AbstractGbifUser {

  protected String userName;
  protected String firstName;
  protected String lastName;
  protected String email;
  protected Set<UserRole> roles = new HashSet<>();
  protected Map<String, String> settings = new HashMap<>();
  // settings that the user will not set directly
  protected Map<String, String> systemSettings = new HashMap<>();
  protected Date deleted;

  @NotNull
  @ValidEmail
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * The unique, immutable drupal user account name. This name should be used for referring to a
   * user. The account name is made of ASCII lower case alphanumerics, underscore, dash or dots and
   * is in particular void of whitespace.
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

  /** @return the first name of a person */
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /** @return the last name of the user */
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /** @return the first and last name of the user concatenated with a space */
  @JsonIgnore
  public String getName() {
    return Stream.of(firstName, lastName).filter(Objects::nonNull).collect(Collectors.joining(" "));
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
   * @param role user role
   * @return true if the user has the requested role
   */
  public boolean hasRole(UserRole role) {
    return role != null && roles.contains(role);
  }

  /** Sets the settings object, setting an empty map if null is provided. */
  public void setSettings(Map<String, String> settings) {
    // safeguard against misuse to avoid NPE
    this.settings = settings == null ? new HashMap<>() : settings;
  }

  /** Gets the settings which may be empty but never null. */
  @NotNull
  public Map<String, String> getSettings() {
    return settings;
  }

  /** Sets the settings object, setting an empty map if null is provided. */
  public void setSystemSettings(Map<String, String> systemSettings) {
    // safeguard against misuse to avoid NPE
    this.systemSettings = systemSettings == null ? new HashMap<>() : systemSettings;
  }

  /** Gets the settings which may be empty but never null. */
  @NotNull
  public Map<String, String> getSystemSettings() {
    return systemSettings;
  }

  public Date getDeleted() {
    return deleted;
  }

  public void setDeleted(Date deleted) {
    this.deleted = deleted;
  }

  @JsonIgnore
  public Locale getLocale() {
    String languageTag = settings.get("locale");
    return languageTag != null ? Locale.forLanguageTag(languageTag) : null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractGbifUser that = (AbstractGbifUser) o;
    return Objects.equals(userName, that.userName)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(email, that.email)
        && Objects.equals(roles, that.roles)
        && Objects.equals(settings, that.settings)
        && Objects.equals(systemSettings, that.systemSettings)
        && Objects.equals(deleted, that.deleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        userName, firstName, lastName, email, roles, settings, systemSettings, deleted);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AbstractGbifUser.class.getSimpleName() + "[", "]")
        .add("userName='" + userName + "'")
        .add("firstName='" + firstName + "'")
        .add("lastName='" + lastName + "'")
        .add("email='" + email + "'")
        .add("roles=" + roles)
        .add("settings=" + settings)
        .add("systemSettings=" + systemSettings)
        .add("deleted=" + deleted)
        .toString();
  }
}
