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
package org.gbif.api.ws.mixin;

import org.gbif.api.model.occurrence.Download;
import org.gbif.api.model.occurrence.Occurrence;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.model.registry.search.DatasetSearchResult;

import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * Mixins are typically used to leave serialization-oriented annotations outside the models to avoid
 * introducing coupling on a specific SerDe.
 * <p>
 * This class provides access to predefined mixins used in the GBIF web service application (client and server).
 */
public class Mixins {

  // utility class
  private Mixins() {
  }

  private static final ImmutableMap<Class<?>, Class<?>> PREDEFINED_MIXINS =
    ImmutableMap.of(
      Dataset.class, DatasetMixin.class,
      DatasetSearchResult.class, DatasetMixin.class,
      Download.class, LicenseMixin.class,
      Occurrence.class, OccurrenceMixin.class
    );

  /**
   * Return an immutable map of the predefined Jackson Mixins used by the web services.
   *
   * @return immutable map of the predefined Jackson Mixins
   */
  public static Map<Class<?>, Class<?>> getPredefinedMixins() {
    return PREDEFINED_MIXINS;
  }

  /**
   * Get an immutable map of the predefined Jackson Mixins after the provided keyFilter is applied.
   *
   * @param keyFilter predicate to filter the predefined mixins based on the model class
   * @return immutable map of the predefined Jackson Mixins after applying the predicate
   */
  public static Map<Class<?>, Class<?>> getPredefinedMixins(Predicate<Class<?>> keyFilter) {
    return ImmutableMap.copyOf(Maps.filterKeys(PREDEFINED_MIXINS, keyFilter));
  }
}
