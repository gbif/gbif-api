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

  @NotNull
  /**
   * The date this record was last crawled/harvested from the endpoint.
   */
  public Date getLastCrawled() {
    return new Date(lastCrawled.getTime());
  }

  public void setLastCrawled(Date lastCrawled) {
    checkNotNull(lastCrawled, "lastCrawled can't be null");
    this.lastCrawled = new Date(lastCrawled.getTime());
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
  public int hashCode() {
    return Objects.hashCode(key, datasetKey, publishingOrgKey, publishingCountry, protocol, lastCrawled,
                            fields, identifiers, media, facts, relations);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof VerbatimOccurrence)) {
      return false;
    }
    VerbatimOccurrence that = (VerbatimOccurrence) obj;
    return Objects.equal(this.key, that.key)
           && Objects.equal(this.datasetKey, that.datasetKey)
           && Objects.equal(this.publishingOrgKey, that.publishingOrgKey)
           && Objects.equal(this.publishingCountry, that.publishingCountry)
           && Objects.equal(this.protocol, that.protocol)
           && Objects.equal(this.lastCrawled, that.lastCrawled)
           && Objects.equal(this.fields, that.fields)
           && Objects.equal(this.identifiers, that.identifiers)
           && Objects.equal(this.media, that.media)
           && Objects.equal(this.facts, that.facts)
           && Objects.equal(this.relations, that.relations);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("datasetKey", datasetKey)
      .add("protocol", protocol)
      .add("lastCrawled", lastCrawled)
      .add("fields", fields.size())
      .toString();
  }
}
