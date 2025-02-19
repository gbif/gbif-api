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
package org.gbif.api.model.occurrence;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.model.predicate.EqualsPredicate;
import org.gbif.api.model.predicate.Predicate;
import org.gbif.api.model.predicate.WithinPredicate;
import org.gbif.api.vocabulary.Extension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

/**
 * Test cases for DownloadRequest serialization and building.
 */
public class DownloadRequestTest {

  private static final String TEST_USER = "user@gbif.org";
  private static final String TEST_EMAIL = "test@gbif.org";
  private static final String TEST_DESCRIPTION = "Unit test";

  // Note these include each combination of underscores or camel case for notificationAddresses and sendNotification.

  private static final String SIMPLE_CSV = "{"
      + " \"creator\":\"" + TEST_USER + "\", "
      + " \"notification_addresses\": [\"" + TEST_EMAIL +"\"],"
      + " \"send_notification\":\"true\","
      + " \"format\": \"SIMPLE_CSV\","
      + " \"predicate\":{\"type\":\"equals\",\"key\":\"TAXON_KEY\",\"value\":\"3\"},"
      + " \"description\": \"" + TEST_DESCRIPTION + "\""
      + "}";

  private static final String SIMPLE_CSV_NULL_PREDICATE = "{"
      + " \"creator\":\"" + TEST_USER + "\", "
      + " \"notificationAddress\": [\"" + TEST_EMAIL +"\"],"
      + " \"sendNotification\":true," // Note boolean rather than string
      + " \"format\": \"SIMPLE_CSV\""
      + "}";

  private static final String SIMPLE_CSV_NULL_PREDICATE_AVAIL = "{"
      + " \"creator\":\"" + TEST_USER + "\", "
      + " \"notification_address\": [\"" + TEST_EMAIL +"\"],"
      + " \"send_notification\":\"true\","
      + " \"format\": \"SIMPLE_CSV\","
      + " \"predicate\": null"
      + "}";

  private static final String SQL_REQUEST = "{"
    + " \"creator\":\"" + TEST_USER + "\","
    + " \"notificationAddresses\": [\"" + TEST_EMAIL +"\"],"
    + " \"sendNotification\":\"true\","
    + " \"format\": \"SQL_TSV_ZIP\","
    + " \"sql\": \"SELECT basisOfRecord, COUNT(DISTINCT speciesKey) AS speciesCount FROM occurrence WHERE year = 2018 GROUP BY basisOfRecord\","
    + " \"description\": \"" + TEST_DESCRIPTION + "\","
    + " \"machineDescription\": {\"purpose\": \"" + TEST_DESCRIPTION + "\", \"elements\": [], \"array\": [{}, \"a\", 1.0, true, null]}"
    + "}";

  private static final String UNKNOWN_PROPERTY = "{"
    + " \"creator\":\"" + TEST_USER + "\", "
    + " \"notification_addresses\": [\"" + TEST_EMAIL +"\"],"
    + " \"send_notification\":\"true\","
    + " \"format\": \"SIMPLE_CSV\","
    + " \"predicate\":{\"type\":\"equals\",\"key\":\"TAXON_KEY\",\"value\":\"3\"},"
    + " \"somethingElse\": 12345"
    + "}";


  private static final ObjectMapper MAPPER = new ObjectMapper();

  private static PredicateDownloadRequest newDownload(Predicate p) {
    return newDownload(p, TEST_USER);
  }

  private static PredicateDownloadRequest newDownload(Predicate p, String user) {
    return new PredicateDownloadRequest(p, user, Collections.singleton(TEST_EMAIL), false, DownloadFormat.DWCA, DownloadType.OCCURRENCE,
                                        "Unit test download", null,
                                        Collections.singleton(Extension.AUDUBON));
  }

  @Test
  public void testAvailable() {
    //When a Download is created it has RUNNING as its status
    Download download = new Download();
    download.setStatus(Download.Status.RUNNING);
    assertFalse(download.isAvailable());

    //Status changed
    download.setStatus(Download.Status.SUCCEEDED);
    assertTrue(download.isAvailable());
  }

  @Test
  public void testBasic() {
    Predicate p = mock(Predicate.class);
    PredicateDownloadRequest dl = newDownload(p);

    assertThat(dl.getNotificationAddresses(), IsCollectionContaining.hasItem(TEST_EMAIL));
    assertThat(dl.getPredicate(), equalTo(p));
  }

