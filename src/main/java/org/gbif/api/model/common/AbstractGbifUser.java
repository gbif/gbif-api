package org.gbif.api.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.gbif.api.vocabulary.UserRole;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * An abstract GBIF user account.
 * The main purpose of this abstraction is to let subclasses handle key and password information only if required.
 * By doing so, it is possible to have classes working for user information without having to carry those
 * information around.
 */
public abstract class AbstractGbifUser {
  protected static final String EMAIL_PATTERN =
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  private static final Joiner JOINER = Joiner.on(" ").skipNulls();

  protected String userName;
  protected String firstName;
  protected String lastName;
  protected String email;
  protected Set<UserRole> roles = Sets.newHashSet();

//  //country of the user (user for stats)
//  protected Country country;

  protected Map<String, String> settings = Maps.newHashMap();

  //settings that the user will not set directly
  protected Map<String, String> systemSettings = Maps.newHashMap();

  protected Date deleted;

  @NotNull
  @Pattern(regexp = EMAIL_PATTERN)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * The unique, immutable drupal user account name.
   * This name should be used for referring to a user.
   * The account name is made of ASCII lower case alphanumerics, underscore, dash or dots and is in particular void
   * of whitespace.
   */
  @NotNull
  @Pattern(regexp = "^[a-z0-9_.-]+$")
  @Size(min = 3, max = 64)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the first name of a person
   */
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the last name of the user
   */
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the first and last name of the user concatenated with a space
   */
  @JsonIgnore
  public String getName() {
    return JOINER.join(firstName, lastName);
  }

  @NotNull
  public Set<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }

  public void addRole(UserRole role) {
    roles.add(role);
  }

  /**
   * Checks if the user has the given user role.
   *
   * @param role
   * @return true if the user has the requested role
   */
  public boolean hasRole(UserRole role) {
    return role != null && roles.contains(role);
  }

  /**
   * Sets the settings object, setting an empty map if null is provided.
   */
  public void setSettings(Map<String, String> settings) {
    // safeguard against misuse to avoid NPE
    this.settings = settings == null ? Maps.newHashMap() : settings;
  }

  /**
   * Gets the settings which may be empty but never null.
   *
   * @return
   */
  @NotNull
  public Map<String, String> getSettings() {
    return settings;
  }

  /**
   * Sets the settings object, setting an empty map if null is provided.
   */
  public void setSystemSettings(Map<String, String> systemSettings) {
    // safeguard against misuse to avoid NPE
    this.systemSettings = systemSettings == null ? Maps.newHashMap() : systemSettings;
  }

  /**
   * Gets the settings which may be empty but never null.
   *
   * @return
   */
  @NotNull
  public Map<String, String> getSystemSettings() {
    return systemSettings;
  }

  public Date getDeleted() {
    return deleted;
  }

  public void setDeleted(Date deleted) {
    this.deleted = deleted;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AbstractGbifUser)) {
      return false;
    }

    AbstractGbifUser that = (AbstractGbifUser) obj;
    return Objects.equal(this.userName, that.userName)
      && Objects.equal(this.firstName, that.firstName)
      && Objects.equal(this.lastName, that.lastName)
      && Objects.equal(this.email, that.email)
      && Objects.equal(this.roles, that.roles)
      && Objects.equal(this.settings, that.settings)
      && Objects.equal(this.systemSettings, that.systemSettings)
      && Objects.equal(this.deleted, that.deleted);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(userName, firstName, lastName, email, roles, settings,
      systemSettings, deleted);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("userName", userName)
      .add("firstName", firstName)
      .add("lastName", lastName)
      .add("email", email)
      .add("roles", roles)
      .add("settings", settings)
      .add("systemSettings", systemSettings)
      .add("deleted", deleted)
      .toString();
  }

}
