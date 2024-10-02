/*
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
package org.gbif.api.model.pipelines;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/** Enum to represent the pipelines step names. */
@Getter
@AllArgsConstructor
public enum StepType {

  // Ingestion

  // Verbatim
  TO_VERBATIM("toVerbatim"),
  DWCA_TO_VERBATIM("dwcaToVerbatim"),
  XML_TO_VERBATIM("xmlToVerbatim"),
  ABCD_TO_VERBATIM("abcdToVerbatim"),
  FRAGMENTER("fragmenter"),

  // Occurrence interpretation
  VERBATIM_TO_IDENTIFIER("verbatimToIdentifier"),
  VERBATIM_TO_INTERPRETED("verbatimToInterpreted"),
  INTERPRETED_TO_INDEX("interpretedToIndex"),
  HDFS_VIEW("hdfsView"),

  // Event interpretation
  EVENTS_VERBATIM_TO_INTERPRETED("eventsVerbatimToInterpreted"),
  EVENTS_INTERPRETED_TO_INDEX("eventsInterpretedToIndex"),
  EVENTS_HDFS_VIEW("eventsHdfsView"),

  // Validator
  VALIDATOR_UPLOAD_ARCHIVE("validatorUploadArchive"),
  VALIDATOR_VALIDATE_ARCHIVE("validatorValidateArchive"),
  VALIDATOR_DWCA_TO_VERBATIM("validatorDwcaToVerbatim"),
  VALIDATOR_XML_TO_VERBATIM("validatorXmlToVerbatim"),
  VALIDATOR_ABCD_TO_VERBATIM("validatorAbcdToVerbatim"),
  VALIDATOR_TABULAR_TO_VERBATIM("validatorTabularToVerbatim"),
  VALIDATOR_VERBATIM_TO_INTERPRETED("validatorVerbatimToInterpreted"),
  VALIDATOR_INTERPRETED_TO_INDEX("validatorInterpretedToIndex"),
  VALIDATOR_COLLECT_METRICS("validatorCollectMetrics");

  private final String label;

  private static final Set<StepType> VERBATIM_TYPE =
    Set.of(TO_VERBATIM, DWCA_TO_VERBATIM, XML_TO_VERBATIM, ABCD_TO_VERBATIM, FRAGMENTER);

  private static final Set<StepType> OCCURRENCE_TYPE =
    Set.of(VERBATIM_TO_IDENTIFIER, VERBATIM_TO_INTERPRETED, INTERPRETED_TO_INDEX, HDFS_VIEW);

  private static final Set<StepType> EVENT_TYPE =
    Set.of(EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_INTERPRETED_TO_INDEX, EVENTS_HDFS_VIEW);

  public static boolean isEventType(StepType type) {
    return EVENT_TYPE.contains(type);
  }

  public static boolean isOccurrenceType(StepType type) {
    return OCCURRENCE_TYPE.contains(type);
  }

  public static boolean isVerbatimType(StepType type) {
    return VERBATIM_TYPE.contains(type);
  }
}
