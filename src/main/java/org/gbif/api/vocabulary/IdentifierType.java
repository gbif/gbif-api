/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
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

import java.util.List;
import javax.annotation.Nullable;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

/**
 * Enumeration for all possible identifier types.
 */
public enum IdentifierType {

  URL,
  /**
   * Reference controlled by a separate system, used for example by DOI.
   * {http://en.wikipedia.org/wiki/Handle_(computing)}
   */
  LSID,
  HANDLER,
  DOI,
  UUID,
  FTP,
  URI,
  UNKNOWN,
  /**
   * Indicates the identifier originated from an auto_increment column in the portal.data_provider or
   * portal.data_resource table respectively.
   */
  GBIF_PORTAL,
  /**
   * Identifies the node (e.g: 'DK' for Denmark, 'sp2000' for Species 2000).
   */
  GBIF_NODE,

  /**
   * Participant identifier from the GBIF IMS Filemaker system.
   */
  GBIF_PARTICIPANT;

  // TODO: Check if this is used, it didn't exist in the new Registry2 API, but I preserved it from the old vocabulary
  public static final List<IdentifierType> TYPES;

  static {
    TYPES = ImmutableList.copyOf(IdentifierType.values());
  }

  /**
   * @return the matching IdentifierType or null
   */
  public static IdentifierType fromString(String identifierType) {
    return (IdentifierType) VocabularyUtils.lookupEnum(identifierType, IdentifierType.class);
  }


  /**
   * Tries to infer the identifier type from a given identifier.
   * Most identifiers have a URI protocol prefix or a specific structure that
   * allows the guess.
   *
   * @return the inferred identifier type or Unknown if identifier is null or cant be inferred.
   */
  public static IdentifierType inferFrom(@Nullable String identifier) {
    String lcIdentifier = Strings.nullToEmpty(identifier).trim().toLowerCase();

    if (lcIdentifier.isEmpty()) {
      return UNKNOWN;
    }

    if (lcIdentifier.startsWith("doi:10")
        || lcIdentifier.startsWith("urn:doi:")
        || lcIdentifier.startsWith("http://dx.doi.org/10.")) {
      return DOI;
    }
    if (lcIdentifier.startsWith("http:")
        || lcIdentifier.startsWith("https:")
        || lcIdentifier.startsWith("www.")) {
      return URL;
    }
    if (lcIdentifier.startsWith("ftp:")) {
      return FTP;
    }
    if (lcIdentifier.startsWith("urn:lsid:") || lcIdentifier.startsWith("lsid:")) {
      return LSID;
    }

    if (lcIdentifier.startsWith("urn:uuid:") || lcIdentifier.startsWith("uuid:")) {
      return UUID;
    }
    try {
      java.util.UUID.fromString(lcIdentifier);
      return UUID;
    } catch (IllegalArgumentException ignored) {
      // We're just trying to convert a String to anything readable. Apparently the UUID approach failed.
    }

    return UNKNOWN;
  }
}
