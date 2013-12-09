package org.gbif.api.model.occurrence.predicate;

import org.junit.Test;

public class WithinPredicateTest {

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyConstructor() {
    new WithinPredicate("");
  }

  @Test(expected = NullPointerException.class)
  public void testNullConstructor() {
    new WithinPredicate(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor1() {
    new WithinPredicate("POLYGON");
  }

  @Test
  public void testGoodConstructor() {
    new WithinPredicate("POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))");
  }
}
