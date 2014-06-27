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

import org.gbif.api.model.checklistbank.Description;
import org.gbif.api.model.checklistbank.TableOfContents;

import javax.annotation.Nullable;

/**
 * Service interface for Description Interface methods. This is the public interface
 * providing methods for retrieving Description.
 * These methods will for the most part return a Description object.
 *
 * @see Description
 */
public interface DescriptionService extends NameUsageExtensionService<Description> {

  /**
   * Returns a table of content for all available descriptions of a name usage.
   * @param taxonKey the name usage key
   */
  TableOfContents getToc(int taxonKey);

  /**
   * Retrieve a description by its key.
   * The portal species pages rely on this method to asynchroneously load species descriptions.
   *
   * @param key     that identifies a description
   */
  @Nullable
  Description get(int key);

}
