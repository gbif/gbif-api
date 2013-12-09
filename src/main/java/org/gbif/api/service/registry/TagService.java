/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.service.registry;

import org.gbif.api.model.registry.Tag;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public interface TagService {

  int addTag(@NotNull UUID targetEntityKey, @NotNull String value);

  /**
   * Add a new Tag to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param tag             Tag to add
   *
   * @return key of Tag added
   */
  int addTag(@NotNull UUID targetEntityKey, @NotNull Tag tag);

  void deleteTag(@NotNull UUID taggedEntityKey, int tagKey);

  List<Tag> listTags(@NotNull UUID taggedEntityKey, @Nullable String owner);

}
