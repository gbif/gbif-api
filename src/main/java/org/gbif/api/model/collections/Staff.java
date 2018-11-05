package org.gbif.api.model.collections;

import org.gbif.api.model.registry.LenientEquals;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Person associated to a collection or institution.
 */
public class Staff implements CollectionEntity, LenientEquals<Staff> {

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

  /**
   * GBIF Unique identifier.
   */
  @Override
  public UUID getKey() {
    return key;
  }

  @Override
  public void setKey(UUID key) {
    this.key = key;
  }

  /**
   * Firs name, it can also include the first and last name in some cases.
   */
  @NotNull
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Last and secondary names.
   */
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Textual description  of the role performed.
   */
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * Description of the main responsibility assigned to a staff member.
   */
  public String getAreaResponsibility() {
    return areaResponsibility;
  }

  public void setAreaResponsibility(String areaResponsibility) {
    this.areaResponsibility = areaResponsibility;
  }

  /**
   * Description of main research activities.
   */
  public String getResearchPursuits() {
    return researchPursuits;
  }

  public void setResearchPursuits(String researchPursuits) {
    this.researchPursuits = researchPursuits;
  }

  /**
   * Primary telephone number.
   */
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Fax number.
   */
  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  /**
   * Staff email.
   */
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Address used to send physical mail.
   */
  @Valid
  public Address getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(Address mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  /**
   * Institution where staff work.
   */
  public UUID getInstitutionKey() {
    return institutionKey;
  }

  public void setInstitutionKey(UUID institutionKey) {
    this.institutionKey = institutionKey;
  }

  /**
   * Collection linked to a staff member.
   */
  public UUID getCollectionKey() {
    return collectionKey;
  }

  public void setCollectionKey(UUID collectionKey) {
    this.collectionKey = collectionKey;
  }

  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  @Override
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public String getModifiedBy() {
    return modifiedBy;
  }

  @Override
  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Override
  public Date getCreated() {
    return created;
  }

  @Override
  public void setCreated(Date created) {
    this.created = created;
  }

  @Override
  public Date getModified() {
    return modified;
  }

  @Override
  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public Date getDeleted() {
    return deleted;
  }

  @Override
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
