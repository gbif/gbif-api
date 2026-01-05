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

import org.gbif.api.SerdeTestUtils;
import org.gbif.api.vocabulary.UserRole;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Deprecated
public class UserTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void testHasRole() {
    User u = new User();
    u.setUserName("betty");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty Ford");

    assertFalse(u.hasRole(UserRole.USER));
    assertFalse(u.hasRole(UserRole.REGISTRY_EDITOR));
    assertFalse(u.hasRole(UserRole.REGISTRY_ADMIN));
    assertFalse(u.hasRole(null));

    u.addRole(UserRole.USER);
    assertTrue(u.hasRole(UserRole.USER));
    assertFalse(u.hasRole(UserRole.REGISTRY_EDITOR));
    assertFalse(u.hasRole(UserRole.REGISTRY_ADMIN));
    assertFalse(u.hasRole(null));

    u.addRole(UserRole.REGISTRY_ADMIN);
    assertTrue(u.hasRole(UserRole.USER));
    assertFalse(u.hasRole(UserRole.REGISTRY_EDITOR));
    assertTrue(u.hasRole(UserRole.REGISTRY_ADMIN));
    assertFalse(u.hasRole(null));
  }

  @Test
  public void testIsAdmin() {
    User u = new User();
    u.setUserName("betty");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty Ford");

    assertFalse(u.isAdmin());

    u.addRole(UserRole.ADMIN);
    assertTrue(u.isAdmin());
  }

  @Test
  public void testValidation() {
    User u = new User();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    assertValidation("userName", u);

    u.setUserName("betty");
    u.setFirstName(null);
    assertValidation("firstName", u);

    u.setFirstName("Betty");
    assertValidation(null, u);

    u.setEmail("betty at gbif.org");
    assertValidation("email", u);
  }

  private void assertValidation(String property, User u) {
    Set<ConstraintViolation<User>> err = validator.validate(u);
    System.out.print(err);
    if (property == null) {
      assertEquals(0, err.size());
    } else {
      assertEquals(1, err.size());
      assertEquals(property, err.iterator().next().getPropertyPath().toString());
    }
  }

  @Test
  public void testSerDe() throws IOException {
    User u = new User();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    SerdeTestUtils.testSerDe(u, User.class);
  }

  @Test
  public void testSerDeSettings() throws IOException {
    User u = new User();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    Map<String, String> settings = new HashMap<>();
    settings.put("name", "tim");
    u.setSettings(settings);
    SerdeTestUtils.testSerDe(u, User.class);
  }

  @Test
  public void testName() {
    User u = new User();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    assertEquals("Betty Ford", u.getName());
  }
}
