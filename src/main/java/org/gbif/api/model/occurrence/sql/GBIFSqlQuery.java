package org.gbif.api.model.occurrence.sql;

import javax.validation.ValidationException;

/**
 * Splits GBIF SQL Query to sql part and export format.
 * Example GBIF Query:
 * SELECT gbifid, countrycode, datasetkey, license, month, year FROM occurrence WHERE month=3 AND year = 2018 EXPORT AS AVRO;
 */
public class GBIFSqlQuery {

  private static final String EXPORT_AS = "(?i)EXPORT\\s+AS";

  private final String uncheckedHiveQuery;
  private final SqlDownloadExportFormat sqlDownloadExportFormat;

  /**
   * Creates GBIF SQL Query with appropriate hive SQL and export format.
   * @param sqlQuery gbif defined sql query.
   * @return GBIFSqlQuery object.
   */
  public static GBIFSqlQuery create(String sqlQuery) {
    SqlDownloadExportFormat exportFormat = SqlDownloadExportFormat.TSV;

    String[] sqlSplit = sqlQuery.split(EXPORT_AS);

    if (sqlSplit.length == 2) {
      try {
        exportFormat = SqlDownloadExportFormat.valueOf(sqlSplit[1].trim().toUpperCase());
      } catch (IllegalArgumentException iex) {
        throw new ValidationException("Failure reading export format please specify TSV or AVRO");
      }
    }

    return new GBIFSqlQuery(sqlSplit[0].trim(), exportFormat);
  }

  private GBIFSqlQuery(String uncheckedHiveQuery, SqlDownloadExportFormat sqlDownloadExportFormat) {
    this.uncheckedHiveQuery = uncheckedHiveQuery;
    this.sqlDownloadExportFormat = sqlDownloadExportFormat;
  }

  public String getUncheckedHiveQuery() {
    return uncheckedHiveQuery;
  }

  public SqlDownloadExportFormat getSqlDownloadExportFormat() {
    return sqlDownloadExportFormat;
  }
}
