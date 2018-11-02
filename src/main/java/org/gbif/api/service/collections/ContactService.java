package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Staff;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;

/** Service for the contacts in the collections service. */
public interface ContactService {

  /**
   * Lists all the contacts of an entity.
   *
   * @param entityKey of the entity
   * @return list of {@link Staff}
   */
  List<Staff> listContacts(@NotNull UUID entityKey);

  /**
   * Adds a {@link Staff} contact to an entity.
   *
   * @param entityKey key of the entity where the contact will be added to.
   * @param staffKey key of the contact to add.
   */
  void addContact(@NotNull UUID entityKey, @NotNull UUID staffKey);

  /**
   * Removes a {@link Staff} contact from an entity.
   *
   * @param entityKey key of the entity where the contact will be removed from.
   * @param staffKey key of the contact to remove.
   */
  void removeContact(@NotNull UUID entityKey, @NotNull UUID staffKey);
}
