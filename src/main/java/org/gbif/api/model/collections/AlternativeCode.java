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
package org.gbif.api.model.collections;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Models a GrSciColl alternative code.
 *
 * <p>It contains the code and a description to specify why this code exists.
 */
public class AlternativeCode implements Serializable {

  private String code;
  private String description;

  public AlternativeCode() {}

  public AlternativeCode(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlternativeCode that = (AlternativeCode) o;
    return Objects.equals(code, that.code) && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, description);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AlternativeCode.class.getSimpleName() + "[", "]")
        .add("code='" + code + "'")
        .add("description='" + description + "'")
        .toString();
  }
}
