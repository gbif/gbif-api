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
package org.gbif.api.ws.mixin;

import org.gbif.api.jackson.DateSerde;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public interface OccurrenceMixin extends LicenseMixin {

  @JsonSerialize(using = DateSerde.NoTimezoneDateJsonSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
  Date getDateIdentified();

  @JsonSerialize(using = DateSerde.NoTimezoneDateJsonSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
  Date getEventDate();
}
