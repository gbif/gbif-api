package org.gbif.api.model.occurrence;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.Collection;

/**
 * SQL Based downloads.
 * This type of downloads accept and simple SELECT query that follows the  pattern:
 * SELECT field1, field2, ...fieldN FROM occurrence WHERE [multiple simple predicates] GROUP BY field1,field2..fieldN.
 */
public class SqlDownloadRequest extends DownloadRequest {

  private String sql;

  public SqlDownloadRequest() {

  }

  /**
   * Full constructor. Used to create instances using JSON serialization.
   */
  @JsonCreator
  @com.fasterxml.jackson.annotation.JsonCreator
  public SqlDownloadRequest(
    @com.fasterxml.jackson.annotation.JsonProperty("sql") @JsonProperty("sql") String sql,
    @com.fasterxml.jackson.annotation.JsonProperty("creator") @JsonProperty("creator") @Nullable String creator,
    @com.fasterxml.jackson.annotation.JsonProperty("notificationAddresses") @JsonProperty("notificationAddresses") @Nullable Collection<String> notificationAddresses,
    @com.fasterxml.jackson.annotation.JsonProperty("sendNotification") @JsonProperty("sendNotification") @Nullable boolean sendNotification) {
    super(creator, notificationAddresses, sendNotification, DownloadFormat.SQL);
    this.sql = sql;
  }

  /**
   * @return the sql query.
   */
  @Valid
  public String getSql() {
    return sql;
  }

  /**
   * This parameter when present provides the SQL query for custom download
   *
   * @param sql
   */
  public void setSql(String sql) {
    this.sql = sql;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), sql);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof SqlDownloadRequest)) {
      return false;
    }

    SqlDownloadRequest that = (SqlDownloadRequest) obj;
    return super.equals(that) && Objects.equal(this.sql, that.sql);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).addValue(super.toString())
      .add("sql", sql).toString();
  }
}
