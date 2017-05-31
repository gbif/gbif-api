package org.gbif.api.model.registry.search;

import java.util.UUID;

import com.google.common.base.Objects;

/**
 * A utility container for holding only the title and key of an entity.
 */
public class KeyTitleResult {

  private UUID key;
  private String title;

  public KeyTitleResult() {
  }

  public KeyTitleResult(UUID key, String title) {
    this.key = key;
    this.title = title;
  }

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

  @Override
  public int hashCode() {
    return Objects
      .hashCode(key, title);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof KeyTitleResult) {
      KeyTitleResult that = (KeyTitleResult) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.title, that.title);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("title", title)
      .toString();
  }
}
