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
