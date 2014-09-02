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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * An ordered taxonomic rank enumeration with the most frequently used values.
 * Several static methods, lists, sets and maps are provided to help with ordering and lookup from strings.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/rank.xml">rs.gbif.org vocabulary</a>
 */
public enum Rank {

    DOMAIN("dom."),
    KINGDOM("king."),
    SUBKINGDOM("subking."),
    SUPERPHYLUM("superphyl."),
    PHYLUM("phyl."),
    SUBPHYLUM("subphyl."),
    SUPERCLASS("supercl."),
    CLASS("cl."),
    SUBCLASS("subcl."),
    SUPERORDER("superord."),
    ORDER("ord."),
    SUBORDER("subord."),
    INFRAORDER(null),
    SUPERFAMILY("superfam."),
    FAMILY("fam."),
    SUBFAMILY("subfam."),
    TRIBE("trib."),
    SUBTRIBE("subtrib."),
    /**
     * Used for any other unspecific rank above genera.
     */
    SUPRAGENERIC_NAME("supergen."),
    GENUS("gen."),
    SUBGENUS("subgen."),
    SECTION("sect."),
    SUBSECTION("subsect."),
    SERIES("ser."),
    SUBSERIES("subser."),
    /**
     * used for any other unspecific rank below genera and above species.
     */
    INFRAGENERIC_NAME("infragen."),
    SPECIES("sp."),
    /**
     * used for any other unspecific rank below genera and above species.
     */
    INFRASPECIFIC_NAME("agg."),
    SUBSPECIES("subsp."),
    /**
     * used also for any other unspecific rank below subspecies.
     */
    INFRASUBSPECIFIC_NAME("infrasubsp."),
    VARIETY("var."),
    SUBVARIETY("subvar."),
    FORM("f."),
    SUBFORM("subf."),
    CULTIVAR_GROUP,
    CULTIVAR("cv."),
    /**
     * A microbial strain.
     */
    STRAIN("strain"),
    INFORMAL,
    UNRANKED;

    /**
     * All main Linnean ranks ordered.
     */
    public static final List<Rank> LINNEAN_RANKS;
    /**
     * An ordered list of all ranks that appear in Darwin Core with their own term.
     */
    public static final List<Rank> DWC_RANKS;
    /**
     * A set of ranks which cannot clearly be compared to any other rank as they represent rank "ranges".
     * For example a subgeneric rank is anything below genus,
     * so one cannot say if its higher or lower than a species for example.
     */
    public static final Set<Rank> UNCOMPARABLE_RANKS;
    /**
     * Set of all ranks including uncomparable ones which are species or below.
     */
    public static final Set<Rank> SPECIES_OR_BELOW;


    /**
     * Map of rank markers to their respective rank enum.
     */
    public static final Map<String, Rank> RANK_MARKER_MAP;
    /**
     * Map of only suprageneric rank markers to their respective rank enum.
     */
    public static final Map<String, Rank> RANK_MARKER_MAP_SUPRAGENERIC;
    /**
     * Map of only infrageneric rank markers to their respective rank enum.
     */
    public static final Map<String, Rank> RANK_MARKER_MAP_INFRAGENERIC;
    /**
     * Map of only infraspecific rank markers to their respective rank enum.
     */
    public static final Map<String, Rank> RANK_MARKER_MAP_INFRASPECIFIC;

    /**
     * An immutable map of name suffices to corresponding ranks across all kingdoms.
     * To minimize wrong matches this map is sorted by suffix length with the first suffices being the longest and
     * therefore most accurate matches.
     */
    public static final SortedMap<String, Rank> SUFFICES_RANK_MAP =
            new ImmutableSortedMap.Builder<String, Rank>(Ordering.natural()).put("mycetidae", SUBCLASS)
                    .put("phycidae", SUBCLASS)
                    .put("mycotina", SUBPHYLUM)
                    .put("phytina", SUBPHYLUM)
                    .put("phyceae", CLASS)
                    .put("mycetes", CLASS)
                    .put("mycota", PHYLUM)
                    .put("opsida", CLASS)
                    .put("oideae", SUBFAMILY)
                    .put("aceae", FAMILY)
                    .put("phyta", PHYLUM)
                    .put("oidea", SUPERFAMILY)
                    .put("anae", SUPERORDER)
                    .put("ales", ORDER)
                    .put("acea", SUPERFAMILY)
                    .put("idae", FAMILY)
                    .put("inae", SUBFAMILY)
                    .put("eae", TRIBE)
                    .put("ini", TRIBE)
                    .build();

