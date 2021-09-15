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
package org.gbif.api.model.collections.search;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/** Models the response for the Collections search. */
public class CollectionsSearchResponse {

  private String type;
  private UUID key;
  private String code;
  private String name;
  private UUID institutionKey;
  private String institutionCode;
  private String institutionName;
  private Set<Match> matches;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getInstitutionKey() {
    return institutionKey;
  }

  public void setInstitutionKey(UUID institutionKey) {
    this.institutionKey = institutionKey;
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

  public Set<Match> getMatches() {
    return matches;
  }

  public void setMatches(Set<Match> matches) {
    this.matches = matches;
  }

  public static class Match {
    private String field;
    private String snippet;

    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }

    public String getSnippet() {
      return snippet;
    }

    public void setSnippet(String snippet) {
      this.snippet = snippet;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Match match = (Match) o;
      return Objects.equals(field, match.field);
    }

    @Override
    public int hashCode() {
      return Objects.hash(field);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollectionsSearchResponse response = (CollectionsSearchResponse) o;
    return Objects.equals(type, response.type)
        && Objects.equals(key, response.key)
        && Objects.equals(code, response.code)
        && Objects.equals(name, response.name)
        && Objects.equals(institutionKey, response.institutionKey)
        && Objects.equals(institutionCode, response.institutionCode)
        && Objects.equals(institutionName, response.institutionName)
        && Objects.equals(matches, response.matches);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type, key, code, name, institutionKey, institutionCode, institutionName, matches);
  }
}
