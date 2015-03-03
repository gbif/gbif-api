/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.vocabulary;

import org.gbif.api.util.VocabularyUtils;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

/**
 * Enumeration for all contact types.
 * A utility to infer types is provided which has historically been used during data migration activities from legacy
 * systems such as the previous "GBRDS" which was built on MySQL.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/agent_role.xml">The IPT vocabulary</a>
 */
public enum ContactType {

  TECHNICAL_POINT_OF_CONTACT,
  ADMINISTRATIVE_POINT_OF_CONTACT,
  POINT_OF_CONTACT,
  ORIGINATOR,
  METADATA_AUTHOR,
  PRINCIPAL_INVESTIGATOR,
  AUTHOR,
  CONTENT_PROVIDER,
  CUSTODIAN_STEWARD,
  DISTRIBUTOR,
  EDITOR,
  OWNER,
  PROCESSOR,
  PUBLISHER,
  USER,
  PROGRAMMER,
  DATA_ADMINISTRATOR,
  SYSTEM_ADMINISTRATOR,
  HEAD_OF_DELEGATION,
  TEMPORARY_HEAD_OF_DELEGATION,
  ADDITIONAL_DELEGATE,
  TEMPORARY_DELEGATE,
  REGIONAL_NODE_REPRESENTATIVE,
  NODE_MANAGER,
  NODE_STAFF;

  /**
   * @return the matching ContactType or null
   */
  public static ContactType fromString(String contactType) {
    return (ContactType) VocabularyUtils.lookupEnum(contactType, ContactType.class);
  }

  // deliberate typos have existed and might be in genuine use
  private static final ImmutableMap<String, ContactType> TYPE_LOOKUP = ImmutableMap.<String, ContactType> builder()
    .put("administrative", ADMINISTRATIVE_POINT_OF_CONTACT)
    .put("technical", TECHNICAL_POINT_OF_CONTACT)
    .put("pointofcontact", POINT_OF_CONTACT)
    .put("originator", ORIGINATOR)
    .put("metadataprovider", METADATA_AUTHOR)
    .put("principleinvestigator", PRINCIPAL_INVESTIGATOR)
    .put("author", AUTHOR)
    .put("contentprovider", CONTENT_PROVIDER)
    .put("custodiansteward", CUSTODIAN_STEWARD)
    .put("distributor", DISTRIBUTOR)
    .put("editor", EDITOR)
    .put("owner", OWNER)
    .put("processor", PROCESSOR)
    .put("publisher", PUBLISHER)
    .put("user", USER)
    .put("programmer", PROGRAMMER)
    .put("data administrator", DATA_ADMINISTRATOR)
    .put("system adminsitrator", SYSTEM_ADMINISTRATOR) // deliberate typo
    .put("system administrator", SYSTEM_ADMINISTRATOR)
  .build();

  /**
   * Tries its best to infer a ContactType from a given string. This can for example be used for the various contact
   * types from DiGIR, TAPIR and BioCASe.
   *
   * @return the inferred ContactType
   */
  public static ContactType inferType(@Nullable String type) {
    if (type != null) {
      ContactType contactType = TYPE_LOOKUP.get(type.toLowerCase());
      if (contactType != null) {
        return contactType;
      }
      return fromString(type);
    }
    return null;
  }
}
