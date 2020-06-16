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
package org.gbif.api.vocabulary;

/**
 * The IUCN threat status.
 * The IUCN Red List Categories and Criteria are intended to be an easily and widely understood system
 * for classifying species at high risk of global extinction. The general aim of the system is to provide an explicit,
 * objective framework for the classification of the broadest range of species according to their extinction risk.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/iucn/threat_status.xml">rs.gbif.org vocabulary</a>
 */
public enum ThreatStatus {

  /**
   * A taxon is Extinct when there is no reasonable doubt that the last individual has died. A taxon is presumed Extinct
   * when exhaustive surveys in known and/or expected habitat, at appropriate times (diurnal, seasonal, annual),
   * throughout its historic range have failed to record an individual. Surveys should be over a time frame appropriate
   * to the taxon's life cycle and life form.
   */
  EXTINCT("EX"),

  /**
   * A taxon is Extinct in the Wild when it is known only to survive in cultivation, in captivity or as a naturalized
   * population (or populations) well outside the past range. A taxon is presumed Extinct in the Wild when exhaustive
   * surveys in known and/or expected habitat, at appropriate times (diurnal, seasonal, annual), throughout its historic
   * range have failed to record an individual. Surveys should be over a time frame appropriate to the taxon's life
   * cycle and life form.
   */
  EXTINCT_IN_THE_WILD("EW"),

  /**
   * Category for a taxon when there is no reasonable doubt that the last individual potentially capable of reproduction
   * within the region has died or has disappeared from the wild in the region, or when, if it is a former visiting
   * taxon, the last individual has died or disappeared in the wild from the region. The setting of any time limit for
   * listing under RE is left to the discretion of the regional Red List authority, but should not normally pre-date
   * 1500 AD.
   */
  REGIONALLY_EXTINCT("RE"),

  /**
   * A taxon is Critically Endangered when the best available evidence indicates that it meets any of the criteria A to
   * E for Critically Endangered (see Section V), and it is therefore considered to be facing an extremely high risk of
   * extinction in the wild.
   */
  CRITICALLY_ENDANGERED("CR"),

  /**
   * A taxon is Endangered when the best available evidence indicates that it meets any of the criteria A to E for
   * Endangered (see Section V), and it is therefore considered to be facing a very high risk of extinction in the wild.
   */
  ENDANGERED("EN"),

  /**
   * A taxon is Vulnerable when the best available evidence indicates that it meets any of the criteria A to E for
   * Vulnerable (see Section V), and it is therefore considered to be facing a high risk of extinction in the wild.
   */
  VULNERABLE("VU"),

  /**
   * A taxon is Near Threatened when it has been evaluated against the criteria but does not qualify for Critically
   * Endangered, Endangered or Vulnerable now, but is close to qualifying for or is likely to qualify for a threatened
   * category in the near future.
   */
  NEAR_THREATENED("NT"),

  /**
   * A taxon is Least Concern when it has been evaluated against the criteria and does not qualify for Critically
   * Endangered, Endangered, Vulnerable or Near Threatened. Widespread and abundant taxa are included in this category.
   */
  LEAST_CONCERN("LC"),

  /**
   * A taxon is Data Deficient when there is inadequate information to make a direct, or indirect, assessment of its
   * risk of extinction based on its distribution and/or population status. A taxon in this category may be well
   * studied, and its biology well known, but appropriate data on abundance and/or distribution are lacking. Data
   * Deficient is therefore not a category of threat. Listing of taxa in this category indicates that more information
   * is required and acknowledges the possibility that future research will show that threatened classification is
   * appropriate. It is important to make positive use of whatever data are available. In many cases great care should
   * be exercised in choosing between DD and a threatened status. If the range of a taxon is suspected to be relatively
   * circumscribed, and a considerable period of time has elapsed since the last record of the taxon, threatened status
   * may well be justified.
   */
  DATA_DEFICIENT("DD"),

  /**
   * Category for a taxon deemed to be ineligible for assessment at a regional level. A taxon may be NA because it is
   * not a wild population or not within its natural range in the region, or because it is a vagrant to the region. It
   * may also be NA because it occurs at very low numbers in the region (i.e. when the regional Red List authority has
   * decided to use a filter to exclude taxa before the assessment procedure) or the taxon may be classified at a lower
   * taxonomic level (e.g. below the level of species or subspecies) than considered eligible by the regional Red List
   * authority. In contrast to other Red List Categories, it is not mandatory to use NA for all taxa to which it
   * applies; but is recommended for taxa where its use is informative.
   */
  NOT_APPLICABLE("NA"),

  /**
   * A taxon is Not Evaluated when it is has not yet been evaluated against the criteria.
   */
  NOT_EVALUATED("NE");

  private final String code;

  ThreatStatus(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
