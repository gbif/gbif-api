package org.gbif.api.model.occurrence.sql;

/**
 * SQL Download Format for exporting data.
 */
public enum SqlDownloadExportFormat {
  TSV("ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'\n" + "TBLPROPERTIES (\"serialization.null.format\"=\"\")"),
  AVRO("STORED AS AVRO");

  private final String template;

  SqlDownloadExportFormat(String template) {
    this.template = template;
  }

  /**
   * Gets template (place holders) of the associated format in exporting hive query.
   *
   * @return associated placeholder for hive query.
   */
  public String getTemplate() {
    return template;
  }
}
