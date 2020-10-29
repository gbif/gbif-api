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
package org.gbif.api.model.collections.lookup;

import java.net.URI;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public abstract class BaseEntityMatched implements EntityMatched {

  private UUID key;
  private URI self;
  private String name;
  private String code;

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public URI getSelf() {
    return self;
  }

  public void setSelf(URI self) {
    this.self = self;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseEntityMatched that = (BaseEntityMatched) o;
    return Objects.equals(key, that.key)
        && Objects.equals(self, that.self)
        && Objects.equals(name, that.name)
        && Objects.equals(code, that.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, self, name, code);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BaseEntityMatched.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("self=" + self)
        .add("name='" + name + "'")
        .add("code='" + code + "'")
        .toString();
  }
}
