package org.gbif.api.util.occurrence;

import org.gbif.api.model.occurrence.predicate.ConjunctionPredicate;
import org.gbif.api.model.occurrence.predicate.DisjunctionPredicate;
import org.gbif.api.model.occurrence.predicate.EqualsPredicate;
import org.gbif.api.model.occurrence.predicate.GreaterThanOrEqualsPredicate;
import org.gbif.api.model.occurrence.predicate.GreaterThanPredicate;
import org.gbif.api.model.occurrence.predicate.IsNotNullPredicate;
import org.gbif.api.model.occurrence.predicate.LessThanOrEqualsPredicate;
import org.gbif.api.model.occurrence.predicate.LessThanPredicate;
import org.gbif.api.model.occurrence.predicate.NotPredicate;
import org.gbif.api.model.occurrence.predicate.Predicate;
import org.gbif.api.model.occurrence.predicate.WithinPredicate;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.vocabulary.Country;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class HumanFilterBuilderTest {

  @Mock
  private ResourceBundle resourceBundle;

  @Test
  public void testDate() throws Exception {
    final String lower = "1871-03-21";
    final String upper = "1888-01-01";
    HumanFilterBuilder builder = new HumanFilterBuilder(resourceBundle, null, null, true);
    Map<OccurrenceSearchParameter, LinkedList<String>> x = builder.humanFilter(
      new ConjunctionPredicate(Lists.<Predicate>newArrayList(
        new GreaterThanOrEqualsPredicate(OccurrenceSearchParameter.EVENT_DATE, lower),
        new LessThanOrEqualsPredicate(OccurrenceSearchParameter.EVENT_DATE, upper)
        )
      ));
    assertEquals(1, x.size());
    assertEquals(1, x.get(OccurrenceSearchParameter.EVENT_DATE).size());
    assertEquals(lower + "-" + upper, x.get(OccurrenceSearchParameter.EVENT_DATE).getFirst());
  }


  @Test
  public void testHumanFilter() throws Exception {
    HumanFilterBuilder builder = new HumanFilterBuilder(resourceBundle, null, null, false);

    List<Predicate> ors = Lists.newArrayList();
    ors.add(new GreaterThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "2000"));
    ors.add(new GreaterThanPredicate(OccurrenceSearchParameter.YEAR, "2001"));
    ors.add(new LessThanPredicate(OccurrenceSearchParameter.YEAR, "1750"));
    ors.add(new LessThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "1760"));
    ors.add(new IsNotNullPredicate(OccurrenceSearchParameter.YEAR));
    DisjunctionPredicate or = new DisjunctionPredicate(ors);


    Map<OccurrenceSearchParameter, LinkedList<String>> x = builder.humanFilter(or);
    assertEquals(4, x.get(OccurrenceSearchParameter.YEAR).size());
    assertEquals(">=2000", x.get(OccurrenceSearchParameter.YEAR).getFirst());
    assertEquals(">2001", x.get(OccurrenceSearchParameter.YEAR).get(1));
    assertEquals("<1750", x.get(OccurrenceSearchParameter.YEAR).get(2));
    assertEquals("<=1760", x.get(OccurrenceSearchParameter.YEAR).get(3));
    assertEquals("YEAR IS NOT NULL", x.get(OccurrenceSearchParameter.YEAR).getLast());
  }

  @Test
  public void testHumanFilterXmlEscape() throws Exception {
    HumanFilterBuilder builder = new HumanFilterBuilder(resourceBundle, null, null, true);

    Predicate p = new EqualsPredicate(OccurrenceSearchParameter.COUNTRY, Country.AFGHANISTAN.getIso2LetterCode());

    Map<OccurrenceSearchParameter, LinkedList<String>> x = builder.humanFilter(p);
    assertEquals(1, x.size());
    assertEquals(1, x.get(OccurrenceSearchParameter.COUNTRY).size());
    assertEquals("Afghanistan", x.get(OccurrenceSearchParameter.COUNTRY).get(0));

    List<Predicate> ors = Lists.newArrayList();
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.YEAR, "2000"));
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.YEAR, "2001"));
    ors.add(new LessThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "1760"));
    DisjunctionPredicate or = new DisjunctionPredicate(ors);

    List<Predicate> ands = Lists.newArrayList(p, or);

    x = builder.humanFilter(new ConjunctionPredicate(ands));
    assertEquals(2, x.size());
    assertEquals(3, x.get(OccurrenceSearchParameter.YEAR).size());
    assertEquals("&lt;=1760", x.get(OccurrenceSearchParameter.YEAR).getLast());

    NotPredicate noBirds = new NotPredicate(new EqualsPredicate(OccurrenceSearchParameter.TAXON_KEY, "212"));
    ands.add(noBirds);

    x = builder.humanFilter(new ConjunctionPredicate(ands));
    assertEquals(3, x.size());
    assertEquals(3, x.get(OccurrenceSearchParameter.YEAR).size());
    assertEquals(1, x.get(OccurrenceSearchParameter.TAXON_KEY).size());
    assertEquals("NOT (212)", x.get(OccurrenceSearchParameter.TAXON_KEY).getLast());
  }


  @Test
  public void testPolygon() throws Exception {
    HumanFilterBuilder builder = new HumanFilterBuilder(resourceBundle, null, null, true);
    final String wkt = "POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))";
    Map<OccurrenceSearchParameter, LinkedList<String>> x =
      builder.humanFilter(new ConjunctionPredicate(Lists.<Predicate>newArrayList(new WithinPredicate(wkt))));
    assertEquals(1, x.size());
    assertEquals(1, x.get(OccurrenceSearchParameter.GEOMETRY).size());
    assertEquals(wkt, x.get(OccurrenceSearchParameter.GEOMETRY).getLast());
  }

  @Test
  public void testRange() throws Exception {
    HumanFilterBuilder builder = new HumanFilterBuilder(resourceBundle, null, null, true);

    List<Predicate> rangeAnd = Lists.newArrayList();
    rangeAnd.add(new GreaterThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "2000"));
    rangeAnd.add(new LessThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "2011"));
    Predicate range = new ConjunctionPredicate(rangeAnd);

    Map<OccurrenceSearchParameter, LinkedList<String>> x = builder.humanFilter(range);
    assertEquals(1, x.size());
    assertEquals(1, x.get(OccurrenceSearchParameter.YEAR).size());

    // now test with other filters combined
    Predicate eq = new EqualsPredicate(OccurrenceSearchParameter.TAXON_KEY, "212");
    x = builder.humanFilter(new ConjunctionPredicate(Lists.newArrayList(eq, range)));
    assertEquals(2, x.size());
    assertEquals(1, x.get(OccurrenceSearchParameter.TAXON_KEY).size());
    assertEquals(1, x.get(OccurrenceSearchParameter.YEAR).size());
  }

}
