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
package org.gbif.api.model.checklistbank;

import org.gbif.api.vocabulary.NamePart;
import org.gbif.api.vocabulary.NameType;
import org.gbif.api.vocabulary.NomenclaturalCode;
import org.gbif.api.vocabulary.Rank;

import javax.annotation.Nullable;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Objects.equal;

/**
 * A container of a taxon name that is atomised into it's relevant separate parts.
 * Hybrid formulas that consist of multiple genera, binomials or species
 * epitheta are relatively poor represented. A simple boolean flag indicates a
 * hybrid formula, e.g. Polygala vulgaris X Polygala epinema but not named
 * hybrids that are have an x or notho as part of a well formed
 * mono/bi/trinomial e.g. xPolygala vulgaris. In the case of hybrid formulas,
 * i.e. isHybrid=true, the first name in the formula is tried to be kept, at
 * least the genus.
 * <p/>
 * A container of a taxon name that is only atomised into three name parts maximum plus rank and a notho property
 * indicating the name part of named hybrids that is considered to be the hybrid. No authorship is kept. For subgenera
 * we don't use parenthesis to indicate the subgenus, but use explicit rank markers instead.
 */
public class ParsedName {

  private static final Character HYBRID_MARKER = '×';
  private static final String HYBRID_MARKER_STR = HYBRID_MARKER.toString();

  private Integer key;
  private String scientificName;
  private NameType type;
  private String genusOrAbove;
  private String infraGeneric;
  private String specificEpithet;
  private String infraSpecificEpithet;
  private String cultivarEpithet;
  private String strain;
  private NamePart notho;
  private String rank;
  private boolean authorsParsed = true;
  // the original author of this name, e.g basionym or current author for new names
  private String authorship;
  private String year;
  // original bracket authorship. Only used in addition to current author
  // so for new names when the original author = "current" author the property bracketAuthorship is not set!
  private String bracketAuthorship;
  private String bracketYear;
  private String sensu;
  /**
   * nomenclatural status note.
   */
  private String nomStatus;
  private String remarks;

  /**
   * Replaces all digraphs and ligatures with their underlying 2 latin letters.
   *
   * @param x the string to decompose
   */
  public static String decompose(String x) {
    if (x == null) {
      return x;
    }
    // god knows why, but on OSX with java 1.5&6 at least this doesnt work as it should! Use custom replace method
    // return Normalizer.normalize(x, Form.NFD);
    return x.replaceAll("æ", "ae")
      .replaceAll("Æ", "Ae")
      .replaceAll("œ", "oe")
      .replaceAll("Œ", "Oe")
      .replaceAll("Ĳ", "Ij")
      .replaceAll("ĳ", "ij")
      .replaceAll("ǈ", "Lj")
      .replaceAll("ǉ", "lj")
      .replaceAll("ȸ", "db")
      .replaceAll("ȹ", "qp")
      .replaceAll("ß", "ss")
      .replaceAll("ﬆ", "st")
      .replaceAll("ﬀ", "ff")
      .replaceAll("ﬁ", "fi")
      .replaceAll("ﬂ", "fl")
      .replaceAll("ﬃ", "ffi")
      .replaceAll("ﬄ", "ffl");
  }

  public ParsedName() {
  }

