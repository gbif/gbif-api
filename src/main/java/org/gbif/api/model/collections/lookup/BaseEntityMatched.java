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
  private URI selfLink;
  private String name;
  private String code;
  private boolean active;

  @Override
  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  @Override
  public URI getSelfLink() {
    return selfLink;
  }

  public void setSelfLink(URI selfLink) {
    this.selfLink = selfLink;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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
        && Objects.equals(selfLink, that.selfLink)
        && Objects.equals(name, that.name)
        && Objects.equals(code, that.code)
        && Objects.equals(active, that.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, selfLink, name, code, active);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BaseEntityMatched.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("selfLink=" + selfLink)
        .add("name='" + name + "'")
        .add("code='" + code + "'")
        .add("active='" + active + "'")
        .toString();
  }
}
