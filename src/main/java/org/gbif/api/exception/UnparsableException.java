/***************************************************************************
 * Copyright 2010 Global Biodiversity Information Facility Secretariat
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ***************************************************************************/

package org.gbif.api.exception;

import org.gbif.api.vocabulary.NameType;

/**
 * Exception thrown when a scientific name cannot be parsed.
 * The type property is set for known name types that cannot be parsed but be detected, for example hybrids, virus or placeholder names.
 * The name property holds the name that cannot be parsed.
 */
public class UnparsableException extends Exception {

  public final NameType type;
  public final String name;

  public UnparsableException(NameType type, String name) {
    super("Name of type " + type.name() + " unparsable: " + name);
    this.type = type;
    this.name = name;
  }
}
