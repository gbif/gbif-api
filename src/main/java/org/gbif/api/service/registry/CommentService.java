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

import org.gbif.api.model.registry.Comment;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Service provides a set of operations on {@link Comment}.
 */
@SuppressWarnings("unused")
public interface CommentService {

  /**
   * Add a new Comment to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param comment         Comment to add
   *
   * @return key of Comment added
   */
  int addComment(@NotNull UUID targetEntityKey, @NotNull @Valid Comment comment);

  /**
   * Delete an existing Comment from a target entity by comment key.
   *
   * @param targetEntityKey key of target entity
   * @param commentKey      Comment key to delete
   */
  void deleteComment(@NotNull UUID targetEntityKey, int commentKey);

  /**
   * List all comments of a target entity.
   *
   * @param targetEntityKey key of target entity
   *
   * @return list of comments that belong to the entity
   */
  List<Comment> listComments(@NotNull UUID targetEntityKey);
}
