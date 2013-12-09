package org.gbif.api.util;

import java.util.Arrays;
import java.util.Collection;

import com.google.common.collect.Range;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class SearchTypeValidatorIntegerRangeTest {

  private final String arg;
  private final Integer start;
  private final Integer end;

  public SearchTypeValidatorIntegerRangeTest(String arg, Integer start, Integer end) {
    this.arg = arg;
    this.start = start;
    this.end = end;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    Object[][] data = {
      {"10", null, null},
      {"10,20", 10, 20},
      {"10.1,20.0", null, null},
      {"-1 , 0", -1, 0},
      {"*,20", null, 20},
      {"10, *", 10, null},
      {"peter", null, null},
      {"Puma concolor, 1882", null, null},
    };
    return Arrays.asList(data);
  };


  @Test
  public void testRange() throws Exception {
    try {
      Range<Integer> range = SearchTypeValidator.parseIntegerRange(arg);
      if (start == null && end == null) {
        fail(arg + " supposed to be an invalid range");
      } else {
        // test parse result
        if (range.hasLowerBound()) {
          assertEquals(start, range.lowerEndpoint());
        } else {
          assertNull(start);
        }

        if (range.hasUpperBound()) {
          assertEquals(end, range.upperEndpoint());
        } else {
          assertNull(end);
        }

        assertTrue(SearchTypeValidator.isRange(arg));
      }

    } catch (IllegalArgumentException e) {
      // expected?
      if (start != null || end != null) {
        fail(arg + " supposed to be a valid range");
      }
    }
  }

}
