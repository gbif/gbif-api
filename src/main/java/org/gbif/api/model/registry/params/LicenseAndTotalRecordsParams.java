package org.gbif.api.model.registry.params;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.gbif.api.vocabulary.License;

/**
 * Utility class to send a download license and total records in a request body.
 */
@Data
@AllArgsConstructor
public class LicenseAndTotalRecordsParams {

  private License license;
  private long totalRecords;

}
