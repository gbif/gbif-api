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
package org.gbif.api.util;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatasetKeyTest {

  private static final String EXTERNAL_DATASET_KEY =
    "7879e569-4a13-4643-b833-d1a564675b86:urn%3Alsid%3Aknb.ecoinformatics.org%3Aknb-lter-cdr%3A8133";
  private static final String INVALID_EXTERNAL_DATASET_KEY =
    "7879e569:urn%3Alsid%3Aknb.ecoinformatics.org%3Aknb-lter-cdr%3A8133";
  private static final String SHORT_EXTERNAL_DATASET_KEY = "7879e569";
  private static final String VALID_UUID = "127da08e-5326-41ef-abbd-a27f65f3670d";
  private static final String VALID_UUID_MIXED_CASE = "127DA08E-5326-41ef-abbd-a27f65f3670d";

  @Test
  public void testParseInvalid() {
    assertThrows(IllegalArgumentException.class, () -> DatasetKey.fromString("7879e569-4a13-4643-b833-d1a564675bzz"));
  }

  @Test
  public void testParseInvalid2() {
    assertThrows(IllegalArgumentException.class, () -> DatasetKey.fromString("7879e569-4a13-4643-b833-d1a564675b861"));
  }

  @Test
  public void testParseInvalid3() {
    assertThrows(IllegalArgumentException.class, () -> DatasetKey.fromString(VALID_UUID + ":"));
  }

  @Test
  public void testParseInvalid4() {
    assertThrows(IllegalArgumentException.class, () -> DatasetKey.fromString("7879e569-4a13-4643-b833_d1a564675b85:aha"));
  }

  @Test
  public void testParseInvalid5() {
    assertThrows(IllegalArgumentException.class, () -> DatasetKey.fromString(""));
  }

  @Test
  public void testParseInvalid6() {
    assertThrows(NullPointerException.class, () -> DatasetKey.fromString(null));
  }

  @Test
  public void testParseInvalidWhitespaceOnly() {
    assertThrows(IllegalArgumentException.class, () -> DatasetKey.fromString("7879e569-4a13-4643-b833_d1a564675b85:   "));
  }

  @Test
  public void testInvalidConstructor() {
    assertThrows(NullPointerException.class, () -> new DatasetKey(UUID.randomUUID(), null));
  }

  @Test
  public void testInvalidConstructor2() {
    assertThrows(IllegalArgumentException.class, () -> new DatasetKey(UUID.randomUUID(), ""));
  }

  @Test
  public void testInvalidConstructor3() {
    assertThrows(IllegalArgumentException.class, () -> new DatasetKey(UUID.randomUUID(), "    "));
  }

  @Test
  public void testDatasetIdWhitespace() {
    DatasetKey key = new DatasetKey(UUID.randomUUID(), "  12  ");
    assertEquals("12", key.getDatasetId());
  }

  @Test
  public void testParseValid() {
    DatasetKey key = DatasetKey.fromString(VALID_UUID);
    assertFalse(key.isExternalKey());
    assertNull(key.getDatasetId());
    assertEquals(VALID_UUID, key.getRegistryKey().toString());

    key = DatasetKey.fromString(VALID_UUID_MIXED_CASE);
    assertFalse(key.isExternalKey());
    assertNull(key.getDatasetId());
    assertEquals(VALID_UUID, key.getRegistryKey().toString());

    key = DatasetKey.fromString(VALID_UUID + ":aha");
    assertEquals("aha", key.getDatasetId());

    key = DatasetKey.fromString(VALID_UUID + ":1");
    assertEquals("1", key.getDatasetId());

    key = DatasetKey.fromString(VALID_UUID + ":?:1-3");
    assertEquals("?:1-3", key.getDatasetId());

    key = DatasetKey.fromString(VALID_UUID + ":-");
    assertEquals("-", key.getDatasetId());
  }

  @Test
  public void testParseNetworkKeyInvalid() {
    assertThrows(IllegalArgumentException.class, () -> DatasetKey.fromString(INVALID_EXTERNAL_DATASET_KEY));
  }

  @Test
  public void testParseNetworkKeyShort() {
    assertThrows(IllegalArgumentException.class, () -> DatasetKey.fromString(SHORT_EXTERNAL_DATASET_KEY));
  }

  @Test
  public void testParseNetworkKey() {
    DatasetKey key = DatasetKey.fromString(EXTERNAL_DATASET_KEY);
    assertTrue(key.isExternalKey());
    assertEquals(UUID.fromString("7879e569-4a13-4643-b833-d1a564675b86"), key.getRegistryKey());
    assertEquals("urn%3Alsid%3Aknb.ecoinformatics.org%3Aknb-lter-cdr%3A8133", key.getDatasetId());
  }
  
  @Test
  public void testParseNetworkKeyMixedCase() {
    DatasetKey key = DatasetKey.fromString(VALID_UUID_MIXED_CASE);
    assertFalse(key.isExternalKey());
    assertEquals(UUID.fromString(VALID_UUID_MIXED_CASE), key.getRegistryKey());
  }

  @Test
  public void testRoundtrip() {
    DatasetKey key = DatasetKey.fromString(EXTERNAL_DATASET_KEY);
    assertEquals(EXTERNAL_DATASET_KEY, key.toDatasetKey());

    key = new DatasetKey(UUID.fromString(VALID_UUID));
    assertEquals(VALID_UUID, key.toDatasetKey());
  }

}
