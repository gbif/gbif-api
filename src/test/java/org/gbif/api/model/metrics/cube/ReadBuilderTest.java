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
package org.gbif.api.model.metrics.cube;

import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Kingdom;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests the type safety of the builder, and illustrates usage.
 */
public class ReadBuilderTest {

  private static final class BooleanDimension extends Dimension<Boolean> {

    public BooleanDimension() {
      super("boolean", Boolean.class);
    }
  }

  private static final class DoubleDimension extends Dimension<Double> {

    public DoubleDimension() {
      super("double", Double.class);
    }
  }

  private static final class EnumDimension extends Dimension<Kingdom> {

    public EnumDimension() {
      super("enum", Kingdom.class);
    }
  }

  private static final class FloatDimension extends Dimension<Float> {

    public FloatDimension() {
      super("float", Float.class);
    }
  }

  private static final class IntDimension extends Dimension<Integer> {

    public IntDimension() {
      super("int", Integer.class);
    }
  }

  private static final class StringDimension extends Dimension<String> {

    public StringDimension() {
      super("string", String.class);
    }
  }

  private static final class UUIDDimension extends Dimension<UUID> {

    public UUIDDimension() {
      super("uuid", UUID.class);
    }
  }

  private static final class CountryDimension extends Dimension<Country> {

    public CountryDimension() {
      super("country", Country.class);
    }
  }

  @Test
  public void testDimensions() {
    ReadBuilder b = new ReadBuilder();
    UUID uuid = UUID.randomUUID();

    b.at(new StringDimension(), "1");
    assertNotNull(b.build());
    assertEquals(1, b.build().size());
    assertEquals("1", b.build().get(new StringDimension()));

    b.at(new IntDimension(), 1);
    assertNotNull(b.build());
    assertEquals(2, b.build().size());
    assertEquals("1", b.build().get(new IntDimension()));

    b.at(new FloatDimension(), 1f);
    assertNotNull(b.build());
    assertEquals(3, b.build().size());
    assertEquals("1.0", b.build().get(new FloatDimension()));

    b.at(new DoubleDimension(), 1d);
    assertNotNull(b.build());
    assertEquals(4, b.build().size());
    assertEquals("1.0", b.build().get(new DoubleDimension()));

    b.at(new UUIDDimension(), uuid);
    assertNotNull(b.build());
    assertEquals(5, b.build().size());
    assertEquals(uuid.toString(), b.build().get(new UUIDDimension()));

    b.at(new EnumDimension(), Kingdom.ANIMALIA);
    assertNotNull(b.build());
    assertEquals(6, b.build().size());
    assertEquals(Kingdom.ANIMALIA.toString(), b.build().get(new EnumDimension()));

    b.at(new BooleanDimension(), true);
    assertNotNull(b.build());
    assertEquals(7, b.build().size());
    assertEquals(String.valueOf(true), b.build().get(new BooleanDimension()));

    b.at(new CountryDimension(), Country.GERMANY);
    assertNotNull(b.build());
    assertEquals(8, b.build().size());
    assertEquals("DE", b.build().get(new CountryDimension()));
  }
}
