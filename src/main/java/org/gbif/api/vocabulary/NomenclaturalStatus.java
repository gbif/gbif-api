/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Vocabulary for the nomenclatural status of a name.
 *
 * @see <a href="http://dev.e-taxonomy.eu/trac/wiki/NomenclaturalStatus">EDIT CDM</a>
 * @see <a href="http://wiki.tdwg.org/twiki/bin/view/UBIF/LinneanCoreNomenclaturalStatus">TDWG LinneanCoreNomenclaturalStatus</a>
 * @see <a href="http://www.biologybrowser.org/nomglos">Nomenclatural Glossary for Zoology</a>
 * @see <a href="http://www.northernontarioflora.ca/definitions.cfm">Northern Ontario plant database</a>
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/nomenclatural_status.xml">rs.gbif.org vocabulary</a>
 * @see <a href="http://darwin.eeb.uconn.edu/systsem/table.html">Nomenclatural equivalences</a>
 *
 */
public enum NomenclaturalStatus {

  /**
   *
   */
 LEGITIMATE(null, null,    "acceptable", "potentially valid"),

  /**
 * A name that is correctly proposed according to the a Code of Nomenclature.
 * The different codes have various terminology for the same concept:
 * <ul>
 *   <li>Zoology: available name</li>
 *   <li>Botany: validly published name</li>
 *   <li>BioCode: established name</li>
 *   <li>Bacteria: validly published name</li>
 * </ul>
 * An available name is not necessarily the correct name.
 * @See <a href="http://en.wikipedia.org/wiki/Validly_published_name_(botany)">wikipedia</a>
 * @See <a href="http://en.wikipedia.org/wiki/Available_name">wikipedia</a>
 */
 VALIDLY_PUBLISHED(null, null, "available","valid", "established"),

  /**
   * The name is a new combination, i.e. a name change involving the epithet of the basionym.
   * ICBN: Name of the original author being kept within parantheses.
   *
   * A new name is introduced consisting of a new generic name for an earlier named species
   * combined with the existing epitheton of said species.
   * For exxample when Calymmatobacterium granulomatis was renamed Klebsiella granulomatis,
   * it was referred to as Klebsiella granulomatis comb. nov. to denote it is a new combination.
   */
 NEW_COMBINATION("combinatio nova", "comb. nov."),

  /**
   * A scientific name that is created specifically to replace a name which is a junior synonym or homonym.
   * New name designated when a name cannot be used for nomenclaturalpurposes and no type or original material exists.
   * A name established expressly to replace an already established name. A nominal taxon denoted by a new
   * replacement name (nomen novum) has the same name-bearing type as the nominal taxon denoted by the replaced name.
   * ICZN: new name which is expressly proposed as a replacement name for a preoccupied name, automatically takes
   * the same type and type locality. (= a replacement name or substitute name for a preoccupied name).
   * Commonly applied to names proposed to replace junior homonyms. A name proposed as a substitute for a previously
   * published name (ICBN Art. 7.3 and 33.4).
   */
 REPLACEMENT("nomen novum", "nom. nov.",      "replacement name", "substitute name"),

  /**
   * A scientific name that enjoys special nomenclatural protection, i.e. a name conserved in respective code.
   * Names classed as available and valid by action of the ICZN or ICBN exercising its Plenary Powers .
   * Includes rulings to conserve junior/later synonyms in place of rejected forgotten names (nomen oblitum).
   * Such names are entered on the Official Lists.
   */
 CONSERVED("nomen conservandum", "nom. cons.",     "orth. cons."),

  /**
   * Protected names are conserved names applied to a name which has been given precedence over it unused senior synonym
   * or senior homonym relegated to the status of nomen oblitum (see Article 23.9.2).
   */
 PROTECTED("nomen protectum", "nom. prot."),

  /**
   * Corrected names or 'improved' names, available names which are mandatory and allowable emendations
   * of imperfect names (qv) or of taxonomic names higher than family
   * (which are not subject to name form and ending regulations).
   * Do not depend on transfer in taxon rank or assignment. (= an emended name).
   */
 CORRECTED("nomen correctum", "nom. corr.",    "improved"),

  /**
   * The original combination of a newly described any name regardless of the rank.
   */
 ORIGINAL_COMBINATION(null, null),

