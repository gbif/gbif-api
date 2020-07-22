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
package org.gbif.api.model.collections.lookup;

import org.gbif.api.model.collections.CollectionEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Used in the {@link LookupResult} to express how good an entity matches. It also adds some remarks
 * to help understand how the match was done.
 *
 * @param <T> it can be parameterized. To be used mainly with {@link
 *     org.gbif.api.model.collections.Collection} or {@link
 *     org.gbif.api.model.collections.Institution}
 */
public class Match<T extends CollectionEntity> {

  private MatchType type;
  private Set<MatchRemark> remarks = new HashSet<>();
  private T entityMatched;

  public static <T extends CollectionEntity> Match<T> exact(T entity, MatchRemark... remarks) {
    Match<T> match = new Match<>();
    match.setEntityMatched(entity);
    match.setType(MatchType.EXACT);
    if (remarks != null) {
      match.setRemarks(new HashSet<>(Arrays.asList(remarks)));
    }
    return match;
  }

  public static <T extends CollectionEntity> Match<T> fuzzy(T entity, MatchRemark... remarks) {
    Match<T> match = new Match<>();
    match.setEntityMatched(entity);
    match.setType(MatchType.FUZZY);
    if (remarks != null) {
      match.setRemarks(new HashSet<>(Arrays.asList(remarks)));
    }
    return match;
  }

  public MatchType getType() {
    return type;
  }

  public void setType(MatchType type) {
    this.type = type;
  }

  public Set<MatchRemark> getRemarks() {
    return remarks;
  }

  public void setRemarks(Set<MatchRemark> remarks) {
    this.remarks = remarks;
  }

  public void addRemark(MatchRemark remark) {
    remarks.add(remark);
  }

  public T getEntityMatched() {
    return entityMatched;
  }

  public void setEntityMatched(T entityMatched) {
    this.entityMatched = entityMatched;
  }

  public enum MatchType {
    EXACT,
    FUZZY;
  }

  public enum MatchRemark {
    CODE_MATCH,
    IDENTIFIER_MATCH,
    ALTERNATIVE_CODE_MATCH,
    NAME_MATCH,
    PROBABLY_ON_LOAN,
    INST_COLL_MISMATCH;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Match<?> match = (Match<?>) o;
    return type == match.type
        && Objects.equals(remarks, match.remarks)
        && Objects.equals(entityMatched, match.entityMatched);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, remarks, entityMatched);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Match.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("remarks=" + remarks)
        .add("entityMatched=" + entityMatched)
        .toString();
  }
}
