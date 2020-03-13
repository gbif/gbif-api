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

import org.gbif.api.vocabulary.DatasetSubtype;
import org.gbif.api.vocabulary.DatasetType;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * The dataset search model object for suggest searches of datasets.
 */
public class DatasetSuggestResult {

  private UUID key;
  private String title;
  private String description;
  private DatasetType type;
  private DatasetSubtype subtype;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DatasetType getType() {
    return type;
  }

  public void setType(DatasetType type) {
    this.type = type;
  }

  public DatasetSubtype getSubtype() {
    return subtype;
  }

  public void setSubtype(DatasetSubtype subtype) {
    this.subtype = subtype;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatasetSuggestResult that = (DatasetSuggestResult) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(title, that.title) &&
      Objects.equals(description, that.description) &&
      type == that.type &&
      subtype == that.subtype;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, title, description, type, subtype);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DatasetSuggestResult.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("title='" + title + "'")
      .add("description='" + description + "'")
      .add("type=" + type)
      .add("subtype=" + subtype)
      .toString();
  }
}
