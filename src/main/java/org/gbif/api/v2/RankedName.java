package org.gbif.api.v2;

import org.gbif.api.vocabulary.Rank;

import java.util.Objects;

/**
 *
 */
public class RankedName {
  private int key;
  private String name;
  private Rank rank;

  public RankedName() {
  }

  public RankedName(int key, String name, Rank rank) {
    this.key = key;
    this.name = name;
    this.rank = rank;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RankedName u = (RankedName) o;
    return key == u.key &&
        Objects.equals(name, u.name) &&
        rank == u.rank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, name, rank);
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append(name)
        .append(" [")
        .append(key)
        .append(',')
        .append(rank)
        .append(']')
        .toString();
  }
}
