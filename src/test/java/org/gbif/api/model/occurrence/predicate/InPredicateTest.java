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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.both;
import static org.mockito.Mockito.mock;

public class InPredicateTest {

  private static final OccurrenceSearchParameter TEST_KEY = OccurrenceSearchParameter.CATALOG_NUMBER;

  @Test
  public void testBasics() {
    List<String> list = new ArrayList<>();
    list.add("foo");
    list.add("bar");

    InPredicate p = new InPredicate(TEST_KEY, list, false);

    assertThat(p.getKey(), equalTo(TEST_KEY));
    assertThat(p.getValues(), equalTo(list));

    list.add("oink");
    assertThat(p.getValues(), not(equalTo(list)));
  }

  @Test(expected = NullPointerException.class)
  public void testEmptyConstructor() {
    new InPredicate(null, new ArrayList<>(), false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyConstructor2() {
    new InPredicate(OccurrenceSearchParameter.CATALOG_NUMBER, new ArrayList<>(), false);
  }

  @Test
  public void testEquals() {
    List<String> list = new ArrayList<>();
    list.add("foo");
    list.add("bar");
    Predicate ip1 = new InPredicate(TEST_KEY, list, false);
    Predicate ip2 = new InPredicate(TEST_KEY, list, false);

    assertThat(ip1, both(equalTo(ip1)).and(equalTo(ip2)));

    Predicate p = mock(Predicate.class);
    assertThat(p, not(equalTo(ip1)));

    list.add("oink");
    ip2 = new InPredicate(TEST_KEY, list, false);
    assertThat(ip2, not(equalTo(ip1)));
  }

  @Test
  public void testHashcode() {
    List<String> list = new ArrayList<>();
    list.add("foo");
    list.add("bar");
    Predicate ip1 = new InPredicate(TEST_KEY, list, false);
    Predicate ip2 = new InPredicate(TEST_KEY, list, false);

    assertThat(ip1.hashCode(), both(equalTo(ip1.hashCode())).and(equalTo(ip2.hashCode())));

    Predicate p = mock(Predicate.class);
    assertThat(p.hashCode(), not(equalTo(ip1.hashCode())));

    list.add("oink");
    ip2 = new InPredicate(TEST_KEY, list, false);
    assertThat(ip2.hashCode(), not(equalTo(ip1.hashCode())));
  }

  @Test(expected = NullPointerException.class)
  public void testNullConstructor() {
    new InPredicate(null, null, false);
  }

  @Test(expected = NullPointerException.class)
  public void testNullConstructor2() {
    new InPredicate(OccurrenceSearchParameter.CATALOG_NUMBER, null, false);
  }

}
