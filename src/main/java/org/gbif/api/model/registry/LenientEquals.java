/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.registry;

/**
 * An interface to allow model objects to offer a consistent lenient equality check.
 * Implementations are free to declare what this means in the specific context, but it is expected that this be used to
 * imply a business logic unique. For example, contact details that are not yet persisted might be equal to a persisted
 * contact in every way except that the server controlled fields of key, createdDate, modifiedDate etc are not yet
 * present.
 */
public interface LenientEquals<T> {

  /**
   * Leniently tests if the objects are the same.
   * Implementors should declare what this means in practice.
   * 
   * @param other To compare against
   * @return true if... <insert implementation definition>
   */
  boolean lenientEquals(T other);
}
