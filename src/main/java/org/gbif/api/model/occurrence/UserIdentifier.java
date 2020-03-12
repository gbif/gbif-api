package org.gbif.api.model.occurrence;

import java.util.Objects;

import org.gbif.api.vocabulary.UserIdentifierType;

import com.google.common.base.MoreObjects;
import javax.validation.constraints.NotNull;

public class UserIdentifier {

  private UserIdentifierType type;
  private String value;

  @NotNull
  public UserIdentifierType getType() {
    return type;
  }

  public UserIdentifier setType(UserIdentifierType type) {
    this.type = type;
    return this;
  }

  @NotNull
  public String getValue() {
    return value;
  }

  public UserIdentifier setValue(String value) {
    this.value = value;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserIdentifier that = (UserIdentifier) o;
    return type == that.type &&
      Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("type", type)
      .add("value", value)
      .toString();
  }
}
