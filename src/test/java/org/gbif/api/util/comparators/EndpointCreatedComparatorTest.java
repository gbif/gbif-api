package org.gbif.api.util.comparators;

import java.util.Date;

import org.gbif.api.model.registry.Endpoint;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EndpointCreatedComparatorTest {

  private static final EndpointCreatedComparator COMP = EndpointCreatedComparator.INSTANCE;
  private final long now = System.currentTimeMillis();

  @Test
  public void testSuccessfulSimpleComparisons() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    e1.setCreated(new Date(now));
    e2.setCreated(new Date(now));
    assertEquals(0, COMP.compare(e1, e2));

    e2.setCreated(new Date(now-10000));
    assertTrue(COMP.compare(e1, e2) > 0);

    e2.setCreated(new Date(now+10000));
    assertTrue(COMP.compare(e1, e2) < 0);
  }

  @Test
  public void testNullComparisons() {
    Endpoint e1 = new Endpoint();
    Endpoint e2 = new Endpoint();

    assertEquals(0, COMP.compare(e1, e2));

    e1.setCreated(new Date(now));
    assertTrue(COMP.compare(e1, e2) < 0);

    e2.setCreated(new Date(now-10000));
    assertTrue(COMP.compare(e1, e2) > 0);

    e2.setCreated(new Date(now+10000));
    assertTrue(COMP.compare(e1, e2) < 0);
  }


}
