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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;


/**
 * A single instance in time.
 */
public class SingleDate extends TemporalCoverage implements Serializable {

  private static final long serialVersionUID = 7528038100592298938L;

  private Date date;


  public SingleDate() {
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public Collection<String> toKeywords() {
    Collection<String> keywords = Lists.newArrayList();
    if (date != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      keywords.add(sdf.format(date));
    }
    return keywords;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof org.gbif.api.model.registry.eml.temporal.SingleDate)) {
      return false;
    }

    org.gbif.api.model.registry.eml.temporal.SingleDate that = (org.gbif.api.model.registry.eml.temporal.SingleDate) obj;
    return Objects.equal(this.date, that.date);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), date);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("super", super.toString()).add("date", date).toString();
  }

  @Override
  public String acceptFormatter(TemporalCoverageFormatterVisitor formatter) {
    return formatter.format(this);
  }
}
