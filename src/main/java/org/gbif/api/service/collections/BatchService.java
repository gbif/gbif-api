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
package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Batch;
import org.gbif.api.model.common.export.ExportFormat;

/** Defines the service to work with batches. */
public interface BatchService {

  /**
   * Handles a batch. The batch can be to either do an initial import or to update existing data.
   *
   * @param entitiesFile file with the entities to import or update
   * @param contactsFile file with the contacts to import or update
   * @param format {@link ExportFormat} of the files
   * @return key of the batch created
   */
  int handleBatch(byte[] entitiesFile, byte[] contactsFile, ExportFormat format);

  /**
   * Returns a {@link Batch} by its key.
   *
   * @param key key of the batch
   * @return {@link Batch}
   */
  Batch get(int key);
}
