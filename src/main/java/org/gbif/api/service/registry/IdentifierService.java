/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.service.registry;

import org.gbif.api.model.registry.Identifier;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;

public interface IdentifierService {

  int addIdentifier(@NotNull UUID targetEntityKey, @NotNull Identifier identifier);

  void deleteIdentifier(@NotNull UUID targetEntityKey, int identifierKey);

  List<Identifier> listIdentifiers(@NotNull UUID targetEntityKey);

}
