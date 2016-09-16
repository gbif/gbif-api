/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.checklistbank;

import org.gbif.api.model.Constants;
import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.model.common.LinneanClassificationKeys;
import org.gbif.api.util.ClassificationUtils;
import org.gbif.api.vocabulary.NameType;
import org.gbif.api.vocabulary.NameUsageIssue;
import org.gbif.api.vocabulary.NomenclaturalStatus;
import org.gbif.api.vocabulary.Origin;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;

import java.net.URI;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A usage of a <em>scientific name</em> according to one particular Checklist including the GBIF Taxonomic Backbone,
 * the nub. It is shown as species in the portal and API.
 *
 * All nub usages will have an empty nubKey. Backbone usages can be detected by either the NameUsage.isNub() method or by manually comparing the datasetKey
 * with the fixed backbone datasetKey, see Constants.NUB_DATASET_KEY.
 *
 * Name usages from other checklists with names that also exist in the nub will have a nubKey that points to the related usage in the nub.
 * To store not eagerly loaded subresources such as vernacular names or synonyms with a usage please use
 * the {@link NameUsageContainer} class.
 */
public class NameUsage implements LinneanClassification, LinneanClassificationKeys {

  private Integer key;
  private Integer nubKey;
  private Integer nameKey;
  private String taxonID;
  private Integer sourceTaxonKey;
  // for LinneanClassification
  private String kingdom;
  private String phylum;
  @JsonProperty("class")
  private String clazz;
  private String order;
  private String family;
  private String genus;
  private String subgenus;
  private String species;
  // for LinneanClassificationKeys
  private Integer kingdomKey;
  private Integer phylumKey;
  private Integer classKey;
  private Integer orderKey;
  private Integer familyKey;
  private Integer genusKey;
  private Integer subgenusKey;
  private Integer speciesKey;

  private UUID datasetKey;
  private UUID constituentKey;
  private Integer parentKey;
  private String parent;
  private Integer proParteKey;
  private Integer acceptedKey;
  private String accepted;
  private Integer basionymKey;
  private String basionym;

  private String scientificName;
  private String canonicalName;
  private String vernacularName;
  private String authorship;
  private NameType nameType;
  private Rank rank;
  private Origin origin;
  private TaxonomicStatus taxonomicStatus;
  private Set<NomenclaturalStatus> nomenclaturalStatus = Sets.newHashSet();
  private String remarks;
  private String publishedIn;
  private String accordingTo;

  private int numDescendants;
  private URI references;

  private Date modified;
  private Date deleted;
  private Date lastCrawled;
  private Date lastInterpreted;
  private Set<NameUsageIssue> issues = EnumSet.noneOf(NameUsageIssue.class);

  /**
   * @return the name key for retrieving a parsed name object
   */
  public Integer getNameKey() {
    return nameKey;
  }

  public void setNameKey(Integer nameKey) {
    this.nameKey = nameKey;
  }

  /**
   * For backbone taxa the source taxon key refers to the original name usage that was used during nub building
   * and is the primary reason that this taxon exists in the backbone.
   * <br/>
   * All nub usages are build from several underlying checklist usages,
   * but these are sorted by priority and the usage key for the highest priority one becomes the sourceTaxonKey
   * for a nub usage.
   * <br/>
   * Some nub usages do not have any source record altogether.
   * For example if there is a subspecies found, but no matching parent species,
   * the missing species will be created nevertheless and has no primary source.
   *
   * @return The key of the name usage this backbone taxon is derived from.
   */
  @Nullable
  public Integer getSourceTaxonKey() {
    return sourceTaxonKey;
  }

  public void setSourceTaxonKey(Integer sourceTaxonKey) {
    this.sourceTaxonKey = sourceTaxonKey;
  }

  /**
   * @return the scientific name of the accepted name
   */
  public String getAccepted() {
    return accepted;
  }

  /**
   * Sets the scientific name of the basionym, i.e. original name usage.
   */
  public void setAccepted(String accepted) {
    this.accepted = accepted;
  }

  /**
   * @return the name usage key of the accepted name
   */
  public Integer getAcceptedKey() {
    return acceptedKey;
  }

