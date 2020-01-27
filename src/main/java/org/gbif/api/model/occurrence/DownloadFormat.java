package org.gbif.api.model.occurrence;

/**
 * Download output requested format.
 */
public enum DownloadFormat {
  // Darwin Core Archive file that is a zip of all indexed fields in both verbatim and interpreted, plus multimedia and metadata files.
  DWCA,

  // Zipped text file of the most common indexed terms, but note that it is delimited by tabs, not commas.
  SIMPLE_CSV,

  // AVRO (with Deflate compression codec) format export of the most common indexed terms.
  SIMPLE_AVRO,

  //CSV format export of the distinct species and taxonomic field associated to it.
  SPECIES_LIST,

  //Executes SQL command and provides download
  SQL
}
