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

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.registry.DatasetService;
import org.gbif.api.vocabulary.DatasetType;

import javax.annotation.Nullable;

/**
 * Pages through all datasets filtering by type only.
 */
public class DatasetPager extends DatasetBasePager {

    private final DatasetService ds;
    private final DatasetType type;

    public DatasetPager(DatasetService ds, @Nullable DatasetType type, int pageSize) {
        super(type, pageSize);
        this.ds = ds;
        this.type = type;
    }

    @Override
    PagingResponse<Dataset> nextPage(PagingRequest page) {
        if (type == null) {
            return ds.list(page);
        } else {
            return ds.listByType(type, page);
        }
    }

}
