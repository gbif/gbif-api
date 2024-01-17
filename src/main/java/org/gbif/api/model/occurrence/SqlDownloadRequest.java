package org.gbif.api.model.occurrence;

import java.util.Collection;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SQL Based downloads — experimental feature.
 */
public class SqlDownloadRequest extends DownloadRequest {

  //Default download format.
  private static final DownloadFormat DEFAULT_DOWNLOAD_FORMAT = DownloadFormat.SQL_TSV_ZIP;

  private String sql;

  public SqlDownloadRequest() {

  }

  /**
   * Full constructor. Used to create instances using JSON serialization.
   */
  @JsonCreator
  public SqlDownloadRequest(
    @JsonProperty("sql") String sql,
    @JsonProperty("creator") @Nullable String creator,
    @JsonProperty("notificationAddresses") @Nullable Collection<String> notificationAddresses,
    @JsonProperty("sendNotification") @Nullable Boolean sendNotification,
    @JsonProperty("type") @Nullable DownloadType type,
    @JsonProperty("format") @Nullable DownloadFormat format) {
    // Check the format is suitable for an SQL download, and check predicate formats are suitable for predicate downloads.
    super(creator, notificationAddresses, sendNotification == null ? Boolean.TRUE : sendNotification, format == null ? DEFAULT_DOWNLOAD_FORMAT : format, type == null? DownloadType.OCCURRENCE : type);
    this.sql = sql;
  }

  /**
   *
   * @return the sql query.
   */
  @Valid
  public String getSql() {
    return sql;
  }

  /**
   * This parameter when present provides the SQL query for custom download
   * @param sql
   */
  public void setSql(String sql) {
    this.sql = sql;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), sql);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SqlDownloadRequest that = (SqlDownloadRequest) o;
    return Objects.equals(sql, that.sql);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SqlDownloadRequest.class.getSimpleName() + "[", "]")
      .add("sql=" + sql)
      .toString();
  }
}
