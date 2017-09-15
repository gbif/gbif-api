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

/**
 * Tests related to {@link GbifUser}.
 */
public class GbifUserTest {

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void testHasRole() throws Exception {
    GbifUser u = new GbifUser();
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
  public void testValidation() throws Exception {
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
  public void testEmailValidation() throws Exception {
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
    Map<String, String> settings = Maps.newHashMap();
    settings.put("name", "tim");
    u.setSettings(settings);
    SerdeTestUtils.testSerDe(u, GbifUser.class);
  }

  @Test
  public void testName() throws IOException {
    GbifUser u = new GbifUser();
    u.setKey(100);
    u.setUserName("be");
    u.setEmail("betty@gbif.org");
    u.setFirstName("Betty");
    u.setLastName("Ford");
    assertEquals("Betty Ford", u.getName());
  }
}
