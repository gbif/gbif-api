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
package org.gbif.api.model.common;

import org.gbif.api.vocabulary.UserRole;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserPrincipalTest {
  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void testHasRole() throws Exception {
    User user = new User();
    user.setKey(100);
    user.setUserName("betty");
    user.setEmail("betty@gbif.org");
    user.setFirstName("Betty");
    user.setLastName("Ford");
    UserPrincipal u = new UserPrincipal(user);

    assertFalse(u.hasRole("USER"));
    assertFalse(u.hasRole("User"));
    assertFalse(u.hasRole("admin"));

    user.addRole(UserRole.USER);
    assertTrue(u.hasRole("user"));
    assertTrue(u.hasRole("USER"));
    assertTrue(u.hasRole("User"));
    assertFalse(u.hasRole("admin"));

    user.addRole(UserRole.ADMIN);
    assertTrue(u.hasRole("User"));
    assertTrue(u.hasRole("admin"));
    assertTrue(u.hasRole("Admin"));
  }

}
