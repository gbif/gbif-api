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
package org.gbif.api.model.registry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.gbif.api.vocabulary.License;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents the information required to cite a dataset in an occurrence download.
 */
@SuppressWarnings("unused")
@EqualsAndHashCode
@ToString
@Data
public class DatasetCitation implements Serializable {

  @Schema(
    description = "The GBIF dataset key of the dataset."
  )
  private UUID key;

  @Schema(
    description = "The title of the dataset, at the time the download was created."
  )
  private String title;

  @Schema(
    description = "The citation text to use."
  )
  private String citation;

  @Schema(
    description = "The licence for the dataset."
  )
  private License license;
}
