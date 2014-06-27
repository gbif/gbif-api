/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.metrics;

import org.gbif.api.vocabulary.ProcessingErrorType;

import java.io.Serializable;

import com.google.common.base.Objects;


/**
 * Error reports for a single Record (occurrence, species).
 */
public class RecordError implements Serializable {

  private static final long serialVersionUID = 4942485228689322658L;

  /**
   * type of error.
   */
  private ProcessingErrorType processingErrorType;

  /**
   * ID of the problematic record (occurrence, species).
   */
  private long recordId;

  /**
   * catalog number.
   */
  private String catalogNumber;

  /**
   * collection code.
   */
  private String collectionCode;

  /**
   * institution code.
   */
  private String institutionCode;

  public static Builder builder() {
    return new Builder();
  }

  public RecordError() {
  }

  public RecordError(Builder builder) {
    processingErrorType = builder.processingErrorType;
    recordId = builder.recordId;
    catalogNumber = builder.catalogNumber;
    collectionCode = builder.collectionCode;
    institutionCode = builder.institutionCode;
  }

  /**
   * @return the catalogNumber
   */
  public String getCatalogNumber() {
    return catalogNumber;
  }

  /**
   * @param catalogNumber the catalogNumber to set
   */
  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  /**
   * @return the collectionCode
   */
  public String getCollectionCode() {
    return collectionCode;
  }

  /**
   * @param collectionCode the collectionCode to set
   */
  public void setCollectionCode(String collectionCode) {
    this.collectionCode = collectionCode;
  }

  /**
   * @return the processingErrorType
   */
  public ProcessingErrorType getProcessingErrorType() {
    return processingErrorType;
  }

  /**
   * @param processingErrorType the processingErrorType to set
   */
  public void setProcessingErrorType(ProcessingErrorType processingErrorType) {
    this.processingErrorType = processingErrorType;
  }

  /**
   * @return the institutionCode
   */
  public String getInstitutionCode() {
    return institutionCode;
  }

  /**
   * @param institutionCode the institutionCode to set
   */
  public void setInstitutionCode(String institutionCode) {
    this.institutionCode = institutionCode;
  }

  /**
   * @return the recordId
   */
  public long getRecordId() {
    return recordId;
  }

  /**
   * @param recordId the recordId to set
   */
  public void setRecordId(long recordId) {
    this.recordId = recordId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof RecordError)) {
      return false;
    }

    RecordError that = (RecordError) obj;
    return Objects.equal(this.processingErrorType, that.processingErrorType)
           && Objects.equal(this.recordId, that.recordId)
           && Objects.equal(this.catalogNumber, that.catalogNumber)
           && Objects.equal(this.collectionCode, that.collectionCode)
           && Objects.equal(this.institutionCode, that.institutionCode);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(processingErrorType, recordId, catalogNumber, collectionCode, institutionCode);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("processingErrorType", processingErrorType)
      .add("recordId", recordId)
      .add("catalogNumber", catalogNumber)
      .add("collectionCode", collectionCode)
      .add("institutionCode", institutionCode)
      .toString();
  }

  public static class Builder {

    private ProcessingErrorType processingErrorType;
    private long recordId;
    private String catalogNumber;
    private String collectionCode;
    private String institutionCode;

    public RecordError build() {
      return new RecordError(this);
    }

    public Builder catalogNumber(String catalogNumber) {
      this.catalogNumber = catalogNumber;
      return this;
    }

    public Builder collectionCode(String collectionCode) {
      this.collectionCode = collectionCode;
      return this;
    }

    public Builder errorType(ProcessingErrorType processingErrorType) {
      this.processingErrorType = processingErrorType;
      return this;
    }

    public Builder institutionCode(String institutionCode) {
      this.institutionCode = institutionCode;
      return this;
    }

    public Builder recordId(long recordId) {
      this.recordId = recordId;
      return this;
    }

  }

}
