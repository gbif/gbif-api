package org.gbif.api.vocabulary;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatasetTypeTest {

  @Test
  public void testFromString() throws Exception {
    assertEquals(DatasetType.SAMPLING_EVENT, DatasetType.fromString("samplingEvent"));
    assertEquals(DatasetType.SAMPLING_EVENT, DatasetType.fromString("sampling-event"));
    assertEquals(DatasetType.SAMPLING_EVENT, DatasetType.fromString("sampling_event"));
    assertEquals(DatasetType.OCCURRENCE, DatasetType.fromString("occurrence"));
    assertEquals(DatasetType.CHECKLIST, DatasetType.fromString("checklist"));
    assertEquals(DatasetType.METADATA, DatasetType.fromString("metadata"));
    assertEquals(DatasetType.METADATA, DatasetType.fromString("METADATA"));
    assertEquals(DatasetType.METADATA, DatasetType.fromString("meta-data"));
  }
}
