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
package org.gbif.api.model.crawler;

import org.junit.Test;

import com.google.common.collect.Lists;

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
