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

  // Interpretaion
  TO_VERBATIM("toVerbatim"),
  DWCA_TO_VERBATIM("dwcaToVerbatim"),
  XML_TO_VERBATIM("xmlToVerbatim"),
  ABCD_TO_VERBATIM("abcdToVerbatim"),
  VERBATIM_TO_IDENTIFIER("verbatimToIdentifier"),
  VERBATIM_TO_INTERPRETED("verbatimToInterpreted"),
  INTERPRETED_TO_INDEX("interpretedToIndex"),
  HDFS_VIEW("hdfsView"),
  FRAGMENTER("fragmenter"),
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

  StepType(String label) {
    this.label = label;
  }

  private String label;

  public String getLabel() {
    return label;
  }
}
