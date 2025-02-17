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
package org.gbif.api.vocabulary;

import org.gbif.api.util.IdentifierUtils;
import org.gbif.api.util.VocabularyUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

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
   * Participant identifier from the GBIF Directory.
   */
  GBIF_PARTICIPANT,

  /**
   * ID migrated from GrSciColl.
   */
  GRSCICOLL_ID,

  /**
   * Cool URI migrated from GrSciColl.
   */
  GRSCICOLL_URI,

  /**
   * IRN of an IH record.
   */
  IH_IRN,

  /**
   * Research Organization Registry.
   * https://ror.org
   */
  ROR,

  /**
   * https://www.grid.ac/institutes/
   */
  GRID,

  /**
   * https://cites.org/
   */
  CITES,
  /**
   * Symbiota IDs to help linking GrSciColl occurrences.
   */
  SYMBIOTA_UUID,
  WIKIDATA,

  /**
   * https://www.ncbi.nlm.nih.gov/
   */
  NCBI_BIOCOLLECTION,

  /**
   * https://slks.dk/english/work-areas/libraries-and-literature/library-standards/isil
   */
  ISIL,

  /**
   * ChecklistBank dataset keys (integer)
   * https://www.checklistbank.org/dataset
   */
  CLB_DATASET_KEY;

  // TODO: Check if this is used, it didn't exist in the new Registry2 API, but I preserved it from the old vocabulary
  public static final List<IdentifierType> TYPES;

  static {
    TYPES = Collections.unmodifiableList(Arrays.asList(IdentifierType.values()));
  }

  /**
   * @return the matching IdentifierType or null
   */
  public static IdentifierType fromString(String identifierType) {
    return VocabularyUtils.lookupEnum(identifierType, IdentifierType.class);
  }

  /**
   * Tries to infer the identifier type from a given identifier.
   * Most identifiers have a URI protocol prefix or a specific structure that
   * allows the guess.
   *
   * @return the inferred identifier type or Unknown if identifier is null or cant be inferred.
   */
  public static IdentifierType inferFrom(@Nullable String identifier) {
    String lcIdentifier = StringUtils.trimToEmpty(identifier).toLowerCase();

    if (lcIdentifier.isEmpty()) {
      return UNKNOWN;
    }

    if (lcIdentifier.startsWith(org.gbif.api.model.common.DOI.GBIF_PREFIX)
        || lcIdentifier.startsWith(org.gbif.api.model.common.DOI.TEST_PREFIX)) {
      return DOI;
    }
    if (lcIdentifier.startsWith("10.")
        || lcIdentifier.startsWith("doi:10.")
        || lcIdentifier.startsWith("urn:doi:10.")
        || lcIdentifier.startsWith("http://dx.doi.org/10.")
        || lcIdentifier.startsWith("https://dx.doi.org/10.")
        || lcIdentifier.startsWith("http://doi.org/10.")
        || lcIdentifier.startsWith("https://doi.org/10.")) {
      return DOI;
    }

    if (lcIdentifier.startsWith("https://ror.org")) {
      return ROR;
    }

    if(IdentifierUtils.isValidWikidataIdentifier(lcIdentifier)) {
      return WIKIDATA;
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

    if (lcIdentifier.startsWith("gbif:ih:irn:")) {
      return IH_IRN;
    }

    if (lcIdentifier.startsWith("grid")) {
      return GRID;
    }

    if (IdentifierUtils.isValidISILIdentifier(lcIdentifier)) {
      return ISIL;
    }

   if (IdentifierUtils.isValidCLBDatasetKey(lcIdentifier)) {
     return CLB_DATASET_KEY;
   }

    try {
      //noinspection ResultOfMethodCallIgnored
      java.util.UUID.fromString(lcIdentifier);
      return UUID;
    } catch (IllegalArgumentException ignored) {
      // We're just trying to convert a String to anything readable. Apparently the UUID approach failed.
    }

    return UNKNOWN;
  }
}
