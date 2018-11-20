/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.occurrence;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.gbif.api.model.occurrence.predicate.EqualsPredicate;
import org.gbif.api.model.occurrence.predicate.Predicate;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import com.google.common.collect.Lists;

/**
 * Test cases for DownloadRequest serialization and building.
 */
public class DownloadRequestTest {

  private static final String TEST_USER = "user@gbif.org";
  private static final String TEST_EMAIL = "test@gbif.org";

  private static final String SQL_REQUEST = "{\"creator\":\"" + TEST_USER + "\","
      + "\"notification_address\": [\"" + TEST_EMAIL +"\"],"
      + " \"format\": \"SQL\","
      + " \"sql\": \"SELECT basisOfRecord, count(DISTINCT speciesKey) AS speciesCount FROM occurrence WHERE year=2018 GROUP BY basisOfRecord\""
      + "}";

  private static final String SIMPLE_CSV = "{\"creator\":\"" + TEST_USER + "\", "
      + "\"notification_address\": [\"" + TEST_EMAIL +"\"],"
      + "  \"send_notification\":\"true\"," + "  \"format\": \"SIMPLE_CSV\","
      + "  \"predicate\":{\"type\":\"equals\",\"key\":\"TAXON_KEY\",\"value\":\"3\"}"
    + "}";
  
  private static final String SIMPLE_CSV_NULL_PREDICATE = "{\"creator\":\"" + TEST_USER + "\", "
      + "\"notification_address\": [\"" + TEST_EMAIL +"\"],"
      + "  \"send_notification\":\"true\"," + "  \"format\": \"SIMPLE_CSV\""
    + "}";
  
  private static final String SIMPLE_CSV_NULL_PREDICATE_AVAIL = "{\"creator\":\"" + TEST_USER + "\", "
      + "\"notification_address\": [\"" + TEST_EMAIL +"\"],"
      + "  \"send_notification\":\"true\"," + "  \"format\": \"SIMPLE_CSV\","
      + "  \"predicate\": null"
    + "}";

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private static PredicateDownloadRequest newDownload(Predicate p) {
    return newDownload(p, TEST_USER);
  }

  private static PredicateDownloadRequest newDownload(Predicate p, String user) {
    return new PredicateDownloadRequest(p, user, Lists.newArrayList(TEST_EMAIL), false, DownloadFormat.DWCA);
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
    PredicateDownloadRequest d = newDownload(new EqualsPredicate(OccurrenceSearchParameter.CATALOG_NUMBER, "b"));
    try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
      MAPPER.writeValue(baos, d);
      PredicateDownloadRequest d2 = MAPPER.readValue(baos.toByteArray(), PredicateDownloadRequest.class);
      assertEquals(d, d2);
    } catch (Throwable e) { // closer must catch Throwable
      fail(e.getMessage());
      throw e;
    }
  }

  @Test
  public void testSQLDownloadSerde() throws IOException {
    SqlDownloadRequest request = MAPPER.readValue(SQL_REQUEST, SqlDownloadRequest.class);
    assertEquals(TEST_USER, request.getCreator());
    assertNotNull(request.getSql());
    assertEquals(DownloadFormat.SQL, request.getFormat());
  }

  @Test
  public void testPredicateDownloadSerde() throws IOException {
    DownloadRequest request = MAPPER.readValue(SIMPLE_CSV, PredicateDownloadRequest.class);
    assertEquals(TEST_USER, request.getCreator());
    assertEquals(DownloadFormat.SIMPLE_CSV, request.getFormat());
  }

  @Test
  public void testDownloadRequestSerde() throws IOException {
    DownloadRequest request = MAPPER.readValue(SIMPLE_CSV, DownloadRequest.class);
    assertEquals(TEST_USER, request.getCreator());
    assertEquals(DownloadFormat.SIMPLE_CSV, request.getFormat());
  }
  
  @Test
  public void testDownloadRequestSerde1() throws IOException {
    DownloadRequest request = MAPPER.readValue(SIMPLE_CSV_NULL_PREDICATE, DownloadRequest.class);
    assertNull(((PredicateDownloadRequest)request).getPredicate());
  }
  
  @Test
  public void testDownloadRequestSerde2() throws IOException {
    DownloadRequest request = MAPPER.readValue(SIMPLE_CSV_NULL_PREDICATE_AVAIL, DownloadRequest.class);
    assertNull(((PredicateDownloadRequest)request).getPredicate());
  } 
}
