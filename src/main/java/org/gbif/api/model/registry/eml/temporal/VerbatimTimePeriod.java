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
package org.gbif.api.model.registry.eml.temporal;

import org.gbif.api.util.formatter.TemporalCoverageFormatterVisitor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A verbatim (e.g. free text) period of time.
 */
public class VerbatimTimePeriod extends TemporalCoverage implements Serializable {

  private static final long serialVersionUID = 2161509394401716416L;

  private String period;

  private VerbatimTimePeriodType type;

  public VerbatimTimePeriod() {
  }

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }

  public VerbatimTimePeriodType getType() {
    return type;
  }

  public void setType(VerbatimTimePeriodType type) {
    this.type = type;
  }

  @Override
  public Collection<String> toKeywords() {
    return Stream.of(period).collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerbatimTimePeriod that = (VerbatimTimePeriod) o;
    return Objects.equals(period, that.period) &&
      type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(period, type);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", VerbatimTimePeriod.class.getSimpleName() + "[", "]")
      .add("period='" + period + "'")
      .add("type=" + type)
      .toString();
  }

  @Override
  public String acceptFormatter(TemporalCoverageFormatterVisitor formatter) {
    return formatter.format(this);
  }
}
