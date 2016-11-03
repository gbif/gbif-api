/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.collect.Maps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {
  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void testHasRole() throws Exception {
    User u = new User();
    u.setUserName("betty");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty Ford");

    assertFalse(u.hasRole(UserRole.USER));
    assertFalse(u.hasRole(UserRole.REGISTRY_EDITOR));
    assertFalse(u.hasRole(UserRole.ADMIN));
    assertFalse(u.hasRole(null));

    u.addRole(UserRole.USER);
    assertTrue(u.hasRole(UserRole.USER));
    assertFalse(u.hasRole(UserRole.REGISTRY_EDITOR));
    assertFalse(u.hasRole(UserRole.ADMIN));
    assertFalse(u.hasRole(null));

    u.addRole(UserRole.ADMIN);
    assertTrue(u.hasRole(UserRole.USER));
    assertFalse(u.hasRole(UserRole.REGISTRY_EDITOR));
    assertTrue(u.hasRole(UserRole.ADMIN));
    assertFalse(u.hasRole(null));
  }

  @Test
  public void testIsAdmin() throws Exception {
    User u = new User();
    u.setUserName("betty");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty Ford");

    assertFalse(u.isAdmin());

    u.addRole(UserRole.ADMIN);
    assertTrue(u.isAdmin());
  }

  @Test
  public void testValidation() throws Exception {
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
    Map<String, String> settings = Maps.newHashMap();
    settings.put("name", "tim");
    u.setSettings(settings);
    SerdeTestUtils.testSerDe(u, User.class);
  }

  @Test
  public void testName() throws IOException {
    User u = new User();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    assertEquals("Betty Ford", u.getName());
  }

}
