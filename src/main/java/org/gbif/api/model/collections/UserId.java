/*
 * Copyright 2021 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.collections;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;

/** User ID for GRSciColl contacts. */
public class UserId {

  private IdType type;
  private String id;

  public UserId() {}

  public UserId(IdType type, String id) {
    this.type = type;
    this.id = id;
  }

  @NotNull
  public IdType getType() {
    return type;
  }

  public void setType(IdType type) {
    this.type = type;
  }

  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserId userID = (UserId) o;
    return type == userID.type && Objects.equals(id, userID.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", UserId.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("id='" + id + "'")
        .toString();
  }
}
