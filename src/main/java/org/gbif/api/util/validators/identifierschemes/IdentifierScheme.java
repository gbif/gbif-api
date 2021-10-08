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
package org.gbif.api.util.validators.identifierschemes;

public enum IdentifierScheme {
  ORCID("https://orcid.org"),
  ISNI("http://www.isni.org"),
  VIAF("https://viaf.org"),
  HUH("https://kiki.huh.harvard.edu"),
  RESEARCHER_ID("http://www.researcherid.com"),
  IH_IRN("http://sweetgum.nybg.org"),
  OTHER("");

  private String schemeURI;

  IdentifierScheme(String schemeURI) {
    this.schemeURI = schemeURI;
  }

  public String getSchemeURI() {
    return schemeURI;
  }
}
