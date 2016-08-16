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

import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VocabularyUtils {

  private static final Logger LOG = LoggerFactory.getLogger(VocabularyUtils.class);

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
   * FIXME returning Guava Optional will cause issues, Java 8 Optional should be returned.
   * Same as {@link #lookupEnum(String, Class)} } without IllegalArgumentException.
   * On failure, this method will return Optional.absent().
   *
   * @param name
   * @param vocab
   * @param <T>
   * @return instance of com.google.common.base.Optional, never null.
   */
  public static <T extends Enum<?>> Optional<T> lookup(String name, Class<T> vocab) {
    T result = null;
    // this try/catch in needed until we replace all calls to lookupEnum() in favor of this method
    try{
      result = lookupEnum(name, vocab);
    }
    catch (IllegalArgumentException iaEx){/*ignore*/}
    return Optional.fromNullable(result);
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
   * Utility method to get a map of all enumerations within a package.
   * The map will use the enumeration class simple name as key and the enum itself as value.
   *
   * @return a map of all enumeration within the package or an empty map in all other cases.
   */
  public static Map<String, Enum<?>[]> listEnumerations(String packageName) {
    try {
      ClassPath cp = ClassPath.from(VocabularyUtils.class.getClassLoader());
      ImmutableMap.Builder<String, Enum<?>[]> builder = ImmutableMap.builder();

      List<ClassPath.ClassInfo> infos = cp.getTopLevelClasses(packageName).asList();
      for (ClassPath.ClassInfo info : infos) {
        Class<? extends Enum<?>> vocab = lookupVocabulary(info.getName());
        // verify that it is an Enumeration
        if (vocab != null && vocab.getEnumConstants() != null) {
          builder.put(info.getSimpleName(), vocab.getEnumConstants());
        }
      }
      return builder.build();
    } catch (Exception e) {
      LOG.error("Unable to read the classpath for enumerations", e);
      return ImmutableMap.<String, Enum<?>[]>of(); // empty
    }
  }

  /**
   * A static utils class.
   */
  private VocabularyUtils() {
  }

}
