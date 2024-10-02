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
package org.gbif.api.model.collections.search;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.gbif.api.vocabulary.Country;

/** Models the response for the Collections search. */
@EqualsAndHashCode(callSuper = true)
@Data
public class CollectionsFullSearchResponse extends BaseSearchResponse {

  private String type;
  private UUID institutionKey;
  private String institutionCode;
  private String institutionName;
  private Set<DescriptorMatch> descriptorMatches = new HashSet<>();
}
