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
package org.gbif.api.model.metrics.cube;

import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.api.vocabulary.TypeStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * The occurrence cube dimension definitions and the way in which they are rolled up into counts.
 */
@SuppressWarnings("unused")
public class OccurrenceCube {

  public static final Dimension<UUID> DATASET_KEY = new Dimension<>("datasetKey", UUID.class);
  public static final Dimension<String> TAXON_KEY = new Dimension<>("taxonKey", String.class);
  public static final Dimension<BasisOfRecord> BASIS_OF_RECORD = new Dimension<>("basisOfRecord",
    BasisOfRecord.class);
  // georeferenced is different from hasCoordinate and includes a no geospatial issue check!
  public static final Dimension<Boolean> IS_GEOREFERENCED = new Dimension<>("isGeoreferenced",
    Boolean.class);
  public static final Dimension<Country> COUNTRY = new Dimension<>("country", Country.class);
  public static final Dimension<Country> PUBLISHING_COUNTRY = new Dimension<>("publishingCountry",
    Country.class);
  public static final Dimension<Integer> YEAR = new Dimension<>("year", Integer.class);
  public static final Dimension<EndpointType> PROTOCOL = new Dimension<>("protocol",
    EndpointType.class);
  public static final Dimension<TypeStatus> TYPE_STATUS = new Dimension<>("typeStatus",
    TypeStatus.class);
  public static final Dimension<OccurrenceIssue> ISSUE = new Dimension<>("issue",
    OccurrenceIssue.class);

  public static final List<Dimension<?>> DIMENSIONS = Collections.unmodifiableList(
    Arrays.asList(
      COUNTRY, IS_GEOREFERENCED, BASIS_OF_RECORD, PUBLISHING_COUNTRY, DATASET_KEY, TAXON_KEY,
      PROTOCOL, YEAR, TYPE_STATUS, ISSUE
    ));

  // PLEASE KEEP THIS LIST MAINTAINED ALPHABETICALLY so we can spot duplicates and groupings
  // sort rows as well as dimensions within each row
  public static final List<Rollup> ROLLUPS = Collections.unmodifiableList(
    Arrays.asList(
      new Rollup(BASIS_OF_RECORD),
      new Rollup(BASIS_OF_RECORD, COUNTRY),
      new Rollup(BASIS_OF_RECORD, COUNTRY, IS_GEOREFERENCED),
      new Rollup(BASIS_OF_RECORD, COUNTRY, IS_GEOREFERENCED, TAXON_KEY),
      new Rollup(BASIS_OF_RECORD, COUNTRY, TAXON_KEY),
      new Rollup(BASIS_OF_RECORD, DATASET_KEY),
      new Rollup(BASIS_OF_RECORD, DATASET_KEY, IS_GEOREFERENCED),
      new Rollup(BASIS_OF_RECORD, DATASET_KEY, IS_GEOREFERENCED, TAXON_KEY),
      new Rollup(BASIS_OF_RECORD, DATASET_KEY, TAXON_KEY),
      new Rollup(BASIS_OF_RECORD, IS_GEOREFERENCED, TAXON_KEY),
      new Rollup(BASIS_OF_RECORD, IS_GEOREFERENCED, PUBLISHING_COUNTRY),
      new Rollup(BASIS_OF_RECORD, IS_GEOREFERENCED, PUBLISHING_COUNTRY, TAXON_KEY),
      new Rollup(BASIS_OF_RECORD, PUBLISHING_COUNTRY),
      new Rollup(BASIS_OF_RECORD, PUBLISHING_COUNTRY, TAXON_KEY),
      new Rollup(BASIS_OF_RECORD, TAXON_KEY),
      new Rollup(COUNTRY),
      new Rollup(COUNTRY, DATASET_KEY, IS_GEOREFERENCED),
      new Rollup(COUNTRY, IS_GEOREFERENCED),
      new Rollup(COUNTRY, IS_GEOREFERENCED, PUBLISHING_COUNTRY),
      new Rollup(COUNTRY, IS_GEOREFERENCED, TAXON_KEY),
      new Rollup(COUNTRY, PUBLISHING_COUNTRY),
      new Rollup(COUNTRY, TAXON_KEY),
      new Rollup(COUNTRY, TYPE_STATUS),
      new Rollup(DATASET_KEY),
      new Rollup(DATASET_KEY, IS_GEOREFERENCED),
      new Rollup(DATASET_KEY, IS_GEOREFERENCED, TAXON_KEY),
      new Rollup(DATASET_KEY, ISSUE),
      new Rollup(DATASET_KEY, TAXON_KEY),
      new Rollup(DATASET_KEY, TYPE_STATUS),
      new Rollup(IS_GEOREFERENCED),
      new Rollup(IS_GEOREFERENCED, PUBLISHING_COUNTRY),
      new Rollup(IS_GEOREFERENCED, PUBLISHING_COUNTRY, TAXON_KEY),
      new Rollup(IS_GEOREFERENCED, TAXON_KEY),
      new Rollup(ISSUE),
      new Rollup(PUBLISHING_COUNTRY),
      new Rollup(PUBLISHING_COUNTRY, TAXON_KEY),
      new Rollup(PUBLISHING_COUNTRY, TYPE_STATUS),
      new Rollup(TAXON_KEY),
      new Rollup(TAXON_KEY, TYPE_STATUS),
      new Rollup(TYPE_STATUS),
      new Rollup(PROTOCOL),
      new Rollup(YEAR)
    ));

  // Not intended for instantiation
  private OccurrenceCube() {
  }
}
