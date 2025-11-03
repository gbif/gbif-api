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

import org.gbif.api.model.registry.Dataset;
import org.gbif.api.vocabulary.DatasetType;

import jakarta.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Iterator over datasets from paging responses that filters out deleted and constituent datasets
 * It also allows for an optional type filter.
 */
abstract class DatasetBasePager extends EntityPager<Dataset> {
    private static final Logger LOG = LoggerFactory.getLogger(DatasetBasePager.class);
    private final DatasetType type;

    /**
     * @param pageSize to use when talking to the registry
     * @param type the accepted dataset type, null for all
     */
    public DatasetBasePager(@Nullable DatasetType type, int pageSize) {
        super(pageSize);
        this.type = type;
    }

    @Override
    protected boolean exclude(Dataset d) {
        if (d.getParentDatasetKey() != null) {
            LOG.debug("Ignore constituent dataset {} {} of parent {}", d.getKey(), d.getTitle().replaceAll("\n", " "), d.getParentDatasetKey());
        } else if (type != null && d.getType() != type) {
            LOG.debug("Ignore {} dataset {}: {}", d.getType(), d.getKey(), d.getTitle().replaceAll("\n", " "));
        }
        return super.exclude(d);
    }
}
