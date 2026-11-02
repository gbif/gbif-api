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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.event.search.EventSearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Test cases and examples of serialization or predicates using a mixing. */
public class PredicateDeSerTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    MAPPER.registerModule(
        new SimpleModule()
            .addDeserializer(
                SearchParameter.class, new SearchParameter.SearchParameterDeserializer())
            .addDeserializer(
                OccurrenceSearchParameter.class,
                new OccurrenceSearchParameter.OccurrenceSearchParameterDeserializer())
            .addDeserializer(
                EventSearchParameter.class,
                new EventSearchParameter.EventSearchParameterDeserializer()));
  }

  private File getTestFile(String predicateFile) {
    return new File(getClass().getResource("/predicate/" + predicateFile).getFile());
  }

  @Test
  public void deserTest() throws IOException {
    EqualsPredicate<OccurrenceSearchParameter> eq =
        new EqualsPredicate<>(OccurrenceSearchParameter.OCCURRENCE_STATUS, "present", false);
    System.out.println(MAPPER.writeValueAsString(eq));

    assertPredicate("is_null.json");
    assertPredicate("conjunction.json");
    assertPredicate("within.json");
    assertPredicate("equals_catalog_number.json");
    assertPredicate("equals_date_range.json");
    assertPredicate("like_catalog_number.json");
    assertPredicate("and_with_not.json");
    assertPredicate("conjunction_with_in.json");
    assertPredicate("complex_conjunction_with_in.json");
    assertPredicate("range.json");
    assertPredicate("distance.json");
    assertPredicate("is_null_event.json");
  }

  @Test
  public void eventSearchParameterTest() throws IOException {
    ObjectMapper eventsMapper =
        new ObjectMapper()
            .registerModule(
                new SimpleModule()
                    .addDeserializer(
                        SearchParameter.class,
                        new EventSearchParameter.EventSearchParameterDeserializer())
                    .addDeserializer(
                        EventSearchParameter.class,
                        new EventSearchParameter.EventSearchParameterDeserializer()));

    Predicate predicate = eventsMapper.readValue(getTestFile("equals_event.json"), Predicate.class);
    Assertions.assertEquals(
        EventSearchParameter.YEAR, ((EqualsPredicate<EventSearchParameter>) predicate).getKey());
  }

  @Test
  public void dnaTaxonSearchDeserTest() throws IOException {
    ConjunctionPredicate predicate =
        (ConjunctionPredicate) MAPPER.readValue(getTestFile("dna_taxon_search.json"), Predicate.class);

    List<Predicate> predicates = new ArrayList<>(predicate.getPredicates());
    Assertions.assertEquals(5, predicates.size());
    EqualsPredicate<?> equals = (EqualsPredicate<?>) predicates.get(0);
    Assertions.assertTrue(equals.getDnaTaxonSearch());
    Assertions.assertEquals("7ddf754f-d193-4cc9-b351-99906754a03b", equals.getChecklistKey());
    Assertions.assertTrue(((InPredicate<?>) predicates.get(1)).getDnaTaxonSearch());
    Assertions.assertTrue(((LikePredicate<?>) predicates.get(2)).getDnaTaxonSearch());
    Assertions.assertTrue(((IsNullPredicate<?>) predicates.get(3)).getDnaTaxonSearch());
    Assertions.assertTrue(((IsNotNullPredicate<?>) predicates.get(4)).getDnaTaxonSearch());
  }

  @Test
  public void dnaTaxonSearchRoundTripTest() throws IOException {
    // the stored downloads are serialized and deserialized again, so the flag must be kept
    List<Predicate> predicates =
        List.of(
            new EqualsPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "212", false, "1", true),
            new InPredicate<>(
                OccurrenceSearchParameter.KINGDOM_KEY, List.of("1", "6"), false, "1", true),
            new IsNullPredicate<>(OccurrenceSearchParameter.GENUS_KEY, "1", true),
            new IsNotNullPredicate<>(OccurrenceSearchParameter.SPECIES_KEY, "1", true));

    for (Predicate p : predicates) {
      Predicate roundTrip = MAPPER.readValue(MAPPER.writeValueAsString(p), Predicate.class);
      Assertions.assertEquals(p, roundTrip);
    }

    // LikePredicate equality doesn't take into account the flag, so it's checked explicitly
    LikePredicate<OccurrenceSearchParameter> like =
        new LikePredicate<>(OccurrenceSearchParameter.SCIENTIFIC_NAME, "Puma*", "1", false, true);
    LikePredicate<?> likeRoundTrip =
        (LikePredicate<?>) MAPPER.readValue(MAPPER.writeValueAsString(like), Predicate.class);
    Assertions.assertTrue(likeRoundTrip.getDnaTaxonSearch());
  }

  @Test
  public void dnaTaxonSearchDistinguishesPredicatesTest() {
    Assertions.assertNotEquals(
        new EqualsPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "212", false, "1", true),
        new EqualsPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "212", false, "1", null));
    Assertions.assertNotEquals(
        new InPredicate<>(OccurrenceSearchParameter.TAXON_KEY, List.of("212"), false, "1", true),
        new InPredicate<>(OccurrenceSearchParameter.TAXON_KEY, List.of("212"), false, "1", null));
    Assertions.assertNotEquals(
        new IsNullPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "1", true),
        new IsNullPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "1", null));
    Assertions.assertNotEquals(
        new IsNotNullPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "1", true),
        new IsNotNullPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "1", null));
  }

  @Test
  public void dnaTaxonSearchOnlySerializedWhenSetTest() throws IOException {
    // predicates without the flag keep the same JSON, so older clients can still read them
    List<Predicate> withoutFlag =
        List.of(
            new EqualsPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "212", false),
            new InPredicate<>(OccurrenceSearchParameter.TAXON_KEY, List.of("212"), false),
            new LikePredicate<>(OccurrenceSearchParameter.SCIENTIFIC_NAME, "Puma*", false),
            new IsNullPredicate<>(OccurrenceSearchParameter.TAXON_KEY),
            new IsNotNullPredicate<>(OccurrenceSearchParameter.TAXON_KEY));
    for (Predicate p : withoutFlag) {
      Assertions.assertFalse(MAPPER.writeValueAsString(p).contains("dnaTaxonSearch"));
    }

    List<Predicate> withFlag =
        List.of(
            new EqualsPredicate<>(OccurrenceSearchParameter.TAXON_KEY, "212", false, null, true),
            new InPredicate<>(OccurrenceSearchParameter.TAXON_KEY, List.of("212"), false, null, true),
            new LikePredicate<>(OccurrenceSearchParameter.SCIENTIFIC_NAME, "Puma*", null, false, true),
            new IsNullPredicate<>(OccurrenceSearchParameter.TAXON_KEY, null, true),
            new IsNotNullPredicate<>(OccurrenceSearchParameter.TAXON_KEY, null, true));
    for (Predicate p : withFlag) {
      Assertions.assertTrue(MAPPER.writeValueAsString(p).contains("\"dnaTaxonSearch\":true"));
    }
  }

  @Test
  public void withoutDnaTaxonSearchTest() throws IOException {
    EqualsPredicate<?> predicate =
        (EqualsPredicate<?>) MAPPER.readValue(getTestFile("equals_catalog_number.json"), Predicate.class);
    Assertions.assertNull(predicate.getDnaTaxonSearch());
  }

  private void assertPredicate(String fileName) throws IOException {
    Predicate predicate = MAPPER.readValue(getTestFile(fileName), Predicate.class);
    Assertions.assertNotNull(predicate);
  }
}
