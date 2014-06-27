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

  LEAST_CONCERN("LC"),
  NEAR_THREATENED("NT"),
  VULNERABLE("VU"),
  ENDANGERED("EN"),
  CRITICALLY_ENDANGERED("CR"),
  EXTINCT_IN_THE_WILD("EW"),
  EXTINCT("EX"),
  DATA_DEFICIENT("DD"),
  NOT_EVALUATED("NE");

  private final String code;

  ThreatStatus(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
