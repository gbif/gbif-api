/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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

import org.gbif.api.model.common.Identifier;
import org.gbif.api.model.common.Image;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.jackson.TermKeyDeserializer;
import org.gbif.dwc.terms.jackson.TermKeySerializer;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An extended map holding all core terms of an occurrence record.
 * Major extensions that we index are also supported, i.e. media, identifiers and measurements or facts.
 */
public class VerbatimOccurrence {

  private Integer key;
  private UUID datasetKey;
  private UUID publishingOrgKey;
  private Country publishingCountry;
  private EndpointType protocol;
  private Date lastCrawled;
  private Date lastParsed;

  // the verbatim fields for the occurrence
  private Map<Term, String> fields = Maps.newHashMap();
  // indexed occurrence extensions
  private List<Identifier> identifiers = Lists.newArrayList();
  private List<Image> media = Lists.newArrayList();
  private List<FactOrMeasurment> facts = Lists.newArrayList();
  private List<OccurrenceRelation> relations = Lists.newArrayList();

  @Nullable
  /**
   * Get the value of a specific field (Term).
   */
  public String getField(Term term) {
    checkNotNull(term, "term can't be null");
    return fields.get(term);
  }

  /**
   * @return true if a term has content
   */
  public boolean hasField(Term term) {
    checkNotNull(term, "term can't be null");
    return !Strings.isNullOrEmpty(fields.get(term));
  }

  /**
   * For setting a specific field without having to replace the entire fields Map.
   *
   * @param term the field to set
   * @param fieldValue the field's value
   */
  public void setField(Term term, @Nullable String fieldValue) {
    checkNotNull(term, "term can't be null");
    fields.put(term, fieldValue);
  }

  @NotNull
  /**
   * The GBIF assigned, persistent key to the occurrence record.
   * OccurrenceID itself is kept in the verbatim fields map.
   */
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
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

  @Nullable
  /**
   * The country of the organization that publishes the dataset to which the occurrence belongs.
   */
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

  @Nullable
  /**
   * The date this record was last crawled/harvested from the endpoint.
   */
  public Date getLastCrawled() {
    return lastCrawled == null ? null : new Date(lastCrawled.getTime());
  }

  public void setLastCrawled(@Nullable Date lastCrawled) {
    this.lastCrawled = lastCrawled == null ? null : new Date(lastCrawled.getTime());
  }

  @Nullable
  /**
   * The date this record was last parsed from raw xml/json into verbatim fields.
   */
  public Date getLastParsed() {
    return lastParsed;
  }

  public void setLastParsed(@Nullable Date lastParsed) {
    this.lastParsed = lastParsed == null ? null : new Date(lastParsed.getTime());
  }

  @NotNull
  /**
   * A map holding all verbatim core terms.
   */
  @JsonSerialize(keyUsing = TermKeySerializer.class)
  @JsonDeserialize(keyUsing = TermKeyDeserializer.class)
  public Map<Term, String> getFields() {
    return fields;
  }

  public void setFields(Map<Term, String> fields) {
    this.fields = fields;
  }

  @NotNull
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  @NotNull
  public List<Image> getMedia() {
    return media;
  }

  public void setMedia(List<Image> media) {
    this.media = media;
  }

  @NotNull
  public List<FactOrMeasurment> getFacts() {
    return facts;
  }

  public void setFacts(List<FactOrMeasurment> facts) {
    this.facts = facts;
  }

  @NotNull
  public List<OccurrenceRelation> getRelations() {
    return relations;
  }

  public void setRelations(List<OccurrenceRelation> relations) {
    this.relations = relations;
  }



  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("lastParsed", lastParsed).add("key", key).add("datasetKey", datasetKey)
      .add("publishingOrgKey", publishingOrgKey).add("publishingCountry", publishingCountry).add("protocol", protocol)
      .add("lastCrawled", lastCrawled).toString();
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(key, datasetKey, publishingOrgKey, publishingCountry, protocol, lastCrawled, lastParsed, fields,
        identifiers, media, facts, relations);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final VerbatimOccurrence other = (VerbatimOccurrence) obj;
    return Objects.equal(this.key, other.key)
           && Objects.equal(this.datasetKey, other.datasetKey)
           && Objects.equal(this.publishingOrgKey, other.publishingOrgKey)
           && Objects.equal(this.publishingCountry, other.publishingCountry)
           && Objects.equal(this.protocol, other.protocol)
           && Objects.equal(this.lastCrawled, other.lastCrawled)
           && Objects.equal(this.lastParsed, other.lastParsed)
           && Objects.equal(this.fields, other.fields)
           && Objects.equal(this.identifiers, other.identifiers)
           && Objects.equal(this.media, other.media)
           && Objects.equal(this.facts, other.facts)
           && Objects.equal(this.relations, other.relations);
  }
}
