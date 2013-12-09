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

import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.ContactType;

import java.util.Date;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

// TODO: Should have a cross-field validation for key & created
public class Contact implements Address, LenientEquals<Contact> {

  private Integer key;
  private ContactType type;
  private boolean primary;
  private String firstName;
  private String lastName;
  private String position;
  private String description;
  private String email;
  private String phone;
  private String organization;
  private String address;
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

  @Nullable
  @Size(min = 2)
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // TODO: Add @Email validation
  @Override
  @Nullable
  @Size(min = 1, max = 254)
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  @Nullable
  @Size(min = 5, max = 50)
  public String getPhone() {
    return phone;
  }

  @Override
  public void setPhone(String phone) {
    this.phone = phone;
  }

  // TODO: Missing validation annotations below
  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public void setAddress(String address) {
    this.address = address;
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

  @Nullable
  @Size(min = 2)
  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
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
      .hashCode(key, type, primary, firstName, lastName, position, description, email, phone, organization, address,
        city, province, country, postalCode, createdBy, modifiedBy, created, modified);
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
    return Objects.toStringHelper(this).add("key", key).add("type", type).add("primary", primary)
      .add("firstName", firstName).add("lastName", lastName).add("position", position)
      .add("description", description).add("email", email).add("phone", phone).add("organization", organization)
      .add("address", address).add("city", city).add("province", province).add("country", country)
      .add("postalCode", postalCode).add("createdBy", createdBy).add("modifiedBy", modifiedBy).add("created", created)
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
      && Objects.equal(firstName, contact.firstName)
      && Objects.equal(lastName, contact.lastName)
      && Objects.equal(position, contact.position)
      && Objects.equal(description, contact.description)
      && Objects.equal(email, contact.email)
      && Objects.equal(phone, contact.phone)
      && Objects.equal(organization, contact.organization)
      && Objects.equal(address, contact.address)
      && Objects.equal(city, contact.city)
      && Objects.equal(province, contact.province)
      && Objects.equal(country, contact.country)
      && Objects.equal(postalCode, contact.postalCode);
  }

}
