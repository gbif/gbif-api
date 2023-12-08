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

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/** Test cases and examples of serialization or predicates using a mixing. */
public class PredicateDeSerTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private File getTestFile(String predicateFile) {
    return new File(getClass().getResource("/predicate/" + predicateFile).getFile());
  }

  @Test
  public void deserTest() throws IOException {
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
  }

  private void assertPredicate(String fileName) throws IOException {
    Predicate predicate = MAPPER.readValue(getTestFile(fileName), Predicate.class);
    Assertions.assertNotNull(predicate);
  }
}
