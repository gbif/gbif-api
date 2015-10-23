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
package org.gbif.api.util;

import org.gbif.api.vocabulary.ContactType;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.IdentifierType;
import org.gbif.api.vocabulary.TechnicalInstallationType;

import com.google.common.base.Strings;

public final class VocabularyUtils {

  public static ContactType parseContactType(String type) {
    return (ContactType) lookupEnum(type, ContactType.class);
  }

  public static EndpointType parseEndpointType(String type) {
    return (EndpointType) lookupEnum(type, EndpointType.class);
  }

  public static IdentifierType parseIdentifierType(String type) {
    return (IdentifierType) lookupEnum(type, IdentifierType.class);
  }

  public static TechnicalInstallationType parseTechnicalInstallationType(String type) {
    return (TechnicalInstallationType) lookupEnum(type, TechnicalInstallationType.class);
  }

  /**
   * Generic method to lookup an enumeration value for a given string based on the name of the enum member.
   * The lookup is case insensitive and ignore whitespaces, underscores and dashes.
   *
   * @param name the enum members name to lookup
   * @param vocab the enumeration class
   * @return the matching enum member or null if {@code name} is null or empty (see http://dev.gbif.org/issues/browse/POR-2858)
   * @throws IllegalArgumentException if the name cannot be parsed into a known name
   */
  public static <T extends Enum<?>> T lookupEnum(String name, Class<T> vocab) {
    if (Strings.isNullOrEmpty(name)) {
      return null;
    }
    final String normedType = name.toUpperCase().replaceAll("[. _-]", "");
    T[] values = vocab.getEnumConstants();
    if (values != null) {
      for (T val : values) {
        final String normedVal = val.name().toUpperCase().replaceAll("[. _-]", "");
        if (normedType.equals(normedVal)) {
          return val;
        }
      }
    }
    throw new IllegalArgumentException("Cannot parse " + name + " into a known " + vocab.getSimpleName());
  }

  /**
   * Looks up an enumeration by class name. One can get the classname using the likes of:
   * <p/>
   *
   * <pre>
   * {@code
   * Country.class.getName()
   * }
   * </pre>
   *
   * @param fullyQualifiedClassName Which should name the enumeration (e.g. org.gbif.api.vocabulary.Country)
   * @return The enumeration or null if {@code fullyQualifiedClassName} is null or empty (see http://dev.gbif.org/issues/browse/POR-2858)
   * @throws IllegalArgumentException if {@code fullyQualifiedClassName} class cannot be located
   */
  @SuppressWarnings("unchecked")
  public static Class<? extends Enum<?>> lookupVocabulary(String fullyQualifiedClassName) {
    if (!Strings.isNullOrEmpty(fullyQualifiedClassName)) {
      try {
        Class<?> cl = Class.forName(fullyQualifiedClassName);
        if (Enum.class.isAssignableFrom(cl)) {
          return (Class<? extends Enum<?>>) cl;
        }
      } catch (Exception e) {
        throw new IllegalArgumentException("Unable to lookup the vocabulary: " + fullyQualifiedClassName, e);
      }
    }
    return null;
  }

  /**
   * A static utils class.
   */
  private VocabularyUtils() {
  }

}
