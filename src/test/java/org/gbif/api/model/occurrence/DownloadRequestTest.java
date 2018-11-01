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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.both;
import static org.mockito.Mockito.mock;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.gbif.api.model.occurrence.predicate.EqualsPredicate;
import org.gbif.api.model.occurrence.predicate.Predicate;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import com.google.common.collect.Lists;
import com.google.common.io.Closer;

public class DownloadRequestTest {

  private static final String TEST_USER = "user@gbif.org";
  private static final String TEST_EMAIL = "test@gbif.org";
  
  private static final String SQLREQUEST = "{\n" + 
      "  \"creator\": \"userName\",\n" + 
      "  \"notification_address\": [\n" + 
      "    \"userEmail@example.com\"\n" + 
      "  ],\n" + 
      "  \"format\": \"SQL\",\n" + 
      "  \"sql\": \"SELECT basisOfRecord, count(DISTINCT speciesKey) AS speciesCount FROM occurrence WHERE year=2018 GROUP BY basisOfRecord\"\n" + 
      "}\n" + 
      "";
  
  private static final String SIMPLE_CSV = "{\n" + 
      "  \"creator\":\"rpathak\",\n" + 
      "  \"notification_address\": [\"rpathak@gbif.org\"],\n" + 
      "  \"send_notification\":\"true\",\n" + 
      "  \"format\": \"SIMPLE_CSV\",\n" + 
      "  \"predicate\":{\"type\":\"equals\",\"key\":\"TAXON_KEY\",\"value\":\"3\"}\n" + 
      "}";

  private static PredicateDownloadRequest newDownload(Predicate p) {
    return newDownload(p, TEST_USER);
  }

  private static PredicateDownloadRequest newDownload(Predicate p, String user) {
    return newDownload(p, user, null);
  }

  private static PredicateDownloadRequest newDownload(Predicate p, String user, Date completed) {
    return new PredicateDownloadRequest(p, user, Lists.newArrayList(TEST_EMAIL), false, DownloadFormat.DWCA);
  }

  @Test
  public void testAvailable() {
    Download download = new Download();
    download.setStatus(Download.Status.RUNNING);
    assertFalse(download.isAvailable());

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
    ObjectMapper mapper = new ObjectMapper();
    PredicateDownloadRequest d = newDownload(new EqualsPredicate(OccurrenceSearchParameter.CATALOG_NUMBER, "b"));

    Closer closer = Closer.create();
    try {
      ByteArrayOutputStream baos = closer.register(new ByteArrayOutputStream());
      mapper.writeValue(baos, d);
      baos.flush();
      PredicateDownloadRequest d2 = mapper.readValue(baos.toByteArray(), PredicateDownloadRequest.class);
      assertEquals(d, d2);

    } catch (Throwable e) { // closer must catch Throwable
      fail(e.getMessage());
      throw closer.rethrow(e);

    } finally {
      closer.close();
    }
  }
  
  @Test
  public void testSQLDownloadSerde() throws JsonProcessingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    SqlDownloadRequest request = mapper.readValue(SQLREQUEST, SqlDownloadRequest.class);
    assertEquals("userName", request.getCreator());
    assertNotNull(request.getSql());
    assertEquals(DownloadFormat.SQL, request.getFormat());
    System.out.println(mapper.writeValueAsString(request));
  }
  
  @Test
  public void testPredicateDownloadSerde() throws JsonProcessingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    PredicateDownloadRequest request = mapper.readValue(SIMPLE_CSV, PredicateDownloadRequest.class);
    assertEquals("rpathak", request.getCreator());
    assertEquals(DownloadFormat.SIMPLE_CSV, request.getFormat());
    System.out.println(mapper.writeValueAsString(request));
  }
}
