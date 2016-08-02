/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.registry;

import org.gbif.api.vocabulary.ContactType;
import org.gbif.api.vocabulary.Country;

import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

// TODO: Should have a cross-field validation for key & created
public class Contact implements Address, LenientEquals<Contact> {

  private Integer key;
  private ContactType type;
  private boolean primary;
  private List<String> userId = Lists.newArrayList();
  private String firstName;
  private String lastName;
  private List<String> position = Lists.newArrayList();
  private String description;
  private List<String> email = Lists.newArrayList();
  private List<String> phone = Lists.newArrayList();
  private List<URI> homepage = Lists.newArrayList();
  private String organization;
  private List<String> address = Lists.newArrayList();
  private String city;
  private String province;
  private Country country;
  private String postalCode;
  private String createdBy;
  private String modifiedBy;
  private Date created;
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

  @Nullable
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
   * Format used by EML.
   * @param directory identifier for the directory, preferrably a URL domain like http://orcid.org
   * @param id the iddentifier in that directory
   */
  public void addUserId(String directory, String id) {
    if (!Strings.isNullOrEmpty(id)) {
      if (Strings.isNullOrEmpty(directory)) {
        userId.add(id);
      } else {
        URI dir = URI.create(directory);
        if (dir.isAbsolute()) {
          String dir2 = dir.toString();
          if (!dir2.endsWith("/") && !dir2.endsWith("=")) {
            dir2 = dir2 + "/";
          }
          userId.add( dir2 + id);
        } else {
          userId.add(dir + ":" + id);
        }
      }
    }
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
  @Nullable
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
  @Nullable
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
  public int hashCode() {
    return Objects
      .hashCode(key, type, primary, userId, firstName, lastName, position, description, email, phone, homepage,
                organization, address, city, province, country, postalCode, createdBy, modifiedBy, created, modified);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof Contact) {
      Contact that = (Contact) object;
      return lenientEquals(that)
        && Objects.equal(this.key, that.key)
        && Objects.equal(this.primary, that.primary)
        && Objects.equal(this.createdBy, that.createdBy)
        && Objects.equal(this.modifiedBy, that.modifiedBy)
        && Objects.equal(this.created, that.created)
        && Objects.equal(this.modified, that.modified);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("key", key).add("type", type)
      .add("primary", primary)
      .add("userId", userId)
      .add("firstName", firstName).add("lastName", lastName).add("position", position)
      .add("description", description).add("email", email).add("phone", phone)
      .add("homepage", homepage)
      .add("organization", organization)
      .add("address", address).add("city", city).add("province", province).add("country", country)
      .add("postalCode", postalCode)
      .add("createdBy", createdBy).add("modifiedBy", modifiedBy).add("created", created)
      .add("modified", modified).toString();
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

    return Objects.equal(type, contact.type)
      && Objects.equal(primary, contact.primary)
      && Objects.equal(userId, contact.userId)
      && Objects.equal(firstName, contact.firstName)
      && Objects.equal(lastName, contact.lastName)
      && Objects.equal(position, contact.position)
      && Objects.equal(description, contact.description)
      && Objects.equal(email, contact.email)
      && Objects.equal(phone, contact.phone)
      && Objects.equal(homepage, contact.homepage)
      && Objects.equal(organization, contact.organization)
      && Objects.equal(address, contact.address)
      && Objects.equal(city, contact.city)
      && Objects.equal(province, contact.province)
      && Objects.equal(country, contact.country)
      && Objects.equal(postalCode, contact.postalCode);
  }

}
