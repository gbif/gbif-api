package org.gbif.api.model.event;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.gbif.api.v2.RankedName;

@Data
public class Humboldt {

  private Integer siteCount;
  private List<String> verbatimSiteDescriptions;
  private List<String> verbatimSiteNames;
  private Double geospatialScopeAreaValue;
  private String geospatialScopeAreaUnit;
  private Double totalAreaSampledValue;
  private String totalAreaSampledUnit;
  private List<String> targetHabitatScope;
  private List<String> excludedHabitatScope;
  private Double eventDurationValue;
  private String eventDurationUnit;
  private Map<String, List<TaxonClassification>> targetTaxonomicScope;
  private Map<String, List<TaxonClassification>> excludedTaxonomicScope;
  private List<String> taxonCompletenessProtocols;
  private Boolean isTaxonomicScopeFullyReported;
  private Boolean isAbsenceReported;
  private Map<String, List<TaxonClassification>> absentTaxa;
  private Boolean hasNonTargetTaxa;
  private Map<String, List<TaxonClassification>> nonTargetTaxa;
  private Boolean areNonTargetTaxaFullyReported;
  private List<String> targetLifeStageScope;
  private List<String> excludedLifeStageScope;
  private Boolean isLifeStageScopeFullyReported;
  private List<String> targetDegreeOfEstablishmentScope;
  private List<String> excludedDegreeOfEstablishmentScope;
  private Boolean isDegreeOfEstablishmentScopeFullyReported;
  private List<String> targetGrowthFormScope;
  private List<String> excludedGrowthFormScope;
  private Boolean isGrowthFormScopeFullyReported;
  private Boolean hasNonTargetOrganisms;
  private List<String> compilationTypes;
  private List<String> compilationSourceTypes;
  private List<String> inventoryTypes;
  private List<String> protocolNames;
  private List<String> protocolDescriptions;
  private List<String> protocolReferences;
  private Boolean isAbundanceReported;
  private Boolean isAbundanceCapReported;
  private Integer abundanceCap;
  private Boolean isVegetationCoverReported;
  private Boolean isLeastSpecificTargetCategoryQuantityInclusive;
  private Boolean hasVouchers;
  private List<String> voucherInstitutions;
  private Boolean hasMaterialSamples;
  private List<String> materialSampleTypes;
  private List<String> samplingPerformedBy;
  private Boolean isSamplingEffortReported;
  private Double samplingEffortValue;
  private String samplingEffortUnit;

  @Data
  public static class TaxonClassification {
    private String usageKey;
    private String usageName;
    private String usageRank;
    private List<RankedName> classification;
    private List<String> issues;
  }
}
