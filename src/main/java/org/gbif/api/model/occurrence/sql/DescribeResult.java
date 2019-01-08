package org.gbif.api.model.occurrence.sql;

/**
 * Describe HDFS table response.
 */
public class DescribeResult {

  private String columnName;
  private String dataType;
  private String comment;

  /**
   * Empty constructor, required for serialization.
   */
  public DescribeResult() {}

  /**
   * Full constructor.
   */
  public DescribeResult(String columnName, String dataType, String comment) {
    this.columnName = columnName;
    this.dataType = dataType;
    this.comment = comment;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
