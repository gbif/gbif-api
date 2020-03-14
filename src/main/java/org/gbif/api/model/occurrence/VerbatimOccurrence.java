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

import org.gbif.api.jackson.ExtensionKeyDeserializer;
import org.gbif.api.jackson.ExtensionSerializer;
import org.gbif.api.jackson.TermMapListDeserializer;
import org.gbif.api.jackson.TermMapListSerializer;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.Extension;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * An extended map holding all core terms of an occurrence record.
 * Major extensions that we index are also supported, i.e. media, identifiers and measurements or facts.
 */
@SuppressWarnings("unused")
public class VerbatimOccurrence {

  private Long key;
  private UUID datasetKey;
  private UUID publishingOrgKey;
  private List<UUID> networkKeys;
  private UUID installationKey;
  private Country publishingCountry;
  private EndpointType protocol;
  private Date lastCrawled;
  private Date lastParsed;
  private Integer crawlId;
  //GBIF Participation: Programme and Project
  private String projectId;
  private String programmeAcronym;

  // the verbatim fields for the occurrence
  private Map<Term, String> verbatimFields = new HashMap<>();
  // verbatim extension data
  private Map<Extension, List<Map<Term, String>>> extensions = new HashMap<>();

  /**
   * Get the value of a specific field (Term).
   */
  @Nullable
  public String getVerbatimField(Term term) {
    Objects.requireNonNull(term, "term can't be null");
    return verbatimFields.get(term);
  }

  /**
   * @return true if a verbatim field exists and is not null or an empty string
   */
  public boolean hasVerbatimField(Term term) {
    Objects.requireNonNull(term, "term can't be null");
    return StringUtils.isNotEmpty(verbatimFields.get(term));
  }

  /**
   * For setting a specific field without having to replace the entire verbatimFields Map.
   *
   * @param term       the field to set
   * @param fieldValue the field's value
   */
  public void setVerbatimField(Term term, @Nullable String fieldValue) {
    Objects.requireNonNull(term, "term can't be null");
    verbatimFields.put(term, fieldValue);
  }

