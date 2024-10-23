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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A vocabulary to be used for a nomenclatural type status of a specimen or name.
 * See also the related TypeDesignationType enumeration.
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/type_status_2021-01-18.xml">rs.gbif.org vocabulary</a>
 * @see <a href="http://www.bgbm.org/TDWG/CODATA/Schema/ABCD_2.06/HTML/ABCD_2.06.html#element_NomenclaturalTypeDesignations_Link">Types in ABCD</a>
 */
@Deprecated
public enum TypeStatus {

  /**
   * General type designation.
   * A specimen designated or indicated any kind of type of a species or infraspecific taxon.
   * If possible more specific type terms (holotype, syntype, etc.) should be applied.
   *
   * Or the type name of a name of higher rank for taxa above the species rank.
   */
  TYPE,

  /**
   * For designating a type record as a reference to type information tied to a genus
   */
  TYPE_SPECIES,

  /**
   * For designating a type record as a reference to type information tied to a Family
   */
  TYPE_GENUS,

  /**
   * A paralectotype specimen that is the opposite sex of the lectotype. The term is not regulated by the ICZN. [Zoo.]
   */
  ALLOLECTOTYPE,

  /**
   * A paraneotype specimen that is the opposite sex of the neotype. The term is not regulated by the ICZN. [Zoo.]
   */
  ALLONEOTYPE,

  /**
   * A paratype specimen designated from the type series by the original author that is the opposite sex of the holotype. The term is not regulated by the ICZN. [Zoo.]
   */
  ALLOTYPE,

  /**
   * A deprecated term no longer recognized in the ICZN; formerly used for either syntype or paratype [see ICZN Recommendation 73E]. [Zoo.]
   */
  COTYPE,

  /**
   * An epitype is a specimen or illustration selected to serve as an interpretative type when any kind of holotype, lectotype, etc. is demonstrably ambiguous and cannot be critically identified for purposes of the precise application of the name of a taxon (see Art. ICBN 9.7, 9.18). An epitype supplements, rather than replaces existing types. [Bot./Bio.]
   */
  EPITYPE,

  /**
   * A strain or cultivation derived from epitype material. Ex-types are not regulated by the botanical or zoological code. [Bot.]
   */
  EXEPITYPE,

  /**
   * A strain or cultivation derived from holotype material. Ex-types are not regulated by the botanical or zoological code. [Zoo./Bot.]
   */
  EXHOLOTYPE,

  /**
   * A strain or cultivation derived from isotype material. Ex-types are not regulated by the botanical or zoological code. [Zoo./Bot.]
   */
  EXISOTYPE,

  /**
   * A strain or cultivation derived from lectotype material. Ex-types are not regulated by the botanical or zoological code. [Zoo./Bot.]
   */
  EXLECTOTYPE,

  /**
   * A strain or cultivation derived from neotype material. Ex-types are not regulated by the botanical or zoological code. [Zoo./Bot.]
   */
  EXNEOTYPE,

  /**
   * A strain or cultivation derived from paratype material. Ex-types are not regulated by the botanical or zoological code. [Zoo./Bot.]
   */
  EXPARATYPE,

  /**
   * A strain or cultivation derived from neotype material. Ex-types are not regulated by the botanical or zoological code. [Zoo./Bot.]
   */
  EXSYNTYPE,

  /**
   * A strain or cultivation derived from some kind of type material. Ex-types are not regulated by the botanical or zoological code. [Zoo./Bot.]
   */
  EXTYPE,

  /**
   * One or more preparations of directly related individuals representing distinct stages in the life cycle, which together form the type in an extant species of protistan [ICZN Article 72.5.4]. A hapantotype, while a series of individuals, is a holotype that must not be restricted by lectotype selection. If an hapantotype is found to contain individuals of more than one species, however, components may be excluded until it contains individuals of only one species [ICZN Article 73.3.2]. [Zoo.]
   */
  HAPANTOTYPE,

  /**
   * The one specimen or other element used or designated by the original author at the time of publication of the original description as the nomenclatural type of a species or infraspecific taxon. A holotype may be 'explicit' if it is clearly stated in the originating publication or 'implicit' if it is the single specimen proved to have been in the hands of the originating author when the description was published. [Zoo./Bot./Bio.]
   */
  HOLOTYPE,

  /**
   * A specimen that was not part of the original type series of the species, but is known from a published description, figure, or listing.
   */
  HYPOTYPE,

  /**
   * A drawing or photograph (also called 'phototype') of a type specimen. Note: the term 'iconotype' is not used in the ICBN, but implicit in, e. g., ICBN Art. 7 and 38. [Zoo./Bot.]
   */
  ICONOTYPE,

  /**
   * A duplicate of a lectotype, compare lectotype. [Bot.]
   */
  ISOLECTOTYPE,

  /**
   * A duplicate of a neotype, compare neotype. [Bot.]
   */
  ISONEOTYPE,

  /**
   *  A duplicate of a paratype, compare paratype. [Bot.]
   */
  ISOPARATYPE,

