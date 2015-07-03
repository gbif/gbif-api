package org.gbif.api.model.crawler;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenericValidationReportTest {

  @Test(expected = NullPointerException.class)
  public void testConstructor() throws Exception {
    GenericValidationReport report = new GenericValidationReport(10, true, null, null);
  }

  @Test(expected = NullPointerException.class)
  public void testConstructor2() throws Exception {
    GenericValidationReport report = new GenericValidationReport(10, true, Lists.<String>newArrayList(), null);
  }

  @Test(expected = NullPointerException.class)
  public void testConstructor3() throws Exception {
    GenericValidationReport report = new GenericValidationReport(10, true, null, Lists.<Integer>newArrayList());
  }

  @Test
  public void testIsValid() throws Exception {
    assertTrue(new GenericValidationReport(10, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList()).isValid());
    assertTrue(new GenericValidationReport(0, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList()).isValid());
    assertFalse(new GenericValidationReport(10, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList(1)).isValid());
    assertFalse(new GenericValidationReport(10, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList(1,2,3,4)).isValid());
    assertFalse(new GenericValidationReport(10, true, Lists.<String>newArrayList("r32"), Lists.<Integer>newArrayList(1,2)).isValid());
    assertFalse(new GenericValidationReport(10, true, Lists.<String>newArrayList("r32"), Lists.<Integer>newArrayList()).isValid());
  }

}
