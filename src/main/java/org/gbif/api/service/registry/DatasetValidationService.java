package org.gbif.api.service.registry;

import java.util.UUID;

public interface DatasetValidationService {

  /**
   * Stores or updates the validation report for a specific crawl attempt of a dataset.
   *
   * @param datasetKey key of the target dataset
   * @param attempt    crawl attempt number
   * @param report     validation report as a JSON string
   */
  void createOrUpdate(UUID datasetKey, int attempt, String report);

  /**
   * Retrieves the validation report for a specific crawl attempt of a dataset.
   *
   * @param datasetKey key of the target dataset
   * @param attempt    crawl attempt number
   * @return validation report as a JSON string
   */
  String get(UUID datasetKey, int attempt);

  /**
   * Retrieves the validation report for the latest crawl attempt of a dataset.
   *
   * @param datasetKey key of the target dataset
   * @return validation report as a JSON string
   */
  String getLatest(UUID datasetKey);

}