  /**
   * Sets the usage key for the accepted name.
   */
  public void setAcceptedKey(Integer acceptedKey) {
    this.acceptedKey = acceptedKey;
  }

  /**
   * The taxon concept reference usually a reference to some publication or author + year.
   * The dwc:taxonAccordingTo reference is usually appended to the scientific name to further qualify the concept
   * with "sensu" or "sec." being used for concatenation. E.g. "Acer nigrum sec. Gleason Cronquist 1991".
   * In the case of backbone taxa this refers to the primary checklist the name was found in.
   *
   * @return the taxon concept reference
   */
  @Nullable
  public String getAccordingTo() {
    return accordingTo;
  }

  /**
   * @param accordingTo the accordingTo to set
   */
  public void setAccordingTo(String accordingTo) {
    this.accordingTo = accordingTo;
  }

  /**
   * Returns the authorship information for the scientific name.
   *
   * @return the authorship
   */
  @Nullable
  public String getAuthorship() {
    return authorship;
  }

  /**
   * @param authorship the authorship to set
   */
  public void setAuthorship(String authorship) {
    this.authorship = authorship;
  }

  /**
   * @return the scientific name of the basionym
   */
  public String getBasionym() {
    return basionym;
  }

  /**
   * sets the basionym name.
   */
  public void setBasionym(String basionym) {
    this.basionym = basionym;
  }

  /**
   * Returns the earlier name (basionym) for this scientific name. Return null if the basionym does not exists.
   *
   * @return the basionymKey
   */
  @Nullable
  public Integer getBasionymKey() {
    return basionymKey;
  }

  /**
   * @param basionymKey the basionymKey to set
   */
  public void setBasionymKey(Integer basionymKey) {
    this.basionymKey = basionymKey;
  }

  /**
   * @return the canonicalName
   */
  @Nullable
  public String getCanonicalName() {
    return canonicalName;
  }

  /**
   * @param canonicalName the canonicalName to set
   */
  public void setCanonicalName(String canonicalName) {
    this.canonicalName = canonicalName;
  }

  /**
   * Returns the key of the checklist that "hosts" this name usage.
   *
   * @return the datasetKey
   */
  @NotNull
  public UUID getDatasetKey() {
    return datasetKey;
  }

  /**
   * @param datasetKey the datasetKey to set
   */
  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  /**
   * Return the key that uniquely identifies this name usage.
   *
   * @return the key
   */
  @NotNull
  public Integer getKey() {
    return key;
  }

  /**
   * @param key the key to set
   */
  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * @return the type of name string classified by CLB.
   */
  public NameType getNameType() {
    return nameType;
  }

  /**
   * @param nameType the type of name string
   */
  public void setNameType(NameType nameType) {
    this.nameType = nameType;
  }

  /**
   * The status related to the conformance to the relevant rules of nomenclature.
   * <blockquote>
   * <p>
   * <i>Example:</i> "invalid", "misapplied", "homotypic synonym", "accepted"
   * </p>
   * </blockquote>
   *
   * @return the set of known nomenclaturalStatus values
   *
   * @see <a href="http://rs.gbif.org/vocabulary/gbif/nomenclatural_status.xml">Nomenclatural Status GBIF
   *      Vocabulary</a>
   */
  public Set<NomenclaturalStatus> getNomenclaturalStatus() {
    return nomenclaturalStatus;
  }

  /**
   * @param nomenclaturalStatus the nomenclaturalStatus to set
   */
  public void setNomenclaturalStatus(Set<NomenclaturalStatus> nomenclaturalStatus) {
    this.nomenclaturalStatus = nomenclaturalStatus;
  }

  /**
   * @return the taxon key of the matching backbone name usage
   */
  @Nullable
  public Integer getNubKey() {
    return nubKey;
  }

  /**
   * @param nubKey the nubKey to set
   */
  public void setNubKey(Integer nubKey) {
    this.nubKey = nubKey;
  }

  /**
   * The number of all accepted taxonomic elements under this usage.
   *
   * @return the number of descendants
   */
  public int getNumDescendants() {
    return numDescendants;
  }

