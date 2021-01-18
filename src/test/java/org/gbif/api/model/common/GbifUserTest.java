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
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests related to {@link GbifUser}.
 */
public class GbifUserTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void testHasRole() {
    GbifUser u = new GbifUser();
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
  public void testValidation() {
    GbifUser u = new GbifUser();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    u.setPasswordHash("a");
    assertValidation("userName", u);

    u.setUserName("betty");
    u.setEmail("betty at gbif.org");
    assertValidation("email", u);
  }

  @Test
  public void testEmailValidation() {
    GbifUser u = new GbifUser();
    u.setKey(100);
    u.setUserName("betty");
    u.setEmail("b.betty@pre.name-dash.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    u.setPasswordHash("a");
    assertValidation(null, u);
  }

  private void assertValidation(String property, GbifUser u) {
    Set<ConstraintViolation<GbifUser>> err = validator.validate(u);
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
    GbifUser u = new GbifUser();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    SerdeTestUtils.testSerDe(u, GbifUser.class);
  }

  @Test
  public void testSerDeSettings() throws IOException {
    GbifUser u = new GbifUser();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    Map<String, String> settings = new HashMap<>();
    settings.put("name", "tim");
    u.setSettings(settings);
    SerdeTestUtils.testSerDe(u, GbifUser.class);
  }

  @Test
  public void testName() {
    GbifUser u = new GbifUser();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    assertEquals("Betty Ford", u.getName());
  }

  @Test
  public void testCopyConstructor() {
    GbifUser u = new GbifUser();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");

    Map<String, String> settings = new HashMap<>();
    settings.put("name", "tim");
    settings.put("locale", Locale.SIMPLIFIED_CHINESE.toLanguageTag());
    u.setSettings(settings);

    Map<String, String> systemSettings = new HashMap<>();
    systemSettings.put("hello", "world");
    u.setSystemSettings(systemSettings);

    Set<UserRole> roles = new HashSet<>();
    roles.add(UserRole.USER);
    u.setRoles(roles);

    GbifUser copy = new GbifUser(u);

    // change original
    u.setKey(101);
    u.setUserName("new");
    u.setEmail("new@gbif.org");
    u.setFirstName("John");
    u.setLastName("Smith");

    settings.put("another", "one");
    settings.put("locale", Locale.TRADITIONAL_CHINESE.toLanguageTag());
    u.setSettings(settings);

    systemSettings.put("another", "two");
    u.setSystemSettings(systemSettings);

    roles.add(UserRole.REGISTRY_ADMIN);
    u.setRoles(roles);

    // assert copy not changed
    assertEquals(Integer.valueOf(100), copy.getKey());
    assertEquals("be", copy.getUserName());
    assertEquals("betty@gbif.org", copy.getEmail());
    assertEquals("Betty", copy.getFirstName());
    assertEquals("Ford", copy.getLastName());
    assertEquals(Locale.SIMPLIFIED_CHINESE, copy.getLocale());
    assertEquals(2, copy.getSettings().size());
    assertEquals("tim", copy.getSettings().get("name"));
    assertEquals(Locale.SIMPLIFIED_CHINESE.toLanguageTag(), copy.getSettings().get("locale"));
    assertEquals(1, copy.getSystemSettings().size());
    assertEquals("world", copy.getSystemSettings().get("hello"));
    assertEquals(1, copy.getRoles().size());
  }
}