  public ParsedName(
    NameType type,
    String genusOrAbove,
    String infraGeneric,
    String specificEpithet,
    String infraSpecificEpithet,
    NamePart notho,
    String rank,
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

  public String getAuthorship() {
    return authorship;
  }

  public void setAuthorship(String authorship) {
    this.authorship = authorship;
  }

  public String getBracketAuthorship() {
    return bracketAuthorship;
  }

  public void setBracketAuthorship(String bracketAuthorship) {
    this.bracketAuthorship = bracketAuthorship;
  }

  public String getBracketYear() {
    return bracketYear;
  }

  public void setBracketYear(String bracketYear) {
    this.bracketYear = bracketYear;
  }

  public String getCultivarEpithet() {
    return cultivarEpithet;
  }

  public void setCultivarEpithet(String cultivarEpithet) {
    this.cultivarEpithet = cultivarEpithet;
  }

  /**
   * The strain or isolate name. Usually a capital collection code string followed by an accession number.
   * See <a href="http://www.bacterio.net/-collections.html">List of culture collection codes</a>
   */
  public String getStrain() {
    return strain;
  }

  public void setStrain(String strain) {
    this.strain = strain;
  }

  public String getGenusOrAbove() {
    return genusOrAbove;
  }

  public String getInfraGeneric() {
    return infraGeneric;
  }

  public String getInfraSpecificEpithet() {
    return infraSpecificEpithet;
  }

  public String getNomStatus() {
    return nomStatus;
  }

  public void setNomStatus(String nomStatus) {
    this.nomStatus = nomStatus;
  }

  public NamePart getNotho() {
    return notho;
  }

  public void setNotho(NamePart notho) {
    this.notho = notho;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getSensu() {
    return sensu;
  }

  public void setSensu(String sensu) {
    this.sensu = sensu;
  }

  public String getSpecificEpithet() {
    return specificEpithet;
  }

  public NameType getType() {
    return type;
  }

  public void setType(NameType type) {
    this.type = type;
  }

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

  public boolean isAuthorsParsed() {
    return authorsParsed;
  }

  public void setAuthorsParsed(boolean authorsParsed) {
    this.authorsParsed = authorsParsed;
  }

  public void setRank(Rank rank) {
    this.rank = rank == null ? null : rank.getMarker();
  }

  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * @return The full concatenated authorship or null if it is a hybrid
   */
  public String authorshipComplete() {
    StringBuilder sb = new StringBuilder();
    appendAuthorship(sb);
    return sb.toString().trim();
  }

  public String buildName(
    boolean hybridMarker,
    boolean rankMarker,
    boolean authorship,
    boolean subgenus,
    boolean abbreviateGenus,
    boolean decomposition,
    boolean showIndet,
    boolean nomNote,
    boolean remarks,
    boolean showSensu,
    boolean showCultivar,
    boolean showStrain
  ) {
    return buildName(hybridMarker,
                     rankMarker,
                     authorship,
                     subgenus,
                     abbreviateGenus,
                     decomposition,
                     showIndet,
                     nomNote,
                     remarks,
                     showSensu,
                     showCultivar,
                     showStrain,
                     null);
  }

  /**
   * build a name controlling all available flags for name parts to be included in the resulting name.
   *
   * @param hybridMarker    include the hybrid marker with the name if existing
   * @param rankMarker      include the infraspecific or infrageneric rank marker with the name if existing
   * @param authorship      include the names authorship (authorteam and year)
   * @param subgenus        include the subgenus in brackets in species or subspecies
   * @param abbreviateGenus if true abreviate the genus with its first character
   * @param decomposition   decompose unicode letters into their corresponding ascii ones, e.g. æ beomes ae
   * @param showIndet       if true include the rank marker for incomplete determinations, for example Puma spec.
   * @param nomNote         include nomenclatural notes
   * @param remarks         include informal remarks
   */
  public String buildName(
    boolean hybridMarker,
    boolean rankMarker,
    boolean authorship,
    boolean subgenus,
    boolean abbreviateGenus,
    boolean decomposition,
    boolean showIndet,
    boolean nomNote,
    boolean remarks,
    boolean showSensu,
    boolean showCultivar,
    boolean showStrain,
    @Nullable NomenclaturalCode code
  ) {
    StringBuilder sb = new StringBuilder();
    Rank rnk = getRank();

    if (NameType.CANDIDATUS == type) {
      sb.append("Candidatus ");
    }

    if (genusOrAbove != null) {
      if (hybridMarker && NamePart.GENERIC == notho) {
        sb.append(HYBRID_MARKER);
      }
      if (abbreviateGenus) {
        sb.append(genusOrAbove.substring(0, 1)).append('.');
      } else {
        sb.append(genusOrAbove);
      }
    }
    if (specificEpithet == null) {
      if (Rank.SPECIES == rnk) {
        // no species epitheton given, but rank=species. Indetermined species!
        if (showIndet) {
          sb.append(" spec.");
        }
      } else if (rnk != null && rnk.isInfraspecific()) {
        // no species epitheton given, but rank below species. Indetermined!
        if (showIndet) {
          sb.append(' ');
          sb.append(rnk.getMarker());
        }
      } else if (infraGeneric != null) {
        // this is the terminal name part - always show it!
        if (rankMarker && rank != null) {
          // If we know the rank we dont use parenthesis to indicate an infrageneric, but use explicit rank markers instead
          // this is how botanical infrageneric names are formed, see http://www.iapt-taxon.org/nomen/main.php?page=art21
          sb.append(' ')
            .append(rank)
            .append(' ')
            .append(infraGeneric);
        } else {
          sb.append(" (")
            .append(infraGeneric)
            .append(")");
        }
      }
      // genus/infrageneric authorship
      if (authorship) {
        appendAuthorship(sb);
      }
    } else {
      if (subgenus && infraGeneric != null && (rank == null || getRank() == Rank.GENUS)) {
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
        if (showIndet && rnk != null && rnk.isInfraspecific() && (Rank.CULTIVAR != rnk || cultivarEpithet == null)) {
          // no infraspecific epitheton given, but rank below species. Indetermined!
          sb.append(' ');
          sb.append(rnk.getMarker());
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
          String rm = getInfraspecificRankMarker(code);
          if (rm != null) {
            sb.append(rm);
            sb.append(' ');
          }
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

    if (decomposition) {
      return decompose(sb.toString().trim());
    }
    return sb.toString().trim();
  }

  private String getInfraspecificRankMarker(NomenclaturalCode code) {
    if (getRank() == Rank.SUBSPECIES && NomenclaturalCode.ZOOLOGICAL == code) {
      return null;
    }
    return rank;
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
   * <p/>
   * For example:
   * Abies alba
   * Abies alba alpina
   * Abies Bracteata
   * Heucherella tiarelloides
   *
   * @return the 1,2 or 3 parted name as a single string
   */
  @JsonProperty
  public String canonicalName() {
    return buildName(false, false, false, false, false, true, true, false, false, false, false, false);
  }

  /**
   * The code compliant, canonical name with 3 name parts at max (genus, species, infraspecific), a rank marker for
   * infraspecific names and cultivar or strain epithets. The canonical name can be a 1, 2 or 3 parted name, but does
   * not include any informal notes or
   * authorships. Notho taxa will have the hybrid marker.
   * <p/>
   * For example:
   * Abies alba
   * Abies alba subsp. alpina
   * Abies sect. Bracteata
   * ×Heucherella tiarelloides
   *
   * @return the 1,2 or 3 parted name as a single string
   */
  @JsonProperty
  public String canonicalNameWithMarker() {
    return buildName(true, true, false, false, false, true, true, false, false, false, true, true);
  }

  /**
   * The code compliant, canonical name with rank & hybrid marker, authorship and cultivar or strain name included.
   * Informal or nomenclatoral notes, concept references, subgenus and non terminal authorships are removed.
   * @return the 1,2 or 3 parted name as a single string
   */
  @JsonProperty
  public String canonicalNameComplete() {
    return buildName(true, true, true, false, false, true, true, false, false, false, true, true);
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
    return buildName(true, true, true, true, false, false, true, true, true, true, true, true);
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
   * @return interpreted rank as enumeration or null
   */
  @JsonIgnore
  public Rank getRank() {
    Rank rankEnum = Rank.inferRank(genusOrAbove, infraGeneric, specificEpithet, rank, infraSpecificEpithet);
    return Rank.UNRANKED == rankEnum ? null : rankEnum;
  }

  public String getRankMarker() {
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
    return specificEpithet != null && infraSpecificEpithet != null && specificEpithet.equals(infraSpecificEpithet);
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
   * @return true for names with a rank marker but missing lowest name part. E.g. Coccyzuz americanus ssp. or
   *         Asteraceae
   *         spec. but not Maxillaria sect. Acaules
   */
  @JsonIgnore
  public boolean isIndetermined() {
    return rank != null && infraSpecificEpithet == null && (specificEpithet != null || infraGeneric == null);
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

  public void setRankMarker(String rankMarker) {
    if (rankMarker == null) {
      rank = null;
    } else {
      rankMarker = rankMarker.trim().toLowerCase();
      if (rankMarker.startsWith("notho")) {
        rankMarker = rankMarker.substring(5);
        notho = NamePart.INFRASPECIFIC;
      }
      Rank r = Rank.inferRank(rankMarker);
      if (r == null || Rank.UNCOMPARABLE_RANKS.contains(r)) {
        this.rank = rankMarker;
      } else {
        this.rank = r.getMarker();
      }
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
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ParsedName)) {
      return false;
    }
    ParsedName o = (ParsedName) obj;
    return equal(key, o.key)
           && equal(scientificName, o.scientificName)
           && equal(type, o.type)
           && equal(genusOrAbove, o.genusOrAbove)
           && equal(infraGeneric, o.infraGeneric)
           && equal(specificEpithet, o.specificEpithet)
           && equal(infraSpecificEpithet, o.infraSpecificEpithet)
           && equal(cultivarEpithet, o.cultivarEpithet)
           && equal(strain, o.strain)
           && equal(authorship, o.authorship)
           && equal(year, o.year)
           && equal(bracketAuthorship, o.bracketAuthorship)
           && equal(bracketYear, o.bracketYear)
           && equal(rank, o.rank);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key,
                            scientificName,
                            type,
                            genusOrAbove,
                            infraGeneric,
                            specificEpithet,
                            infraSpecificEpithet,
                            cultivarEpithet,
                            strain,
                            authorship,
                            year,
                            bracketAuthorship,
                            bracketYear,
                            rank
    );
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
