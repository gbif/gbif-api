package org.gbif.api.service.occurrence;

import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Kingdom;

import java.util.Map;

import javax.validation.constraints.Min;

/**
 * Supports listing occurrence counts by known dimensions.
 */
public interface OccurrenceDistributionIndexService {

  /**
   * Returns the occurrence records count by basis of record. The result is ordered descending by the count.
   */
  Map<BasisOfRecord, Long> getBasisOfRecordCounts();

  /**
   * Returns the occurrence records count by kingdom. The result is ordered descending by the count.
   */
  Map<Kingdom, Long> getKingdomCounts();

  /**
   * Returns the occurrence records count by year. The result is ordered descending by the count.
   * 
   * @param from minimum year
   * @param to maximum year
   */
  Map<Integer, Long> getYearCounts(@Min(0) int from, @Min(0) int to);

}
