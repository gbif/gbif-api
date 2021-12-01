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
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.registry.CommentService;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.MachineTagService;
import org.gbif.api.service.registry.TagService;

import javax.annotation.Nullable;

public interface CollectionEntityService<T extends CollectionEntity>
    extends CrudService<T>, IdentifierService, TagService, MachineTagService, CommentService {

  /**
   * Lists the entities having a machine tag in the provided namespace, with the provided name and
   * value.
   *
   * @return a pageable response of collection entities, with accurate counts for the machine tag
   *     provided
   */
  PagingResponse<T> listByMachineTag(
      String namespace, @Nullable String name, @Nullable String value, @Nullable Pageable page);
}
