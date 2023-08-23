/*
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
package org.gbif.api.model.predicate;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CompoundPredicateTest {

  @Test
  public void testEmptyPredicates() {
    assertThrows(IllegalArgumentException.class, () -> new ConjunctionPredicate(new ArrayList<>()));
  }

  @Test
  public void testEquals() {
    List<Predicate> mocks = new ArrayList<>();
    mocks.add(mock(Predicate.class));
    mocks.add(mock(Predicate.class));

    // Coming from the same list so should be the same
    Predicate cp1 = new ConjunctionPredicate(mocks);
    Predicate cp2 = new ConjunctionPredicate(mocks);
    assertThat(cp1, equalTo(cp2));
    assertThat(cp1, equalTo(cp1));

    // Now add one to the list, the underlying list for cp1 should not change so cp1 != cp2
    mocks.add(mock(Predicate.class));
    cp2 = new ConjunctionPredicate(mocks);
    assertThat(cp1, not(equalTo(cp2)));

    // Compare a Conjunction with a Disjunction
    cp1 = new ConjunctionPredicate(mocks);
    Predicate dp1 = new DisjunctionPredicate(mocks);
    assertThat(cp1, not(equalTo(dp1)));
    assertThat(dp1, not(equalTo(cp1)));
    assertThat(dp1, equalTo(dp1));

    Predicate dp2 = new DisjunctionPredicate(mocks);
    assertThat(dp1, equalTo(dp2));
  }

  @Test
  public void testToString() {
    Predicate p =
        new ConjunctionPredicate(
            Arrays.asList(
                new EqualsPredicate<>(
                    OccurrenceSearchParameter.BASIS_OF_RECORD, "FOSSIL_SPECIMEN", false),
                new NotPredicate(
                    new DisjunctionPredicate(
                        Arrays.asList(
                            new EqualsPredicate<>(OccurrenceSearchParameter.YEAR, "2001", false),
                            new EqualsPredicate<>(OccurrenceSearchParameter.YEAR, "2000", false),
                            new EqualsPredicate<>(OccurrenceSearchParameter.YEAR, "1999", false)))),
                new EqualsPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "212", false)));

    // make this doesn't throw an exception
    assertNotNull(p.toString());
  }

  @Test
  public void testGetPredicates() {
    List<Predicate> mocks = new ArrayList<>();
    mocks.add(mock(Predicate.class));
    mocks.add(mock(Predicate.class));
    org.gbif.api.model.predicate.ConjunctionPredicate predicate = new ConjunctionPredicate(mocks);
    assertThat(mocks, equalTo(predicate.getPredicates()));
  }

  @Test
  public void testHashcode() {
    List<Predicate> mocks = new ArrayList<>();
    mocks.add(mock(Predicate.class));
    mocks.add(mock(Predicate.class));

    Predicate p1 = new ConjunctionPredicate(mocks);
    Predicate p2 = new DisjunctionPredicate(mocks);

    assertThat(p1.hashCode(), equalTo(p2.hashCode()));

    mocks.add(mock(Predicate.class));
    p2 = new DisjunctionPredicate(mocks);
    assertThat(p1.hashCode(), not(equalTo(p2.hashCode())));
  }

  @Test
  public void testNullPredicate() {
    assertThrows(NullPointerException.class, () -> new ConjunctionPredicate(null));
  }
}
