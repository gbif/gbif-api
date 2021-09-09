package org.gbif.api.util.validators.identifierschemes;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViafValidator implements IdentifierSchemeValidator {

  private static final Pattern VIAF_PATTERN =
      Pattern.compile("^((?<scheme>http(?:s)?:\\/\\/viaf.org\\/)(?:\\w+\\/)+)?([0-9]{1,22})$");

  @Override
  public boolean isValid(String value) {
    if (value == null || value.isEmpty()) {
      return false;
    }
    Matcher matcher = VIAF_PATTERN.matcher(value);
    return matcher.matches();
  }

  @Override
  public String normalize(String value) {
    Objects.requireNonNull(value, "Identifier value can't be null");
    String trimmedValue = value.trim();
    Matcher matcher = VIAF_PATTERN.matcher(trimmedValue);
    if (matcher.matches()) {
      return trimmedValue;
    }
    throw new IllegalArgumentException(value + " it not a valid VIAF");
  }
}
