package org.gbif.api.model.crawler;

import org.gbif.api.vocabulary.Origin;
import org.gbif.api.vocabulary.Rank;

import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Statistics of a checklist normalization result.
 */
public class NormalizerStats {

  private final int roots;
  private final int depth;
  private final int synonyms;
  private final int ignored;
  private final Map<Origin, Integer> countByOrigin;
  private final Map<Rank, Integer> countByRank;
  private final List<String> cycles;

  @JsonCreator
  public NormalizerStats(@JsonProperty("roots") int roots, @JsonProperty("depth") int depth,
    @JsonProperty("synonyms") int synonyms, @JsonProperty("ignored") int ignored,
    @JsonProperty("countByOrigin") Map<Origin, Integer> countByOrigin,
    @JsonProperty("countByRank") Map<Rank, Integer> countByRank, @JsonProperty("cycles") List<String> cycles) {
    this.roots = roots;
    this.depth = depth;
    this.synonyms = synonyms;
    this.ignored = ignored;
    this.countByOrigin = countByOrigin;
    this.countByRank = countByRank;
    this.cycles = cycles;
  }

  /**
   * @return list of cycles, each given as one taxonID of the loop
   */
  public List<String> getCycles() {
    return cycles;
  }

  /**
   * @return total count of name usages existing as neo nodes, both accepted and synonyms and regardless of their Origin
   */
  public int getCount() {
    int total = 0;
    for (int x : countByOrigin.values()) {
      total += x;
    }
    return total;
  }

  /**
   * @return the number of root taxa without a parent
   */
  public int getRoots() {
    return roots;
  }

  /**
   * @return maximum depth of the classification
   */
  public int getDepth() {
    return depth;
  }

  /**
   * @return the number of synonym nodes in this checklist
   */
  public int getSynonyms() {
    return synonyms;
  }

  public int getIgnored() {
    return ignored;
  }

  public int getCountByRank(Rank rank) {
    if (countByRank.containsKey(rank)) {
      return countByRank.get(rank);
    }
    return 0;
  }

  public int getCountByOrigin(Origin o) {
    return countByOrigin.get(o);
  }

  public Map<Origin, Integer> getCountByOrigin() {
    return countByOrigin;
  }

  public Map<Rank, Integer> getCountByRank() {
    return countByRank;
  }

  @Override
  public String toString() {
    return "NormalizerStats{" +
           ", roots=" + roots +
           ", depth=" + depth +
           ", synonyms=" + synonyms +
           ", ignored=" + ignored+
           ", cycles=" + cycles.size() +
           ", countByOrigin=" + countByOrigin +
           ", countByRank=" + countByRank +
           '}';
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(roots, depth, synonyms, ignored, cycles, countByOrigin, countByRank);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final NormalizerStats other = (NormalizerStats) obj;
    return Objects.equal(this.roots, other.roots)
           && Objects.equal(this.depth, other.depth)
           && Objects.equal(this.synonyms, other.synonyms)
           && Objects.equal(this.ignored, other.ignored)
           && Objects.equal(this.cycles, other.cycles)
           && Objects.equal(this.countByOrigin, other.countByOrigin)
           && Objects.equal(this.countByRank, other.countByRank);
  }

}
