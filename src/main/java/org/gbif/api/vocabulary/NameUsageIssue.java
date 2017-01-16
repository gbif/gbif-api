package org.gbif.api.vocabulary;

import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.GbifTerm;
import org.gbif.dwc.terms.Term;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * Enumeration of issues for each name usage record encountered during checklist processing.
 */
public enum NameUsageIssue implements InterpretationRemark {
  /**
   * The value for dwc:parentNameUsageID could not be resolved.
   */
  PARENT_NAME_USAGE_ID_INVALID(DwcTerm.parentNameUsageID),

  /**
   * The value for dwc:acceptedNameUsageID could not be resolved.
   */
  ACCEPTED_NAME_USAGE_ID_INVALID(DwcTerm.acceptedNameUsageID),

  /**
   * The value for dwc:originalNameUsageID could not be resolved.
   */
  ORIGINAL_NAME_USAGE_ID_INVALID(DwcTerm.originalNameUsageID),

  /**
   * Synonym lacking an accepted name.
   */
  ACCEPTED_NAME_MISSING(DwcTerm.taxonomicStatus, DwcTerm.acceptedNameUsageID, DwcTerm.acceptedNameUsage),

  /**
   * dwc:taxonRank could not be interpreted
   */
  RANK_INVALID(DwcTerm.taxonRank),

  /**
   * dwc:nomenclaturalStatus could not be interpreted
   */
  NOMENCLATURAL_STATUS_INVALID(DwcTerm.nomenclaturalStatus),

  /**
   * dwc:taxonomicStatus could not be interpreted
   */
  TAXONOMIC_STATUS_INVALID(DwcTerm.taxonomicStatus),

  /**
   * The scientific name was assembled from the individual name parts and not given as a whole string.
   */
  SCIENTIFIC_NAME_ASSEMBLED(DwcTerm.scientificName, DwcTerm.genus, DwcTerm.specificEpithet, DwcTerm.infraspecificEpithet, DwcTerm.taxonRank, DwcTerm.scientificNameAuthorship, DwcTerm.namePublishedInYear),

  /**
   * If a synonym points to another synonym as its accepted taxon the chain is resolved.
   */
  CHAINED_SYNOYM(DwcTerm.acceptedNameUsageID, DwcTerm.acceptedNameUsage),

  /**
   * The authorship of the original name does not match the authorship in brackets of the actual name.
   */
  BASIONYM_AUTHOR_MISMATCH(DwcTerm.scientificName, DwcTerm.scientificNameAuthorship, DwcTerm.originalNameUsageID, DwcTerm.originalNameUsage),

  TAXONOMIC_STATUS_MISMATCH(DwcTerm.taxonomicStatus, DwcTerm.acceptedNameUsageID, DwcTerm.acceptedNameUsage),

  /**
   * The child parent classification resulted into a cycle that needed to be resolved/cut.
   */
  PARENT_CYCLE(DwcTerm.parentNameUsageID, DwcTerm.parentNameUsage),

  CLASSIFICATION_RANK_ORDER_INVALID(DwcTerm.parentNameUsageID, DwcTerm.parentNameUsage, DwcTerm.taxonRank),

  /**
   * The denormalized classification could not be applied to the name usage.
   * For example if the id based classification has no ranks.
   */
  CLASSIFICATION_NOT_APPLIED(DwcTerm.kingdom, DwcTerm.phylum, DwcTerm.class_, DwcTerm.order, DwcTerm.family, DwcTerm.genus),

  /**
   * At least one vernacular name extension record attached to this name usage is invalid.
   */
  VERNACULAR_NAME_INVALID(DwcTerm.vernacularName),

  /**
   * At least one description extension record attached to this name usage is invalid.
   */
  DESCRIPTION_INVALID(GbifTerm.Description),

  /**
   * At least one distribution extension record attached to this name usage is invalid.
   */
  DISTRIBUTION_INVALID(GbifTerm.Distribution),

  /**
   * At least one species profile extension record attached to this name usage is invalid.
   */
  SPECIES_PROFILE_INVALID(GbifTerm.SpeciesProfile),

