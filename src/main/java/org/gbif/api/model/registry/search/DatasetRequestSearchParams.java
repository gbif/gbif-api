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
package org.gbif.api.model.registry.search;

import java.util.Set;

import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.DatasetType;

import lombok.Getter;
import lombok.Setter;

/** Parameters to list datasets. */
@Getter
@Setter
public class DatasetRequestSearchParams extends RequestSearchParams {

  public static final String TYPE_PARAM = "type";

  private DatasetType type; // datasetType
  private Country country;
  private String contactUserId;
  private String contactEmail;
  private Set<String> category;
}