  /**
   * A duplicate of a syntype, compare isotype = duplicate of holotype. [Bot.]
   */
  ISOSYNTYPE,

  /**
   * An isotype is any duplicate of the holotype (i. e. part of a single gathering made by a collector at one time, from which the holotype was derived); it is always a specimen (ICBN Art. 7). [Bot.]
   */
  ISOTYPE,

  /**
   * A specimen or other element designated subsequent to the publication of the original description from the original material (syntypes or paratypes) to serve as nomenclatural type. Lectotype designation can occur only where no holotype was designated at the time of publication or if it is missing (ICBN Art. 7, ICZN Art. 74). [Zoo./Bot.] -- Note: the BioCode defines lectotype as selection from holotype material in cases where the holotype material contains more than one taxon [Bio.].
   */
  LECTOTYPE,

  /**
   * A specimen designated as nomenclatural type subsequent to the publication of the original description in cases where the original holotype, lectotype, all paratypes and syntypes are lost or destroyed, or suppressed by the (botanical or zoological) commission on nomenclature. In zoology also called 'Standard specimen' or 'Representative specimen'. [Zoo./Bot./Bio.]
   */
  NEOTYPE,

  /**
   * For specimens erroneously labelled as types an explicit negative statement may be desirable. [General]
   */
  NOTATYPE,

  /**
   * 'type-suspicious' material. The term is in accordance with the Botanical Code, where it is used for material that has been at the disposal of an author describing a new species, and is distinct from a type only in the fact that either the Type has not been officially assigned yet, or that the specific material has not been explicitly cited in the typification process (i.e. it was part of the swarm of specimens examined in the process, but not labelled as type itself).
   */
  ORIGINALMATERIAL,

  /**
   * All of the specimens in the syntype series of a species or infraspecific taxon other than the lectotype itself. Also called 'lectoparatype'. [Zoo.]
   */
  PARALECTOTYPE,

  /**
   * All of the specimens in the syntype series of a species or infraspecific taxon other than the neotype itself. Also called 'neoparatype'. [Zoo.]
   */
  PARANEOTYPE,

  /**
   * All of the specimens in the type series of a species or infraspecific taxon other than the holotype (and, in botany, isotypes). Paratypes must have been at the disposition of the author at the time when the original description was created and must have been designated and indicated in the publication. Judgment must be exercised on paratype status, for only rarely are specimens explicitly cited as paratypes, but usually as 'specimens examined,' 'other material seen', etc. [Zoo./Bot.]
   */
  PARATYPE,

  /**
   * A copy or cast of holotype material (compare Plastotype).
   */
  PLASTOHOLOTYPE,

  /**
   * A copy or cast of isotype material (compare Plastotype).
   */
  PLASTOISOTYPE,

  /**
   * A copy or cast of lectotype material (compare Plastotype).
   */
  PLASTOLECTOTYPE,

  /**
   * A copy or cast of neotype material (compare Plastotype).
   */
  PLASTONEOTYPE,

  /**
   * A copy or cast of paratype material (compare Plastotype).
   */
  PLASTOPARATYPE,

  /**
   * A copy or cast of syntype material (compare Plastotype).
   */
  PLASTOSYNTYPE,

  /**
   * A copy or cast of type material, esp. relevant for fossil types. Not regulated by the botanical or zoological code (?). [Zoo./Bot.]
   */
  PLASTOTYPE,

  /**
   * A specimen that is both a homeotype and a hypotype.
   */
  PLESIOTYPE,

  /**
   * A referred, described, measured or figured specimen in the original publication (including a neo/lectotypification publication) that is not a primary type. [Zoo.]
   */
  SECONDARYTYPE,

  /**
   * A referred, described, measured or figured specimen in a revision of a previously described taxon. [Zoo.]
   */
  SUPPLEMENTARYTYPE,

  /**
   * One of the series of specimens used to describe a species or infraspecific taxon when neither a single holotype nor a lectotype has been designated. The syntypes collectively constitute the name-bearing type. [Zoo./Bot.]
   */
  SYNTYPE,

  /**
   * One or more specimens collected at the same location as the type series (type locality), regardless of whether they are part of the type series. Topotypes are not regulated by the botanical or zoological code. Also called 'locotype'. [Zoo./Bot.]
   */
  TOPOTYPE;

  /**
   * @return a list of all type status values applicable for specimens.
   */
  public static List<TypeStatus> specimenTypeStatusList() {
    return Collections.unmodifiableList(
      Stream.of(values())
        .filter(TypeStatus::isTypeSpecimen)
        .collect(Collectors.toList()));
  }

  /**
   * @return a list of all type status values applicable for scientific names, not specimens.
   */
  public static List<TypeStatus> nameTypeStatusList() {
    return Collections.unmodifiableList(
      Stream.of(values())
        .filter(status -> TypeStatus.TYPE == status || !status.isTypeSpecimen())
        .collect(Collectors.toList())
    );
  }

  /**
   * @return true if the type status is referring to a type specimen in contrast to a type genus or species name
   */
  public boolean isTypeSpecimen() {
    return this != TYPE_GENUS && this != TYPE_SPECIES;
  }

}
