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
package org.gbif.api.model.collections;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

/** Contains the parameters to create an institution from an organization. */
public class InstitutionImportParams {

  private UUID organizationKey;
  private String institutionCode;

  public UUID getOrganizationKey() {
    return organizationKey;
  }

  public void setOrganizationKey(UUID organizationKey) {
    this.organizationKey = organizationKey;
  }

  public String getInstitutionCode() {
    return institutionCode;
  }

  public void setInstitutionCode(String institutionCode) {
    this.institutionCode = institutionCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstitutionImportParams that = (InstitutionImportParams) o;
    return Objects.equals(organizationKey, that.organizationKey)
        && Objects.equals(institutionCode, that.institutionCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(organizationKey, institutionCode);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InstitutionImportParams.class.getSimpleName() + "[", "]")
        .add("organizationKey=" + organizationKey)
        .add("institutionCode='" + institutionCode + "'")
        .toString();
  }
}
