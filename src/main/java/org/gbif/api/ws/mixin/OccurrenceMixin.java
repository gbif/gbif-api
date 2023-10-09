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
package org.gbif.api.ws.mixin;

import org.gbif.api.jackson.DateSerde;
import org.gbif.api.jackson.IsoDateIntervalSerde;
import org.gbif.api.util.IsoDateInterval;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public interface OccurrenceMixin extends LicenseMixin {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonSerialize(using = DateSerde.NoTimezoneDateJsonSerializer.class, keyUsing = DateSerde.NoTimezoneDateJsonSerializer.class)
  Date getDateIdentified();

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonSerialize(using = IsoDateIntervalSerde.IsoDateIntervalSerializer.class, keyUsing = IsoDateIntervalSerde.IsoDateIntervalSerializer.class)
  IsoDateInterval getEventDate();
}
