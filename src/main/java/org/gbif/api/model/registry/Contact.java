/*
 * Copyright 2020-2021 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.registry;

import io.swagger.v3.oas.annotations.media.Schema;

import org.gbif.api.vocabulary.ContactType;
import org.gbif.api.vocabulary.Country;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

// TODO: Should have a cross-field validation for key & created
@SuppressWarnings({"unused", "LombokSetterMayBeUsed", "LombokGetterMayBeUsed"})
public class Contact implements Address, LenientEquals<Contact> {

  @Schema(
    description = "Identifier for the contact",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Integer key;

  @Schema(
    description = "The type of contact."
  )
  private ContactType type;

  @Schema(
    description = "Whether this is the primary contact for the associated entity."
  )
  private boolean primary;

  @Schema(
    description = "A list of user identifiers for this contact."
  )
  private List<String> userId = new ArrayList<>();

  @Schema(
      description = "The salutation is used in addressing an individual with a particular title, " +
          "such as Dr., Ms., Mrs., Mr., etc."
  )
  private String salutation;

  @Schema(
    description = "The personal name of the contact."
  )
  private String firstName;

  @Schema(
    description = "The family name of the contact."
  )
  private String lastName;

  @Schema(
    description = "The contact's position, job title or similar within the `organization`."
  )
  private List<String> position = new ArrayList<>();

  @Schema(
    description = "A description of this contact."
  )
  private String description;

  @Schema(
    description = "Email addresses associated with this contact."
  )
  private List<String> email = new ArrayList<>();

  @Schema(
    description = "Telephone numbers associated with this contact."
  )
  private List<String> phone = new ArrayList<>();

  @Schema(
    description = "Homepages with further details on the contact."
  )
  private List<URI> homepage = new ArrayList<>();

  @Schema(
    description = "The organization (e.g. employer) associated with this contact."
  )
  private String organization;

  @Schema(
    description = "Address lines other than the city, province, country and" +
      "postal code, which have their own fields."
  )
  private List<String> address = new ArrayList<>();

  @Schema(
    description = "The city or similar line of the contact's address."
  )
  private String city;

  @Schema(
    description = "The province or similar line of the contact's address."
  )
  private String province;

  @Schema(
    description = "The country or other region of the contact's address."
  )
  private Country country;

  @Schema(
    description = "The postal code or similar line of the contact's address."
  )
  private String postalCode;

  @Schema(
    description = "The GBIF username of the creator of the contact",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "The GBIF username of the last user to modify the contact",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String modifiedBy;

  @Schema(
    description = "Timestamp of when the contact was created",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  @Schema(
    description = "Timestamp of when the contact was last modified",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date modified;

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @Nullable
  public ContactType getType() {
    return type;
  }

  public void setType(ContactType type) {
    this.type = type;
  }

  public boolean isPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

  public List<String> getUserId() {
    return userId;
  }

  public void setUserId(List<String> userId) {
    this.userId = userId;
  }

  public void addUserId(String userId) {
    this.userId.add(userId);
  }

  /**
   * Adds a new user id that is assembled from a directory name and a local id within it.
   * Format used by EML, though see https://github.com/gbif/gbif-api/issues/30.
   * The directory should be a valid URI, if it's not, it will be ignored by this method.
   *
   * @param directory identifier for the directory, preferably a URL domain like http://orcid.org
   * @param id the identifier in that directory
   */
  public void addUserId(String directory, String id) {
    if (StringUtils.isNotEmpty(id)) {
      if (StringUtils.isEmpty(directory)) {
        userId.add(id);
      } else {
        try {
          URI dir = URI.create(directory);
          if (dir.isAbsolute()) {
            String dir2 = dir.toString();
            if (!dir2.endsWith("/") && !dir2.endsWith("=")) {
              dir2 = dir2 + "/";
            }

            // Check if the id is already prefixed with the directory URI, either HTTP or HTTPS.
            //noinspection HttpUrlsUsage
            if (id.startsWith(dir2)
                || id.startsWith(dir2.replace("http://", "https://"))
                || id.startsWith(dir2.replace("https://", "http://"))) {
              userId.add(id);
              // Check if the id is prefixed with the hostname.
            } else if (id.startsWith(dir.getHost())) {
              userId.add(dir.getScheme() + "://" + id);
            } else {
              userId.add(dir2 + id);
            }
          } else {
            if (id.startsWith(dir.toString())) {
              userId.add(id);
            } else {
              userId.add(dir + ":" + id);
            }
          }
        } catch (IllegalArgumentException iaEx) {
          // in case the directory is not a valid URL keep only the user id
          userId.add(id);
        }
      }
    }
  }

  @Nullable
  public String getSalutation() {
    return salutation;
  }

  public void setSalutation(String salutation) {
    this.salutation = salutation;
  }

  @Nullable
  @Size(min = 1)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Nullable
  @Size(min = 1)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Compute and returns the complete name in the form: FirstName LastName.
   * Since all parts are optional, this method can return an empty string (but never null)
   *
   * @return the non-empty parts of FirstName LastName or empty string if none
   */
  public String computeCompleteName() {
    return Stream.of(firstName, lastName)
        .map(StringUtils::trimToNull)
        .filter(Objects::nonNull)
        .collect(Collectors.joining(" "));
  }

  public List<String> getPosition() {
    return position;
  }

  public void setPosition(List<String> position) {
    this.position = position;
  }

  public void addPosition(String position) {
    this.position.add(position);
  }

  @Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public List<String> getEmail() {
    return email;
  }

  @Override
  public void setEmail(List<String> email) {
    this.email = email;
  }

  public void addEmail(String email) {
    this.email.add(email);
  }

  @Override
  public List<String> getPhone() {
    return phone;
  }

  @Override
  public void setPhone(List<String> phone) {
    this.phone = phone;
  }

  public void addPhone(String phone) {
    this.phone.add(phone);
  }

  @Override
  public List<String> getAddress() {
    return address;
  }

  @Override
  public void setAddress(List<String> address) {
    this.address = address;
  }

  public void addAddress(String address) {
    this.address.add(address);
  }

  @Override
  public String getCity() {
    return city;
  }

  @Override
  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public String getProvince() {
    return province;
  }

  @Override
  public void setProvince(String province) {
    this.province = province;
  }

  @Override
  public Country getCountry() {
    return country;
  }

  @Override
  public void setCountry(Country country) {
    this.country = country;
  }

  @Override
  public String getPostalCode() {
    return postalCode;
  }

  @Override
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Override
  @Nullable
  @Size(min = 2)
  public String getOrganization() {
    return organization;
  }

  @Override
  public void setOrganization(String organization) {
    this.organization = organization;
  }

  @Override
  public List<URI> getHomepage() {
    return homepage;
  }

  @Override
  public void setHomepage(List<URI> homepage) {
    this.homepage = homepage;
  }

  public void addHomepage(URI homepage) {
    this.homepage.add(homepage);
  }

  @Size(min = 3)
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Size(min = 3)
  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return primary == contact.primary
        && Objects.equals(key, contact.key)
        && type == contact.type
        && Objects.equals(userId, contact.userId)
        && Objects.equals(salutation, contact.salutation)
        && Objects.equals(firstName, contact.firstName)
        && Objects.equals(lastName, contact.lastName)
        && Objects.equals(position, contact.position)
        && Objects.equals(description, contact.description)
        && Objects.equals(email, contact.email)
        && Objects.equals(phone, contact.phone)
        && Objects.equals(homepage, contact.homepage)
        && Objects.equals(organization, contact.organization)
        && Objects.equals(address, contact.address)
        && Objects.equals(city, contact.city)
        && Objects.equals(province, contact.province)
        && country == contact.country
        && Objects.equals(postalCode, contact.postalCode)
        && Objects.equals(createdBy, contact.createdBy)
        && Objects.equals(modifiedBy, contact.modifiedBy)
        && Objects.equals(created, contact.created)
        && Objects.equals(modified, contact.modified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        type,
        primary,
        userId,
        salutation,
        firstName,
        lastName,
        position,
        description,
        email,
        phone,
        homepage,
        organization,
        address,
        city,
        province,
        country,
        postalCode,
        createdBy,
        modifiedBy,
        created,
        modified);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("type=" + type)
        .add("primary=" + primary)
        .add("userId=" + userId)
        .add("salutation='" + salutation + "'")
        .add("firstName='" + firstName + "'")
        .add("lastName='" + lastName + "'")
        .add("position=" + position)
        .add("description='" + description + "'")
        .add("email=" + email)
        .add("phone=" + phone)
        .add("homepage=" + homepage)
        .add("organization='" + organization + "'")
        .add("address=" + address)
        .add("city='" + city + "'")
        .add("province='" + province + "'")
        .add("country=" + country)
        .add("postalCode='" + postalCode + "'")
        .add("createdBy='" + createdBy + "'")
        .add("modifiedBy='" + modifiedBy + "'")
        .add("created=" + created)
        .add("modified=" + modified)
        .toString();
  }

  /**
   * This implementation of the {@link #equals(Object)} method does only check <em>business equality</em> and disregards
   * automatically set and maintained fields like {@code createdBy, key} and others.
   */
  @Override
  public boolean lenientEquals(Contact contact) {
    if (this == contact) {
      return true;
    }

    return Objects.equals(type, contact.type)
        && Objects.equals(primary, contact.primary)
        && Objects.equals(userId, contact.userId)
        && Objects.equals(salutation, contact.salutation)
        && Objects.equals(firstName, contact.firstName)
        && Objects.equals(lastName, contact.lastName)
        && Objects.equals(position, contact.position)
        && Objects.equals(description, contact.description)
        && Objects.equals(email, contact.email)
        && Objects.equals(phone, contact.phone)
        && Objects.equals(homepage, contact.homepage)
        && Objects.equals(organization, contact.organization)
        && Objects.equals(address, contact.address)
        && Objects.equals(city, contact.city)
        && Objects.equals(province, contact.province)
        && Objects.equals(country, contact.country)
        && Objects.equals(postalCode, contact.postalCode);
  }
}
