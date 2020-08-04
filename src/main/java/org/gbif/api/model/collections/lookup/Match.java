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
import java.util.Comparator;
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

  public static final Comparator<MatchType> MATCH_TYPE_COMPARATOR =
      (t1, t2) -> {
        if (t1 == null) {
          return t2 == null ? 0 : 1;
        } else if (t2 == null) {
          return -1;
        }

        if (t1 == t2) {
          return 0;
        }
        if (t1 == Match.MatchType.EXACT) {
          return -1;
        }
        if (t2 == Match.MatchType.EXACT) {
          return 1;
        }
        return t1.compareTo(t2);
      };

  private MatchType matchType;
  private Status status;
  private Set<Reason> reasons;
  private T entityMatched;

  public static <T extends CollectionEntity> Match<T> exact(T entity, Reason... reasons) {
    Match<T> match = new Match<>();
    match.setEntityMatched(entity);
    match.setMatchType(MatchType.EXACT);
    if (reasons != null) {
      match.setReasons(new HashSet<>(Arrays.asList(reasons)));
    }
    return match;
  }

  public static <T extends CollectionEntity> Match<T> fuzzy(T entity, Reason... reasons) {
    Match<T> match = new Match<>();
    match.setEntityMatched(entity);
    match.setMatchType(MatchType.FUZZY);
    if (reasons != null) {
      match.setReasons(new HashSet<>(Arrays.asList(reasons)));
    }
    return match;
  }

  public static <T extends CollectionEntity> Match<T> none() {
    Match<T> match = new Match<>();
    match.setMatchType(MatchType.NONE);
    return match;
  }

  public static <T extends CollectionEntity> Match<T> none(Status status) {
    Match<T> match = new Match<>();
    match.setMatchType(MatchType.NONE);
    match.setStatus(status);
    return match;
  }

  public static <T extends CollectionEntity> Match<T> machineTag(T entity, Reason... reasons) {
    Match<T> match = new Match<>();
    match.setEntityMatched(entity);
    match.setMatchType(MatchType.MACHINE_TAG);
    if (reasons != null) {
      match.setReasons(new HashSet<>(Arrays.asList(reasons)));
    }
    return match;
  }

  public MatchType getMatchType() {
    return matchType;
  }

  public void setMatchType(MatchType matchType) {
    this.matchType = matchType;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Set<Reason> getReasons() {
    return reasons;
  }

  public void setReasons(Set<Reason> reasons) {
    this.reasons = reasons;
  }

  public Match<T> addReason(Reason reason) {
    if (reasons == null) {
      reasons = new HashSet<>();
    }
    reasons.add(reason);
    return this;
  }

  public T getEntityMatched() {
    return entityMatched;
  }

  public void setEntityMatched(T entityMatched) {
    this.entityMatched = entityMatched;
  }

  public enum MatchType {
    EXACT,
    FUZZY,
    MACHINE_TAG,
    NONE;
  }

  public enum Reason {
    CODE_MATCH,
    IDENTIFIER_MATCH,
    ALTERNATIVE_CODE_MATCH,
    NAME_MATCH,
    PROBABLY_ON_LOAN,
    INST_COLL_MISMATCH,
    COUNTRY_MATCH,
    INSTITUTION_TAG,
    COLLECTION_TAG,
    COLLECTION_TO_INSTITUTION_TAG,
    INSTITUTION_TO_COLLECTION_TAG;
  }

  public enum Status {
    ACCEPTED,
    AMBIGUOUS,
    AMBIGUOUS_MACHINE_TAGS,
    AMBIGUOUS_OWNER,
    AMBIGUOUS_INSTITUTION_MISMATCH,
    DOUBTFUL;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Match<?> match = (Match<?>) o;
    return matchType == match.matchType
        && status == match.status
        && Objects.equals(reasons, match.reasons)
        && Objects.equals(entityMatched, match.entityMatched);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matchType, status, reasons, entityMatched);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Match.class.getSimpleName() + "[", "]")
        .add("matchType=" + matchType)
        .add("status=" + status)
        .add("reasons=" + reasons)
        .add("entityMatched=" + entityMatched)
        .toString();
  }
}
