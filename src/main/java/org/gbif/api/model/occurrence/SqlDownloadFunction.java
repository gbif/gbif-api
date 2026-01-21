package org.gbif.api.model.occurrence;

/**
 * Custom functions supported by the SQL download API.
 */
public enum SqlDownloadFunction {
  DEGREE_MINUTE_SECOND_GRID_CELL_CODE("GBIF_DMSGCode"),
  EEA_CELL_CODE("GBIF_EEARGCode"),
  EUROSTAT_CELL_CODE("GBIF_EuroStatCode"),
  ISEA3H_CELL_CODE("GBIF_ISEA3HCode"),
  MILITARY_GRID_REFERENCE_SYSTEM_CELL_CODE("GBIF_MGRSCode"),
  EXTENDED_QUARTER_DEGREE_GRID_CELL_CODE("GBIF_EQDGCode"),
  TEMPORAL_UNCERTAINTY("GBIF_TemporalUncertainty"),
  GEO_DISTANCE("GBIF_GeoDistance"),
  MILLISECONDS_TO_ISO8601("GBIF_MillisecondsToISO8601"),
  SECONDS_TO_ISO8601("GBIF_SecondsToISO8601"),
  SECONDS_TO_LOCAL_ISO8601("GBIF_SecondsToLocalISO8601"),
  CONTAINS("GBIF_Within"),
  STRING_ARRAY_CONTAINS_GENERIC("GBIF_StringArrayContains"),
  STRING_ARRAY_LIKE_GENERIC("GBIF_StringArrayLike");

  private final String sqlIdentifier;

  SqlDownloadFunction(String sqlIdentifier) {
    this.sqlIdentifier = sqlIdentifier;
  }

  public String getSqlIdentifier() {
    return sqlIdentifier;
  }
}
