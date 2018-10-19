package org.gbif.api.model.occurrence.predicate;

import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class SQLPredicate implements Predicate {
  @NotNull
  private final String sql;

  
  @JsonCreator
  public SQLPredicate(@JsonProperty("sql") String sql) {
    Preconditions.checkNotNull(sql, "<sql> may not be null");
    this.sql = sql;
  }

  public String sql() {
    return sql;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof WithinPredicate)) {
      return false;
    }

    SQLPredicate that = (SQLPredicate) obj;
    return Objects.equal(this.sql, that.sql);
  }

  @Override
   public int hashCode() {
     return Objects.hashCode(sql);
   }

   @Override
   public String toString() {
     return Objects.toStringHelper(this).add("sql", sql).toString();
   }

}
