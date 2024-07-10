package org.gbif.api.model.collections.search;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Highlight {
  private String field;
  private String snippet;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Highlight highlight = (Highlight) o;
    return Objects.equals(field, highlight.field);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field);
  }
}
