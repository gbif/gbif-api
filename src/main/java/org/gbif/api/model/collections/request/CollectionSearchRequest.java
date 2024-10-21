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
package org.gbif.api.model.collections.request;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gbif.api.vocabulary.collections.CollectionFacetParameter;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class CollectionSearchRequest extends SearchRequest<CollectionFacetParameter> {

  @Deprecated @Nullable private List<UUID> institution;
  @Nullable private List<String> contentTypes;
  @Nullable private List<String> preservationTypes;
  @Nullable private List<String> accessionStatus;
  @Nullable private List<Boolean> personalCollection;
}
