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
package org.gbif.api.model.checklistbank;

import org.gbif.api.jackson.RankSerde;
import org.gbif.api.util.UnicodeUtils;
import org.gbif.api.vocabulary.NamePart;
import org.gbif.api.vocabulary.NameType;
import org.gbif.api.vocabulary.Rank;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A container of a taxon name that is atomised into its relevant, separate parts.
 * <br>
 * Hybrid formulas that consist of multiple genera, binomials or species
 * epithets are relatively poorly represented. A simple boolean flag indicates a
 * hybrid formula, e.g. Polygala vulgaris × Polygala epinema but not named
 * hybrids that are have an × or notho as part of a well-formed
 * mono/bi/trinomial e.g. ×Polygala vulgaris. In the case of hybrid formulas,
 * i.e. isHybrid=true, the first name in the formula is tried to be kept, at
 * least the genus.
 * <p/>
 * A container of a taxon name that is only atomised into three name parts maximum plus rank and a notho property
 * indicating the name part of named hybrids that is considered to be the hybrid. No authorship is kept. For subgenera
 * we don't use parentheses to indicate the subgenus, but use explicit rank markers instead.
 */
@SuppressWarnings("unused")
public class ParsedName {

  public static final Character HYBRID_MARKER = '×';
  private static final String HYBRID_MARKER_STR = HYBRID_MARKER.toString();

  private Integer key;
  private String scientificName;
  @JsonProperty("rankMarker")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonSerialize(using = RankSerde.RankJsonSerializer.class)
  @JsonDeserialize(using = RankSerde.RankJsonDeserializer.class)
  private Rank rank;
  private NameType type;
  private String genusOrAbove;
  private String infraGeneric;
  private String specificEpithet;
  private String infraSpecificEpithet;
  private String cultivarEpithet;
  private String strain;
  private NamePart notho;
  private String authorship;
  private String year;
  private String bracketAuthorship;
  private String bracketYear;
  private String sensu;
  private boolean parsed = true;
  private boolean parsedPartially = false;

  /**
   * nomenclatural status note.
   */
  private String nomStatus;
  private String remarks;

  public ParsedName() {
  }

  public ParsedName(
    NameType type,
    String genusOrAbove,
    String infraGeneric,
    String specificEpithet,
    String infraSpecificEpithet,
    NamePart notho,
    Rank rank,
    String authorship,
    String year,
    String bracketAuthorship,
    String bracketYear,
    String cultivarEpithet,
    String strain,
    String sensu,
    String nomStatus,
    String remarks
  ) {
    this.type = type;
    this.genusOrAbove = genusOrAbove;
    this.infraGeneric = infraGeneric;
    this.specificEpithet = specificEpithet;
    this.infraSpecificEpithet = infraSpecificEpithet;
    this.notho = notho;
    this.rank = rank;
    this.authorship = authorship;
    this.year = year;
    this.bracketAuthorship = bracketAuthorship;
    this.bracketYear = bracketYear;
    this.cultivarEpithet = cultivarEpithet;
    this.strain = strain;
    this.sensu = sensu;
    this.nomStatus = nomStatus;
    this.remarks = remarks;
  }

  /**
   * The original author of this name, e.g. basionym or recombination author
   */
  @Schema(description = "The original author of this name, e.g. basionym or recombination author.")
  public String getAuthorship() {
    return authorship;
  }

  public void setAuthorship(String authorship) {
    this.authorship = authorship;
  }

  /**
   * The authorship of the original name, i.e. basionym, given in brackets.
   */
  @Schema(description = "The authorship of the original name, i.e. basionym, given in brackets.")
  public String getBracketAuthorship() {
    return bracketAuthorship;
  }

  public void setBracketAuthorship(String bracketAuthorship) {
    this.bracketAuthorship = bracketAuthorship;
  }

  /**
   * The code relevant year of publication of the original name, i.e. basionym, given in brackets.
   */
  @Schema(description = "The code relevant year of publication of the original name, i.e. basionym, given in brackets.")
  public String getBracketYear() {
    return bracketYear;
  }

  public void setBracketYear(String bracketYear) {
    this.bracketYear = bracketYear;
  }

