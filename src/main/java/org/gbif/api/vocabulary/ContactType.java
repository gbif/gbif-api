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
 * See vocabulary used by the IPT:
 * http://rs.gbif.org/vocabulary/gbif/agent_role.xml
 *
 * The IMS contains these types, most of them are not mappable to this enumeration:
 * <ul>
 *
 * </ul>
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

  private static final ImmutableMap<String, ContactType> TYPE_LOOKUP;

  static {

    Map<String, ContactType> lookup = new HashMap<String, ContactType>();
    lookup.put("administrative", ADMINISTRATIVE_POINT_OF_CONTACT);
    lookup.put("technical", TECHNICAL_POINT_OF_CONTACT);
    lookup.put("pointofcontact", POINT_OF_CONTACT);
    lookup.put("originator", ORIGINATOR);
    lookup.put("metadataprovider", METADATA_AUTHOR);
    lookup.put("principleinvestigator", PRINCIPAL_INVESTIGATOR);
    lookup.put("author", AUTHOR);
    lookup.put("contentprovider", CONTENT_PROVIDER);
    lookup.put("custodiansteward", CUSTODIAN_STEWARD);
    lookup.put("distributor", DISTRIBUTOR);
    lookup.put("editor", EDITOR);
    lookup.put("owner", OWNER);
    lookup.put("processor", PROCESSOR);
    lookup.put("publisher", PUBLISHER);
    lookup.put("user", USER);
    lookup.put("programmer", PROGRAMMER);
    lookup.put("data administrator", DATA_ADMINISTRATOR);
    lookup.put("system adminsitrator", SYSTEM_ADMINISTRATOR);
    TYPE_LOOKUP = ImmutableMap.copyOf(lookup);
  }

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
