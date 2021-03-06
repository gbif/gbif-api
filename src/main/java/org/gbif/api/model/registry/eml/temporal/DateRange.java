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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.gbif.api.util.PreconditionUtils.checkArgument;

/**
 * A period of time.
 */
public class DateRange extends TemporalCoverage implements Serializable {

  private static final long serialVersionUID = -1482059589547915674L;

  private Date start;

  private Date end;

  public DateRange() {
  }

  public DateRange(Date start, Date end) {
    checkArgument(start.before(end), "start date must be before end");
    this.start = start;
    this.end = end;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  @Override
  public Collection<String> toKeywords() {
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    if (start != null) {
      sb.append(sdf.format(start));
    }
    if (end != null) {
      if (sb.length() > 0) {
        sb.append("-");
      }
      sb.append(sdf.format(end));
    }

    return Stream.of(sb.toString()).collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DateRange dateRange = (DateRange) o;
    return Objects.equals(start, dateRange.start) &&
      Objects.equals(end, dateRange.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DateRange.class.getSimpleName() + "[", "]")
      .add("start=" + start)
      .add("end=" + end)
      .toString();
  }

  @Override
  public String acceptFormatter(TemporalCoverageFormatterVisitor formatter) {
    return formatter.format(this);
  }
}
