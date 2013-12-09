package org.gbif.api.model.metrics.cube;

import org.gbif.api.vocabulary.Kingdom;

import java.util.UUID;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
  }
}
