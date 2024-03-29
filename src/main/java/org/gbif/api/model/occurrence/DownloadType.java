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
package org.gbif.api.model.occurrence;

import org.gbif.dwc.terms.DwcTerm;

/**
 * Defines the type of citable data download.
 */
public enum DownloadType {
  OCCURRENCE(DwcTerm.Occurrence), EVENT(DwcTerm.Event);

  private DwcTerm coreTerm;

  DownloadType(DwcTerm coreTerm){
    this.coreTerm = coreTerm;
  }

  public DwcTerm getCoreTerm() {
    return coreTerm;
  }

  public static DownloadType fromCoreTerm(DwcTerm coreTerm) {
    if (DwcTerm.Event == coreTerm) {
      return EVENT;
    }
    if (DwcTerm.Occurrence == coreTerm) {
      return OCCURRENCE;
    }
    throw new IllegalArgumentException("Unsupported download type for term " + coreTerm);
  }
}
