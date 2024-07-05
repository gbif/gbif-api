package org.gbif.api.model.collections.search;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Match {
  private String field;
  private String snippet;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Match match = (Match) o;
    return Objects.equals(field, match.field);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field);
  }
}