    /**
     * A map of very short abbreviations, e.g k,p,g,sg for mayor ranks often used to construct bean properties.
     */
    public static final Map<String, Rank> ABBREVIATION_RANK_BI_MAP =
            new ImmutableBiMap.Builder<String, Rank>().put("k", KINGDOM)
                    .put("sk", SUBKINGDOM)
                    .put("p", PHYLUM)
                    .put("sp", SUBPHYLUM)
                    .put("c", CLASS)
                    .put("sc", SUBCLASS)
                    .put("o", ORDER)
                    .put("so", SUBORDER)
                    .put("f", FAMILY)
                    .put("sf", SUBFAMILY)
                    .put("g", GENUS)
                    .put("sg", SUBGENUS)
                    .put("s", SPECIES)
                    .put("ss", SUBSPECIES)
                    .put("is", INFRASPECIFIC_NAME)
                    .put("v", VARIETY)
                    .put("ff", FORM)
                    .build();

    /**
     * Matches all dots ("."), underscores ("_") and dashes ("-").
     */
    private static final Pattern NORMALIZE_TERM = Pattern.compile("[._ -]+");

    private final String marker;

    static {
        List<Rank> dwcRanks = Lists.newArrayList();
        dwcRanks.add(KINGDOM);
        dwcRanks.add(PHYLUM);
        dwcRanks.add(CLASS);
        dwcRanks.add(ORDER);
        dwcRanks.add(FAMILY);
        dwcRanks.add(GENUS);
        dwcRanks.add(SUBGENUS);
        dwcRanks.add(SPECIES);
        DWC_RANKS = ImmutableList.copyOf(dwcRanks);

        dwcRanks.remove(SUBGENUS);
        LINNEAN_RANKS = ImmutableList.copyOf(dwcRanks);

        Set<Rank> ranks = new HashSet<Rank>();
        ranks.add(INFRAGENERIC_NAME);
        ranks.add(INFRASPECIFIC_NAME);
        ranks.add(INFRASUBSPECIFIC_NAME);
        ranks.add(INFORMAL);
        ranks.add(UNRANKED);
        UNCOMPARABLE_RANKS = ImmutableSet.copyOf(ranks);

        ranks = new HashSet<Rank>();
        ranks.add(SPECIES);
        ranks.add(INFRASPECIFIC_NAME);
        ranks.add(SUBSPECIES);
        ranks.add(INFRASUBSPECIFIC_NAME);
        ranks.add(VARIETY);
        ranks.add(SUBVARIETY);
        ranks.add(FORM);
        ranks.add(SUBFORM);
        ranks.add(CULTIVAR_GROUP);
        ranks.add(CULTIVAR);
        ranks.add(STRAIN);
        SPECIES_OR_BELOW = ImmutableSet.copyOf(ranks);
    }


    static {
        Map<String, Rank> ranks = new HashMap<String, Rank>();
        ranks.put("fam", FAMILY);
        ranks.put("trib", TRIBE);
        ranks.put("sect", SECTION);
        ranks.put("supertrib", SUPRAGENERIC_NAME);
        ranks.put("supersubtrib", SUPRAGENERIC_NAME);
        ranks.put("ib", SUPRAGENERIC_NAME);
        ranks.put("gen", GENUS);
        RANK_MARKER_MAP_SUPRAGENERIC = ImmutableMap.copyOf(ranks);


        ranks = new HashMap<String, Rank>();
        ranks.put("subgenus", SUBGENUS);
        ranks.put("subgen", SUBGENUS);
        ranks.put("subg", SUBGENUS);
        ranks.put("section", SECTION);
        ranks.put("sect", SECTION);
        ranks.put("subsection", SUBSECTION);
        ranks.put("subsect", SUBSECTION);
        ranks.put("series", SERIES);
        ranks.put("ser", SERIES);
        ranks.put("subseries", SUBSERIES);
        ranks.put("subser", SUBSERIES);
        ranks.put("agg", INFRAGENERIC_NAME);
        ranks.put("species", SPECIES);
        ranks.put("spec", SPECIES);
        ranks.put("spp", SPECIES);
        ranks.put("sp", SPECIES);
        RANK_MARKER_MAP_INFRAGENERIC = ImmutableMap.copyOf(ranks);

        ranks = new HashMap<String, Rank>();
        ranks.put("subsp", SUBSPECIES);
        ranks.put("ssp", SUBSPECIES);
        ranks.put("var", VARIETY);
        ranks.put("v", VARIETY);
        ranks.put("subvar", SUBVARIETY);
        ranks.put("subv", SUBVARIETY);
        ranks.put("sv", SUBVARIETY);
        ranks.put("forma", FORM);
        ranks.put("form", FORM);
        ranks.put("fo", FORM);
        ranks.put("f", FORM);
        ranks.put("subform", SUBFORM);
        ranks.put("subf", SUBFORM);
        ranks.put("sf", SUBFORM);
        ranks.put("cv", CULTIVAR);
        ranks.put("hort", CULTIVAR);
        ranks.put("m", INFRASUBSPECIFIC_NAME);
        ranks.put("morph", INFRASUBSPECIFIC_NAME);
        ranks.put("nat", INFRASUBSPECIFIC_NAME);
        ranks.put("ab", INFRASUBSPECIFIC_NAME);
        ranks.put("aberration", INFRASUBSPECIFIC_NAME);
        ranks.put("\\*+", INFRASPECIFIC_NAME);
        RANK_MARKER_MAP_INFRASPECIFIC = ImmutableMap.copyOf(ranks);

        ranks = new HashMap<String, Rank>();
        ranks.putAll(RANK_MARKER_MAP_SUPRAGENERIC);
        ranks.putAll(RANK_MARKER_MAP_INFRAGENERIC);
        ranks.putAll(RANK_MARKER_MAP_INFRASPECIFIC);
        ranks.put("subser", SUBSERIES);
        RANK_MARKER_MAP = ImmutableMap.copyOf(ranks);
    }

