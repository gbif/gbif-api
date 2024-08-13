/*
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
package org.gbif.api.model.literature.search;

import java.util.Date;

import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.literature.LiteratureRelevance;
import org.gbif.api.model.literature.LiteratureTopic;
import org.gbif.api.model.literature.LiteratureType;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Language;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = LiteratureSearchParameter.class)
public enum LiteratureSearchParameter implements SearchParameter {
  COUNTRIES_OF_RESEARCHER(Country.class),
  COUNTRIES_OF_COVERAGE(Country.class),
  LITERATURE_TYPE(LiteratureType.class),
  RELEVANCE(LiteratureRelevance.class),
  YEAR(Integer.class),
  TOPICS(LiteratureTopic.class),
  GBIF_DATASET_KEY(UUID.class), // dataset
  PUBLISHING_ORGANIZATION_KEY(UUID.class), // publisher
  PEER_REVIEW(Boolean.class),
  OPEN_ACCESS(Boolean.class),
  GBIF_DOWNLOAD_KEY(String.class), // download key
  GBIF_OCCURRENCE_KEY(Long.class), // occurrence key
  GBIF_TAXON_KEY(Integer.class), // taxon key
  GBIF_HIGHERTAXON_KEY(Integer.class), //higher taxon key
  GBIF_NETWORK_KEY(UUID.class), // network keys
  GBIF_PROJECT_IDENTIFIER(String.class), // project identifier
  GBIF_PROGRAMME(String.class), // project identifier
  CITATION_TYPE(String.class), // citation type
  DOI(String.class),
  SOURCE(String.class), // journal
  PUBLISHER(String.class), // journal publisher
  LANGUAGE(Language.class),
  ADDED(Date.class), // created in es
  PUBLISHED(Date.class), // publish date
  DISCOVERED(Date.class), // access date
  MODIFIED(Date.class); // update date

  private final Class<?> type;

  LiteratureSearchParameter(Class<?> type) {
    this.type = type;
  }

  @Override
  public Class<?> type() {
    return type;
  }
}
