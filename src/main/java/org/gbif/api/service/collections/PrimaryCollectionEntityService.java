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
package org.gbif.api.service.collections;

import org.gbif.api.vocabulary.collections.MasterSourceType;
import org.gbif.api.model.collections.PrimaryCollectionEntity;

import java.util.UUID;

public interface PrimaryCollectionEntityService<T extends PrimaryCollectionEntity>
    extends CollectionEntityService<T>, ContactService, OccurrenceMappingService {

  /** Replaces a entity with another. The entity replaced is also deleted. */
  void replace(UUID entityToReplaceKey, UUID replacementKey);

  /**
   * Updates the master source of an entity. It's done in a separate method to have control over
   * these updates since they are required to be in sync with the creation or deletion of the
   * corresponding machine tags.
   */
  void updateMasterSource(UUID entityKey, MasterSourceType masterSource);
}
