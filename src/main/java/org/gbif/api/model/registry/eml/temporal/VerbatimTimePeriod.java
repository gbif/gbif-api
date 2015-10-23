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
package org.gbif.api.model.registry.eml.temporal;

import org.gbif.api.util.formatter.TemporalCoverageFormatterVisitor;

import java.io.Serializable;
import java.util.Collection;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;


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
    return Lists.newArrayList(period);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof org.gbif.api.model.registry.eml.temporal.VerbatimTimePeriod)) {
      return false;
    }

    org.gbif.api.model.registry.eml.temporal.VerbatimTimePeriod
      that = (org.gbif.api.model.registry.eml.temporal.VerbatimTimePeriod) obj;
    return Objects.equal(this.period, that.period) && Objects.equal(this.type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), period, type);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("super", super.toString())
      .add("period", period)
      .add("type", type)
      .toString();
  }

  @Override
  public String acceptFormatter(TemporalCoverageFormatterVisitor formatter) {
    return formatter.format(this);
  }
}
