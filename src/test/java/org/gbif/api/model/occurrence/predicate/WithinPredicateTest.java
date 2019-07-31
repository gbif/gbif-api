package org.gbif.api.model.occurrence.predicate;

import org.gbif.utils.file.FileUtils;
import org.junit.Test;

import java.nio.file.Files;

public class WithinPredicateTest {

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyConstructor() {
    new WithinPredicate("");
  }

  @Test(expected = NullPointerException.class)
  public void testNullConstructor() {
    new WithinPredicate(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor1() {
    new WithinPredicate("POLYGON");
  }

  @Test
  public void testGoodConstructor() {
    new WithinPredicate("POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))");
  }

  @Test
  public void testGoodComplexConstructor() throws Exception {
    byte[] enc = Files.readAllBytes(FileUtils.getClasspathFile("predicate/large-polygon.wkt").toPath());
    new WithinPredicate(new String(enc, "UTF-8"));
  }

  /**
   * TODO: The JTS library does not properly support wrapping over the antimeridian or around a pole.
   * https://github.com/gbif/gbif-api/issues/44
   *
   * <a href="https://github.com/locationtech/spatial4j#why-not-use-jts-why-should-you-use-spatial4j">The README on
   * Spatial4J</a>, which is a derivative of JTS, says "it wraps JTS geometries to add dateline-wrap support (no pole wrap yet)."
   * We could use this library instead to get dateline-wrap support.
   *
   * https://github.com/locationtech/spatial4j/issues/5
   */
  @Test
  public void testGoodPolygonOverDateline() {
    // A rectangle over the Bering sea
    new WithinPredicate("POLYGON((-206.71875 39.20502,-133.59375 39.20502,-133.59375 77.26611,-206.71875 77.26611,-206.71875 39.20502))");

    // A big polygon over the Arctic
    new WithinPredicate("POLYGON((-40 65,80 75,160 65, 220 75,280 55,-40 65))");
    // The same, but with all values ≤±180.
//  new WithinPredicate("POLYGON((-40 65,80 75,160 65,-140 75,-80 55,-40 65))");

    // A more detailed polygon around the Arctic (CAFF polygon, vastly simplified).
    // https://dev.gbif.org/issues/browse/POR-3042/
//  new WithinPredicate("POLYGON((-181 50,-180 50,-179 50,-170 51,-150 67,-130 62,-110 59,-90 55,-70 52,-50 59,-30 66,-10 62,10 66,30 66,50 66,70 65,90 64,110 63,130 63,150 62,170 53,178 50,179 50,-181 50))");
    // "Fixed" by turning it into a WGS84 rectangle.
    new WithinPredicate("POLYGON((-180 90,-180 50,-179 50,-170 51,-150 67,-130 62,-110 59,-90 55,-70 52,-50 59,-30 66,-10 62,10 66,30 66,50 66,70 65,90 64,110 63,130 63,150 62,170 53,178 50,179 50,180 50,180 90,-180 90))");
  }
}