    /**
     * Tries its best to infer a rank from a given rank marker such as subsp.
     *
     * @return the inferred rank or null
     */
    public static Rank inferRank(@Nullable String rankMarker) {
        // first try rank marker
        if (rankMarker != null) {
            Rank markerRank = RANK_MARKER_MAP.get(NORMALIZE_TERM.matcher(rankMarker.toLowerCase()).replaceAll(""));
            if (markerRank != null) {
                return markerRank;
            }
        }
        return null;
    }

    /**
     * Tries its best to infer a rank from an atomised name.
     * As a final resort for higher monomials the suffices are inspected, but no attempt is made to disambiguate
     * the 2 known homonym suffices -idae and -inae, but instead the far more widespread zoological versions are
     * interpreted.
     * TODO: pass optional nomenclatural code paraemeter to disambiguate homonym suffices.
     *
     * @param genusOrAbove         an optional uninomial at genus level or above
     * @param infraGeneric         an optional subgeneric name part, e.g. the subgenus
     * @param specificEpithet      an optional specific epithet
     * @param rankMarker           an optional rank marker such as subsp.
     * @param infraSpecificEpithet an optional infraspecific epithet
     * @return the inferred rank or Unranked if it cant be found, never null.
     */

    public static Rank inferRank(
            @Nullable String genusOrAbove,
            @Nullable String infraGeneric,
            @Nullable String specificEpithet,
            @Nullable String rankMarker,
            @Nullable String infraSpecificEpithet
    ) {
        // first try rank marker
        Rank markerRank = inferRank(rankMarker);
        if (markerRank != null) {
            return markerRank;
        }

        // default if we cant find anything else
        Rank rank = UNRANKED;
        // detect rank based on parsed name
        if (infraSpecificEpithet != null) {
            // some infraspecific name
            rank = INFRASPECIFIC_NAME;
        } else if (specificEpithet != null) {
            // a species
            rank = SPECIES;
        } else if (infraGeneric != null) {
            // some infrageneric name
            rank = INFRAGENERIC_NAME;
        } else if (genusOrAbove != null) {
            // a suprageneric name, check suffices
            for (String suffix : SUFFICES_RANK_MAP.keySet()) {
                if (genusOrAbove.endsWith(suffix)) {
                    rank = SUFFICES_RANK_MAP.get(suffix);
                    break;
                }
            }
        }
        return rank;
    }

    Rank() {
        this(null);
    }

    Rank(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return marker;
    }

    /**
     * @return true for infraspecific ranks excluding cultivars and strains.
     */
    public boolean isInfraspecific() {
        return isSpeciesOrBelow() && this != SPECIES && this != CULTIVAR && this != CULTIVAR_GROUP && this != STRAIN;
    }

    /**
     * @return true for rank is below genus.
     */
    public boolean isInfrageneric() {
        return ordinal() > GENUS.ordinal() && this != UNRANKED && this != INFORMAL;
    }

    /**
     * True for all mayor Linnéan ranks, ie kingdom,phylum,class,order,family,genus and species.
     */
    public boolean isLinnean() {
        for (Rank r : LINNEAN_RANKS) {
            if (r == this) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpeciesOrBelow() {
        return SPECIES_OR_BELOW.contains(this);
    }

    /**
     * @return true if the rank is a above genus.
     */
    public boolean isSuprageneric() {
        return ordinal() < GENUS.ordinal();
    }

    /**
     * True for names of informal ranks that represent a range of ranks really and therefore cannot safely be compared to
     * other ranks in all cases.
     * Example ranks are infraspecies or subgeneric
     *
     * @return true if uncomparable
     */
    public boolean isUncomparable() {
        return UNCOMPARABLE_RANKS.contains(this);
    }

    /**
     * @return true if this rank is higher than the given other
     */
    public boolean higherThan(Rank other) {
        return ordinal() < other.ordinal();
    }
}
