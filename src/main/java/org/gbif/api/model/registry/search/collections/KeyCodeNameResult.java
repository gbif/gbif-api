package org.gbif.api.model.registry.search.collections;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * A utility container for holding the key, code and name of an entity.
 */
public class KeyCodeNameResult {

  private UUID key;
  private String code;
  private String name;

  public KeyCodeNameResult() {

  }

  public KeyCodeNameResult(UUID key, String code, String name) {
    this.key = key;
    this.code = code;
    this.name = name;
  }

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    KeyCodeNameResult that = (KeyCodeNameResult) o;
    return Objects.equals(key, that.key) && Objects.equals(code, that.code) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, code, name);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", KeyCodeNameResult.class.getSimpleName() + "[", "]").add("key=" + key)
      .add("code='" + code + "'")
      .add("name='" + name + "'")
      .toString();
  }
}
