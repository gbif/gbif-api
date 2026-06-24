package org.gbif.api.util;

public class TermNormalizationUtils {

  /**
   * This normalization is used to generate the avro schemas of the verbatim extensions tables in
   * downloadTables->XmlToAvscGeneratorMojo and hence the same normalization has to be used when
   * populating the tables(pipelines->VerbatimExtensionsInterpretationPipeline) and when downloading
   * these extensions from ES(occurrence->DownloadDwcaActor).
   */
  public static String normalizeFieldName(String name) {
    String normalizedNamed = name.toLowerCase().trim()
      .replace("-", "")
      .replace("_", "")
      .replace(":", "_");
    if (Character.isDigit(normalizedNamed.charAt(0))) {
      return '_' + normalizedNamed;
    }
    return normalizedNamed;
  }
}
