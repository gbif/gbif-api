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

  private void assertPredicate(String fileName) throws IOException {
    Predicate predicate = MAPPER.readValue(getTestFile(fileName), Predicate.class);
    Assertions.assertNotNull(predicate);
  }
}
