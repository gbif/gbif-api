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
package org.gbif.api.model.collections.duplicates;

import org.gbif.api.jackson.OffsetDateTimeSerDe;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/** Result to be used for the GRSciColl API duplicates services. */
public class DuplicatesResult implements Serializable {

  @JsonSerialize(using = OffsetDateTimeSerDe.OffsetDateTimeSerializer.class)
  @JsonDeserialize(using = OffsetDateTimeSerDe.OffsetDateTimeDeserializer.class)
  private OffsetDateTime generationDate;

  private List<Set<Duplicate>> duplicates = new ArrayList<>();

  public OffsetDateTime getGenerationDate() {
    return generationDate;
  }

  public void setGenerationDate(OffsetDateTime generationDate) {
    this.generationDate = generationDate;
  }

  public List<Set<Duplicate>> getDuplicates() {
    return duplicates;
  }

  public void setDuplicates(List<Set<Duplicate>> duplicates) {
    this.duplicates = duplicates;
  }
}