  /**
   * @param numDescendants the n umber of descendants to set
   */
  public void setNumDescendants(int numDescendants) {
    this.numDescendants = numDescendants;
  }

  /**
   * The origin of this name usage record, i.e. the reason why it exists.
   * In most cases this is because the record existed explicitly in the checklist sources, but
   * some usages are created de novo because they exist implicitly in the data.
   *
   * @return the name usage origin
   *
   * @see Origin
   */
  @NotNull
  public Origin getOrigin() {
    return origin;
  }

  /**
   * @param origin the origin to set
   */
  public void setOrigin(Origin origin) {
    this.origin = origin;
  }

  /**
   * The scientific name of the parent.
   *
   * @return the parent name
   */
  public String getParent() {
    return parent;
  }

  /**
   * @param parent the parent name to set
   */
  public void setParent(String parent) {
    this.parent = parent;
  }

  /**
   * Returns the immediate parent. If this usage if for the highest taxonomic level, return null.
   *
   * @return the parentKey
   */
  @Nullable
  public Integer getParentKey() {
    return parentKey;
  }

  /**
   * @param parentKey the parentKey to set
   */
  public void setParentKey(Integer parentKey) {
    this.parentKey = parentKey;
  }

  /**
   * Pro parte synonyms, i.e. a synonym with multiple accepted names, are grouped by a single, primary name usage key.
   *
   * @return the primary name usage key for a prop parte synonym or null
   */
  public Integer getProParteKey() {
    return proParteKey;
  }

  /**
   * Sets the pro parte usage key.
   */
  public void setProParteKey(Integer proParteKey) {
    this.proParteKey = proParteKey;
  }

  /**
   * Original publication for this name usage.
   *
   * @return the publishedIn
   */
  @Nullable
  public String getPublishedIn() {
    return publishedIn;
  }

  /**
   * @param publishedIn the publishedIn to set
   */
  public void setPublishedIn(String publishedIn) {
    this.publishedIn = publishedIn;
  }

  /**
   * Returns the rank for this usage.
   * <blockquote>
   * <p>
   * <i>Example:</i> "Kingdom", "Genus"
   * </p>
   * </blockquote>
   *
   * @return the rank
   */
  @Nullable
  public Rank getRank() {
    return rank;
  }

  /**
   * @param rank the rank to set
   */
  public void setRank(Rank rank) {
    this.rank = rank;
  }

  /**
   * The taxon name (with date and authorship information if applicable).
   * <blockquote>
   * <p>
   * <i>Example:</i> "Coleoptera" (order), "Vespertilionidae" (family), "Manis" (genus), "Ctenomys sociabilis" (genus +
   * specificEpithet), "Ambystoma tigrinum diaboli" (genus + specificEpithet + infraspecificEpithet),
   * "Quercus agrifolia var. oxyadenia (Torr.)"
   * </p>
   * </blockquote>
   *
   * @return the scientificName
   */
  @NotNull
  public String getScientificName() {
    return scientificName;
  }

  /**
   * @param scientificName the scientificName to set
   */
  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * Return the optional sub dataset key for this usage.
   *
   * @return the subDatasetKey or null
   */
  @Nullable
  public UUID getConstituentKey() {
    return constituentKey;
  }

  /**
   * @param constituentKey to set
   */
  public void setConstituentKey(UUID constituentKey) {
    this.constituentKey = constituentKey;
  }

  /**
   * A common or vernacular name for this usage.
   * <blockquote>
   * <p>
   * <i>Example:</i> Andean Condor", "Condor Andino", "American Eagle", "Gänsegeier".
   * </p>
   * </blockquote>
   *
   * @return the vernacularName
   */
  @Nullable
  public String getVernacularName() {
    return vernacularName;
  }

  /**
   * @param vernacularName the vernacularName to set
   */
  public void setVernacularName(String vernacularName) {
    this.vernacularName = vernacularName;
  }

  /**
   * @param taxonomicStatus the taxonomicStatus to set
   */
  public void setTaxonomicStatus(TaxonomicStatus taxonomicStatus) {
    this.taxonomicStatus = taxonomicStatus;
  }

