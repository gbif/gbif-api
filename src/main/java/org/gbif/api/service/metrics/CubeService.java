/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.service.metrics;

import org.gbif.api.model.metrics.cube.ReadBuilder;
import org.gbif.api.model.metrics.cube.Rollup;

import java.util.List;

/**
 * The cube API service, for reading addressable counts from a basic cube.
 */
public interface CubeService {

  /**
   * Using the supplied {@link ReadBuilder} to obtain the address, looks up the cube value.
   * Should the cube support no dimensions (e.g. count all), then calling with an empty ReadBuilder
   * will return this.  E.g. cubeService.get(new ReadBuilder());
   *
   * @param addressBuilder To obtain the address at which to look up from the cube
   * @return The value which might be 0. A value of 0 means that the count is truly at 0
   * @throws IllegalArgumentException Should the addressBuilder provide an address that does not exist in the cube
   */
  long get(ReadBuilder addressBuilder) throws IllegalArgumentException;

  /**
   * Provides the list of rollups thus specifying the available combinations of addressable dimensions for a cube.
   *
   * @return The schema for the cube (defined by the queryable addresses)
   */
  List<Rollup> getSchema();
}
