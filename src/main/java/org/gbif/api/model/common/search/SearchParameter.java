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
package org.gbif.api.model.common.search;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Interface to be implemented by all search parameter enumerations.
 * The enumeration member name should avoid the following names which are already defined as
 * standard (faceted) search parameters:
 * <ul>
 * <li>limit</li>
 * <li>offset</li>
 * <li>q</li>
 * <li>hl</li>
 * <li>language</li>
 * <li>facet</li>
 * </ul>
 */
@JsonDeserialize(as = OccurrenceSearchParameter.class)
public interface SearchParameter {

  String name();

  Class<?> type();
}
