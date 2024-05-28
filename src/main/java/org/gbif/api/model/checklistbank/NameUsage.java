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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A usage of a <em>scientific name</em> according to one particular Checklist including the GBIF Taxonomic Backbone,
 * the NUB. It is shown as species in the portal and API.
 * <br>
 * Backbone (NUB) usages have key==nubKey. Backbone usages can also be detected by either the NameUsage.isNub() method
 * or by manually comparing the datasetKey with the fixed backbone datasetKey, see Constants.NUB_DATASET_KEY.
 * <br>
 * Name usages from other checklists with names that also exist in the backbone will have a nubKey that points to the related usage in the NUB.
 * <br>
 * To store not eagerly loaded subresources such as vernacular names or synonyms with a usage please use
 * the {@link NameUsageContainer} class.
 */
@SuppressWarnings("unused")
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
  private Set<NomenclaturalStatus> nomenclaturalStatus = new HashSet<>();
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

  public NameUsage() {}

  public NameUsage(NameUsage other) {
    this.key = other.key;
    this.nubKey = other.nubKey;
    this.nameKey = other.nameKey;
    this.taxonID = other.taxonID;
    this.sourceTaxonKey = other.sourceTaxonKey;
    this.kingdom = other.kingdom;
    this.phylum = other.phylum;
    this.clazz = other.clazz;
    this.order = other.order;
    this.family = other.family;
    this.genus = other.genus;
    this.subgenus = other.subgenus;
    this.species = other.species;
    this.kingdomKey = other.kingdomKey;
    this.phylumKey = other.phylumKey;
    this.classKey = other.classKey;
    this.orderKey = other.orderKey;
    this.familyKey = other.familyKey;
    this.genusKey = other.genusKey;
    this.subgenusKey = other.subgenusKey;
    this.speciesKey = other.speciesKey;
    this.datasetKey = other.datasetKey;
    this.constituentKey = other.constituentKey;
    this.parentKey = other.parentKey;
    this.parent = other.parent;
    this.proParteKey = other.proParteKey;
    this.acceptedKey = other.acceptedKey;
    this.accepted = other.accepted;
    this.basionymKey = other.basionymKey;
    this.basionym = other.basionym;
    this.scientificName = other.scientificName;
    this.canonicalName = other.canonicalName;
    this.vernacularName = other.vernacularName;
    this.authorship = other.authorship;
    this.nameType = other.nameType;
    this.rank = other.rank;
    this.origin = other.origin;
    this.taxonomicStatus = other.taxonomicStatus;
    this.nomenclaturalStatus = other.nomenclaturalStatus;
    this.remarks = other.remarks;
    this.publishedIn = other.publishedIn;
    this.accordingTo = other.accordingTo;
    this.numDescendants = other.numDescendants;
    this.references = other.references;
    this.modified = other.modified;
    this.deleted = other.deleted;
    this.lastCrawled = other.lastCrawled;
    this.lastInterpreted = other.lastInterpreted;
    this.issues = other.issues;
  }

  /**
   * @return the name key for retrieving a parsed name object
   */
  @Schema(description = "The key for retrieving a parsed name object.\n\n" +
    "*You are more likely to need the `key` or `nubKey` properties*")
  public Integer getNameKey() {
    return nameKey;
  }

  public void setNameKey(Integer nameKey) {
    this.nameKey = nameKey;
  }

  /**
   * For backbone taxa the source taxon key refers to the original name usage that was used during backbone building
   * and is the primary reason that this taxon exists in the backbone.
   * <br/>
   * All backbone name usages are built from several underlying checklist usages,
   * but these are sorted by priority and the usage key for the highest priority one becomes the sourceTaxonKey
   * for a backbone usage.
   * <br/>
   * Some backbone usages do not have any source record altogether.
   * For example if there is a subspecies found, but no matching parent species,
   * the missing species will be created nevertheless and has no primary source.
   *
   * @return The key of the name usage this backbone taxon is derived from.
   */
  @Nullable
  @Schema(description = "The key of the name usage from which this backbone taxon derives.\n" +
    "\n" +
    "For backbone taxa the source taxon key refers to the original name usage that was used during " +
    "backbone building and is the primary reason that this taxon exists in the backbone.\n" +
    "\n" +
    "All backbone name usages are built from several underlying checklist usages, but these are sorted by priority " +
    "and the usage key for the highest priority one becomes the sourceTaxonKey for a backbone usage.\n" +
    "\n" +
    "Some backbone usages do not have any source record at all; for example if there is a subspecies found, but no matching " +
    "parent species, the missing species will be created nevertheless and has no primary source.")
  public Integer getSourceTaxonKey() {
    return sourceTaxonKey;
  }

  public void setSourceTaxonKey(Integer sourceTaxonKey) {
    this.sourceTaxonKey = sourceTaxonKey;
  }

  /**
   * @return the scientific name of the accepted name
   */
  @Schema(description = "The scientific name of the accepted name.")
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
  @Schema(description = "The name usage key of the accepted name.")
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
   * The taxon concept reference is usually a reference to some publication or author and year.
   * <br>
   * The dwc:taxonAccordingTo reference is usually appended to the scientific name to further qualify the concept
   * with "sensu" or "sec." being used for concatenation. E.g. "Acer nigrum sec. Gleason Cronquist 1991".
   * <br>
   * In the case of backbone taxa this refers to the primary checklist the name was found in.
   *
   * @return the taxon concept reference
   */
  @Schema(description = "The taxon concept reference.\n\n" +
    "This is usually a reference to some publication or an author and year.\n\n" +
    "The Darwin Core `taxonAccordingTo` reference is usually appended to the scientific name to further qualify " +
    "the concept with “sensu” or “sec.” being used for concatenation; for example “_Acer nigrum_ sec. Gleason Cronquist 1991”.\n\n" +
    "In the case of backbone taxa, this refers to the primary checklist in which the name was found.")
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
  @Schema(description = "The authorship for the scientific name.")
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
  @Schema(description = "The scientific name of the basionym.")
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
   * Returns the earlier name (basionym) for this scientific name. Return null if the basionym does not exist.
   *
   * @return the basionymKey
   */
  @Schema(description = "The name usage key of the basionym.")
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
  @Schema(description = "The canonical name; the name without authorship or references.")
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
  @Schema(description = "The checklist that “hosts” this name usage.\n\n" +
    "For a backbone name usage, this will be `d7dddbf4-2cf0-4f39-9b2a-bb099caae36c`.")
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
  @Schema(description = "The name usage key that uniquely identifies this name usage.")
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
  @Schema(description = "The type of name string classified by Checklistbank.")
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
  @Schema(description = "The nomenclatural statuses of this name usage.")
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
  @Schema(description = "The taxon key of the matching backbone name usage.\n\n" +
    "If this is equal to `key`, this name usage is a backbone name usage.")
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
  @Schema(description = "A total count of all accepted taxonomic elements under this usage.")
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
  @Schema(description = "The name usage origin.\n\n" +
    "The origin of this name usage record, the reason it exists.\n\n" +
    "In most cases this is because the record existed explicitly in the checklist sources, but some usages are " +
    "created *de novo* because the exist implicitly in the data.")
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
  @Schema(description = "The scientific name of the parent.")
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
  @Schema(description = "The name usage key of the immediate parent.  Null for the highest taxonomic level.")
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
   * @return the primary name usage key for a pro parte synonym or null
   */
  @Schema(description = "The primary name usage key for a *pro parte* synonym.\n\n" +
    "Synonyms with multiple accepted names are grouped by a single, primary name usage key.")
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
  @Schema(description = "Original publication for this name usage.")
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
  @Schema(description = "The rank for this usage.")
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
   * The scientific name (with date and authorship information if applicable).
   * <blockquote>
   * <p>
   * <i>Example:</i> "Coleoptera" (order), "Vespertilionidae" (family), "Manis" (genus), "Ctenomys sociabilis" (genus +
   * specific name), "Ambystoma tigrinum diaboli" (genus + specific name + infraspecific name),
   * "Quercus agrifolia var. oxyadenia (Torr.)"
   * </p>
   * </blockquote>
   *
   * @return the scientificName
   */
  @Schema(description = "The scientific name, with date and authorship information if available.\n\n" +
    "Examples: *Coleoptera* (order), *Vespertilionidae* (family), *Manis* (genus), *Ctenomys sociabilis* " +
    "(genus + specificEpithet), *Ambystoma tigrinum diaboli* (zoology, genus + specific name + infraspecific name) " +
    "*Quercus agrifolia* var. *oxyadenia* (Torr.) (botany, genus + specific epithet + infraspecific epithet + authorship)")
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
  @Schema(description = "The optional sub-dataset key for this usage.")
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
  @Schema(description = "A common or vernacular name for this usage.")
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

  @Schema(description = "Kingdom.")
  @Override
  public String getKingdom() {
    return kingdom;
  }

  @Override
  public void setKingdom(String kingdom) {
    this.kingdom = kingdom;
  }

  @Schema(description = "Phylum.")
  @Override
  public String getPhylum() {
    return phylum;
  }

  @Override
  public void setPhylum(String phylum) {
    this.phylum = phylum;
  }

  @Schema(description = "Class.")
  @Override
  public String getClazz() {
    return clazz;
  }

  @Override
  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  @Schema(description = "Order.")
  @Override
  public String getOrder() {
    return order;
  }

  @Override
  public void setOrder(String order) {
    this.order = order;
  }

  @Schema(description = "Family.")
  @Override
  public String getFamily() {
    return family;
  }

  @Override
  public void setFamily(String family) {
    this.family = family;
  }

  @Schema(description = "Genus.")
  @Override
  public String getGenus() {
    return genus;
  }

  @Override
  public void setGenus(String genus) {
    this.genus = genus;
  }

  @Schema(description = "Subgenus.")
  @Override
  public String getSubgenus() {
    return subgenus;
  }

  @Override
  public void setSubgenus(String subgenus) {
    this.subgenus = subgenus;
  }

  @Schema(description = "Species.")
  @Override
  public String getSpecies() {
    return species;
  }

  @Override
  public void setSpecies(String species) {
    this.species = species;
  }

  @Schema(description = "Name usage key of the kingdom.")
  @Override
  public Integer getKingdomKey() {
    return kingdomKey;
  }

  @Override
  public void setKingdomKey(Integer kingdomKey) {
    this.kingdomKey = kingdomKey;
  }

  @Schema(description = "Name usage key of the phylum.")
  @Override
  public Integer getPhylumKey() {
    return phylumKey;
  }

  @Override
  public void setPhylumKey(Integer phylumKey) {
    this.phylumKey = phylumKey;
  }

  @Schema(description = "Name usage key of the class.")
  @Override
  public Integer getClassKey() {
    return classKey;
  }

  @Override
  public void setClassKey(Integer classKey) {
    this.classKey = classKey;
  }

  @Schema(description = "Name usage key of the order.")
  @Override
  public Integer getOrderKey() {
    return orderKey;
  }

  @Override
  public void setOrderKey(Integer orderKey) {
    this.orderKey = orderKey;
  }

  @Schema(description = "Name usage key of the family.")
  @Override
  public Integer getFamilyKey() {
    return familyKey;
  }

  @Override
  public void setFamilyKey(Integer familyKey) {
    this.familyKey = familyKey;
  }

  @Schema(description = "Name usage key of the genus.")
  @Override
  public Integer getGenusKey() {
    return genusKey;
  }

  @Override
  public void setGenusKey(Integer genusKey) {
    this.genusKey = genusKey;
  }

  @Schema(description = "Name usage key of the subgenus.")
  @Override
  public Integer getSubgenusKey() {
    return subgenusKey;
  }

  @Override
  public void setSubgenusKey(Integer subgenusKey) {
    this.subgenusKey = subgenusKey;
  }

  @Schema(description = "Name usage key of the species.")
  @Override
  public Integer getSpeciesKey() {
    return speciesKey;
  }

  @Override
  public void setSpeciesKey(Integer speciesKey) {
    this.speciesKey = speciesKey;
  }

  @Schema(description = "Remarks on the name usage.")
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

  /**
   * A URI link or reference to the source of this record, the record's "homepage".
   * <blockquote>
   * <p>
   * <i>Example:</i> https://www.catalogueoflife.org/data/taxon/4R5YN
   * </p>
   * </blockquote>
   *
   * @return the link
   */
  @Schema(description = "A URI link or reference to the source of the record, the record's “homepage”.")
  @Nullable
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
  @Schema(description = "The original taxonID of the name usage as found in the source.\n\n" +
    "For backbone taxa and name usages with an origin different to SOURCE this is null.")
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
  @Schema(description = "The taxonomic status of the name usage.\n\n" +
    "Can be null, but for all synonyms with an accepted name usage it is guaranteed to exist.")
  @Nullable
  public TaxonomicStatus getTaxonomicStatus() {
    return taxonomicStatus;
  }

  /**
   * @param taxonomicStatus the taxonomicStatus to set
   */
  public void setTaxonomicStatus(TaxonomicStatus taxonomicStatus) {
    this.taxonomicStatus = taxonomicStatus;
  }

  /**
   * The interpreted dc:modified from the verbatim source data.
   * Ideally indicating when a record was last modified in the source.
   */
  @Schema(description = "The interpreted dc:modified from the verbatim source data, ideally indicating when a record " +
    "was last modified in the source.")
  @Nullable
  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  /**
   * The date this record was deleted.
   * Logical deletions only occur for backbone usages!
   */
  @Schema(description = "The date this record was deleted.\n\n" +
    "*Only backbone name usages are soft-deleted.*")
  @Nullable
  public Date getDeleted() {
    return deleted;
  }

  public void setDeleted(Date deleted) {
    this.deleted = deleted;
  }

  /**
   * The date this record was last crawled during clb indexing.
   */
  @Schema(description = "The date this record was last crawled (downloaded from the source) during Checklistbank indexing.")
  @Nullable
  public Date getLastCrawled() {
    return lastCrawled;
  }

  public void setLastCrawled(Date lastCrawled) {
    this.lastCrawled = lastCrawled;
  }

  /**
   * The date this record was last interpreted during indexing.
   * This includes matching to the backbone.
   */
  @Schema(description = "The date this record was last interpreted during indexing.  This includes matching to the backbone.")
  @Nullable
  public Date getLastInterpreted() {
    return lastInterpreted;
  }

  public void setLastInterpreted(Date lastInterpreted) {
    this.lastInterpreted = lastInterpreted;
  }

  @Schema(description = "Data quality issues found during Checklistbank interpretation.")
  @NotNull
  public Set<NameUsageIssue> getIssues() {
    return issues;
  }

  public void setIssues(Set<NameUsageIssue> issues) {
    Objects.requireNonNull(issues, "Issues cannot be null");
    this.issues = issues.isEmpty() ? EnumSet.noneOf(NameUsageIssue.class) : EnumSet.copyOf(issues);
  }

  public void addIssue(NameUsageIssue issue) {
    Objects.requireNonNull(issue, "Issue needs to be specified");
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
   * @return true if it's a synonym
   */
  @JsonIgnore
  public boolean isSynonym() {
    return taxonomicStatus != null && taxonomicStatus.isSynonym();
  }

  public void setTaxonID(String taxonID) {
    this.taxonID = taxonID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NameUsage nameUsage = (NameUsage) o;
    return numDescendants == nameUsage.numDescendants &&
      Objects.equals(key, nameUsage.key) &&
      Objects.equals(nubKey, nameUsage.nubKey) &&
      Objects.equals(nameKey, nameUsage.nameKey) &&
      Objects.equals(taxonID, nameUsage.taxonID) &&
      Objects.equals(kingdom, nameUsage.kingdom) &&
      Objects.equals(phylum, nameUsage.phylum) &&
      Objects.equals(clazz, nameUsage.clazz) &&
      Objects.equals(order, nameUsage.order) &&
      Objects.equals(family, nameUsage.family) &&
      Objects.equals(genus, nameUsage.genus) &&
      Objects.equals(subgenus, nameUsage.subgenus) &&
      Objects.equals(species, nameUsage.species) &&
      Objects.equals(kingdomKey, nameUsage.kingdomKey) &&
      Objects.equals(phylumKey, nameUsage.phylumKey) &&
      Objects.equals(classKey, nameUsage.classKey) &&
      Objects.equals(orderKey, nameUsage.orderKey) &&
      Objects.equals(familyKey, nameUsage.familyKey) &&
      Objects.equals(genusKey, nameUsage.genusKey) &&
      Objects.equals(subgenusKey, nameUsage.subgenusKey) &&
      Objects.equals(speciesKey, nameUsage.speciesKey) &&
      Objects.equals(datasetKey, nameUsage.datasetKey) &&
      Objects.equals(constituentKey, nameUsage.constituentKey) &&
      Objects.equals(parentKey, nameUsage.parentKey) &&
      Objects.equals(parent, nameUsage.parent) &&
      Objects.equals(proParteKey, nameUsage.proParteKey) &&
      Objects.equals(acceptedKey, nameUsage.acceptedKey) &&
      Objects.equals(accepted, nameUsage.accepted) &&
      Objects.equals(basionymKey, nameUsage.basionymKey) &&
      Objects.equals(basionym, nameUsage.basionym) &&
      Objects.equals(scientificName, nameUsage.scientificName) &&
      Objects.equals(canonicalName, nameUsage.canonicalName) &&
      Objects.equals(vernacularName, nameUsage.vernacularName) &&
      Objects.equals(authorship, nameUsage.authorship) &&
      nameType == nameUsage.nameType &&
      rank == nameUsage.rank &&
      origin == nameUsage.origin &&
      taxonomicStatus == nameUsage.taxonomicStatus &&
      Objects.equals(nomenclaturalStatus, nameUsage.nomenclaturalStatus) &&
      Objects.equals(remarks, nameUsage.remarks) &&
      Objects.equals(publishedIn, nameUsage.publishedIn) &&
      Objects.equals(accordingTo, nameUsage.accordingTo) &&
      Objects.equals(references, nameUsage.references) &&
      Objects.equals(modified, nameUsage.modified) &&
      Objects.equals(deleted, nameUsage.deleted) &&
      Objects.equals(lastCrawled, nameUsage.lastCrawled) &&
      Objects.equals(lastInterpreted, nameUsage.lastInterpreted) &&
      Objects.equals(issues, nameUsage.issues);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(key, nubKey, nameKey, taxonID, kingdom, phylum, clazz, order, family, genus, subgenus,
        species, kingdomKey, phylumKey, classKey, orderKey, familyKey, genusKey, subgenusKey,
        speciesKey, datasetKey, constituentKey, parentKey, parent, proParteKey, acceptedKey,
        accepted, basionymKey, basionym, scientificName, canonicalName, vernacularName,
        authorship, nameType, rank, origin, taxonomicStatus, nomenclaturalStatus, remarks,
        publishedIn, accordingTo, numDescendants, references, modified, deleted, lastCrawled,
        lastInterpreted, issues);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", NameUsage.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("nubKey=" + nubKey)
      .add("nameKey=" + nameKey)
      .add("taxonID='" + taxonID + "'")
      .add("sourceTaxonKey=" + sourceTaxonKey)
      .add("kingdom='" + kingdom + "'")
      .add("phylum='" + phylum + "'")
      .add("clazz='" + clazz + "'")
      .add("order='" + order + "'")
      .add("family='" + family + "'")
      .add("genus='" + genus + "'")
      .add("subgenus='" + subgenus + "'")
      .add("species='" + species + "'")
      .add("kingdomKey=" + kingdomKey)
      .add("phylumKey=" + phylumKey)
      .add("classKey=" + classKey)
      .add("orderKey=" + orderKey)
      .add("familyKey=" + familyKey)
      .add("genusKey=" + genusKey)
      .add("subgenusKey=" + subgenusKey)
      .add("speciesKey=" + speciesKey)
      .add("datasetKey=" + datasetKey)
      .add("constituentKey=" + constituentKey)
      .add("parentKey=" + parentKey)
      .add("parent='" + parent + "'")
      .add("proParteKey=" + proParteKey)
      .add("acceptedKey=" + acceptedKey)
      .add("accepted='" + accepted + "'")
      .add("basionymKey=" + basionymKey)
      .add("basionym='" + basionym + "'")
      .add("scientificName='" + scientificName + "'")
      .add("canonicalName='" + canonicalName + "'")
      .add("vernacularName='" + vernacularName + "'")
      .add("authorship='" + authorship + "'")
      .add("nameType=" + nameType)
      .add("rank=" + rank)
      .add("origin=" + origin)
      .add("taxonomicStatus=" + taxonomicStatus)
      .add("nomenclaturalStatus=" + nomenclaturalStatus)
      .add("remarks='" + remarks + "'")
      .add("publishedIn='" + publishedIn + "'")
      .add("accordingTo='" + accordingTo + "'")
      .add("numDescendants=" + numDescendants)
      .add("references=" + references)
      .add("modified=" + modified)
      .add("deleted=" + deleted)
      .add("lastCrawled=" + lastCrawled)
      .add("lastInterpreted=" + lastInterpreted)
      .add("issues=" + issues)
      .toString();
  }
}
