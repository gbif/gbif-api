package org.gbif.api.model.occurrence;

import java.util.Collection;
import javax.annotation.Nullable;
import javax.validation.Valid;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

public class SQLDownloadRequest extends DownloadRequest {

  private static final DownloadFormat DEFAULT_DOWNLOAD_FORMAT = DownloadFormat.SQL;
  private String sql;
  
  /**
   * Full constructor. Used to create instances using JSON serialization.
   */
  @JsonCreator
  public SQLDownloadRequest(@JsonProperty("sql") String sql, @JsonProperty("creator") @Nullable String creator,
      @JsonProperty("notification_address") @Nullable Collection<String> notificationAddresses,
      @JsonProperty("send_notification") @Nullable boolean sendNotification, @JsonProperty("format") DownloadFormat format) {
    this.creator = creator;
    this.notificationAddresses = notificationAddresses == null ? ImmutableSet.<String>of() : ImmutableSet.copyOf(notificationAddresses);
    this.sendNotification = sendNotification;
    this.format = DEFAULT_DOWNLOAD_FORMAT;
    this.sql = sql;
  }

  /**
   * 
   * @return the sql query.
   */
  @Valid
  public String getSQL() {
    return sql;
  }
  
  /**
   * This parameter when present provides the SQL query for custom download
   * @param sql
   */
  public void setSQL(String sql) {
    this.sql = sql;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(creator, sql, notificationAddresses, sendNotification, format);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SQLDownloadRequest other = (SQLDownloadRequest) obj;
    if (sql == null) {
      if (other.sql != null)
        return false;
    } else if (!sql.equals(other.sql))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("creator", creator).add("sql", sql)
        .add("notificationAddresses", notificationAddresses).add("emailNotification", sendNotification).add("format", format).toString();
  }
}
