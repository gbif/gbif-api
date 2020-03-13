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
package org.gbif.api.model.occurrence;

import org.gbif.api.vocabulary.UserIdentifierType;

import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserIdentifierTest {

  @Test
  public void testValueOf() {
    Optional<UserIdentifier> emptyValue = UserIdentifier.valueOf("");
    Optional<UserIdentifier> nullValue = UserIdentifier.valueOf(null);
    Optional<UserIdentifier> delimiter = UserIdentifier.valueOf(":");
    Optional<UserIdentifier> comma = UserIdentifier.valueOf(",");
    Optional<UserIdentifier> orcid = UserIdentifier.valueOf("orcid:213123");
    Optional<UserIdentifier> orcidUpper = UserIdentifier.valueOf("ORCID:213123");

    assertFalse(emptyValue.isPresent());
    assertFalse(nullValue.isPresent());
    assertFalse(delimiter.isPresent());
    assertFalse(comma.isPresent());
    assertFalse(orcid.isPresent());

    assertTrue(orcidUpper.isPresent());
    assertEquals(UserIdentifierType.ORCID, orcidUpper.get().getType());
    assertEquals("213123", orcidUpper.get().getValue());
  }

}
