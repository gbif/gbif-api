package org.gbif.api.model.occurrence;

import com.google.common.base.Objects;

/**
 * Occurrence relations based on the dwc:ResourceRelationship model.
 * See <a href="http://darwincore.googlecode.com/svn/trunk/terms/index.htm#relindex">Darwin Core</a>.
 */
public class OccurrenceRelation {
  private String id;  // resourceRelationshipID
  private int occurrenceId;  // "from" resourceID
  private int relatedOccurrenceId;  // "to" relatedResourceID
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

  public int getOccurrenceId() {
    return occurrenceId;
  }

  public void setOccurrenceId(int occurrenceId) {
    this.occurrenceId = occurrenceId;
  }

  public int getRelatedOccurrenceId() {
    return relatedOccurrenceId;
  }

  public void setRelatedOccurrenceId(int relatedOccurrenceId) {
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
  public int hashCode() {
    return Objects.hashCode(id, occurrenceId, relatedOccurrenceId, type, accordingTo, establishedDate, remarks);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof OccurrenceRelation)) {
      return false;
    }
    OccurrenceRelation that = (OccurrenceRelation) obj;
    return Objects.equal(this.id, that.id)
        && Objects.equal(this.occurrenceId, that.occurrenceId)
        && Objects.equal(this.relatedOccurrenceId, that.relatedOccurrenceId)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.accordingTo, that.accordingTo)
        && Objects.equal(this.establishedDate, that.establishedDate)
        && Objects.equal(this.remarks, that.remarks);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("id", id)
      .add("occurrenceId", occurrenceId)
      .add("relatedOccurrenceId", relatedOccurrenceId)
      .add("type", type)
      .add("accordingTo", accordingTo)
      .add("establishedDate", establishedDate)
      .add("remarks", remarks)
      .toString();
  }
}