  /**
   * The cultivar, cultivar group or grex part of a cultivated plant name.
   * If given the name should be of type NameType.CULTIVAR
   */
  @Schema(description = "The cultivar, cultivar group or grex part of a cultivated plant name.\n\n" +
    "If given the name should be of type CULTIVAR")
  public String getCultivarEpithet() {
    return cultivarEpithet;
  }

  public void setCultivarEpithet(String cultivarEpithet) {
    this.cultivarEpithet = cultivarEpithet;
  }

  /**
   * The strain or isolate name. Usually a capital collection code string followed by an accession number.
   * See <a href="http://www.bacterio.net/-collections.html">List of culture collection codes</a>
   * If given the name should be of type NameType.STRAIN
   */
  @Schema(description = "The strain or isolate name. Usually a capital collection code string followed by an accession number.\n\n" +
    "See [List of culture collection codes](http://www.bacterio.net/-collections.html).  If given the name should be of type STRAIN.")
  public String getStrain() {
    return strain;
  }

  public void setStrain(String strain) {
    this.strain = strain;
  }

  /**
   * The genus part of a bi/trinomial or the monomial in case of names of higher ranks
   */
  @Schema(description = "The genus part of a bi/trinomial or the monomial in case of names of higher ranks.")
  public String getGenusOrAbove() {
    return genusOrAbove;
  }

  /**
   * The infrageneric part of a name, often given in parentheses between genus and species epithet, e.g. for a subgenus
   */
  @Schema(description = "The infrageneric part of a name, often given in parentheses between genus and species epithet, " +
    "e.g. for a subgenus")
  public String getInfraGeneric() {
    return infraGeneric;
  }

  @Schema(description = "The infraspecific part of a name.")
  public String getInfraSpecificEpithet() {
    return infraSpecificEpithet;
  }

  /**
   * Any nomenclatoral remarks given in this name, e.g. nom. illeg.
   */
  @Schema(description = "Any nomenclatoral remarks given in this name, e.g. nom. illeg.")
  public String getNomStatus() {
    return nomStatus;
  }

  public void setNomStatus(String nomStatus) {
    this.nomStatus = nomStatus;
  }

  /**
   * For hybrid names notho indicates which part of the name is considered a hybrid,
   * i.e. genus, species or infraspecific epithet.
   */
  @Schema(description = "For hybrid names notho indicates which part of the name is considered a hybrid, " +
    "i.e. genus, species or infraspecific epithet.")
  public NamePart getNotho() {
    return notho;
  }

  public void setNotho(NamePart notho) {
    this.notho = notho;
  }

  /**
   * Any further remarks found
   */
  @Schema(description = "Any further remarks found.")
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * Taxon concept references as part of the name,
   * e.g. "MSW2005" for Gorilla gorilla (Savage, 1847) sec. MSW2005
   */
  @Schema(description = "Taxon concept references as part of the name, e.g. “MSW2006” for *Gorilla gorilla* " +
    "(Savage, 1847) sec. MSW2005")
  public String getSensu() {
    return sensu;
  }

  public void setSensu(String sensu) {
    this.sensu = sensu;
  }

  @Schema(description = "The specific epithet of the name")
  public String getSpecificEpithet() {
    return specificEpithet;
  }

  /**
   * @return the terminal epithet, infraspecific epithet if existing, the species epithet or null
   */
  @Schema(description = "The terminal epithet, infraspecific epithet if existing, the species epithet or null")
  @JsonIgnore
  public String getTerminalEpithet() {
    return infraSpecificEpithet == null ? specificEpithet : infraSpecificEpithet;
  }

  /**
   * A coarse classification of names helping to deal with different syntactical name string structures.
   */
  @Schema(description = "A coarse classification of names helping to deal with different syntactical name string structures.")
  public NameType getType() {
    return type;
  }

  public void setType(NameType type) {
    this.type = type;
  }

  /**
   * The year of publication as given in the authorship.
   */
  @Schema(description = "The year of publication as given in the authorship.")
  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  @JsonIgnore
  public boolean hasAuthorship() {
    return authorship != null || year != null || bracketAuthorship != null || bracketYear != null;
  }

  @Schema(description = "The taxonomic rank of the name.")
  public void setRank(Rank rank) {
    this.rank = rank;
  }