  @Override
  public String getKingdom() {
    return kingdom;
  }

  @Override
  public void setKingdom(String kingdom) {
    this.kingdom = kingdom;
  }

  @Override
  public String getPhylum() {
    return phylum;
  }

  @Override
  public void setPhylum(String phylum) {
    this.phylum = phylum;
  }

  @Override
  public String getClazz() {
    return clazz;
  }

  @Override
  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public String getOrder() {
    return order;
  }

  @Override
  public void setOrder(String order) {
    this.order = order;
  }

  @Override
  public String getFamily() {
    return family;
  }

  @Override
  public void setFamily(String family) {
    this.family = family;
  }

  @Override
  public String getGenus() {
    return genus;
  }

  @Override
  public void setGenus(String genus) {
    this.genus = genus;
  }

  @Override
  public String getSubgenus() {
    return subgenus;
  }

  @Override
  public void setSubgenus(String subgenus) {
    this.subgenus = subgenus;
  }

  @Override
  public String getSpecies() {
    return species;
  }

  @Override
  public void setSpecies(String species) {
    this.species = species;
  }

  @Override
  public Integer getKingdomKey() {
    return kingdomKey;
  }

  @Override
  public void setKingdomKey(Integer kingdomKey) {
    this.kingdomKey = kingdomKey;
  }

  @Override
  public Integer getPhylumKey() {
    return phylumKey;
  }

  @Override
  public void setPhylumKey(Integer phylumKey) {
    this.phylumKey = phylumKey;
  }

  @Override
  public Integer getClassKey() {
    return classKey;
  }

  @Override
  public void setClassKey(Integer classKey) {
    this.classKey = classKey;
  }

  @Override
  public Integer getOrderKey() {
    return orderKey;
  }

  @Override
  public void setOrderKey(Integer orderKey) {
    this.orderKey = orderKey;
  }

  @Override
  public Integer getFamilyKey() {
    return familyKey;
  }

  @Override
  public void setFamilyKey(Integer familyKey) {
    this.familyKey = familyKey;
  }

  @Override
  public Integer getGenusKey() {
    return genusKey;
  }

  @Override
  public void setGenusKey(Integer genusKey) {
    this.genusKey = genusKey;
  }

  @Override
  public Integer getSubgenusKey() {
    return subgenusKey;
  }

  @Override
  public void setSubgenusKey(Integer subgenusKey) {
    this.subgenusKey = subgenusKey;
  }

  @Override
  public Integer getSpeciesKey() {
    return speciesKey;
  }

