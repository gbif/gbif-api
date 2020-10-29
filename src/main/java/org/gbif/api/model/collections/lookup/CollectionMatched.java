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

public class CollectionMatched extends BaseEntityMatched {

  private UUID institutionKey;
  private URI institutionLink;
  private String institutionCode;
  private String institutionName;

  public UUID getInstitutionKey() {
    return institutionKey;
  }

  public void setInstitutionKey(UUID institutionKey) {
    this.institutionKey = institutionKey;
  }

  public URI getInstitutionLink() {
    return institutionLink;
  }

  public void setInstitutionLink(URI institutionLink) {
    this.institutionLink = institutionLink;
  }

  public String getInstitutionCode() {
    return institutionCode;
  }

  public void setInstitutionCode(String institutionCode) {
    this.institutionCode = institutionCode;
  }

  public String getInstitutionName() {
    return institutionName;
  }

  public void setInstitutionName(String institutionName) {
    this.institutionName = institutionName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    CollectionMatched that = (CollectionMatched) o;
    return Objects.equals(institutionKey, that.institutionKey)
        && Objects.equals(institutionLink, that.institutionLink)
        && Objects.equals(institutionCode, that.institutionCode)
        && Objects.equals(institutionName, that.institutionName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(), institutionKey, institutionLink, institutionCode, institutionName);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CollectionMatched.class.getSimpleName() + "[", "]")
        .add("super=" + super.toString())
        .add("institutionKey=" + institutionKey)
        .add("institutionLink=" + institutionLink)
        .add("institutionCode='" + institutionCode + "'")
        .add("institutionName='" + institutionName + "'")
        .toString();
  }
}