  @Schema(description = "The key for this parsed name object.")
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * The exact verbatim, full scientific name as given before parsing.
   */
  @Schema(description = "The exact verbatim, full scientific name as given before parsing.")
  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * The GBIF name parser cannot always parse the entire name.
   * This is often the case when taxonomic, nomenclatural or identification notes are added to the end of the name.
   * In such cases when the name was only partially parsed (NameUsageIssue.PARTIALLY_PARSABLE)
   * this flag should be true.
   *
   * If the name was not parsed at all this will be false.
   *
   * @return true if name was only partially parsed, false otherwise
   */
  @Schema(description = "The GBIF name parser cannot always parse the entire name.\n\n" +
    "This is often the case when taxonomic, nomenclatural or identification notes are added to the end of the name. " +
    "In such cases when the name was only partially parsed (NameUsageIssue.PARTIALLY_PARSABLE) " +
    "this flag should be true.\n\n" +
    "If the name was not parsed at all this will be false.")
  public boolean isParsedPartially() {
    return parsedPartially;
  }

  public void setParsedPartially(boolean parsedPartially) {
    this.parsedPartially = parsedPartially;
  }

  /**
   * A flag indicating if a name could not be parsed at all.
   * If false, only the scientific name, rank and potentially the name type is given.
   */
  @Schema(description = "A flag indicating if a name could not be parsed at all.\n\n" +
    "If false, only the scientific name, rank and potentially the name type is given.")
  public boolean isParsed() {
    return parsed;
  }

  public void setParsed(boolean parsed) {
    this.parsed = parsed;
  }

  /**
   * @return The full concatenated authorship or null if it is a hybrid
   */
  public String authorshipComplete() {
    StringBuilder sb = new StringBuilder();
    appendAuthorship(sb);
    return sb.toString().trim();
  }

