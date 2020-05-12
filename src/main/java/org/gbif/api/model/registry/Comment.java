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
package org.gbif.api.model.registry;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class Comment implements Serializable, LenientEquals<Comment> {

  private Integer key;
  private String content;
  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @NotNull
  @Size(min = 1)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Size(min = 3)
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Size(min = 3)
  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(key, comment.key) &&
      Objects.equals(content, comment.content) &&
      Objects.equals(createdBy, comment.createdBy) &&
      Objects.equals(modifiedBy, comment.modifiedBy) &&
      Objects.equals(created, comment.created) &&
      Objects.equals(modified, comment.modified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, content, createdBy, modifiedBy, created, modified);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Comment.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("content='" + content + "'")
      .add("createdBy='" + createdBy + "'")
      .add("modifiedBy='" + modifiedBy + "'")
      .add("created=" + created)
      .add("modified=" + modified)
      .toString();
  }

  /**
   * A lenient equality check ignoring server side fields. If the objects are equal or have the same content, they are
   * considered the same.
   */
  @Override
  public boolean lenientEquals(Comment other) {
    if (this == other) {
      return true;
    }
    return Objects.equals(this.content, other.content);
  }
}
