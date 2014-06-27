/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import javax.annotation.Nullable;

/**
 * Parameterized service interface for model classes extending a NameUsage.
 * @param <T> the extension model class.
 */
public interface NameUsageExtensionService<T> {

  /**
   * Returns all extension records for a checklist usage.
   *
   * @param taxonKey the usage the extensions are related to
   * @param page     paging parameters or null for first page with default size
   *
   * @return Wrapper that contains a potentially empty component list, but never null
   */
  PagingResponse<T> listByUsage(int taxonKey, @Nullable Pageable page);

}
