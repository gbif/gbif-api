package org.gbif.api.model.collections;

import java.util.List;
import javax.annotation.Nullable;
import javax.validation.Valid;

/**
 * Interface to model a contact in the collections context.
 */
public interface Contactable {

  @Nullable
  List<Staff> getContacts();

  void setContacts(List<Staff> contacts);

  @Nullable
  @Valid
  Address getMailingAddress();

  void setMailingAddress(Address mailingAddress);

  @Nullable
  @Valid
  Address getAddress();

  void setAddress(Address address);

}
