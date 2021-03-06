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
package org.gbif.api.vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * Known namespaces for tags.
 */
public enum TagNamespace {

  /**
   * The public namespace.
   */
  PUBLIC(null),

  /**
   * Used by the Crawler and related projects.
   */
  GBIF_CRAWLER("crawler.gbif.org"),

  /**
   * The GBIF DwC-Archive Validator namespace.
   */
  GBIF_VALIDATOR("validator.gbif.org"),

  /**
   * The GBIF Harvesting and Indexing Toolkit (HIT) namespace.
   */
  GBIF_HARVESTING("hit.gbif.org"),

  /**
   * The GBIF Registry Metasynchronizer namespace.
   */
  GBIF_METASYNC("metasync.gbif.org"),

  /**
   * The GBIF Orphans namespace.
   */
  GBIF_ORPHANS("orphans.gbif.org"),

  /**
   * The GBIF Default Term values namespace.
   */
  GBIF_DEFAULT_TERM("default-term.gbif.org"),

  /**
   * The Encyclopedia of Life (EOL) namespace.
   */
  EOL("eol.org"),

  /**
   * The Catalogue of Life (COL) namespace.
   */
  COL("catalogueoflife.org"),

  /**
   * The Atlas of Living Australia namespace.
   */
  ALA("ala.org.au");

  private final String namespace;

  TagNamespace(String namespace) {
    this.namespace = namespace;
  }

  /**
   * @return the unique full namespace uri in lower case.
   */
  public String getNamespace() {
    return namespace;
  }

  /**
   * Gets the list of {@link TagName} which belongs to the namespace.
   */
  public List<TagName> getPredicates() {
    List<TagName> tagNames = new ArrayList<>();
    for (TagName tagName : TagName.values()) {
      if (tagName.getNamespace() == this) {
        tagNames.add(tagName);
      }
    }
    return tagNames;
  }

}
