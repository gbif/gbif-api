package org.gbif.api.util.validators.identifierschemes;

public enum IdentifierScheme {
  ORCID("https://orcid.org"),
  ISNI("http://www.isni.org"),
  VIAF("https://viaf.org"),
  HUH("https://kiki.huh.harvard.edu"),
  RESEARCHER_ID("http://www.researcherid.com"),
  OTHER("");

  private String schemeURI;

  IdentifierScheme(String schemeURI) {
    this.schemeURI = schemeURI;
  }

  public String getSchemeURI() {
    return schemeURI;
  }
}
