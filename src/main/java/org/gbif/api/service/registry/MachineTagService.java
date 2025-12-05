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
package org.gbif.api.service.registry;

import org.gbif.api.model.registry.MachineTag;
import org.gbif.api.vocabulary.TagName;
import org.gbif.api.vocabulary.TagNamespace;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Service provides a set of operations on {@link MachineTag}.
 */
@SuppressWarnings("unused")
public interface MachineTagService {

  /**
   * Add a new {@link MachineTag} to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param machineTag      tag to add
   *
   * @return key of tag added
   */
  int addMachineTag(@NotNull UUID targetEntityKey, @NotNull @Valid MachineTag machineTag);

  /**
   * Add a new {@link MachineTag} to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param tagName         {@link TagName}
   * @param value           tag value to add
   *
   * @return key of tag added
   */
  int addMachineTag(@NotNull UUID targetEntityKey, @NotNull TagName tagName, @NotNull String value);

  /**
   * Add a new {@link MachineTag} to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param namespace       tag namespace
   * @param name            name of the tag to add
   * @param value           value of the tag to add
   *
   * @return key of tag added
   */
  int addMachineTag(@NotNull UUID targetEntityKey, @NotNull String namespace, @NotNull String name, @NotNull String value);

  /**
   * Delete an existing {@link MachineTag} from a tagged entity by tag key.
   *
   * @param targetEntityKey key of tagged entity
   * @param machineTagKey   key of the tag to delete
   */
  void deleteMachineTag(@NotNull UUID targetEntityKey, int machineTagKey);

  /**
   * Delete machine tags from a tagged entity by tag namespace.
   *
   * @param targetEntityKey key of tagged entity
   * @param tagNamespace    {@link TagNamespace}
   */
  void deleteMachineTags(@NotNull UUID targetEntityKey, @NotNull TagNamespace tagNamespace);

  /**
   * Delete machine tags from a tagged entity by tag namespace.
   *
   * @param targetEntityKey key of tagged entity
   * @param namespace       tag namespace
   */
  void deleteMachineTags(@NotNull UUID targetEntityKey, @NotNull String namespace);

  /**
   * Delete machine tags from a tagged entity by tag name.
   *
   * @param targetEntityKey key of tagged entity
   * @param tagName         {@link TagName}
   */
  void deleteMachineTags(@NotNull UUID targetEntityKey, @NotNull TagName tagName);

  /**
   * Delete machine tags from a tagged entity by namespace and tag name.
   *
   * @param targetEntityKey key of tagged entity
   * @param namespace       tag namespace
   * @param name            {@link TagName}
   */
  void deleteMachineTags(@NotNull UUID targetEntityKey, @NotNull String namespace, @NotNull String name);

  /**
   * List all machine tags of the entity.
   *
   * @param targetEntityKey key of the entity
   *
   * @return list of tags that belong to the entity
   */
  List<MachineTag> listMachineTags(@NotNull UUID targetEntityKey);
}
