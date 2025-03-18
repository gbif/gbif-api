/*
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
package org.gbif.api.model.collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.Size;

import org.gbif.api.model.registry.LenientEquals;
import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.util.validators.email.ValidEmail;
import org.gbif.api.vocabulary.Country;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/** Contact associated to a GRSciColl {@link Collection} or {@link Institution}. */
public class Contact implements LenientEquals<Contact>, Serializable {

  private Integer key;
  @Size(min = 1, message = "First name cannot be empty")
  private String firstName;
  @Size(min = 1, message = "Last name cannot be empty")
  private String lastName;
  private List<String> position = new ArrayList<>();
  private List<String> phone = new ArrayList<>();
  private List<String> fax = new ArrayList<>();
  private List<@ValidEmail String> email = new ArrayList<>();
  private List<String> address = new ArrayList<>();
  @Size(min = 1, message = "City cannot be empty")
  private String city;
  @Size(min = 1, message = "Province cannot be empty")
  private String province;
  private Country country;
  @Size(min = 1, message = "Postal code cannot be empty")
  private String postalCode;
  private boolean primary;
  private List<String> taxonomicExpertise = new ArrayList<>();
  private String notes;
  private List<@Valid UserId> userIds = new ArrayList<>();
  @Size(min = 3, message = "Created by must have at least 3 characters")
  private String createdBy;
  @Size(min = 3, message = "Modified by must have at least 3 characters")
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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

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

  public List<String> getPhone() {
    return phone;
  }

  public void setPhone(List<String> phone) {
    this.phone = phone;
  }

  public List<String> getFax() {
    return fax;
  }

  public void setFax(List<String> fax) {
    this.fax = fax;
  }

  public List<String> getEmail() {
    return email;
  }

  public void setEmail(List<String> email) {
    this.email = email;
  }

  public List<String> getAddress() {
    return address;
  }

  public void setAddress(List<String> address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public boolean isPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

  public List<String> getTaxonomicExpertise() {
    return taxonomicExpertise;
  }

  public void setTaxonomicExpertise(List<String> taxonomicExpertise) {
    this.taxonomicExpertise = taxonomicExpertise;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public List<UserId> getUserIds() {
    return userIds;
  }

  public void setUserIds(List<UserId> userIds) {
    this.userIds = userIds;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

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
    return Objects.equals(key, contact.key)
        && Objects.equals(firstName, contact.firstName)
        && Objects.equals(lastName, contact.lastName)
        && Objects.equals(position, contact.position)
        && Objects.equals(phone, contact.phone)
        && Objects.equals(fax, contact.fax)
        && Objects.equals(email, contact.email)
        && Objects.equals(address, contact.address)
        && Objects.equals(city, contact.city)
        && Objects.equals(province, contact.province)
        && country == contact.country
        && Objects.equals(postalCode, contact.postalCode)
        && primary == contact.primary
        && Objects.equals(taxonomicExpertise, contact.taxonomicExpertise)
        && Objects.equals(notes, contact.notes)
        && Objects.equals(userIds, contact.userIds)
        && Objects.equals(createdBy, contact.createdBy)
        && Objects.equals(modifiedBy, contact.modifiedBy)
        && Objects.equals(created, contact.created)
        && Objects.equals(modified, contact.modified);
  }

  @Override
  public boolean lenientEquals(Contact contact) {
    if (this == contact) {
      return true;
    }

    return Objects.equals(firstName, contact.firstName)
        && Objects.equals(lastName, contact.lastName)
        && Objects.equals(position, contact.position)
        && Objects.equals(phone, contact.phone)
        && Objects.equals(fax, contact.fax)
        && Objects.equals(email, contact.email)
        && Objects.equals(address, contact.address)
        && Objects.equals(city, contact.city)
        && Objects.equals(province, contact.province)
        && country == contact.country
        && Objects.equals(postalCode, contact.postalCode)
        && primary == contact.primary
        && Objects.equals(taxonomicExpertise, contact.taxonomicExpertise)
        && Objects.equals(notes, contact.notes)
        && Objects.equals(userIds, contact.userIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        firstName,
        lastName,
        position,
        phone,
        fax,
        email,
        address,
        city,
        province,
        country,
        postalCode,
        primary,
        taxonomicExpertise,
        notes,
        userIds,
        createdBy,
        modifiedBy,
        created,
        modified);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("firstName='" + firstName + "'")
        .add("lastName='" + lastName + "'")
        .add("position='" + position + "'")
        .add("phone=" + phone)
        .add("fax=" + fax)
        .add("email=" + email)
        .add("address=" + address)
        .add("city='" + city + "'")
        .add("province='" + province + "'")
        .add("country=" + country)
        .add("postalCode='" + postalCode + "'")
        .add("primary='" + primary + "'")
        .add("taxonomicExpertise='" + taxonomicExpertise + "'")
        .add("notes='" + notes + "'")
        .add("userIds=" + userIds)
        .add("createdBy='" + createdBy + "'")
        .add("modifiedBy='" + modifiedBy + "'")
        .add("created=" + created)
        .add("modified=" + modified)
        .toString();
  }
}
