package org.gbif.api.util;

import org.gbif.api.vocabulary.IdentifierType;

import javax.annotation.Nullable;

/**
 * This class contains utility methods for identifiers. Currently there are 3 separate Identifier
 * classes:
 * </br>
 * 1) org.gbif.api.model.checklistbank.Identifier
 * 2) org.gbif.api.model.common.Identifier
 * 3) org.gbif.api.model.registry.Identifier
 * </br>
 * Methods common to 2 or more classes should be listed here.
 */
public class IdentifierUtils {

  /**
   * Creates a http link for an identifier if possible by passing it to some known resolvers for the specific id type.
   * If no link can be constructed, null is returned.
   *
   * @param identifier Identifier's identifier
   * @param type Identifier's type
   *
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
        return identifier;
      case DOI:
        return "http://dx.doi.org/" + identifier;
      case LSID:
        return "http://lsid.tdwg.org/summary/" + identifier;
      case GBIF_PORTAL:
        return "http://data.gbif.org/datasets/resource/" + identifier;
    }
    return null;
  }
}
