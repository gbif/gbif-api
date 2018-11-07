package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Person;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;

/**
 * API Service for the contacts in the collections service.
 */
public interface ContactService {

  /**
   * Lists all the contacts of an entity.
   *
   * @param entityKey of the entity
   * @return list of {@link Person}
   */
  List<Person> listContacts(@NotNull UUID entityKey);

  /**
   * Adds a {@link Person} contact to an entity.
   *
   * @param entityKey key of the entity where the contact will be added to.
   * @param personKey key of the contact to add.
   */
  void addContact(@NotNull UUID entityKey, @NotNull UUID personKey);

  /**
   * Removes a {@link Person} contact from an entity.
   *
   * @param entityKey key of the entity where the contact will be removed from.
   * @param personKey key of the contact to remove.
   */
  void removeContact(@NotNull UUID entityKey, @NotNull UUID personKey);
}
