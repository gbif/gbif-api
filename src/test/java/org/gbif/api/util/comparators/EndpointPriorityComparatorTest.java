package org.gbif.api.util.comparators;

import org.gbif.api.model.registry.Endpoint;
import org.gbif.api.vocabulary.EndpointType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EndpointPriorityComparatorTest {

  private static final EndpointPriorityComparator COMP = new EndpointPriorityComparator();

  @Test
  public void testSuccessfulSimpleComparisons() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setType(EndpointType.TAPIR);
    e2.setType(EndpointType.BIOCASE);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.BIOCASE);
    e2.setType(EndpointType.BIOCASE);
    assertEquals(0, COMP.compare(e1, e2));

    e1.setType(EndpointType.BIOCASE);
    e2.setType(EndpointType.TAPIR);
    assertTrue(COMP.compare(e1, e2) < 0);

    e1.setType(EndpointType.BIOCASE);
    e2.setType(EndpointType.DIGIR_MANIS);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.DWC_ARCHIVE);
    e2.setType(EndpointType.TAPIR);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.BIOCASE_XML_ARCHIVE);
    e2.setType(EndpointType.BIOCASE);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.DWC_ARCHIVE);
    e2.setType(EndpointType.BIOCASE_XML_ARCHIVE);
    assertTrue(COMP.compare(e1, e2) > 0);
  }

  @Test
  public void testPriorityComparisons() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setType(EndpointType.TAPIR);
    e2.setType(EndpointType.BIOCASE);
    assertTrue(COMP.compare(e1, e2) > 0);

    e1.setType(EndpointType.DIGIR_MANIS);
    e2.setType(EndpointType.DIGIR_MANIS);
    assertEquals(0, COMP.compare(e1, e2));
  }

  @Test(expected = ClassCastException.class)
  public void testInvalidComparison1() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setType(EndpointType.TAPIR);
    e2.setType(EndpointType.WMS);
    COMP.compare(e1, e2);
  }

  @Test(expected = ClassCastException.class)
  public void testInvalidComparison2() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setType(EndpointType.WMS);
    e2.setType(EndpointType.TAPIR);
    COMP.compare(e1, e2);
  }


}
