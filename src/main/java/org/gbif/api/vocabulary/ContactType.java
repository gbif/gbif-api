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

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

/**
 * Enumeration for all contact types.
 * A utility to infer types is provided which has historically been used during data migration activities from legacy
 * systems such as the previous "GBRDS" which was built on MySQL.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/agent_role.xml">rs.gbif.org vocabulary</a>
 */
public enum ContactType {
  /**
   * A contact to contact for further technical information related to the dataset.
   */
  TECHNICAL_POINT_OF_CONTACT,
  /**
   * A contact to contact for further non-technical information related to the dataset.
   */
  ADMINISTRATIVE_POINT_OF_CONTACT,
  /**
   * A contact to contact for further information about the dataset.
   */
  POINT_OF_CONTACT,
  /**
   * A contact who originally gathered/prepared the dataset.
   */
  ORIGINATOR,
  /**
   * A contact responsible for providing the metadata.
   */
  METADATA_AUTHOR,
  /**
   * A primary scientific contact associated with the dataset.
   */
  PRINCIPAL_INVESTIGATOR,
  /**
   * A contact who is an author of a publication that used the dataset, or author of a data paper.
   */
  AUTHOR,
  /**
   * A contact who contributed content to a dataset (the dataset being described may be a composite).
   */
  CONTENT_PROVIDER,
  /**
   * A contact who is responsible for/takes care of the dataset.
   */
  CUSTODIAN_STEWARD,
  /**
   * A contact involved in the publishing/distribution chain of a dataset.
   */
  DISTRIBUTOR,
  /**
   * A contact associated with editing a publication that used the dataset, or a data paper.
   */
  EDITOR,
  /**
   * A contact who owns the dataset (may or may not be the custodian).
   */
  OWNER,
  /**
   * A contact responsible for any post-collection processing of the dataset.
   */
  PROCESSOR,
  /**
   * A contact associated with the publishing of some entity (paper, article, book, etc) based on the dataset, or of a
   * data paper.
   */
  PUBLISHER,
  /**
   * The contact that makes use of the dataset.
   */
  USER,
  /**
   * The contact providing informatics/programming support related to the dataset.
   */
  PROGRAMMER,
  /**
   * The contact that maintains and documents the specimens in a collection. Some of their duties include preparing and
   * labeling specimens so they are ready for identification, and protecting the specimens.
   */
  CURATOR,
  /**
   * A contact who manages the operation of a data system.
   */
  DATA_ADMINISTRATOR,
  /**
   * A contact who manages the operation of a computer system.
   */
  SYSTEM_ADMINISTRATOR,
  /**
   * A contact appointed to lead and represent a Participant's delegation at the GBIF Governing Board.
   */
  HEAD_OF_DELEGATION,
  /**
   * A contact temporarily appointed to lead and represent a Participant's delegation at the GBIF Governing Board.
   */
  TEMPORARY_HEAD_OF_DELEGATION,
  /**
   * A contact appointed to a Participant's delegation at the GBIF Governing Board.
   */
  ADDITIONAL_DELEGATE,
  /**
   * A contact temporarily appointed to a Participant's delegation at the GBIF Governing Board.
   */
  TEMPORARY_DELEGATE,
  /**
   * A contact representing a regional group of Nodes.
   */
  REGIONAL_NODE_REPRESENTATIVE,
  /**
   * A contact leading the work of the Node and representing the Node in the Nodes Committee.
   */
  NODE_MANAGER,
  /**
   * A contact who is a member of the Node's staff.
   */
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
    .put("curator", CURATOR)
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