  /**
   * The original combination of a newly described species.
   * Specific type of ORIGINAL_COMBINATION.
   */
 NEW_SPECIES("species novum", "sp. nov."),

  /**
   * The original combination of a newly described genus.
   * Specific type of ORIGINAL_COMBINATION.
   */
 NEW_GENUS("genus novum", "gen. nov."),

  /**
   * An alternative name given in the original publication before 1953 based on the same type.
   */
 ALTERNATIVE("nomen alternativum", "nom. altern."),

  /**
   * A name, which was published in an obscure publication, was never widely used.
   * In botanical literature, the name remained in obscurity.
   * This has no influence on the formal evaluation of valid publication under ICBN.
   * It may be valuable information nevertheless.
   */
 OBSCURE("nomen obscurum", "nom. obsc."),

  /**
   * A proposed conserved name. See CONSERVED.
   */
 CONSERVED_PROPOSED("nomen conservandum propositum", "orth. cons. prop."),

  /**
   * Provisional name, a name proposed in anticipation of the future acceptance of the taxon concerned,
   * or of a particular circumscription, position, or rank of the taxon (ICBN Art. 34.1).
   */
 PROVISIONAL("nomen provisorium", "nom. prov."),


  /**
   * Formerly, a new taxon with a scant diagnosis/description
   * (e.g., perennial; robust plant; large leaf; aromatic plant; fragrant flower; Red flowers; large fruits; etc.).
   * Such short descriptions/diagnoses were termed as nom. subnud.
   *
   * Occasionally, a short diagnosis may be a key character providing an ID of a taxon.
   * Formally, any arbitrary short description is valid under ICBN ("small fungus, spores not seen").
   * Thus the qualifiers "nom. ambig", "nom. confus.", "nom. obsc." and "nom. subnud.",
   * are applicable either to a currently "botany: valid and legitimate" or "zoology: available" name,
   * or are reasons given for "nom. rej."/"nom. utique rej.".
   * The are not actual status codes, rather highlight potential problems.
   */
 SUBNUDUM("nomen subnudum", "nom. subnud."),

  /**
   * proposed rejected name. Temporary status until the next botanical congress decides about the proposal.
   */
 REJECTED_PROPOSED("nomen rejiciendum propositum", "nom. rej. prop."),

  /**
   * proposed rejected name on the basis of appendix V of ICBN
  */
 REJECTED_OUTRIGHT_PROPOSED("nomen utique rejiciendum propositum", "nom. utique rej. prop."),

  /**
   * A name of uncertain sense, of doubtful validity.
   * E.g. the name Encephalartos tridentatus (Willdenow) Lehmann (Pugillus 6, 1834) is a nomen dubium
   * which may refer to several species of Encephalartos or Macrozamia.
   * ICZN: doubtful or dubious names, names which are not certainly applicable to any known taxon or
   * for which the evidence is insufficient to permit recognition of the taxon to which they belong.
   * May possess availability conducive to uncertainty and instability.
   * Also 'names under enquiry': NOMEN INQUIRENDUM (NOMINA INQUIRENDA).
   *
   * In botany a name whose application is uncertain;
   * the confusion being derived from an incomplete or confusing description.
   * Example: Platanus hispanica auct., non Mill. ex Münchh., nom. dub.
   * The application of the name Platanus hispanica is uncertain, so the name has been rejected
   * in favour of Platanus ×acerifolia (Aiton) Willd., pro. sp.
   */
 DOUBTFUL("nomen dubium", "nom. dub.",     "dubious"),

  /**
   * Ambiguous name, one which has been used so long by different authors in different senses that it has become
   * a persistent cause of error and confusion. It is used in senses other than originally intended,
   * and thus the source of much confusion. A nom. ambig. is a rejected name.
   * Example: Trifolium agrarium L., nom. ambig.
   * The name Trifolium agrarium was misapplied to three taxa, so the name has been rejected in favour of the names
   * Trifolium aureum Pollich, T. dubium Sibth., and T. campestre Schreb., each referring to different taxa.
   */
 AMBIGUOUS("nomen ambigua", "nom. ambig."),

