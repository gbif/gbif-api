/*
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
package org.gbif.api.model.registry.eml;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * ProjectAward is used to enter information about a funding award associated with a project. The containing project
 * contains the list of investigators and for the award, while the `award` field contains specifics such as the agency
 * name, award number, and funding program identifiers.
 */
@SuppressWarnings({"unused", "LombokSetterMayBeUsed", "LombokGetterMayBeUsed"})
public class ProjectAward {

  private String funderName;
  private List<String> funderIdentifiers = new ArrayList<>();
  private String awardNumber;
  private String title;
  private String awardUrl;

  @NotNull
  public String getFunderName() {
    return funderName;
  }

  public void setFunderName(String funderName) {
    this.funderName = funderName;
  }

  public List<String> getFunderIdentifiers() {
    return funderIdentifiers;
  }

  public void setFunderIdentifiers(List<String> funderIdentifiers) {
    this.funderIdentifiers = funderIdentifiers;
  }

  public void addFunderIdentifier(String funderIdentifier) {
    this.funderIdentifiers.add(funderIdentifier);
  }

  @Nullable
  public String getAwardNumber() {
    return awardNumber;
  }

  public void setAwardNumber(@Nullable String awardNumber) {
    this.awardNumber = awardNumber;
  }

  @NotNull
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Nullable
  public String getAwardUrl() {
    return awardUrl;
  }

  public void setAwardUrl(@Nullable String awardUrl) {
    this.awardUrl = awardUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProjectAward that = (ProjectAward) o;
    return Objects.equals(funderName, that.funderName)
        && Objects.equals(funderIdentifiers, that.funderIdentifiers)
        && Objects.equals(awardNumber, that.awardNumber)
        && Objects.equals(title, that.title)
        && Objects.equals(awardUrl, that.awardUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(funderName, funderIdentifiers, awardNumber, title, awardUrl);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ProjectAward.class.getSimpleName() + "[", "]")
        .add("funderName='" + funderName + "'")
        .add("funderIdentifiers=" + funderIdentifiers)
        .add("awardNumber='" + awardNumber + "'")
        .add("title='" + title + "'")
        .add("awardUrl='" + awardUrl + "'")
        .toString();
  }
}
