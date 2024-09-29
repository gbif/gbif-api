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

import org.gbif.api.util.VocabularyUtils;

/**
 * Enumeration for all possible installation types.
 */
public enum InstallationType {

  IPT_INSTALLATION,
  DIGIR_INSTALLATION,
  TAPIR_INSTALLATION,
  BIOCASE_INSTALLATION,
  HTTP_INSTALLATION,
  SYMBIOTA_INSTALLATION,
  EARTHCAPE_INSTALLATION;

  /**
   * @return the matching InstallationType or null
   */
  public static InstallationType fromString(String installationType) {
    return (InstallationType) VocabularyUtils.lookupEnum(installationType, InstallationType.class);
  }

}
