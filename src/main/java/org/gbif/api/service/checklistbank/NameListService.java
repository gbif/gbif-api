/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.service.checklistbank;

import org.gbif.api.model.checklistbank.UserChecklist;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import javax.annotation.Nullable;

/**
 * Service interface for NameList Interface methods. This is the public interface
 * providing methods for retrieving name lists.
 *
 * @see org.gbif.api.model.checklistbank.UserChecklist
 */
public interface NameListService {

  /**
   * Gets a single name list.
   *
   * @param key that identifies a name list
   *
   * @return the name list or null if not existing
   */
  @Nullable
  UserChecklist get(int key);

  /**
   * Lists all name lists for a given name list creator.
   *
   * @param email the email address of the name list creator
   * @param page  paging parameters or null for first page with default size
   *
   * @return Wrapper that contains a list of name lists
   */
  PagingResponse<UserChecklist> list(String email, @Nullable Pageable page);

}
