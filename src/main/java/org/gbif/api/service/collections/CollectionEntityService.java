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

import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.model.collections.MasterSourceMetadata;
import org.gbif.api.service.registry.CommentService;
import org.gbif.api.service.registry.MachineTagService;
import org.gbif.api.service.registry.PrimaryIdentifierService;
import org.gbif.api.service.registry.TagService;
import org.gbif.api.vocabulary.collections.Source;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface CollectionEntityService<T extends CollectionEntity>
    extends CrudService<T>,
        PrimaryIdentifierService,
        TagService,
        MachineTagService,
        CommentService,
        ContactService,
        OccurrenceMappingService {

  /** Replaces a entity with another. The entity replaced is also deleted. */
  void replace(UUID entityToReplaceKey, UUID replacementKey);

  /**
   * Adds {@link MasterSourceMetadata} to an entity.
   *
   * @param targetEntityKey key of the entity to add the metadata to
   * @param masterSourceMetadata metadata to add
   * @return key of the created metadata
   */
  int addMasterSourceMetadata(UUID targetEntityKey, MasterSourceMetadata masterSourceMetadata);

  /**
   * Removes the {@link MasterSourceMetadata} from an entity.
   *
   * @param targetEntityKey key of the entity whose metadata will be deleted
   */
  void deleteMasterSourceMetadata(UUID targetEntityKey);

  /**
   * Returns the {@link MasterSourceMetadata} of the entity.
   *
   * @param targetEntityKey key of the entity
   * @return {@link MasterSourceMetadata}
   */
  MasterSourceMetadata getMasterSourceMetadata(@NotNull UUID targetEntityKey);

  /**
   * Finds the collection entity whose master data metadata matches with the parameters received.
   *
   * @param source source of the metadata
   * @param sourceId source Id of the metadata
   * @return {@link Optional} with the collection entity found
   */
  List<T> findByMasterSource(Source source, String sourceId);

  /**
   * Updates an existing entity.
   *
   * @param entity that will replace the existing entity.
   * @param lockFields indicates if fields that come from an external master source has to be
   *     locked.
   */
  void update(@NotNull @Valid T entity, boolean lockFields);
}
