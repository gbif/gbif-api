package org.gbif.api.model.registry.search;

import org.gbif.api.vocabulary.DatasetSubtype;
import org.gbif.api.vocabulary.DatasetType;

import java.util.UUID;

import com.google.common.base.Objects;

/**
 * The dataset search model object for suggest searches of datasets.
 */
public class DatasetSuggestResult {

  private UUID key;
  private String title;
  private String description;
  private DatasetType type;
  private DatasetSubtype subtype;


  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DatasetType getType() {
    return type;
  }

  public void setType(DatasetType type) {
    this.type = type;
  }

  public DatasetSubtype getSubtype() {
    return subtype;
  }

  public void setSubtype(DatasetSubtype subtype) {
    this.subtype = subtype;
  }


  @Override
  public int hashCode() {
    return Objects
      .hashCode(key, title, description, type, subtype);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof DatasetSuggestResult) {
      DatasetSuggestResult that = (DatasetSuggestResult) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.title, that.title)
        && Objects.equal(this.description, that.description)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.subtype, that.subtype);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("title", title)
      .add("description", description)
      .add("type", type)
      .add("subtype", subtype)
      .toString();
  }
}
