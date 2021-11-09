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
package org.gbif.api.model.occurrence.predicate;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class SimplePredicateTest {

  private static final OccurrenceSearchParameter TEST_KEY = OccurrenceSearchParameter.CATALOG_NUMBER;
  private static final String TEST_VALUE = "bar";
  private static final OccurrenceSearchParameter COMP_PARAM = OccurrenceSearchParameter.ELEVATION;
  private static final String COMP_VALUE = "100";

  @Test
  public void testEmptyConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new EqualsPredicate(TEST_KEY, "", false));
  }

  @Test
  public void testEquals() {
    SimplePredicate ep1 = new EqualsPredicate(TEST_KEY, TEST_VALUE, false);
    SimplePredicate ep2 = new EqualsPredicate(TEST_KEY, TEST_VALUE, false);
    SimplePredicate gt1 = new GreaterThanPredicate(COMP_PARAM, COMP_VALUE);
    SimplePredicate gt2 = new GreaterThanPredicate(COMP_PARAM, COMP_VALUE);
    SimplePredicate lt1 = new LessThanPredicate(COMP_PARAM, COMP_VALUE);
    SimplePredicate lt2 = new LessThanPredicate(COMP_PARAM, COMP_VALUE);

    // All of them are equal to themselves
    assertThat(ep1, equalTo(ep1));
    assertThat(gt1, equalTo(gt1));
    assertThat(lt1, equalTo(lt1));

    // But unequal among themselves
    assertThat(ep1, both(not(equalTo(gt1))).and(not(equalTo(lt1))));
    assertThat(gt1, both(not(equalTo(ep1))).and(not(equalTo(lt1))));
    assertThat(lt1, both(not(equalTo(ep1))).and(not(equalTo(gt1))));

    // And certainly unequal to any other Predicate
    assertThat(ep1, not(equalTo(mock(Predicate.class))));

    assertThat(ep1, equalTo(ep2));
    assertThat(gt1, equalTo(gt2));
    assertThat(lt1, equalTo(lt2));

    ep2 = new EqualsPredicate(TEST_KEY, "FOOBAR", false);
    assertThat(ep1, not(equalTo(ep2)));
  }

  @Test
  public void testGoodDoubleValue() {
    new EqualsPredicate(OccurrenceSearchParameter.DECIMAL_LATITUDE, "32", false);
    new EqualsPredicate(OccurrenceSearchParameter.DECIMAL_LATITUDE, "32.032", false);
    new EqualsPredicate(OccurrenceSearchParameter.ELEVATION, "-10,5.5", false);
    new EqualsPredicate(OccurrenceSearchParameter.ELEVATION, "*,200", false);
    new EqualsPredicate(OccurrenceSearchParameter.ELEVATION, "100,*", false);
  }

  @Test
  public void testGoodEnumValue() {
    new EqualsPredicate(OccurrenceSearchParameter.BASIS_OF_RECORD, "PRESERVED_SPECIMEN", false);
  }

  @Test
  public void testHashcode() {
    SimplePredicate sp1 = new EqualsPredicate(TEST_KEY, TEST_VALUE, false);
    SimplePredicate sp2 = new EqualsPredicate(TEST_KEY, TEST_VALUE, false);
    SimplePredicate sp3 = new LessThanPredicate(COMP_PARAM, COMP_VALUE);
    SimplePredicate sp4 = new GreaterThanPredicate(COMP_PARAM, COMP_VALUE);

    assertThat(sp1.hashCode(), equalTo(sp2.hashCode()));
    assertThat(sp1.hashCode(), not(equalTo(sp3.hashCode())));
    assertThat(sp3.hashCode(), equalTo(sp4.hashCode()));
  }

  @Test
  public void testNullConstructor() {
    assertThrows(NullPointerException.class, () -> new EqualsPredicate(null, null, false));
  }

  @Test
  public void testOnlyEqualsAllowed() {
    assertThrows(IllegalArgumentException.class, () -> new LessThanPredicate(OccurrenceSearchParameter.DATASET_KEY, UUID.randomUUID().toString()));
  }

  @Test
  public void testSimplePredicateConstruction() {
    SimplePredicate p = new EqualsPredicate(TEST_KEY, TEST_VALUE, false);
    assertThat(p.getKey(), equalTo(TEST_KEY));
    assertThat(p.getValue(), equalTo(TEST_VALUE));
  }

  @Test
  public void testWrongDoubleValue() {
    assertThrows(IllegalArgumentException.class, () -> new EqualsPredicate(OccurrenceSearchParameter.DECIMAL_LATITUDE, "32..312", false));
  }

  @Test
  public void testWrongEnumValue() {
    assertThrows(IllegalArgumentException.class, () -> new EqualsPredicate(OccurrenceSearchParameter.BASIS_OF_RECORD, "Specimen", false));
  }

  @Test
  public void testWrongDoubleValue2() {
    assertThrows(IllegalArgumentException.class, () -> new EqualsPredicate(OccurrenceSearchParameter.ELEVATION, "0.6s", false));
  }

  @Test
  public void testWrongUuidValue() {
    assertThrows(IllegalArgumentException.class, () -> new EqualsPredicate(OccurrenceSearchParameter.DATASET_KEY, "133-4312-443-2-43-32432423-", false));
  }

  @Test
  public void testGeometryEquals() {
    assertThrows(IllegalArgumentException.class, () -> new EqualsPredicate(OccurrenceSearchParameter.GEOMETRY, "POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))", false));
  }
}
