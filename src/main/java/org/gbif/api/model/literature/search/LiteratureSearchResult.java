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

@SuppressWarnings("unused")
public class LiteratureSearchResult {

  private String abstr;
  private String discovered;
  private List<Map<String, Object>> authors = new ArrayList<>();
  private Set<Country> countriesOfCoverage = new HashSet<>();
  private Set<Country> countriesOfResearcher = new HashSet<>();
  private Date added;
  private Date published;
  private Integer day;
  private List<String> gbifDownloadKey = new ArrayList<>();
  private List<Long> gbifOccurrenceKey = new ArrayList<>();
  private List<Integer> gbifTaxonKey = new ArrayList<>();
  private List<Integer> gbifHigherTaxonKey = new ArrayList<>();
  private List<UUID> gbifNetworkKey = new ArrayList<>();
  private List<String> gbifProjectIdentifier = new ArrayList<>();
  private List<String> gbifProgramme = new ArrayList<>();
  private String citationType;
  private Set<GbifRegion> gbifRegion = new HashSet<>();
  private UUID id;
  private Map<String, Object> identifiers = new HashMap<>();
  private List<String> keywords = new ArrayList<>();
  private Language language;
  private LiteratureType literatureType;
  private Integer month;
  private String notes;
  private boolean openAccess;
  private boolean peerReview;
  private String publisher;
  private Set<LiteratureRelevance> relevance = new HashSet<>();
  private String source;
  private List<String> tags = new ArrayList<>();
  private String title;
  private Set<LiteratureTopic> topics = new HashSet<>();
  private Date modified;
  private List<String> websites = new ArrayList<>();
  private Integer year;

  @JsonProperty("abstract")
  public String getAbstr() {
    return abstr;
  }

  public void setAbstr(String abstr) {
    this.abstr = abstr;
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