  /**
   * build a name controlling all available flags for name parts to be included in the resulting name.
   *
   * @param hybridMarker    include the hybrid marker with the name if existing
   * @param rankMarker      include the infraspecific or infrageneric rank marker with the name if existing
   * @param authorship      include the names authorship (authorteam and year)
   * @param infrageneric include the infrageneric name in brackets for species or infraspecies
   * @param genusForInfrageneric include the genus name in front of an infrageneric name (not a species)
   * @param abbreviateGenus if true abreviate the genus with its first character
   * @param decomposition   decompose unicode ligatures into their corresponding ascii ones, e.g. æ beomes ae
   * @param asciiOnly       transform unicode letters into their corresponding ascii ones, e.g. ø beomes o and ü u
   * @param showIndet       if true include the rank marker for incomplete determinations, for example Puma spec.
   * @param nomNote         include nomenclatural notes
   * @param remarks         include informal remarks
   */
  public String buildName(
    boolean hybridMarker,
    boolean rankMarker,
    boolean authorship,
    boolean infrageneric,
    boolean genusForInfrageneric,
    boolean abbreviateGenus,
    boolean decomposition,
    boolean asciiOnly,
    boolean showIndet,
    boolean nomNote,
    boolean remarks,
    boolean showSensu,
    boolean showCultivar,
    boolean showStrain
  ) {
    StringBuilder sb = new StringBuilder();

    if (NameType.CANDIDATUS == type) {
      sb.append("Candidatus ");
    }

    if (genusOrAbove != null && (genusForInfrageneric || infraGeneric == null || specificEpithet != null)) {
      if (hybridMarker && NamePart.GENERIC == notho) {
        sb.append(HYBRID_MARKER);
      }
      if (abbreviateGenus) {
        sb.append(genusOrAbove.charAt(0)).append('.');
      } else {
        sb.append(genusOrAbove);
      }
    }
    if (specificEpithet == null) {
      if (Rank.SPECIES == rank) {
        // no species epitheton given, but rank=species. Indetermined species!
        if (showIndet(showIndet)) {
          sb.append(" spec.");
        }
      } else if (rank != null && rank.isInfraspecific()) {
        // no species epitheton given, but rank below species. Indetermined!
        if (showIndet(showIndet)) {
          sb.append(' ');
          sb.append(rank.getMarker());
        }
      } else if (infraGeneric != null) {
        // this is the terminal name part - always show it!
        if (rankMarker && rank != null) {
          // If we know the rank we use explicit rank markers
          // this is how botanical infrageneric names are formed, see http://www.iapt-taxon.org/nomen/main.php?page=art21
          sb.append(' ');
          appendRankMarker(sb, rank);
          sb.append(infraGeneric);

        } else {
          if (genusForInfrageneric && genusOrAbove != null) {
            // if we have shown the genus already and we do not know the rank we use parenthesis to indicate an infrageneric
            sb.append(" (")
            .append(infraGeneric)
            .append(")");
          } else {
            // no genus shown yet, just show the plain infrageneric name
            sb.append(infraGeneric);
          }
        }
      }
      // genus/infrageneric authorship
      if (authorship) {
        appendAuthorship(sb);
      }
    } else {
      if (infrageneric && infraGeneric != null && (rank == null || rank == Rank.GENUS)) {
        // only show subgenus if requested
        sb.append(" (");
        sb.append(infraGeneric);
        sb.append(')');
      }

      // species part
      sb.append(' ');
      if (hybridMarker && NamePart.SPECIFIC == notho) {
        sb.append(HYBRID_MARKER);
      }
      String epi = specificEpithet.replaceAll("[ _-]", "-");
      sb.append(epi);

      if (infraSpecificEpithet == null) {
        // Indetermined? Only show indet cultivar marker if no cultivar epithet exists
        if (showIndet(showIndet) && rank != null && rank.isInfraspecific() && (Rank.CULTIVAR != rank || cultivarEpithet == null)) {
          // no infraspecific epitheton given, but rank below species. Indetermined!
          sb.append(' ');
          sb.append(rank.getMarker());
        }

        // species authorship
        if (authorship) {
          appendAuthorship(sb);
        }
      } else {
        // infraspecific part
        sb.append(' ');
        if (hybridMarker && NamePart.INFRASPECIFIC == notho) {
          if (rankMarker) {
            sb.append("notho");
          } else {
            sb.append(HYBRID_MARKER);
          }
        }
        if (rankMarker) {
          appendRankMarker(sb, rank);
        }
        epi = infraSpecificEpithet.replaceAll("[ _-]", "-");
        sb.append(epi);
        // non autonym authorship ?
        if (authorship && !isAutonym()) {
          appendAuthorship(sb);
        }
      }
    }

    // add cultivar name
    if (showStrain && strain != null) {
      sb.append(" ");
      sb.append(strain);
    }

    // add cultivar name
    if (showCultivar && cultivarEpithet != null) {
      sb.append(" '");
      sb.append(cultivarEpithet);
      sb.append("'");
    }

    // add sensu/sec reference
    if (showSensu && sensu != null) {
      sb.append(" ");
      sb.append(sensu);
    }

    // add nom status
    if (nomNote && nomStatus != null) {
      sb.append(", ");
      sb.append(nomStatus);
    }

    // add remarks
    if (remarks && this.remarks != null) {
      sb.append(" [");
      sb.append(this.remarks);
      sb.append("]");
    }

    String name = sb.toString().trim();
    if (decomposition) {
      name = UnicodeUtils.decompose(name);
    }
    if (asciiOnly) {
      name = UnicodeUtils.ascii(name);
    }

    return StringUtils.trimToNull(name);
  }

  private boolean showIndet(boolean showIndet) {
    return showIndet && (type == null || type.isParsable());
  }

  private void appendRankMarker(StringBuilder sb, Rank rank) {
    if (rank != null && !rank.isUncomparable() && rank.getMarker() != null) {
      sb.append(rank.getMarker());
      sb.append(' ');
    }
  }

  private void appendAuthorship(StringBuilder sb) {
    if (bracketAuthorship == null) {
      if (bracketYear != null) {
        sb.append(" (");
        sb.append(bracketYear);
        sb.append(")");
      }
    } else {
      sb.append(" (");
      sb.append(bracketAuthorship);
      if (bracketYear != null) {
        sb.append(", ");
        sb.append(bracketYear);
      }
      sb.append(")");
    }
    if (authorship != null) {
      sb.append(" ").append(authorship);
    }
    if (year != null) {
      sb.append(", ");
      sb.append(year);
    }
  }

