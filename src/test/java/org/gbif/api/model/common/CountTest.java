package org.gbif.api.model.common;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class CountTest {

  @Test
  public void testCompareTo() throws Exception {
    List<Count<String>> counts = Lists.newArrayList();
    counts.add(new Count<String>("HH", "Hamburg", 2567128));
    counts.add(new Count<String>("B", "Berlin", 4367846));
    counts.add(new Count<String>("K", "Köln", 1089456));
    counts.add(new Count<String>("M", "München", 1493201));
    counts.add(new Count<String>("F", "Frankfurt", 983786));

    Collections.sort(counts);
    assertEquals("B", counts.get(0).getKey());
    assertEquals("HH", counts.get(1).getKey());
    assertEquals("M", counts.get(2).getKey());
    assertEquals("K", counts.get(3).getKey());
    assertEquals("F", counts.get(4).getKey());
  }
}