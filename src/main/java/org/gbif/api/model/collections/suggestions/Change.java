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
package org.gbif.api.model.collections.suggestions;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Change implements Serializable {
  private String field;
  private Object suggested;
  private Object previous;
  private Date created;
  private String author;
  private boolean overwritten;
  private boolean outdated;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public Object getSuggested() {
    return suggested;
  }

  public void setSuggested(Object suggested) {
    this.suggested = suggested;
  }

  public Object getPrevious() {
    return previous;
  }

  public void setPrevious(Object previous) {
    this.previous = previous;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public boolean isOverwritten() {
    return overwritten;
  }

  public void setOverwritten(boolean overwritten) {
    this.overwritten = overwritten;
  }

  public boolean isOutdated() {
    return outdated;
  }

  public void setOutdated(boolean outdated) {
    this.outdated = outdated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Change change = (Change) o;
    return overwritten == change.overwritten
        && outdated == change.outdated
        && Objects.equals(field, change.field)
        && Objects.equals(suggested, change.suggested)
        && Objects.equals(previous, change.previous)
        && Objects.equals(created, change.created)
        && Objects.equals(author, change.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, suggested, previous, created, author, overwritten, outdated);
  }
}
