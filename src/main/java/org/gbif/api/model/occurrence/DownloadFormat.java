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
package org.gbif.api.model.occurrence;

/**
 * Download output requested format.
 */
public enum DownloadFormat {
  /**
   * Darwin Core Archive file that is a zip of all indexed fields in both verbatim and interpreted, plus multimedia and metadata files.
   */
  DWCA(".zip"),

  /**
   * Zipped text file of the most common indexed terms, but note that it is delimited by tabs, not commas.
   */
  SIMPLE_CSV(".zip"),

  /**
   * AVRO (with Deflate compression codec) format export of the most common indexed terms.
   */
  SIMPLE_AVRO(".avro"),

  /**
   * TSV format export of the distinct species and taxonomic field associated to each.
   */
  SPECIES_LIST(".zip"),

  /**
   * Special AVRO format for the Map of Life project.
   */
  MAP_OF_LIFE(".avro");

  private final String extension;

  DownloadFormat(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }
}