  @Test
  public void testEquals() {
    Predicate p = mock(Predicate.class);

    DownloadRequest dl1 = newDownload(p);
    DownloadRequest dl2 = newDownload(p);

    assertThat(dl1, both(equalTo(dl1)).and(equalTo(dl2)));
  }

  @Test
  public void testHashcode() {
    Predicate p = mock(Predicate.class);

    PredicateDownloadRequest dl1 = newDownload(p);
    PredicateDownloadRequest dl2 = newDownload(p);

    assertThat(dl1.hashCode(), both(equalTo(dl1.hashCode())).and(equalTo(dl2.hashCode())));

    dl2 = newDownload(p, TEST_EMAIL);
    assertThat(dl1.hashCode(), not(equalTo(dl2.hashCode())));
  }

  @Test
  public void testSerde() throws IOException {
    PredicateDownloadRequest d = newDownload(new EqualsPredicate(OccurrenceSearchParameter.CATALOG_NUMBER, "b", false));
    PredicateDownloadRequest d2;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      MAPPER.writeValue(baos, d);
      d2 = MAPPER.readValue(baos.toByteArray(), PredicateDownloadRequest.class);
    } catch (Throwable e) { // closer must catch Throwable
      fail(e.getMessage());
      throw e;
    }

    assertEquals(d, d2);
  }

  @Test
  public void testPredicateDownloadSerde() throws IOException {
    DownloadRequest request = MAPPER.readValue(SIMPLE_CSV, PredicateDownloadRequest.class);
    assertEquals(TEST_USER, request.getCreator());
    assertEquals(DownloadFormat.SIMPLE_CSV, request.getFormat());
    assertTrue(request.getSendNotification());
    assertEquals(TEST_EMAIL, request.getNotificationAddressesAsString());
    assertEquals(TEST_DESCRIPTION, request.getDescription());
  }

  @Test
  public void testDownloadRequestSerde() throws IOException {
    DownloadRequest request = MAPPER.readValue(SIMPLE_CSV, DownloadRequest.class);
    assertEquals(TEST_USER, request.getCreator());
    assertEquals(DownloadFormat.SIMPLE_CSV, request.getFormat());
    assertTrue(request.getSendNotification());
    assertEquals(TEST_EMAIL, request.getNotificationAddressesAsString());
    assertEquals(TEST_DESCRIPTION, request.getDescription());
  }

  @Disabled
  @Test
  public void testDownloadRequestMissingPredicate() throws IOException {
    try {
      DownloadRequest request = MAPPER.readValue(SIMPLE_CSV_NULL_PREDICATE, DownloadRequest.class);
      fail();
    } catch (Exception e) {
      assertEquals("A predicate must be specified. Use {} for everything.", e.getMessage());
    }
  }

  @Test
  public void testDownloadRequestSerde2() throws IOException {
    DownloadRequest request = MAPPER.readValue(SIMPLE_CSV_NULL_PREDICATE_AVAIL, DownloadRequest.class);
    assertNull(((PredicateDownloadRequest)request).getPredicate());
    assertTrue(request.getSendNotification());
    assertEquals(TEST_EMAIL, request.getNotificationAddressesAsString());
  }

  @Test
  public void testSQLDownloadSerde() throws IOException {
    SqlDownloadRequest request = MAPPER.readValue(SQL_REQUEST, SqlDownloadRequest.class);
    assertEquals(TEST_USER, request.getCreator());
    assertNotNull(request.getSql());
    assertEquals(DownloadFormat.SQL_TSV_ZIP, request.getFormat());
    assertTrue(request.getSendNotification());
    assertEquals(TEST_EMAIL, request.getNotificationAddressesAsString());
    assertEquals(TEST_DESCRIPTION, request.getDescription());

    // Test no-schema JSON
    assertEquals(TEST_DESCRIPTION, request.getMachineDescription().get("purpose").asText());
    assertEquals(0, request.getMachineDescription().get("elements").size());
    assertEquals(0, request.getMachineDescription().get("array").get(0).size());
    assertEquals("a", request.getMachineDescription().get("array").get(1).asText());
    assertEquals("1.0", request.getMachineDescription().get("array").get(2).asText());
    assertEquals(1, request.getMachineDescription().get("array").get(2).asInt());
    assertTrue(request.getMachineDescription().get("array").get(3).asBoolean());
    assertTrue(request.getMachineDescription().get("array").get(4).isNull());
  }

  /**
   * Tests that an unknown property is rejected.
   *
   * For much of the API these are allowed, but it causes problems when typing errors etc trigger all-data downloads.
   */
  @Test
  public void testDownloadRequestSerdeUnknown() throws IOException {
    try {
      DownloadRequest request = MAPPER.readValue(UNKNOWN_PROPERTY, DownloadRequest.class);
      fail();
    } catch (Exception e) {
      assertEquals("Unknown JSON property 'somethingElse'.", e.getMessage());
    }
  }

  /**
   * Tests that an empty JSON {} is translated into null.
   */
  @Test
  public void testDownloadRequestSerdeNull() throws IOException {
    String json = "{}";
    DownloadRequest request = MAPPER.readValue(json, DownloadRequest.class);
    assertNull(request);
  }

  /**
   * Request sent by PyGBIF ≤ 0.6.1.  For backward compatibility, do not change this test!
   *
   * Note the 'created' property, the quoted booleans, and the underscore in notification_address.
   */
  @Test
  public void downloadFromPygbifTest() throws Exception {
    String downloadRequest = "{\n"
      + "  \"creator\":\"pygbif\",\n"
      + "  \"notification_address\":[\"pygbif@mailinator.com\"],\n"
      + "  \"created\":\"2023\",\n"
      + "  \"sendNotification\":\"true\",\n"
      + "  \"format\": \"SIMPLE_CSV\",\n"
      + "  \"predicate\":{\n"
      + "    \"type\":\"within\",\n"
      + "    \"geometry\":\"POLYGON ((-85.781 17.978,-81.035 14.774,-77.343 10.314,-79.277 6.315,-93.955 14.604,-91.450 18.229,-87.626 19.311,-85.781 17.978))\"\n"
      + "  }\n"
      + "}";

    DownloadRequest request = MAPPER.readValue(downloadRequest, DownloadRequest.class);
    assertEquals("pygbif", request.getCreator());
    assertEquals(DownloadFormat.SIMPLE_CSV, request.getFormat());
    assertTrue(request.getSendNotification());
    assertEquals("pygbif@mailinator.com", request.getNotificationAddressesAsString());
    assertEquals(WithinPredicate.class, ((PredicateDownloadRequest) request).getPredicate().getClass());
  }

  /**
   * Request sent by RGBIF 3.75+.  For backward compatibility, do not change this test!
   *
   * Note the lack of sendNotification.
   */
  @Test
  public void downloadFromRgbifTest() throws Exception {
    String downloadRequest = "{\n"
      + "  \"creator\":\"rgbif\",\n"
      + "  \"notification_address\":[\"rgbif@mailinator.com\"],\n"
      + "  \"format\": \"SIMPLE_CSV\",\n"
      + "  \"predicate\":{\n"
      + "    \"type\":\"within\",\n"
      + "    \"geometry\":\"POLYGON ((-85.781 17.978,-81.035 14.774,-77.343 10.314,-79.277 6.315,-93.955 14.604,-91.450 18.229,-87.626 19.311,-85.781 17.978))\"\n"
      + "  }\n"
      + "}";

    DownloadRequest request = MAPPER.readValue(downloadRequest, DownloadRequest.class);
    assertEquals("rgbif", request.getCreator());
    assertEquals(DownloadFormat.SIMPLE_CSV, request.getFormat());
    assertFalse(request.getSendNotification());
    assertEquals("rgbif@mailinator.com", request.getNotificationAddressesAsString());
    assertEquals(WithinPredicate.class, ((PredicateDownloadRequest) request).getPredicate().getClass());
  }

  /**
   * Working request at v0.188.  For backward compatibility, do not change this test!
   *
   * Note the 212 rather than "212".
   */
  @Test
  public void downloadWithNumbersTest() throws Exception {
    String downloadRequest = "{\n"
      + "  \"creator\":\"gbif_user\",\n"
      + "  \"notification_address\":[\"gbif_user@mailinator.com\"],\n"
      + "  \"created\":\"2023\",\n"
      + "  \"format\": \"SIMPLE_CSV\",\n"
      + "  \"predicate\":{\n"
      + "    \"type\":\"equals\",\n"
      + "    \"key\":\"TAXON_KEY\",\n"
      + "    \"value\":212\n"
      + "  }\n"
      + "}";

    DownloadRequest request = MAPPER.readValue(downloadRequest, DownloadRequest.class);
    assertEquals("gbif_user", request.getCreator());
    assertEquals(DownloadFormat.SIMPLE_CSV, request.getFormat());
    assertFalse(request.getSendNotification());
    assertEquals("gbif_user@mailinator.com", request.getNotificationAddressesAsString());
    assertEquals(EqualsPredicate.class, ((PredicateDownloadRequest) request).getPredicate().getClass());
    assertEquals("212", ((EqualsPredicate) ((PredicateDownloadRequest) request).getPredicate()).getValue());
  }

  /**
   * Test three extension situations: known and supported, known but not supported, unknown.
   */
  @Test
  public void downloadWithExtensionTest() throws Exception {
    String requestTemplate = "{\n"
      + "  \"creator\":\"gbif_user\",\n"
      + "  \"notification_address\":[\"gbif_user@mailinator.com\"],\n"
      + "  \"created\":\"2024\",\n"
      + "  \"format\": \"DWCA\",\n"
      + "  \"predicate\":{\n"
      + "    \"type\":\"equals\",\n"
      + "    \"key\":\"TAXON_KEY\",\n"
      + "    \"value\":\"212\"\n"
      + "  },\n"
      + "  %s\n"
      + "}";

    DownloadRequest request;

    // Known and supported extension
    request = MAPPER.readValue(String.format(requestTemplate, "'verbatimExtensions':['http://rs.tdwg.org/dwc/terms/MeasurementOrFact']".replace("'", "\"")), DownloadRequest.class);
    assertEquals("gbif_user", request.getCreator());
    assertEquals(DownloadFormat.DWCA, request.getFormat());
    assertEquals("gbif_user@mailinator.com", request.getNotificationAddressesAsString());
    assertEquals(EqualsPredicate.class, ((PredicateDownloadRequest) request).getPredicate().getClass());
    assertEquals("212", ((EqualsPredicate) ((PredicateDownloadRequest) request).getPredicate()).getValue());
    assertEquals(Extension.MEASUREMENT_OR_FACT, ((PredicateDownloadRequest) request).getVerbatimExtensions().iterator().next());

    // Known and *unsupported* extension
    try {
      MAPPER.readValue(String.format(requestTemplate, "'verbatimExtensions':['http://zooarchnet.org/dwc/terms/ChronometricDate']".replace("'", "\"")), DownloadRequest.class);
      fail();
    } catch (Exception e) {
    }

    // Unknown extension
    try {
      MAPPER.readValue(String.format(requestTemplate, "'verbatimExtensions':['http://example.org/nothing']".replace("'", "\"")), DownloadRequest.class);
      fail();
    } catch (Exception e) {
    }

    // Extension enum value — not supported as these are a bit too internal
    try {
      MAPPER.readValue(String.format(requestTemplate, "'verbatimExtensions':['MEASUREMENT_OR_FACT']".replace("'", "\"")), DownloadRequest.class);
      fail();
    } catch (Exception e) {
    }

    // Null value
    try {
      MAPPER.readValue(String.format(requestTemplate, "'verbatimExtensions':[null]".replace("'", "\"")), DownloadRequest.class);
      fail();
    } catch (Exception e) {
    }
  }

  @Disabled
  @Test
  public void existingDownloadsTest() throws Exception {
    String format = "{\"predicate\": %s}";
    String line = null;
    String[] filterLine = null;
    // SELECT key, filter FROM occurrence_download WHERE filter IS NOT NULL ORDER BY created
    try (RandomAccessFile filterLines = new RandomAccessFile("/home/mblissett/Temp/all-download-filters", "r")) {
      while ((line = filterLines.readLine()) != null ) {
        filterLine = line.split("\t");
        MAPPER.readValue(String.format(format, filterLine[1]), DownloadRequest.class);
      }
    } catch (Exception e) {
      System.err.println(e);
      fail("Exception with existing download " + filterLine[0] + " «" + filterLine[1] + "»");
    }
  }
}