  /**
   * At least one multimedia extension record attached to this name usage is invalid.
   * This covers multimedia coming in through various extensions including
   * Audubon core, Simple images or multimedia or EOL media.
   */
  MULTIMEDIA_INVALID(GbifTerm.Multimedia),

  /**
   * At least one bibliographic reference extension record attached to this name usage is invalid.
   */
  BIB_REFERENCE_INVALID(GbifTerm.Reference),

  /**
   * At least one alternative identifier extension record attached to this name usage is invalid.
   */
  ALT_IDENTIFIER_INVALID(GbifTerm.Identifier),

  /**
   * Name usage could not be matched to the GBIF backbone.
   */
  BACKBONE_MATCH_NONE(DwcTerm.scientificName, DwcTerm.scientificNameAuthorship, DwcTerm.kingdom, DwcTerm.taxonRank),

  /**
   * Name usage could only be matched to the GBIF backbone using fuzzy matching.
   * @deprecated because there should be no fuzzy matching being used anymore for matching checklist names
   */
  @Deprecated
  BACKBONE_MATCH_FUZZY(DwcTerm.scientificName, DwcTerm.scientificNameAuthorship, DwcTerm.kingdom, DwcTerm.taxonRank),

  /**
   * Synonym has a verbatim accepted name which is not unique and refers to several records.
   */
  ACCEPTED_NAME_NOT_UNIQUE(DwcTerm.acceptedNameUsage),

  /**
   * Record has a verbatim parent name which is not unique and refers to several records.
   */
  PARENT_NAME_NOT_UNIQUE(DwcTerm.parentNameUsage),

  /**
   * Record has a verbatim original name (basionym) which is not unique and refers to several records.
   */
  ORIGINAL_NAME_NOT_UNIQUE(DwcTerm.originalNameUsage),

  /**
   * There were problems representing all name usage relationships,
   * i.e. the link to the parent, accepted and/or original name.
   * The interpreted record in ChecklistBank is lacking some of the original source relation.
   */
  RELATIONSHIP_MISSING(DwcTerm.parentNameUsageID, DwcTerm.acceptedNameUsageID, DwcTerm.originalNameUsageID, DwcTerm.parentNameUsage, DwcTerm.acceptedNameUsage, DwcTerm.originalNameUsage),

  /**
   * Record has a original name (basionym) relationship which was derived from name & authorship comparison, but did not exist explicitly in the data.
   * This should only be flagged in programmatically generated GBIF backbone usages.
   * GBIF backbone specific issue.
   */
  ORIGINAL_NAME_DERIVED(),

  /**
   * There have been more than one accepted name in a homotypical basionym group of names.
   * GBIF backbone specific issue.
   */
  CONFLICTING_BASIONYM_COMBINATION(),

  /**
   * The group (currently only genera are tested) are lacking any accepted species
   * GBIF backbone specific issue.
   */
  NO_SPECIES(),

  /**
   * The (accepted) bi/trinomial name does not match the parent name and should be recombined into the parent genus/species.
   * For example the species Picea alba with a parent genus Abies is a mismatch and should be replaced by Abies alba.
   * GBIF backbone specific issue.
   */
  NAME_PARENT_MISMATCH(),

  /**
   * A potential orthographic variant exists in the backbone.
   * GBIF backbone specific issue.
   */
  ORTHOGRAPHIC_VARIANT(),

  /**
   * A not synonymized homonym exists for this name in some other backbone source which have been ignored at build time.
   */
  HOMONYM(DwcTerm.scientificName),

  /**
   * A bi/trinomial name published earlier than the parent genus was published.
   * This might indicate that the name should rather be a recombination.
   */
  PUBLISHED_BEFORE_GENUS(DwcTerm.scientificName, DwcTerm.scientificNameAuthorship, DwcTerm.namePublishedInYear, DwcTerm.genus, DwcTerm.parentNameUsageID, DwcTerm.parentNameUsage);

  private final Set<Term> related;

  NameUsageIssue(Term ... related) {
    if (related == null) {
      this.related = ImmutableSet.of();
    } else {
      this.related = ImmutableSet.copyOf(related);
    }
  }

  public Set<Term> getRelatedTerms() {
    return related;
  }

}