  /**
   * The canonical name sensu strictu with nothing else but 3 name parts at max (genus, species, infraspecific). No
   * rank or hybrid markers and no authorship, cultivar or strain information.
   * Infrageneric names are represented without a leading genus.
   * Unicode characters will be replaced by their matching ASCII characters.
   * <p/>
   * For example:
   * Abies alba
   * Abies alba alpina
   * Abies Bracteata
   * Heucherella tiarelloides
   *
   * @return the 1, 2 or 3 parted name as a single string
   */
  @Schema(description = "The canonical name *sensu strictu* with nothing else but three name parts at most (genus, " +
    "species, infraspecific).\n\n" +
    "No rank or hybrid markers and no authorship, cultivar or strain information.\n\n" +
    "Infrageneric names are represented without a leading genus.\n\n" +
    "Unicode characters are replaced by their matching ASCII characters.")
  @JsonProperty
  public String canonicalName() {
    return buildName(false, false, false, false, false, false, true, true, true, false, false, false, false, false);
  }

  /**
   * The code-compliant, canonical name with three name parts at max (genus, species, infraspecific), a rank marker for
   * infraspecific names and cultivar or strain epithets. The canonical name can be a 1, 2 or 3 parted name, but does
   * not include any informal notes or authorships. Notho taxa will have the hybrid marker.
   * Unicode characters will be replaced by their matching ASCII characters.
   * <p/>
   * For example:
   * Abies alba
   * Abies alba subsp. alpina
   * Abies sect. Bracteata
   * ×Heucherella tiarelloides
   *
   * @return the 1,2 or 3 parted name as a single string
   */
  @Schema(description = "The code-compliant, canonical name with three name parts at max (genus, species, " +
    "infraspecific), a rank marker for infraspecific names and cultivar or strain epithets. The canonical name can " +
    "be a 1, 2 or 3 parted name, but does not include any informal notes or authorships. Notho taxa will have the " +
    "hybrid marker.\n\n" +
    "Unicode characters will be replaced by their matching ASCII characters.")
  @JsonProperty
  public String canonicalNameWithMarker() {
    return buildName(true, true, false, false, false, false, true, true, true, false, false, false, true, true);
  }

  /**
   * The code-compliant, canonical name with rank and hybrid marker, authorship and cultivar or strain name included.
   * Informal or nomenclatoral notes, concept references, subgenus and non-terminal authorships are removed.
   * @return the 1,2 or 3 parted name as a single string
   */
  @Schema(description = "The code-compliant, canonical name with rank and hybrid marker, authorship and cultivar " +
    "or strain name included. Informal or nomenclatoral notes, concept references, subgenus and non-terminal " +
    "authorships are removed.")
  @JsonProperty
  public String canonicalNameComplete() {
    return buildName(true, true, true, false, true, false, true, false, true, false, false, false, true, true);
  }

  /**
   * @return the species binomial if this parsed name is a species or below. Or null in case its superspecific
   */
  public String canonicalSpeciesName() {
    if (genusOrAbove != null && specificEpithet != null) {
      return genusOrAbove + " " + specificEpithet;
    }
    return null;
  }

  /**
   * @return the name with all details that exist.
   */
  public String fullName() {
    return buildName(true, true, true, true, true, false, false, false, true, true, true, true, true, true);
  }

