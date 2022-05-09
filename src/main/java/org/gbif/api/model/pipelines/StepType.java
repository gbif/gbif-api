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

/** Enum to represent the pipelines step names. */
public enum StepType {

  // Generic step name used to be a replace of the following three when client doesn't know the endpoint type of a dataset
  // Interpretaion
  TO_VERBATIM("toVerbatim", 1),
  DWCA_TO_VERBATIM("dwcaToVerbatim", 1),
  XML_TO_VERBATIM("xmlToVerbatim", 1),
  ABCD_TO_VERBATIM("abcdToVerbatim", 1),
  VERBATIM_TO_IDENTIFIER("verbatimToIdentifier", 2),
  VERBATIM_TO_INTERPRETED("verbatimToInterpreted", 3),
  INTERPRETED_TO_INDEX("interpretedToIndex", 4),
  HDFS_VIEW("hdfsView", 4),
  FRAGMENTER("fragmenter", 4),
  // Validator
  VALIDATOR_UPLOAD_ARCHIVE("validatorUploadArchive", 1),
  VALIDATOR_VALIDATE_ARCHIVE("validatorValidateArchive", 2),
  VALIDATOR_DWCA_TO_VERBATIM("validatorDwcaToVerbatim", 3),
  VALIDATOR_XML_TO_VERBATIM("validatorXmlToVerbatim", 3),
  VALIDATOR_ABCD_TO_VERBATIM("validatorAbcdToVerbatim", 3),
  VALIDATOR_TABULAR_TO_VERBATIM("validatorTabularToVerbatim", 3),
  VALIDATOR_VERBATIM_TO_INTERPRETED("validatorVerbatimToInterpreted", 4),
  VALIDATOR_INTERPRETED_TO_INDEX("validatorInterpretedToIndex", 5),
  VALIDATOR_COLLECT_METRICS("validatorCollectMetrics", 6),
  EVENTS_VERBATIM_TO_INTERPRETED("eventsVerbatimToInterpreted", 3),
  EVENTS_INTERPRETED_TO_INDEX("eventsInterpretedToIndex", 4);

  private String label;
  private int executionOrder;

  StepType(String label, int executionOrder) {
    this.label = label;
    this.executionOrder = executionOrder;
  }

  public int getExecutionOrder() {
    return executionOrder;
  }

  public String getLabel() {
    return label;
  }
}
