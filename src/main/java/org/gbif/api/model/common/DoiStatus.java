package org.gbif.api.model.common;

/**
 * This enumeration represents the status of a DOI identifier.
 * It represents both EZID and DataCite DOIs.
 */
public enum DoiStatus {
  /**
   * The identifier is known only to the DOI registration agency.
   * This status may be used to reserve an identifier name without advertising the identifier's existence
   * to resolvers and other external services.
   * A reserved identifier may be fully deleted.
   */
  RESERVED("reserved"),

  /**
   * A public, registered DOI.
   * It's target URL is known to public resolvers and other external services.
   * It may be marked as DELETED in the future, but never again as RESERVED.
   */
  REGISTERED("public"),

  /**
   * The identifier once was registered, but the object referenced by the identifier is not available.
   * This is known as an "inactive" DOI in DataCite and "unavailable" in EZID.
   * <br/>
   * In EZID the identifier redirects to an EZID-provided "tombstone" page regardless of its target URL.
   * In DataCite the original target URL is still available.
   */
  DELETED("unavailable");

  private final String ezid;

  DoiStatus(String ezid) {
    this.ezid = ezid;
  }

  /**
   * @return the identifier status value used in EZID.
   */
  public String getEzid() {
    return ezid;
  }

  /**
   * @return true if the identifier is registered
   */
  public boolean isRegistered() {
    return this != RESERVED;
  }

  public static DoiStatus fromString(String status) {
    for (DoiStatus s : DoiStatus.values()) {
      if (status.equalsIgnoreCase(s.name()) || status.equalsIgnoreCase(s.getEzid())) {
        return s;
      }
    }
    return null;
  }
}