  /**
   * A rejected name that is based on a type consisting of two or more entirely discordant elements,
   * so that it is difficult to select a satisfactory lectotype.
   */
 CONFUSED("nomen confusum", "nom. confus."),

  /**
   * a name that has not been used in the scientific community for more than fifty years after its original proposal.
   * forgotten names, senior synonyms which have remained unused in the literature for many years.
   * Have been treated differently by different editions of the Code, and remain unavailable names.
   */
 FORGOTTEN("nomen oblitum", "nom. obl."),

  /**
   * A name which violated the Code in operation at that time.
   */
 ABORTED("nomen abortivum", "nom. abort."),

  /**
   * In botanical nomenclature, an orthographical variant (abbreviated orth. var.) is a variant spelling
   * of the same name. For example, Hieronima and Hyeronima are orthographical variants of Hieronyma.
   * One of the spellings must be treated as the correct one. In this case, the spelling Hieronyma has been conserved
   * and is to be used as the correct spelling.
   *
   * An inadvertent use of one of the other spellings has no consequences:
   * the name is to be treated as if it were correctly spelled.
   * Any subsequent use is to be corrected. Orthographical variants are treated in Art 61 of the ICBN.
   *
   * In zoology, orthographical variants in the formal sense do not exist;
   * a misspelling or orthographic error is treated as a lapsus, a form of inadvertent error.
   * The first reviser is allowed to choose one variant for mandatory further use, but in other ways,
   * these errors generally have no further formal standing.
   * Inadvertent misspellings are treated in Art. 32-33 of the ICZN.
   */

 ORTHOGRAPHIC_VARIANT("nomen orthographia", "orth. var.",     "spelling variant"),

  /**
   * A name superfluous when published, an unnecessary substitute name.
   * In botany a name for which a validly published name existed previously and should have been adopted,
   * thus the name is deemed nomenclaturally superfluous.
   * Example: Astragalus astragalinus (Hook.) Á. & D. Löve, nom. illeg. superfl.
   * The GRIN database reports that the combination Astragalus astragalinus (Hook.) Á.& D. Löve, is a superfluous name,
   * based on an incorrect basionym, see R.C. Barneby, Taxon, 25(5-6): 628 (1976).
   * The correct basionym is Phaca astragalina DC., not Astragalus astragalinus (DC.) Hook.
   * This taxon is a synonym of Astragalus alpinus L.
   */
 SUPERFLUOUS("nomen superfluum", "nom. superfl."),

  /**
   * A nomen nudum (plural nomina nuda) is used for a name which is unavailable because it does not have a description,
   * reference or indication (specifically a name published before 1931 which fails to conform to Article 12,
   * or after 1930 but fails to conform to Article 13).
   *
   * Nomina nuda and other unavailable names can be made available if they are published again in a way
   * that meets the criteria of availability;
   * however, they are attributed to the author who first made them available, not the person who first used them.
   */
 NUDUM("nomen nudum", "nom. nud.",   "nomen solum","nom. sol."),

  /**
   * Null names, unavailable names which as defined by the Code are non demonstrably intentional changes of
   * an original spelling i.e. a form of incorrect subsequent spelling.
   */
 NULL_NAME("nomen nullum", "nom. null."),

  /**
   * Names in specified ranks included in publications listed as suppressed works (opera utique oppressa; App. VI)
   * are not validly published.
   */
 SUPPRESSED("nomen oppressa", "nom. opp."),

  /**
   * Name rejected outright, i. e. without proposing another name to be conserved in favor of this name
   * (nomen utique rejiciendum). This status applies to explicitly listed protonym names as well as to any
   * combinations based on the protonym. See ICBN (Art. 56.1, Appendix V) because otherwise it would cause a
   * disadvantageous nomenclatural change.
   *
   * Example: Cerastium vulgatum L. 1755, non 1762, nom. utique rej.
   * For a discussion on why this name was rejected, see Brummitt 2000. Taxon 49 (2): 262.
   */
 REJECTED_OUTRIGHT("nomen utique rejiciendum", "nom. utique rej."),

  /**
   * Rejected / surpressed name. Inverse of conserved against
   */
 REJECTED("nomen rejiciendum", "nom. rej."),

