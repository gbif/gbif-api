/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.metrics.cube;

import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableList;

/**
 * The occurrence cube dimension definitions and the way in which they are rolled up into counts.
 */
public class OccurrenceCube {

  public static final Dimension<Country> COUNTRY = new Dimension<Country>("country", Country.class);
  public static final Dimension<Integer> YEAR = new Dimension<Integer>("year", Integer.class);
  public static final Dimension<Boolean> IS_GEOREFERENCED = new Dimension<Boolean>("georeferenced", Boolean.class);
  public static final Dimension<BasisOfRecord> BASIS_OF_RECORD =
    new Dimension<BasisOfRecord>("basisOfRecord", BasisOfRecord.class);
  public static final Dimension<Country> HOST_COUNTRY = new Dimension<Country>("hostCountry", Country.class);
  public static final Dimension<UUID> DATASET_KEY = new Dimension<UUID>("datasetKey", UUID.class);
  public static final Dimension<Integer> NUB_KEY = new Dimension<Integer>("nubKey", Integer.class);
  public static final Dimension<EndpointType> PROTOCOL = new Dimension<EndpointType>("protocol", EndpointType.class);

  public static final List<Dimension<?>> DIMENSIONS = ImmutableList.<Dimension<?>>of(
    COUNTRY, IS_GEOREFERENCED, BASIS_OF_RECORD, HOST_COUNTRY, DATASET_KEY, NUB_KEY, PROTOCOL, YEAR
    );

  // PLEASE KEEP THIS LIST MAINTAINED ALPHABETICALLY so we can spot duplicates and groupings
  // sort rows as well as dimensions within each row
  public static final List<Rollup> ROLLUPS = ImmutableList.of(
    new Rollup(BASIS_OF_RECORD),
    new Rollup(BASIS_OF_RECORD, COUNTRY),
    new Rollup(BASIS_OF_RECORD, COUNTRY, IS_GEOREFERENCED),
    new Rollup(BASIS_OF_RECORD, COUNTRY, NUB_KEY),
    new Rollup(BASIS_OF_RECORD, COUNTRY, NUB_KEY, IS_GEOREFERENCED),
    new Rollup(BASIS_OF_RECORD, DATASET_KEY),
    new Rollup(BASIS_OF_RECORD, DATASET_KEY, IS_GEOREFERENCED),
    new Rollup(BASIS_OF_RECORD, DATASET_KEY, IS_GEOREFERENCED, NUB_KEY),
    new Rollup(BASIS_OF_RECORD, DATASET_KEY, NUB_KEY),
    new Rollup(BASIS_OF_RECORD, HOST_COUNTRY),
    new Rollup(BASIS_OF_RECORD, HOST_COUNTRY, IS_GEOREFERENCED),
    new Rollup(BASIS_OF_RECORD, HOST_COUNTRY, NUB_KEY),
    new Rollup(BASIS_OF_RECORD, HOST_COUNTRY, NUB_KEY, IS_GEOREFERENCED),
    new Rollup(COUNTRY),
    new Rollup(COUNTRY, DATASET_KEY, IS_GEOREFERENCED),
    new Rollup(COUNTRY, IS_GEOREFERENCED),
    new Rollup(COUNTRY, HOST_COUNTRY),
    new Rollup(COUNTRY, HOST_COUNTRY, IS_GEOREFERENCED),
    new Rollup(COUNTRY, NUB_KEY),
    new Rollup(COUNTRY, NUB_KEY, IS_GEOREFERENCED),
    new Rollup(DATASET_KEY),
    new Rollup(DATASET_KEY, IS_GEOREFERENCED),
    new Rollup(DATASET_KEY, NUB_KEY),
    new Rollup(DATASET_KEY, NUB_KEY, IS_GEOREFERENCED),
    new Rollup(HOST_COUNTRY),
    new Rollup(HOST_COUNTRY, IS_GEOREFERENCED),
    new Rollup(HOST_COUNTRY, NUB_KEY),
    new Rollup(HOST_COUNTRY, NUB_KEY, IS_GEOREFERENCED),
    new Rollup(IS_GEOREFERENCED),
    new Rollup(IS_GEOREFERENCED, NUB_KEY),
    new Rollup(NUB_KEY),
    new Rollup(PROTOCOL),
    new Rollup(YEAR)
    );

  // Not intended for instantiation
  private OccurrenceCube() {
  }
}
