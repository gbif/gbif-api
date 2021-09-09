package org.gbif.api.util.validators.identifierschemes;

import java.util.Objects;

public class OtherValidator implements IdentifierSchemeValidator {

  @Override
  public boolean isValid(String value) {
    return value != null && !value.isEmpty();
  }

  @Override
  public String normalize(String value) {
    Objects.requireNonNull(value, "Identifier value can't be null");
    return value.trim();
  }
}
