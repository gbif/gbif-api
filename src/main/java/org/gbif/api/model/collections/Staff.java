package org.gbif.api.model.collections;

import org.gbif.api.model.registry.LenientEquals;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Staff implements LenientEquals<Staff> {

  private UUID key;
  private String firstName;
  private String lastName;
  private String position;
  private String areaResponsibility;
  private String researchPursuits;
  private String phone;
  private String fax;
  private String email;
  private Address mailingAddress;
  private UUID institutionKey;
  private UUID collectionKey;
  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;
  private Date deleted;

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  @NotNull
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

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getAreaResponsibility() {
    return areaResponsibility;
  }

  public void setAreaResponsibility(String areaResponsibility) {
    this.areaResponsibility = areaResponsibility;
  }

  public String getResearchPursuits() {
    return researchPursuits;
  }

  public void setResearchPursuits(String researchPursuits) {
    this.researchPursuits = researchPursuits;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Valid
  public Address getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(Address mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public UUID getInstitutionKey() {
    return institutionKey;
  }

  public void setInstitutionKey(UUID institutionKey) {
    this.institutionKey = institutionKey;
  }

  public UUID getCollectionKey() {
    return collectionKey;
  }

  public void setCollectionKey(UUID collectionKey) {
    this.collectionKey = collectionKey;
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

  public Date getDeleted() {
    return deleted;
  }

  public void setDeleted(Date deleted) {
    this.deleted = deleted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Staff staff = (Staff) o;
    return Objects.equals(key, staff.key)
           && Objects.equals(firstName, staff.firstName)
           && Objects.equals(lastName,
                             staff.lastName)
           && Objects.equals(position, staff.position)
           && Objects.equals(areaResponsibility, staff.areaResponsibility)
           && Objects.equals(researchPursuits, staff.researchPursuits)
           && Objects.equals(phone, staff.phone)
           && Objects.equals(fax, staff.fax)
           && Objects.equals(email, staff.email)
           && Objects.equals(mailingAddress, staff.mailingAddress)
           && Objects.equals(institutionKey, staff.institutionKey)
           && Objects.equals(collectionKey, staff.collectionKey)
           && Objects.equals(createdBy, staff.createdBy)
           && Objects.equals(modifiedBy, staff.modifiedBy)
           && Objects.equals(created, staff.created)
           && Objects.equals(modified, staff.modified)
           && Objects.equals(deleted, staff.deleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key,
                        firstName,
                        lastName,
                        position,
                        areaResponsibility,
                        researchPursuits,
                        phone,
                        fax,
                        email,
                        mailingAddress,
                        institutionKey,
                        collectionKey,
                        createdBy,
                        modifiedBy,
                        created,
                        modified,
                        deleted);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Staff.class.getSimpleName() + "[", "]").add("key=" + key)
      .add("firstName='" + firstName + "'")
      .add("lastName='" + lastName + "'")
      .add("position='" + position + "'")
      .add("areaResponsibility='" + areaResponsibility + "'")
      .add("researchPursuits='" + researchPursuits + "'")
      .add("phone='" + phone + "'")
      .add("fax='" + fax + "'")
      .add("email='" + email + "'")
      .add("mailingAddress=" + mailingAddress)
      .add("institutionKey=" + institutionKey)
      .add("collectionKey=" + collectionKey)
      .add("createdBy='" + createdBy + "'")
      .add("modifiedBy='" + modifiedBy + "'")
      .add("created=" + created)
      .add("modified=" + modified)
      .add("deleted=" + deleted)
      .toString();
  }

  @Override
  public boolean lenientEquals(Staff other) {
    if (this == other) {
      return true;
    }
    return Objects.equals(key, other.key)
           && Objects.equals(firstName, other.firstName)
           && Objects.equals(lastName, other.lastName)
           && Objects.equals(position, other.position)
           && Objects.equals(areaResponsibility, other.areaResponsibility)
           && Objects.equals(researchPursuits, other.researchPursuits)
           && Objects.equals(phone, other.phone)
           && Objects.equals(fax, other.fax)
           && Objects.equals(email, other.email)
           && Objects.equals(mailingAddress, other.mailingAddress)
           && Objects.equals(institutionKey, other.institutionKey)
           && Objects.equals(collectionKey, other.collectionKey)
           && Objects.equals(deleted, other.deleted);
  }
}
