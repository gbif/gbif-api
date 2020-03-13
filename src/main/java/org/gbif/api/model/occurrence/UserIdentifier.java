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
package org.gbif.api.model.occurrence;

import org.gbif.api.vocabulary.UserIdentifierType;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

public class UserIdentifier {

  private UserIdentifierType type;
  private String value;

  public UserIdentifier(UserIdentifierType type, String value) {
    this.type = type;
    this.value = value;
  }

  public UserIdentifier(String value) {
    this.value = value;
  }

  public UserIdentifierType getType() {
    return type;
  }

  public UserIdentifier setType(UserIdentifierType type) {
    this.type = type;
    return this;
  }

  @NotNull
  public String getValue() {
    return value;
  }

  public UserIdentifier setValue(String value) {
    this.value = value;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserIdentifier that = (UserIdentifier) o;
    return type == that.type &&
      Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("type", type)
      .add("value", value)
      .toString();
  }

}
