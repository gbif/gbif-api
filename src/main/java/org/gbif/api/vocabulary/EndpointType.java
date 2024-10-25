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

import org.gbif.api.util.VocabularyUtils;

/**
 * Enumeration for all endpoint types.
 */
public enum EndpointType {
  /**
   * A single <a href="http://knb.ecoinformatics.org/software/eml/">EML metadata document</a> in any EML version.
   */
  EML,
  /**
   * Syndication feeds like RSS or ATOM of various flavors.
   * The feeds can serve very different purpose, for example if attached to an organisation it can be news items.
   * Attached to a technical installation like the IPT it should return the latest dataset resources.
   */
  FEED,
  /**
   * An OGC Web Feature Service.
   */
  WFS,
  /**
   * An OGC Web Map Service.
   */
  WMS,
  /**
   * Taxon Concept data given as RDF
   * based on the <a href="http://rs.tdwg.org/ontology/voc/TaxonConcept.rdf">TDWG ontology</a>.
   */
  TCS_RDF,
  /**
   * A <a href="http://www.tdwg.org/standards/117/">Taxon Concept Schema</a> document.
   */
  TCS_XML,
  /**
   * A Darwin Core Archive as defined by the <a href="http://rs.tdwg.org/dwc/terms/guides/text/">Darwin Core Text Guidelines</a>.
   */
  DWC_ARCHIVE,
  /**
   * A <a href="http://digir.sourceforge.net/">DiGIR</a> service endpoint.
   */
  DIGIR,
  /**
   * A DiGIR service slightly modified for the MANIS network.
   */
  DIGIR_MANIS,
  /**
   * A <a href="http://www.tdwg.org/dav/subgroups/tapir/1.0/docs/">TAPIR</a> service.
   */
  TAPIR,
  /**
   * A <a href="http://www.biocase.org/products/protocols/">BioCASe</a> protocol compliant service.
   */
  BIOCASE,
  /**
   * A <a href="http://www.biocase.org/products/protocols/">BioCASe</a> XML Archive, as explained by the
   * <a href="http://wiki.bgbm.org/bps/index.php/Archiving">BioCASe wiki</a>.
   */
  BIOCASE_XML_ARCHIVE,
  /**
   * The Open Archives Initiative Protocol for Metadata Harvesting.
   * A <a href="http://www.openarchives.org/OAI/openarchivesprotocol.html">OAI-PMH</a> compliant data provider service.
   */
  OAI_PMH,
  /**
   * <a href="https://github.com/CatalogueOfLife/coldp">Catalogue of Life Data Packages</a>.
   */
  COLDP,
  /**
   * <a href="https://tdwg.github.io/camtrap-dp/">CamTrap DP</a>.
   */
  CAMTRAP_DP,

  /**
   * The <a href="https://biom-format.org/documentation/format_versions/biom-1.0.html">BIOM JSON</a> file format for representing biological sample by observation contingency tables.
   */
  BIOM_1_0,

  /**
   * The <a href="https://biom-format.org/documentation/format_versions/biom-2.1.html">BIOM HDF5</a> file format.
   */
  BIOM_2_1,

  /**
   * <a href="https://www.checklistbank.org/about/formats#annual-checklist-exchange-format-acef">Annual Checklist Exchange Format (ACEF)</a>
   * from the Catalogue of Life.
   */
  ACEF,
  /**
   * <a href="https://github.com/gbif/text-tree">TextTree</a>.
   * is a simple format to represent taxonomic trees using indented, plain text.
   */
  TEXT_TREE,
  /**
   * Any other service not covered by this enum so far.
   */
  OTHER;

  /**
   * @return the matching EndpointType or null
   */
  public static EndpointType fromString(String endpointType) {
    return VocabularyUtils.lookupEnum(endpointType, EndpointType.class);
  }

}