  /**
   * A nomen illegitimum is a validly published name, but one that contravenes some of the articles laid down by
   * the International Botanical Congress. The name could be illegitimate because:
   * <ul>
   *   <li>(article 52) it was superfluous at its time of publication, i.e., the taxon (as represented by the type) already has a name</li>
   *   <li>(articles 53 and 54) the name has already been applied to another plant (a homonym)</li>
   * </ul>
   * For the procedure of rejecting otherwise legitimate names, see conserved name.
   */
 ILLEGITIMATE("nomen illegitimum", "nom. illeg."),

  /**
   * A name that was not validly published according to the rules of the code,
   * or a name that was not accepted by the author in the original publication, for example,
   * if the name was suggested as a synonym of an accepted name.
   * In zoology this is called an UNAVAILABLE name.
   * Example: Linaria vulgaris Hill, nom. inval.
   * Many names published by John Hill between 1753 and 1757 were not accepted as validly published.
   */
 INVALID("nomen invalidum", "nom. inval.",    "unavailable"),

  /**
   * denied names, unavailable names which are incorrect original spellings as defined by the Code.
   * Subset of nom.inval. based only on spellings
   */
 DENIED("nomen negatum", "nom. neg.");



  private static final Set VALID_VALUES = ImmutableSet.of(VALIDLY_PUBLISHED, LEGITIMATE, NEW_COMBINATION, REPLACEMENT,
    NEW_COMBINATION, NEW_GENUS, NEW_SPECIES,SUBNUDUM,CONSERVED, PROTECTED, CORRECTED,ALTERNATIVE, CONSERVED_PROPOSED,
    PROVISIONAL);
  private static final Set DOUBTFUL_VALUES = ImmutableSet.of(DOUBTFUL, OBSCURE);

  private static final Pattern NORMALIZE_TERM = Pattern.compile("[._ -]+");
  private static String normalize(String x) {
    return NORMALIZE_TERM.matcher(x.toUpperCase()).replaceAll("");
  }

  private static final Map<String, NomenclaturalStatus> LOOKUP;
  static {
    Map<String, NomenclaturalStatus> lookup = Maps.newHashMap();
    for (NomenclaturalStatus n : values()) {
      lookup.put(normalize(n.name()), n);
      if (n.getLatinLabel() != null) {
        lookup.put(normalize(n.getLatinLabel()), n);
        lookup.put(normalize(n.getLatinLabel().replace("nomen ", "")), n);
      }
      if (n.getAbbreviatedLabel() != null) {
        lookup.put(normalize(n.getAbbreviatedLabel()), n);
      }
      if (n.alternatives != null) {
        for (String alt : n.alternatives) {
          lookup.put(normalize(alt), n);
        }
      }
    }
    LOOKUP = ImmutableMap.copyOf(lookup);
  }


  /**
   * Tries to case insenitively interpret a nomenclatural status given as a string appliying the enums name,
   * the latin and abbreviated name of a term.
   *
   * @param nomStatus
   * @return
   *
   * @deprecated use NomStatusParser instead
   */
  @Deprecated
  public static NomenclaturalStatus fromString(String nomStatus) {
    if (Strings.isNullOrEmpty(nomStatus)) {
      return null;
    }
    return LOOKUP.get(normalize(nomStatus));
  }



  private final String latin;
  private final String abbreviated;
  private final String[] alternatives;

  private NomenclaturalStatus(String latin, String abbreviated, String ... alternatives) {
    this.latin = latin;
    this.abbreviated = abbreviated;
    this.alternatives = alternatives;
  }

  public String getLatinLabel() {
    return latin;
  }

  /**
   * The abbreviated status name, often used in botany.
   * For example nom. inval.
   * @return
   */
  @Nullable
  public String getAbbreviatedLabel() {
    return abbreviated;
  }

  /**
   * A vague grouping of nomenclatural status terms including all that can be used to name a correct/valid taxon.
   * Specifically avoid the confusing terms valid, accepted or correct here as they mean different things in the
   * different codes.
   */
  public boolean isGood() {
    return VALID_VALUES.contains(this);
  }

  public boolean isDoubtful() {
    return DOUBTFUL_VALUES.contains(this);
  }

  /**
   * Opposite of good and not doubtful.
   */
  public boolean isBad() {
    return !(isGood() || isDoubtful());
  }
}
