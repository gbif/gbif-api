package org.gbif.api.service.registry;

import java.util.UUID;

public interface PrimaryIdentifierService extends IdentifierService {

  /**
   * Updates the specified Identifier for a target entity, setting its primary status.
   *
   * @param targetEntityKey the key of the target entity to which the identifier belongs
   * @param identifierKey the key of the identifier to be updated
   * @param isPrimary a boolean indicating whether the identifier should be set as primary
   *                  (true) or non-primary (false)
   *
   * @return the key of the updated Identifier
   */
  int updateIdentifier(UUID targetEntityKey, int identifierKey, Boolean isPrimary);
}
