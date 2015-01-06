/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
import java.util.Set;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * A GBIF user account registered in the drupal user database.
 */
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
  private Set<UserRole> roles = Sets.newHashSet();
  private Date lastLogin;

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
   * @param role
   *
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof User)) {
      return false;
    }

    User that = (User) obj;
    return Objects.equal(this.key, that.key)
           && Objects.equal(this.userName, that.userName)
           && Objects.equal(this.firstName, that.firstName)
           && Objects.equal(this.lastName, that.lastName)
           && Objects.equal(this.email, that.email)
           && Objects.equal(this.roles, that.roles)
           && Objects.equal(this.lastLogin, that.lastLogin)
           && Objects.equal(this.passwordHash, that.passwordHash);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, userName, firstName, lastName, email, roles, lastLogin, passwordHash);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("accountName", userName)
      .add("firstName", firstName)
      .add("lastName", lastName)
      .add("email", email)
      .add("roles", roles)
      .add("lastLogin", lastLogin)
      .add("passwordHash", passwordHash)
      .toString();
  }

}
