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

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A dedicated class for related projects. Unless {@link Project} it only has essential fields.
 */
@SuppressWarnings({"unused", "LombokSetterMayBeUsed", "LombokGetterMayBeUsed"})
public class RelatedProject {

  private String title;
  private String identifier;
  private String abstract_;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getAbstract() {
    return abstract_;
  }

  public void setAbstract(String abstract_) {
    this.abstract_ = abstract_;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RelatedProject that = (RelatedProject) o;
    return Objects.equals(title, that.title)
        && Objects.equals(identifier, that.identifier)
        && Objects.equals(abstract_, that.abstract_);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, identifier, abstract_);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RelatedProject.class.getSimpleName() + "[", "]")
        .add("title='" + title + "'")
        .add("identifier='" + identifier + "'")
        .add("abstract_='" + abstract_ + "'")
        .toString();
  }
}
