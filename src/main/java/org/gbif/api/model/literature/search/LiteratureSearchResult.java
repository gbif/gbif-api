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

@SuppressWarnings("unused")
public class LiteratureSearchResult {

  @Schema(name = "abstract", description = "The abstract from the publication.")
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

  @Schema(description = "The publication date of the publication.")
  private Date published;

  @Schema(description = "The day of publication", minimum = "1", maximum = "31")
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

  @Schema(description = "The day of publication", minimum = "1", maximum = "12")
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

  @Schema(description = "The year of publication")
  private Integer year;

  @JsonProperty("abstract")
  public String getAbstract() {
    return abstract_;
  }

  public void setAbstract(String abstract_) {
    this.abstract_ = abstract_;
  }

  public String getDiscovered() {
    return discovered;
  }

  public void setDiscovered(String discovered) {
    this.discovered = discovered;
  }

  public List<Map<String, Object>> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Map<String, Object>> authors) {
    this.authors = authors;
  }

  public Set<Country> getCountriesOfCoverage() {
    return countriesOfCoverage;
  }

  public void setCountriesOfCoverage(Set<Country> countriesOfCoverage) {
    this.countriesOfCoverage = countriesOfCoverage;
  }

  public Set<Country> getCountriesOfResearcher() {
    return countriesOfResearcher;
  }

  public void setCountriesOfResearcher(Set<Country> countriesOfResearcher) {
    this.countriesOfResearcher = countriesOfResearcher;
  }

  public Date getAdded() {
    return added;
  }

  public void setAdded(Date added) {
    this.added = added;
  }

  public Date getPublished() {
    return published;
  }

  public void setPublished(Date published) {
    this.published = published;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  public List<String> getGbifDownloadKey() {
    return gbifDownloadKey;
  }

  public void setGbifDownloadKey(List<String> gbifDownloadKey) {
    this.gbifDownloadKey = gbifDownloadKey;
  }

  public List<Long> getGbifOccurrenceKey() {
    return gbifOccurrenceKey;
  }

  public void setGbifOccurrenceKey(List<Long> gbifOccurrenceKey) {
    this.gbifOccurrenceKey = gbifOccurrenceKey;
  }

  public List<Integer> getGbifTaxonKey() {
    return gbifTaxonKey;
  }

  public void setGbifTaxonKey(List<Integer> gbifTaxonKey) {
    this.gbifTaxonKey = gbifTaxonKey;
  }

  public List<Integer> getGbifHigherTaxonKey() {
    return gbifHigherTaxonKey;
  }

  public void setGbifHigherTaxonKey(List<Integer> gbifHigherTaxonKey) {
    this.gbifHigherTaxonKey = gbifHigherTaxonKey;
  }

  public String getCitationType() {
    return citationType;
  }

  public void setCitationType(String citationType) {
    this.citationType = citationType;
  }

  public Set<GbifRegion> getGbifRegion() {
    return gbifRegion;
  }

  public void setGbifRegion(Set<GbifRegion> gbifRegion) {
    this.gbifRegion = gbifRegion;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Map<String, Object> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(Map<String, Object> identifiers) {
    this.identifiers = identifiers;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public LiteratureType getLiteratureType() {
    return literatureType;
  }

  public void setLiteratureType(LiteratureType literatureType) {
    this.literatureType = literatureType;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public boolean isOpenAccess() {
    return openAccess;
  }

  public void setOpenAccess(boolean openAccess) {
    this.openAccess = openAccess;
  }

  public boolean isPeerReview() {
    return peerReview;
  }

  public void setPeerReview(boolean peerReview) {
    this.peerReview = peerReview;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Set<LiteratureRelevance> getRelevance() {
    return relevance;
  }

  public void setRelevance(Set<LiteratureRelevance> relevance) {
    this.relevance = relevance;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<LiteratureTopic> getTopics() {
    return topics;
  }

  public void setTopics(Set<LiteratureTopic> topics) {
    this.topics = topics;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public List<String> getWebsites() {
    return websites;
  }

  public void setWebsites(List<String> websites) {
    this.websites = websites;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public List<UUID> getGbifNetworkKey() {
    return gbifNetworkKey;
  }

  public void setGbifNetworkKey(List<UUID> gbifNetworkKey) {
    this.gbifNetworkKey = gbifNetworkKey;
  }

  public List<String> getGbifProjectIdentifier() {
    return gbifProjectIdentifier;
  }

  public void setGbifProjectIdentifier(List<String> gbifProjectIdentifier) {
    this.gbifProjectIdentifier = gbifProjectIdentifier;
  }

  public List<String> getGbifProgramme() {
    return gbifProgramme;
  }

  public void setGbifProgramme(List<String> gbifProgramme) {
    this.gbifProgramme = gbifProgramme;
  }
}
