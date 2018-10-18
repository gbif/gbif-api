package org.gbif.api.model.occurrence.predicate;

import javax.validation.constraints.NotNull;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class SQLPredicate implements Predicate{
  @NotNull
  private final String value;

  public SQLPredicate(@NotNull String value) {
    java.util.Objects.requireNonNull(value);
    Preconditions.checkArgument(!value.isEmpty());
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
   
    SQLPredicate that = (SQLPredicate) obj;
    return Objects.equal(this.getValue(), that.getValue());
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("sql", value).toString();
  }
}
