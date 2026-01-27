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
package org.gbif.api.model.registry.search;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import org.gbif.api.util.Range;
import org.gbif.api.vocabulary.Country;

@Getter
@Setter
public class OrganizationRequestSearchParams extends RequestSearchParams {

  public static final String IS_ENDORSED_PARAM = "isEndorsed";
  public static final String NETWORK_KEY_PARAM = "networkKey";
  public static final String CAN_MODIFY_PARAM = "canModify";

  private Boolean isEndorsed;
  private UUID networkKey;
  private Country country;
  private Range<Integer> numPublishedDatasets;
  private String contactUserId;
  private String contactEmail;
  private String canModify;
}
