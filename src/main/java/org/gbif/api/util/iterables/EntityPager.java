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
package org.gbif.api.util.iterables;

import org.gbif.api.model.registry.NetworkEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Iterator over registry entities from paging responses that filters out deleted entities.
 */
abstract class EntityPager<T extends NetworkEntity> extends BasePager<T> {

    private static final Logger LOG = LoggerFactory.getLogger(EntityPager.class);

  public EntityPager(int pageSize) {
    super(pageSize);
  }

    /**
     * Override this method to implement other exclusion filters.
     */
    @Override
    protected boolean exclude(T entity) {
      if (entity.getDeleted() != null) {
        LOG.debug("Ignore deleted entity {}: {}", entity.getKey(), entity.getTitle().replaceAll("\n", " "));
        return true;
      }
      return false;
    }

}
