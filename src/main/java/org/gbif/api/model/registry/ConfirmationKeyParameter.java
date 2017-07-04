package org.gbif.api.model.registry;

import java.util.UUID;

/**
 * Confirmation key is a UUID used to confirm an entity.
 */
public class ConfirmationKeyParameter {

  private UUID confirmationKey;

  public ConfirmationKeyParameter(){}

  public ConfirmationKeyParameter(UUID confirmationKey) {
    this.confirmationKey = confirmationKey;
  }

  public UUID getConfirmationKey() {
    return confirmationKey;
  }

  public void setConfirmationKey(UUID confirmationKey) {
    this.confirmationKey = confirmationKey;
  }
}
