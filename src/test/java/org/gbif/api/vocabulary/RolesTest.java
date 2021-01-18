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
package org.gbif.api.vocabulary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Ensure that roles defined in {@link UserRole} and {@link AppRole} are unique.
 */
public class RolesTest {

  @Test
  public void testRolesUniqueness() {
    for(UserRole ur : UserRole.values()) {
      try{
        AppRole.valueOf(ur.name());
        fail(ur.name() + " is defined in UserRole AND AppRole.");
      }
      catch (IllegalArgumentException iaEx) {
        //expected
      }
    }
  }
}
