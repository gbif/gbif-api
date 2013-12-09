package org.gbif.api.model.occurrence.predicate;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

import org.junit.Test;

public class LikePredicateTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    new LikePredicate(OccurrenceSearchParameter.ALTITUDE, "123.2%");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor2() {
    new LikePredicate(OccurrenceSearchParameter.BASIS_OF_RECORD, "%FOSSIL%");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor4() {
    new LikePredicate(OccurrenceSearchParameter.COUNTRY, "D%");
  }

  @Test
  public void testValidConstructor() {
    new LikePredicate(OccurrenceSearchParameter.SCIENTIFIC_NAME, "Abies%");
    new LikePredicate(OccurrenceSearchParameter.CATALOG_NUMBER, "kew-%");
  }
}
