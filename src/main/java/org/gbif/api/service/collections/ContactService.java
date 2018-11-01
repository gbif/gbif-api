package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Staff;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;

/**
 * Service for the contacts in the collections service.
 */
public interface ContactService {

  List<Staff> listContacts(@NotNull UUID key);

  void addContact(@NotNull UUID entityKey, @NotNull UUID staffKey);

  void removeContact(@NotNull UUID entityKey, @NotNull UUID staffKey);

}
