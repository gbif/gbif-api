package org.gbif.api.model.collections;

import io.swagger.v3.oas.annotations.media.Schema;

import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.vocabulary.collections.Source;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/** Metadata to sync GRSciColl entities with their master sources. */
public class MasterSourceMetadata implements Serializable {

  @Schema(
    description = "Identifier for the master source metadata.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  private Integer key;

  // TODO
  //@Schema(
  //  description = ""
  //)
  @NotNull private Source source;

  // TODO
  //@Schema(
  //  description = ""
  //)
  @NotNull private String sourceId;

  @Schema(
    description = "The GBIF username of the creator of the master source metadata.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "Timestamp of when the master source metadata was created.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  public MasterSourceMetadata() {}

  public MasterSourceMetadata(Source source, String sourceId) {
    this.source = source;
    this.sourceId = sourceId;
  }

  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  public Source getSource() {
    return source;
  }

  public void setSource(Source source) {
    this.source = source;
  }

  public String getSourceId() {
    return sourceId;
  }

  public void setSourceId(String sourceId) {
    this.sourceId = sourceId;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MasterSourceMetadata metadata = (MasterSourceMetadata) o;
    return Objects.equals(key, metadata.key)
        && source == metadata.source
        && Objects.equals(sourceId, metadata.sourceId)
        && Objects.equals(createdBy, metadata.createdBy)
        && Objects.equals(created, metadata.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, source, sourceId, createdBy, created);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", MasterSourceMetadata.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("source=" + source)
        .add("sourceId='" + sourceId + "'")
        .add("createdBy='" + createdBy + "'")
        .add("created=" + created)
        .toString();
  }
}