  /**
   * The GBIF assigned, persistent key to the occurrence record.
   * OccurrenceID itself is kept in the verbatim verbatimFields map.
   */
  @NotNull
  public Long getKey() {
    return key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  @NotNull
  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  @NotNull
  public UUID getPublishingOrgKey() {
    return publishingOrgKey;
  }

  public void setPublishingOrgKey(UUID publishingOrgKey) {
    this.publishingOrgKey = publishingOrgKey;
  }

  /**
   * The GBIF Network associated to the publishing dataset.
   */
  @Nullable
  public List<UUID> getNetworkKeys() {
    return networkKeys;
  }

  public void setNetworkKeys(List<UUID> networkKeys) {
    this.networkKeys = networkKeys;
  }

  /**
   * Technical installation that publishes this occurrence record.
   */
  @Nullable
  public UUID getInstallationKey() {
    return installationKey;
  }

  public void setInstallationKey(UUID installationKey) {
    this.installationKey = installationKey;
  }

  /**
   * The country of the organization that publishes the dataset to which the occurrence belongs.
   */
  @Nullable
  public Country getPublishingCountry() {
    return publishingCountry;
  }

  public void setPublishingCountry(Country publishingCountry) {
    this.publishingCountry = publishingCountry;
  }

  @NotNull
  public EndpointType getProtocol() {
    return protocol;
  }

  public void setProtocol(EndpointType protocol) {
    this.protocol = protocol;
  }

  /**
   * The date this record was last crawled/harvested from the endpoint.
   */
  @Nullable
  public Date getLastCrawled() {
    return lastCrawled == null ? null : new Date(lastCrawled.getTime());
  }

  public void setLastCrawled(@Nullable Date lastCrawled) {
    this.lastCrawled = lastCrawled == null ? null : new Date(lastCrawled.getTime());
  }

  /**
   * The date this record was last parsed from raw xml/json into verbatim verbatimFields.
   */
  @Nullable
  public Date getLastParsed() {
    return lastParsed;
  }

  public void setLastParsed(@Nullable Date lastParsed) {
    this.lastParsed = lastParsed == null ? null : new Date(lastParsed.getTime());
  }

  /**
   * Crawling attempt id.
   */
  @Nullable
  public Integer getCrawlId() {
    return crawlId;
  }

  public void setCrawlId(Integer crawlId) {
    this.crawlId = crawlId;
  }

  /**
   * GBIF project identifier.
   */
  @Nullable
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  /**
   * GBIF programme acronym/identifier.
   */
  @Nullable
  public String getProgrammeAcronym() {
    return programmeAcronym;
  }

  public void setProgrammeAcronym(String programmeAcronym) {
    this.programmeAcronym = programmeAcronym;
  }

  /**
   * A map holding all verbatim core terms.
   */
  @NotNull
  @JsonIgnore
  public Map<Term, String> getVerbatimFields() {
    return verbatimFields;
  }

  public void setVerbatimFields(Map<Term, String> verbatimFields) {
    this.verbatimFields = verbatimFields;
  }

  /**
   * A map holding all verbatim extension terms.
   */
  @NotNull
  @JsonSerialize(keyUsing = ExtensionSerializer.class, contentUsing = TermMapListSerializer.class)
  @JsonDeserialize(keyUsing = ExtensionKeyDeserializer.class, contentUsing = TermMapListDeserializer.class)
  public Map<Extension, List<Map<Term, String>>> getExtensions() {
    return extensions;
  }

  public void setExtensions(Map<Extension, List<Map<Term, String>>> extensions) {
    this.extensions = extensions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerbatimOccurrence that = (VerbatimOccurrence) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(datasetKey, that.datasetKey) &&
      Objects.equals(publishingOrgKey, that.publishingOrgKey) &&
      Objects.equals(networkKeys, that.networkKeys) &&
      Objects.equals(installationKey, that.installationKey) &&
      publishingCountry == that.publishingCountry &&
      protocol == that.protocol &&
      Objects.equals(lastCrawled, that.lastCrawled) &&
      Objects.equals(lastParsed, that.lastParsed) &&
      Objects.equals(crawlId, that.crawlId) &&
      Objects.equals(projectId, that.projectId) &&
      Objects.equals(programmeAcronym, that.programmeAcronym) &&
      Objects.equals(verbatimFields, that.verbatimFields) &&
      Objects.equals(extensions, that.extensions);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(key, datasetKey, publishingOrgKey, networkKeys, installationKey, publishingCountry,
        protocol, lastCrawled, lastParsed, crawlId, projectId, programmeAcronym, verbatimFields,
        extensions);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", VerbatimOccurrence.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("datasetKey=" + datasetKey)
      .add("publishingOrgKey=" + publishingOrgKey)
      .add("networkKeys=" + networkKeys)
      .add("installationKey=" + installationKey)
      .add("publishingCountry=" + publishingCountry)
      .add("protocol=" + protocol)
      .add("lastCrawled=" + lastCrawled)
      .add("lastParsed=" + lastParsed)
      .add("crawlId=" + crawlId)
      .add("projectId='" + projectId + "'")
      .add("programmeAcronym='" + programmeAcronym + "'")
      .add("extensions=" + extensions)
      .toString();
  }

  /**
   * This private method is only for deserialization via jackson and not exposed anywhere else!
   */
  @JsonAnySetter
  private void addJsonVerbatimField(String key, String value) {
    if(StringUtils.isNotEmpty(value)) {
      Term t = TermFactory.instance().findTerm(key);
      verbatimFields.put(t, value);
    }
  }

  /**
   * This private method is only for serialization via jackson and not exposed anywhere else!
   * It maps the verbatimField terms into properties with their full qualified name.
   */
  @JsonAnyGetter
  private Map<String, String> jsonVerbatimFields() { // note: for 1.6.0 MUST use non-getter name; otherwise doesn't matter
    Map<String, String> extendedProps = new HashMap<>();
    for (Map.Entry<Term, String> prop : verbatimFields.entrySet()) {
      extendedProps.put(prop.getKey().qualifiedName(), prop.getValue());
    }
    return extendedProps;
  }
}
