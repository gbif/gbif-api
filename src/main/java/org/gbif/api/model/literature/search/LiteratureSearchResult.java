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

import org.gbif.api.model.literature.LiteratureRelevance;
import org.gbif.api.model.literature.LiteratureTopic;
import org.gbif.api.model.literature.LiteratureType;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.Language;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("unused")
public class LiteratureSearchResult {

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private String abstract_;

  @Schema(description = "The date the literature item was found by GBIF's staff or automated processes.")
  private String discovered;

  @Schema(description = "The authors of the publication.")
  private List<Map<String, Object>> authors = new ArrayList<>();

  @Schema(description = "Countries or areas of focus of the study.")
  private Set<Country> countriesOfCoverage = new HashSet<>();

  @Schema(description = "Countries or areas of institution with which authors are affiliated.")
  private Set<Country> countriesOfResearcher = new HashSet<>();

  @Schema(description = "The date the publication was added to the GBIF literature database.")
  private Date added;

  @Schema(description = "The publication date of the publication.  See also `year`, `month` and `day`.")
  private Date published;

  @Schema(description = "The day of publication. See also `published`.", minimum = "1", maximum = "31")
  private Integer day;

  @Schema(description = "Keys of GBIF downloads referenced by the publication.")
  private List<String> gbifDownloadKey = new ArrayList<>();

  @Schema(description = "Keys of GBIF occurrences directly mentioned by the paper.")
  private List<Long> gbifOccurrenceKey = new ArrayList<>();

  @Schema(description = "Keys of taxa from the GBIF Backbone Taxonomy that are the focus of the paper.")
  private List<Integer> gbifTaxonKey = new ArrayList<>();

  @Schema(description = "Keys of higher taxa from the GBIF Backbone Taxonomy that are the focus of the paper.")
  private List<Integer> gbifHigherTaxonKey = new ArrayList<>();

  @Schema(description = "A list of GBIF network keys relevant to the publication.")
  private List<UUID> gbifNetworkKey = new ArrayList<>();

  @Schema(description = "A list of GBIF project identifiers relevant to the publication.")
  private List<String> gbifProjectIdentifier = new ArrayList<>();

  @Schema(description = "A list of GBIF programmes relevant to the publication.")
  private List<String> gbifProgramme = new ArrayList<>();

  @Schema(description = "The manner in which GBIF is cited in a paper.")
  private String citationType;

  @Schema(description = "GBIF regions (political divisions) related to the publication.")
  private Set<GbifRegion> gbifRegion = new HashSet<>();

  @Schema(description = "GBIF identifier for this literature item.")
  private UUID id;

  @Schema(description = "Identifiers (such as DOIs) for the literature item.")
  private Map<String, Object> identifiers = new HashMap<>();

  @Schema(description = "Keywords assigned to the literature item.")
  private List<String> keywords = new ArrayList<>();

  @Schema(description = "The language of the publication.")
  private Language language;

  @Schema(description = "Type of literature, e.g. journal article.")
  private LiteratureType literatureType;

  @Schema(description = "The month of publication. See also `published`.", minimum = "1", maximum = "12")
  private Integer month;

  @Schema(description = "Unstructured notes.")
  private String notes;

  @Schema(description = "Whether the publication is open access.")
  private boolean openAccess;

  @Schema(description = "Whether the publication has been peer reviewed.")
  private boolean peerReview;

  @Schema(description = "The publisher of the paper.")
  private String publisher;

  @Schema(description = "Relevance to GBIF community, see [literature relevance](https://www.gbif.org/faq?question=literature-relevance).")
  private Set<LiteratureRelevance> relevance = new HashSet<>();

  @Schema(description = "Journal of publication.")
  private String source;

  @Schema(description = "Various tags applied to the literature.")
  private List<String> tags = new ArrayList<>();

  @Schema(description = "The title of the publication.")
  private String title;

  @Schema(description = "Topics of the publication.")
  private Set<LiteratureTopic> topics = new HashSet<>();

  @Schema(description = "The date this literature entry was last modified.")
  private Date modified;

  @Schema(description = "Websites associated with the publication.")
  private List<String> websites = new ArrayList<>();

  @Schema(description = "The year of publication.  See also `published`.")
  private Integer year;

  @Schema(name = "abstract", description = "The abstract from the publication.")
  @JsonProperty("abstract")
  public String getAbstract() {
    return abstract_;
  }

  public void setAbstract(String abstract_) {
    this.abstract_ = abstract_;
  }

}
