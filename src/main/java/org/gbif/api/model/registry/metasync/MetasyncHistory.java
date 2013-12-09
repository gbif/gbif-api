package org.gbif.api.model.registry.metasync;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

/**
 * Metadata synchronization historical information.
 */
public class MetasyncHistory {

  private UUID installationKey;
  private Date syncDate;
  private MetasyncResult result;
  private String details;
  
  /** 
   * Key of the synchronized installation.
   * 
   * @return the installation key that was synchronized
   */
  @NotNull
  public UUID getInstallationKey() {
    return installationKey;
  }
  
  public void setInstallationKey(UUID installationKey) {
    this.installationKey = installationKey;
  }
  
  /**
   * Date when the metasync operation was executed.
   * 
   * @return timestamp when the synchronization was executed
   */
  @NotNull
  public Date getSyncDate() {
    return syncDate;
  }
  
  public void setSyncDate(Date syncDate) {
    this.syncDate = syncDate;
  }
  
  /**
   * Result of the metasync operation.
   * 
   * @return the result of the metasync operation
   */
  @Nullable
  public MetasyncResult getResult() {
    return result;
  }
  
  public void setResult(MetasyncResult result) {
    this.result = result;
  }
  
  /**
   * Details about the metasync operation.
   * 
   * @return the metasync details
   */
  @Nullable
  public String getDetails() {
    return details;
  }
  
  public void setDetails(String details) {
    this.details = details;
  }
  
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MetasyncHistory other = (MetasyncHistory) obj;
    return Objects.equal(this.installationKey, other.installationKey)
           && Objects.equal(this.syncDate, other.syncDate)
           && Objects.equal(this.result, other.result)
           && Objects.equal(this.details, other.details);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(installationKey,syncDate,result,details);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("installationKey", installationKey)
      .add("syncDate", syncDate)
      .add("result", result)
      .add("details", details).toString();
  }
}
