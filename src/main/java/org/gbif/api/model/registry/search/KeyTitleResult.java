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
package org.gbif.api.model.registry.search;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * A utility container for holding only the title and key of an entity.
 */
public class KeyTitleResult {

  private UUID key;
  private String title;

  public KeyTitleResult() {
  }

  public KeyTitleResult(UUID key, String title) {
    this.key = key;
    this.title = title;
  }

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyTitleResult that = (KeyTitleResult) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, title);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", KeyTitleResult.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("title='" + title + "'")
      .toString();
  }
}
