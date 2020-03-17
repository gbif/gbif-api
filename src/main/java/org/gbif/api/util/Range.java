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
package org.gbif.api.util;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Simplified version of guava's {@code Range}.
 */
public class Range<T extends Comparable<T>> {

  /**
   * Lower bound.
   * Unbound if it is {@code null}.
   */
  private T from;

  /**
   * Upper bound.
   * Unbound if it is {@code null}.
   */
  private T to;

  /**
   * Create a range with bounds {@code from} and {@code to}.
   *
   * @throws IllegalArgumentException if {@code from} is greater than {@code to}
   */
  public Range(T from, T to) {
    if (from != null && to != null && from.compareTo(to) > 0) {
      throw new IllegalArgumentException(String.format("Invalid range: (%s,%s)", from, to));
    }
    this.from = from;
    this.to = to;
  }

  /**
   * Returns {@code true} if this range has a lower endpoint.
   */
  public boolean hasLowerBound() {
    return from != null;
  }

  /**
   * Returns {@code true} if this range has an upper endpoint.
   */
  public boolean hasUpperBound() {
    return to != null;
  }

  /**
   * Returns {@code true} if {@code value} is within the bounds of this range.
   */
  public boolean contains(@NotNull T value) {
    return from.compareTo(value) <= 0 && to.compareTo(value) >= 0;
  }

  /**
   * Returns {@code true} if the bounds of {@code other} do not extend outside the bounds of this
   * range.
   */
  public boolean encloses(@NotNull Range<T> other) {
    return from.compareTo(other.from) <= 0 && to.compareTo(other.to) >= 0;
  }

  @Nullable
  public T lowerEndpoint() {
    return from;
  }

  @Nullable
  public T upperEndpoint() {
    return to;
  }
}
