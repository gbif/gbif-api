package org.gbif.api.model.registry.eml;


import org.gbif.api.model.registry.Contact;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class ProjectTest {

  @Test
  public void testEquals() {
    Project p1 = new Project("BioFresh Project", "226874", Lists.newArrayList(new Contact()),
      "Funded by the EU under the 7th Framework Programme", "Ran from November 2009 until April 2014.",
      "Established an internet platform bringing together information and data on freshwater biodiversity.");

    // identifier is null
    Project p2 = new Project("BioFresh Project", null, Lists.newArrayList(new Contact()),
      "Funded by the EU under the 7th Framework Programme", "Ran from November 2009 until April 2014.",
      "Established an internet platform bringing together information and data on freshwater biodiversity.");

    assertNotEquals(p1, p2);
  }
}
