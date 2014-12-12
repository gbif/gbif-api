package org.gbif.api.model.common;

import org.gbif.api.SerdeTestUtils;

import java.io.IOException;
import java.net.URI;

import org.junit.Test;

public class DoiDataTest {

  @Test
  public void testSerDe() throws IOException {
    DoiData status = new DoiData(DoiStatus.REGISTERED, URI.create("http://www.google.de"));
    SerdeTestUtils.testSerDe(status, DoiData.class);
  }

  @Test(expected = NullPointerException.class)
  public void testStatusNull() throws IOException {
    new DoiData((DoiStatus) null, URI.create("http://www.google.de"));
  }

}