  @Override
  public void setSpeciesKey(Integer speciesKey) {
    this.speciesKey = speciesKey;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * @return the canonicalName or scientific name in case its null
   */
  @Nullable
  @JsonIgnore
  public String getCanonicalOrScientificName() {
    return canonicalName == null ? scientificName : canonicalName;
  }

  @Nullable
  /**
   * A URI link or reference to the source of this record, the records "homepage".
   * <blockquote>
   * <p>
   * <i>Example:</i> http://www.catalogueoflife.org/annual-checklist/show_species_details.php?record_id=6197868
   * </p>
   * </blockquote>
   *
   * @return the link
   */
  public URI getReferences() {
    return references;
  }

  public void setReferences(URI references) {
    this.references = references;
  }

  @Override
  public String getHigherRank(Rank rank) {
    return ClassificationUtils.getHigherRank(this, rank);
  }

  @Override
  public Integer getHigherRankKey(Rank rank) {
    return ClassificationUtils.getHigherRankKey(this, rank);
  }

  /**
   * An ordered map with entries for all higher Linnean ranks down to the actual direct parent of this usage.
   * The map starts with the highest rank, e.g. the kingdom and maps the name usage key to its canonical name.
   * The name usage itself is never included, even though a higher rank might point to the usage itself.
   *
   * @return map of higher ranks
   */
  @NotNull
  @JsonIgnore
  public LinkedHashMap<Integer, String> getHigherClassificationMap() {
    return ClassificationUtils.getHigherClassificationMap(this, key, parentKey, parent);
  }

  /**
   * The original taxonID of the name usage as found in the source.
   * For backbone taxa and name usages with an origin different to SOURCE this is null.
   */
  @Nullable
  public String getTaxonID() {
    return taxonID;
  }

  /**
   * The taxonomic status of the name usage.
   * Can be null, but for all synonyms with an accepted name usage it is guaranteed to exist.
   *
   * @return the taxonomicStatus, can be null
   */
  @Nullable
  public TaxonomicStatus getTaxonomicStatus() {
    return taxonomicStatus;
  }

  @Nullable
  /**
   * The interpreted dc:modified from the verbatim source data.
   * Ideally indicating when a record was last modified in the source.
   */
  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Nullable
  /**
   * The date this record was deleted.
   * Logical deletions only occur for backbone usages!
   */
  public Date getDeleted() {
    return deleted;
  }

  public void setDeleted(Date deleted) {
    this.deleted = deleted;
  }

  @Nullable
  /**
   * The date this record was last crawled during clb indexing.
   */
  public Date getLastCrawled() {
    return lastCrawled;
  }

  public void setLastCrawled(Date lastCrawled) {
    this.lastCrawled = lastCrawled;
  }

  @Nullable
  /**
   * The date this record was last interpreted during indexing.
   * This includes matching to the backbone.
   */
  public Date getLastInterpreted() {
    return lastInterpreted;
  }

  public void setLastInterpreted(Date lastInterpreted) {
    this.lastInterpreted = lastInterpreted;
  }

  @NotNull
  public Set<NameUsageIssue> getIssues() {
    return issues;
  }

  public void setIssues(Set<NameUsageIssue> issues) {
    Preconditions.checkNotNull(issues, "Issues cannot be null");
    this.issues = Sets.newEnumSet(issues, NameUsageIssue.class);
  }

  public void addIssue(NameUsageIssue issue) {
    Preconditions.checkNotNull(issue, "Issue needs to be specified");
    this.issues.add(issue);
  }

  @JsonIgnore
  public boolean isNub() {
    return datasetKey.equals(Constants.NUB_DATASET_KEY);
  }

  /**
   * True for pro parte synonyms with multiple accepted usages.
   *
   * @return true if proParte, false otherwise
   */
  @JsonIgnore
  public boolean isProParte() {
    return proParteKey != null;
  }

  /**
   * Convenience method using the taxonomicStatus field.
   * @return true if its a synonym
   */
  public boolean isSynonym() {
    return taxonomicStatus != null && taxonomicStatus.isSynonym();
  }

  public void setTaxonID(String taxonID) {
    this.taxonID = taxonID;
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(
        key,
        nameKey,
        kingdom,
        phylum,
        clazz,
        order,
        family,
        genus,
        subgenus,
        species,
        kingdomKey,
        phylumKey,
        classKey,
        orderKey,
        familyKey,
        genusKey,
        subgenusKey,
        speciesKey,
        datasetKey, constituentKey, nubKey,
        parentKey,
        parent,
        proParteKey,
        acceptedKey,
        accepted,
        basionymKey,
        basionym,
        scientificName,
        canonicalName,
        vernacularName,
        authorship,
        nameType,
        taxonomicStatus,
        nomenclaturalStatus,
        rank,
        publishedIn,
        accordingTo,
        numDescendants,
        origin,
        remarks,
        references,
        taxonID,
        modified,
        deleted,
        lastCrawled,
        lastInterpreted,
        issues);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NameUsage other = (NameUsage) obj;
    return Objects.equal(this.key, other.key)
           && Objects.equal(this.nameKey, other.nameKey)
           && Objects.equal(this.kingdom, other.kingdom)
           && Objects.equal(this.phylum, other.phylum)
           && Objects.equal(this.clazz, other.clazz)
           && Objects.equal(this.order, other.order)
           && Objects.equal(this.family, other.family)
           && Objects.equal(this.genus, other.genus)
           && Objects.equal(this.subgenus, other.subgenus)
           && Objects.equal(this.species, other.species)
           && Objects.equal(this.kingdomKey, other.kingdomKey)
           && Objects.equal(this.phylumKey, other.phylumKey)
           && Objects.equal(this.classKey, other.classKey)
           && Objects.equal(this.orderKey, other.orderKey)
           && Objects.equal(this.familyKey, other.familyKey)
           && Objects.equal(this.genusKey, other.genusKey)
           && Objects.equal(this.subgenusKey, other.subgenusKey)
           && Objects.equal(this.speciesKey, other.speciesKey)
           && Objects.equal(this.datasetKey, other.datasetKey)
           && Objects.equal(this.constituentKey, other.constituentKey)
           && Objects.equal(this.nubKey, other.nubKey)
           && Objects.equal(this.parentKey, other.parentKey)
           && Objects.equal(this.parent, other.parent)
           && Objects.equal(this.proParteKey, other.proParteKey)
           && Objects.equal(this.acceptedKey, other.acceptedKey)
           && Objects.equal(this.accepted, other.accepted)
           && Objects.equal(this.basionymKey, other.basionymKey)
           && Objects.equal(this.basionym, other.basionym)
           && Objects.equal(this.scientificName, other.scientificName)
           && Objects.equal(this.canonicalName, other.canonicalName)
           && Objects.equal(this.vernacularName, other.vernacularName)
           && Objects.equal(this.authorship, other.authorship)
           && Objects.equal(this.nameType, other.nameType)
           && Objects.equal(this.taxonomicStatus, other.taxonomicStatus)
           && Objects.equal(this.nomenclaturalStatus, other.nomenclaturalStatus)
           && Objects.equal(this.rank, other.rank)
           && Objects.equal(this.publishedIn, other.publishedIn)
           && Objects.equal(this.accordingTo, other.accordingTo)
           && Objects.equal(this.numDescendants, other.numDescendants)
           && Objects.equal(this.origin, other.origin)
           && Objects.equal(this.references, other.references)
           && Objects.equal(this.taxonID, other.taxonID)
           && Objects.equal(this.remarks, other.remarks)
           && Objects.equal(this.modified, other.modified)
           && Objects.equal(this.deleted, other.deleted)
           && Objects.equal(this.lastCrawled, other.lastCrawled)
           && Objects.equal(this.lastInterpreted, other.lastInterpreted)
           && Objects.equal(this.issues, other.issues);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("nameKey", nameKey)
      .add("kingdom", kingdom)
      .add("phylum", phylum)
      .add("clazz", clazz)
      .add("order", order)
      .add("family", family)
      .add("genus", genus)
      .add("subgenus", subgenus)
      .add("species", species)
      .add("kingdomKey", kingdomKey)
      .add("phylumKey", phylumKey)
      .add("classKey", classKey)
      .add("orderKey", orderKey)
      .add("familyKey", familyKey)
      .add("genusKey", genusKey)
      .add("subgenusKey", subgenusKey)
      .add("speciesKey", speciesKey)
      .add("datasetKey", datasetKey)
      .add("subDatasetKey", constituentKey)
      .add("nubKey", nubKey)
      .add("parentKey", parentKey)
      .add("parent", parent)
      .add("proParteKey", proParteKey)
      .add("acceptedKey", acceptedKey)
      .add("accepted", accepted)
      .add("basionymKey", basionymKey)
      .add("basionym", basionym)
      .add("scientificName", scientificName)
      .add("canonicalName", canonicalName)
      .add("vernacularName", vernacularName)
      .add("authorship", authorship)
      .add("nameType", nameType)
      .add("taxonomicStatus", taxonomicStatus)
      .add("nomenclaturalStatus", nomenclaturalStatus)
      .add("rank", rank)
      .add("publishedIn", publishedIn)
      .add("accordingTo", accordingTo)
      .add("numDescendants", numDescendants)
      .add("origin", origin)
      .add("remarks", remarks)
      .add("references", references)
      .add("taxonID", taxonID)
      .add("modified", modified)
      .add("deleted", deleted)
      .add("lastCrawled", lastCrawled)
      .add("lastInterpreted", lastInterpreted)
      .add("issues", issues)
      .toString();
  }
}
