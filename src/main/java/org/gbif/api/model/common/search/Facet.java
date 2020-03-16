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
package org.gbif.api.model.common.search;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a clustering of search results into categories.
 * Each facet shows the number of hits within the search that match that category.
 */
public class Facet<T extends SearchParameter> {

  private T field;
  private List<Count> counts;

  /**
   * Null/Empty constructor.
   */
  public Facet() {
  }

  /**
   * Minimal constructor.
   */
  public Facet(T field) {
    this.field = field;
  }

  /**
   * Full constructor.
   */
  public Facet(T field, List<Count> counts) {
    this.field = field;
    this.counts = counts;
  }

  /**
   * List of the different categories/count for this facet.
   */
  public List<Count> getCounts() {
    return counts;
  }

  /**
   * Sets the list of counts/categories for this faceted field.
   */
  public void setCounts(List<Count> counts) {
    this.counts = counts;
  }

  /**
   * The facet field, it's used only for identify the facet in a possible list of facets.
   */
  public T getField() {
    return field;
  }

  /**
   * Sets the field of the facet.
   */
  public void setField(T field) {
    this.field = field;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Facet<?> facet = (Facet<?>) o;
    return Objects.equals(field, facet.field) &&
      Objects.equals(counts, facet.counts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, counts);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Facet.class.getSimpleName() + "[", "]")
      .add("field=" + field)
      .add("counts=" + counts)
      .toString();
  }

  /**
   * Nested class that represents the count of each category.
   */
  public static class Count {

    private String name;
    private Long count;

    /**
     * Null/empty constructor.
     */
    public Count() {
    }

    /**
     * Full constructor.
     *
     * @param name  or label of the category
     * @param count number of elements of this facet category
     */
    public Count(String name, Long count) {
      this.name = name;
      this.count = count;
    }

    /**
     * Number of occurrences of the category(name).
     */
    public Long getCount() {
      return count;
    }

    /**
     * Sets the count of occurrences for this category.
     */
    public void setCount(Long count) {
      this.count = count;
    }

    /**
     * Name is the label/name/title used to distinguish this category in the list of counts.
     * Each name should be unique in the list.
     *
     * @return the name or label of the category
     */
    public String getName() {
      return name;
    }

    /**
     * Sets the name value of the category/count.
     */
    public void setName(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Count count1 = (Count) o;
      return Objects.equals(name, count1.name) &&
        Objects.equals(count, count1.count);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, count);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Count.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("count=" + count)
        .toString();
    }
  }
}
