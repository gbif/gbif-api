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

/** Contains the parameters to create a collection from a dataset. */
public class CollectionImportParams {

  private UUID datasetKey;
  private String collectionCode;

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public String getCollectionCode() {
    return collectionCode;
  }

  public void setCollectionCode(String collectionCode) {
    this.collectionCode = collectionCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollectionImportParams that = (CollectionImportParams) o;
    return Objects.equals(datasetKey, that.datasetKey)
        && Objects.equals(collectionCode, that.collectionCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datasetKey, collectionCode);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CollectionImportParams.class.getSimpleName() + "[", "]")
        .add("datasetKey=" + datasetKey)
        .add("collectionCode='" + collectionCode + "'")
        .toString();
  }
}
