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
package org.gbif.api.util;

import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.IdentifierType;

import javax.annotation.Nullable;

import java.util.regex.Pattern;

/**
 * This class contains utility methods for identifiers. Currently there are 3 separate Identifier
 * classes: </br> 1) org.gbif.api.model.checklistbank.Identifier 2)
 * org.gbif.api.model.common.Identifier 3) org.gbif.api.model.registry.Identifier </br> Methods
 * common to 2 or more classes should be listed here.
 */
public class IdentifierUtils {

  public static final Pattern WIKIDATA_PATTERN =
      Pattern.compile("http(s)?://www.wikidata.org/entity/([A-Za-z][0-9]+)$");

  public static final Pattern ROR_PATTERN =
    Pattern.compile("https?://ror\\.org/0[a-z0-9]{6}[0-9]{2}");

  /**
   * Creates a http link for an identifier if possible by passing it to some known resolvers for the
   * specific id type. If no link can be constructed, null is returned.
   *
   * @param identifier Identifier's identifier
   * @param type Identifier's type
   * @return the url or null if it cannot be created
   */
  @Nullable
  public static String getIdentifierLink(String identifier, IdentifierType type) {
    if (identifier == null || type == null) {
      return null;
    }
    switch (type) {
      case HANDLER:
      case URI:
      case URL:
      case FTP:
      case ROR:
        return identifier;
      case DOI:
        return "https://doi.org/" + identifier;
      case LSID:
        return "http://www.lsid.info/" + identifier;
      case GBIF_PORTAL:
        return "https://www.gbif.org/dataset/" + identifier;
    }
    return null;
  }

  /** CITES identifier validation according to https://cites.org/eng/common/reg/e_si.html. */
  public static boolean isValidCitesIdentifier(String identifier) {
    if (identifier == null || identifier.isEmpty()) {
      return false;
    }

    String[] parts = identifier.split("\\s+");
    if (parts.length < 2) {
      return false;
    }

    if (parts[0].length() != 2) {
      return false;
    }

    Country country = Country.fromIsoCode(parts[0]);
    return country != null;
  }

  public static boolean isValidWikidataIdentifier(String identifier) {
    if (identifier == null || identifier.isEmpty()) {
      return false;
    }

    return WIKIDATA_PATTERN.matcher(identifier).matches();
  }

  public static boolean isValidRORIdentifier(String identifier) {
    if (identifier == null || identifier.isEmpty()) {
      return false;
    }

    return ROR_PATTERN.matcher(identifier).matches();
  }
}
