/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
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
