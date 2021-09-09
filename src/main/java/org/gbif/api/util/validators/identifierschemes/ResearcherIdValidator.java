package org.gbif.api.util.validators.identifierschemes;

import java.util.Objects;
import java.util.regex.Pattern;

public class ResearcherIdValidator implements IdentifierSchemeValidator {

  private static final Pattern RESEARCHER_ID_PATTERN =
      Pattern.compile(
          "^(?<prefix>http(?:s)?:\\/\\/(?:www.)?researcherid.com\\/rid\\/)?([A-Z]{1}\\-[0-9]{4}\\-[0-9]{4})$");

  private static final Pattern PUBLONS_PATTERN =
      Pattern.compile(
          "^(?<prefix>http(?:s)?:\\/\\/(?:www.)?publons.com\\/researcher\\/)?([A-Z]{1}\\-[0-9]{4}\\-[0-9]{4})$");

  @Override
  public boolean isValid(String value) {
    if (value == null || value.isEmpty()) {
      return false;
    }
    return RESEARCHER_ID_PATTERN.matcher(value).matches()
        || PUBLONS_PATTERN.matcher(value).matches();
  }

  @Override
  public String normalize(String value) {
    Objects.requireNonNull(value, "Identifier value can't be null");
    String trimmedValue = value.trim();
    if (RESEARCHER_ID_PATTERN.matcher(trimmedValue).matches()
        || PUBLONS_PATTERN.matcher(trimmedValue).matches()) {
      return trimmedValue;
    }
    throw new IllegalArgumentException(value + " it not a valid ResearcherID");
  }
}
