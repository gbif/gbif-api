/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.registry;

import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * Confirmation key is a UUID used to confirm an entity.
 */
@SuppressWarnings("unused")
public class ConfirmationKeyParameter {

  private UUID confirmationKey;

  public ConfirmationKeyParameter() {}

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
