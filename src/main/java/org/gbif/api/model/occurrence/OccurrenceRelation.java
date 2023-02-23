/*
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
package org.gbif.api.model.occurrence;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Occurrence relations based on the dwc:ResourceRelationship model.
 * See <a href="http://rs.tdwg.org/dwc/terms/ResourceRelationship">Darwin Core</a>.
 */
public class OccurrenceRelation {
  private String id;  // resourceRelationshipID
  private long occurrenceId;  // "from" resourceID
  private long relatedOccurrenceId;  // "to" relatedResourceID
  private String type;  // relationshipOfResource
  private String accordingTo; // relationshipAccordingTo
  private String establishedDate;  // relationshipEstablishedDate
  private String remarks;  // relationshipRemarks

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getOccurrenceId() {
    return occurrenceId;
  }

  public void setOccurrenceId(long occurrenceId) {
    this.occurrenceId = occurrenceId;
  }

  public long getRelatedOccurrenceId() {
    return relatedOccurrenceId;
  }

  public void setRelatedOccurrenceId(long relatedOccurrenceId) {
    this.relatedOccurrenceId = relatedOccurrenceId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAccordingTo() {
    return accordingTo;
  }

  public void setAccordingTo(String accordingTo) {
    this.accordingTo = accordingTo;
  }

  public String getEstablishedDate() {
    return establishedDate;
  }

  public void setEstablishedDate(String establishedDate) {
    this.establishedDate = establishedDate;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OccurrenceRelation that = (OccurrenceRelation) o;
    return occurrenceId == that.occurrenceId &&
      relatedOccurrenceId == that.relatedOccurrenceId &&
      Objects.equals(id, that.id) &&
      Objects.equals(type, that.type) &&
      Objects.equals(accordingTo, that.accordingTo) &&
      Objects.equals(establishedDate, that.establishedDate) &&
      Objects.equals(remarks, that.remarks);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(id, occurrenceId, relatedOccurrenceId, type, accordingTo, establishedDate, remarks);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", OccurrenceRelation.class.getSimpleName() + "[", "]")
      .add("id='" + id + "'")
      .add("occurrenceId=" + occurrenceId)
      .add("relatedOccurrenceId=" + relatedOccurrenceId)
      .add("type='" + type + "'")
      .add("accordingTo='" + accordingTo + "'")
      .add("establishedDate='" + establishedDate + "'")
      .add("remarks='" + remarks + "'")
      .toString();
  }
}
