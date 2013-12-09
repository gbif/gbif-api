/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.checklistbank;

import java.util.Date;
import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

/**
 * A user generated, custom list of scientific names.
 * A NameList is persisted and managed centrally at GBIF and can be used for offline processing,
 * for example (nub) name matching, custom rss notification feeds and other name services.
 * TODO: The class needs to be linked to an authenticated user, the creator.
 * Currently this is only represented by a String and needs to be updated once the authentication framework is in
 * place.
 */
public class UserChecklist {

  private Integer key;
  private String title;
  private String classification;
  private int numNames;
  private Date modified;
  private String creator;

  /**
   * The higher classification shared by all names in this list.
   * Higher names should be concatenated by semicolon and the rank ordered list must start with the highest name,
   * e.g. kingdom.
   *
   * @return the concatenated higher classification
   */
  @Nullable
  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  /**
   * @return the creator and owner of this list
   */
  @NotNull
  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  /**
   * @return the primary key of the list
   */
  @NotNull
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * @return the date this list or a name on this list was last modified
   */
  @NotNull
  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  /**
   * @return number of names in this list
   */
  @Min(0)
  public int getNumNames() {
    return numNames;
  }

  public void setNumNames(int numNames) {
    this.numNames = numNames;
  }

  /**
   * @return the human, preferrably english title of the list
   */
  @NotNull
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof UserChecklist)) {
      return false;
    }

    UserChecklist that = (UserChecklist) obj;
    return Objects.equal(this.key, that.key)
           && Objects.equal(this.title, that.title)
           && Objects.equal(this.classification, that.classification)
           && Objects.equal(this.numNames, that.numNames)
           && Objects.equal(this.modified, that.modified)
           && Objects.equal(this.creator, that.creator);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, title, classification, numNames, modified, creator);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("title", title)
      .add("classification", classification)
      .add("numNames", numNames)
      .add("modified", modified)
      .add("creator", creator)
      .toString();
  }

}