  @JsonIgnore
  public Integer getBracketYearInt() {
    try {
      return Integer.parseInt(bracketYear);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  /**
   * @return rank as enumeration or null
   */
  public Rank getRank() {
    return rank;
  }

  @JsonIgnore
  public Integer getYearInt() {
    try {
      return Integer.parseInt(year);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  @JsonIgnore
  public boolean isAutonym() {
    return specificEpithet != null && specificEpithet.equals(infraSpecificEpithet);
  }

  @JsonIgnore
  public boolean isBinomial() {
    return genusOrAbove != null && specificEpithet != null;
  }

  @JsonIgnore
  public boolean isHybridFormula() {
    return NameType.HYBRID == type;
  }

  /**
   * @return true for names with an infraspecifc rank but missing lowest name part. E.g. Coccyzuz americanus ssp. or
   *         Asteraceae
   *         spec. but not Maxillaria sect. Acaules
   */
  @JsonIgnore
  public boolean isIndetermined() {
    return rank != null && isParsed() && isParsableType() && rank.notOtherOrUnknown() && (
           (rank.isInfragenericStrictly() && infraGeneric == null)
        || (rank.isSpeciesAggregateOrBelow() && specificEpithet == null)
        || (rank.isInfraspecific() && infraSpecificEpithet == null)
    );
  }

  @JsonIgnore
  public boolean isParsableType() {
    return type != null && type.isParsable();
  }

  @JsonIgnore
  public boolean isQualified() {
    return (authorship != null && !authorship.isEmpty())
           || (year != null && !year.isEmpty())
           || (bracketAuthorship != null && !bracketAuthorship.isEmpty())
           || (bracketYear != null && !bracketYear.isEmpty());
  }

  /**
   * @return true if a bracket authorship is given, indicating that the name has been subsequently recombined.
   */
  @JsonIgnore
  public boolean isRecombination() {
    return (!StringUtils.isBlank(bracketAuthorship) || !StringUtils.isBlank(bracketYear));
  }


  public void setGenusOrAbove(String genusOrAbove) {
    if (genusOrAbove != null && genusOrAbove.startsWith(HYBRID_MARKER.toString())) {
      this.genusOrAbove = genusOrAbove.substring(1);
      notho = NamePart.GENERIC;
    } else {
      this.genusOrAbove = genusOrAbove;
    }
  }

  public void setHybridFormula(boolean hybrid) {
    if (hybrid) {
      type = NameType.HYBRID;
    } else if (NameType.HYBRID == type) {
      type = null;
    }
  }

  public void setInfraGeneric(String infraGeneric) {
    if (infraGeneric != null && infraGeneric.startsWith(HYBRID_MARKER_STR)) {
      this.infraGeneric = infraGeneric.substring(1);
      notho = NamePart.INFRAGENERIC;
    } else {
      this.infraGeneric = infraGeneric;
    }
  }

  public void setInfraSpecificEpithet(String infraSpecies) {
    if (infraSpecies != null && infraSpecies.startsWith(HYBRID_MARKER_STR)) {
      this.infraSpecificEpithet = infraSpecies.substring(1);
      this.notho = NamePart.INFRASPECIFIC;
    } else {
      this.infraSpecificEpithet = infraSpecies;
    }
  }

  public void setSpecificEpithet(String species) {
    if (species != null && species.startsWith(HYBRID_MARKER_STR)) {
      specificEpithet = species.substring(1);
      notho = NamePart.SPECIFIC;
    } else {
      specificEpithet = species;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParsedName that = (ParsedName) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(scientificName, that.scientificName) &&
      rank == that.rank &&
      type == that.type &&
      Objects.equals(genusOrAbove, that.genusOrAbove) &&
      Objects.equals(infraGeneric, that.infraGeneric) &&
      Objects.equals(specificEpithet, that.specificEpithet) &&
      Objects.equals(infraSpecificEpithet, that.infraSpecificEpithet) &&
      Objects.equals(cultivarEpithet, that.cultivarEpithet) &&
      Objects.equals(strain, that.strain) &&
      Objects.equals(authorship, that.authorship) &&
      Objects.equals(year, that.year) &&
      Objects.equals(bracketAuthorship, that.bracketAuthorship) &&
      Objects.equals(bracketYear, that.bracketYear);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(key, scientificName, rank, type, genusOrAbove, infraGeneric, specificEpithet,
        infraSpecificEpithet, cultivarEpithet, strain, authorship, year, bracketAuthorship,
        bracketYear);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(scientificName);
    if (key != null) {
      sb.append(" [");
      sb.append(key);
      sb.append("]");
    }
    if (genusOrAbove != null) {
      sb.append(" G:").append(genusOrAbove);
    }
    if (infraGeneric != null) {
      sb.append(" IG:").append(infraGeneric);
    }
    if (specificEpithet != null) {
      sb.append(" S:").append(specificEpithet);
    }
    if (rank != null) {
      sb.append(" R:").append(rank);
    }
    if (infraSpecificEpithet != null) {
      sb.append(" IS:").append(infraSpecificEpithet);
    }
    if (cultivarEpithet != null) {
      sb.append(" CV:").append(cultivarEpithet);
    }
    if (strain != null) {
      sb.append(" STR:").append(strain);
    }
    if (authorship != null) {
      sb.append(" A:").append(authorship);
    }
    if (year != null) {
      sb.append(" Y:").append(year);
    }
    if (bracketAuthorship != null) {
      sb.append(" BA:").append(bracketAuthorship);
    }
    if (bracketYear != null) {
      sb.append(" BY:").append(bracketYear);
    }
    if (type != null) {
      sb.append(" [");
      sb.append(type);
      sb.append("]");
    }
    return isHybridFormula() ? " [hybrid]" : sb.toString();
  }
}
