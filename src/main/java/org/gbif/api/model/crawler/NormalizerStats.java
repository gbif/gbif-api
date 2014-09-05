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

  private final int records;
  private final int roots;
  private final int depth;
  private final int synonyms;
  private final Map<Origin, Integer> countByOrigin;
  private final Map<Rank, Integer> countByRank;
  private final List<String> cycles;

  @JsonCreator
  public NormalizerStats(@JsonProperty("records") int records,
    @JsonProperty("roots") int roots,
    @JsonProperty("depth") int depth,
    @JsonProperty("synonyms") int synonyms,
    @JsonProperty("countByOrigin") Map<Origin, Integer> countByOrigin,
    @JsonProperty("countByRank") Map<Rank, Integer> countByRank,
    @JsonProperty("cycles") List<String> cycles) {
    this.records = records;
    this.roots = roots;
    this.depth = depth;
    this.synonyms = synonyms;
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

  public void incOrigin(Origin origin) {
    if (origin != null) {
      if (!countByOrigin.containsKey(origin)) {
        countByOrigin.put(origin, 1);
      } else {
        countByOrigin.put(origin, countByOrigin.get(origin) + 1);
      }
    }
  }

  public void incRank(Rank rank) {
    if (rank != null) {
      if (!countByRank.containsKey(rank)) {
        countByRank.put(rank, 1);
      } else {
        countByRank.put(rank, countByRank.get(rank) + 1);
      }
    }
  }

  /**
   * @return raw number of processed source records in dwc archive
   */
  public int getRecords() {
    return records;
  }

  /**
   * @return total number of name usages existing as neo nodes, both accepted and synonyms
   */
  public int getUsages() {
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
           "records=" + records +
           ", roots=" + roots +
           ", depth=" + depth +
           ", synonyms=" + synonyms +
           ", cycles=" + cycles.size() +
           ", countByOrigin=" + countByOrigin +
           ", countByRank=" + countByRank +
           '}';
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(records, roots, depth, synonyms, cycles, countByOrigin, countByRank);
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
    return Objects.equal(this.records, other.records)
           && Objects.equal(this.roots, other.roots)
           && Objects.equal(this.depth, other.depth)
           && Objects.equal(this.synonyms, other.synonyms)
           && Objects.equal(this.cycles, other.cycles)
           && Objects.equal(this.countByOrigin, other.countByOrigin)
           && Objects.equal(this.countByRank, other.countByRank);
  }

}
