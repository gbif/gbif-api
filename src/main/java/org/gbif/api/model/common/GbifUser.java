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

import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A GBIF user account registered in the user Identity database (previously Drupal).
 * This class is the replacement of {@link User}.
 */
@SuppressWarnings("unused")
public class GbifUser extends AbstractGbifUser {

  protected Integer key;

  private String passwordHash;
  private Date lastLogin;

  public GbifUser() {
  }

  public GbifUser(GbifUser another) {
    this.key = another.key;
    this.passwordHash = another.passwordHash;
    this.lastLogin = another.lastLogin;
    this.userName = another.userName;
    this.firstName = another.firstName;
    this.lastName = another.lastName;
    this.email = another.email;
    this.roles = new HashSet<>(another.roles);
    this.settings = new HashMap<>(another.settings);
    this.systemSettings = new HashMap<>(another.systemSettings);
    this.deleted = another.deleted;
    this.locale = another.locale;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @Nullable
  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  /**
   * @return the hashed version of the user password.
   */
  @NotNull
  @JsonIgnore
  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    GbifUser gbifUser = (GbifUser) o;
    return Objects.equals(key, gbifUser.key) &&
      Objects.equals(lastLogin, gbifUser.lastLogin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), key, lastLogin);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GbifUser.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("lastLogin=" + lastLogin)
      .add("userName='" + userName + "'")
      .add("firstName='" + firstName + "'")
      .add("lastName='" + lastName + "'")
      .add("email='" + email + "'")
      .add("roles=" + roles)
      .add("settings=" + settings)
      .add("systemSettings=" + systemSettings)
      .add("deleted=" + deleted)
      .add("language=" + locale)
      .toString();
  }
}
