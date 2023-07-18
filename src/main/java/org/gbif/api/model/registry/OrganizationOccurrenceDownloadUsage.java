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

import java.io.Serializable;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Represents the information about the usage of one organization in an occurrence download. */
@SuppressWarnings("unused")
@Data
public class OrganizationOccurrenceDownloadUsage implements Serializable {

  @Schema(
      description =
          "The GBIF key assigned to the download.\n\n"
              + "Note that citations should instead use the download DOI.")
  private String downloadKey;

  @Schema(description = "The GBIF organization key.")
  private UUID organizationKey;

  @Schema(description = "The title of the organization, at the time the download was created.")
  private String organizationTitle;

  @Schema(description = "The number of records from this organization included in the download.")
  private long numberRecords;

  @Schema(description = "The publishing country code of the organization.")
  private String publishingCountryCode;
}
