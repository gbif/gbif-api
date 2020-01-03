package org.gbif.api.model.collections;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Entity that can have a list of contacts and addresses.
 */
public interface Contactable {

  /**
   * List if associated contacts.
   */
  @Nullable
  List<Person> getContacts();

  void setContacts(List<Person> contacts);

  /**
   * Address used to send and receive mail.
   */
  @Nullable
  Address getMailingAddress();

  void setMailingAddress(Address mailingAddress);

  /**
   * Physical or associated address.
   */
  @Nullable
  Address getAddress();

  void setAddress(Address address);

}
