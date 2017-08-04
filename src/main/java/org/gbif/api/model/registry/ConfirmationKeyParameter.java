package org.gbif.api.model.registry;

import java.util.UUID;
import javax.validation.constraints.NotNull;

/**
 * Confirmation key is a UUID used to confirm an entity.
 */
public class ConfirmationKeyParameter {

  private UUID confirmationKey;

  public ConfirmationKeyParameter(){}

  public ConfirmationKeyParameter(UUID confirmationKey) {
    this.confirmationKey = confirmationKey;
  }

  @NotNull
  public UUID getConfirmationKey() {
    return confirmationKey;
  }

  public void setConfirmationKey(UUID confirmationKey) {
    this.confirmationKey = confirmationKey;
  }
}
