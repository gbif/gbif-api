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
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.checklistbank.NameUsageService;
import org.gbif.api.service.registry.DatasetService;
import org.gbif.api.vocabulary.Country;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HumanFilterBuilderTest {
  HumanFilterBuilder builder;

  @Before
  public void init(){
    DatasetService ds = mock(DatasetService.class);
    Dataset d = new Dataset();
    d.setKey(UUID.randomUUID());
    d.setTitle("The little Mermaid");
    when(ds.get(Matchers.<UUID>any())).thenReturn(d);

    NameUsageService us = mock(NameUsageService.class);
    builder = new HumanFilterBuilder(ds, us, false);
  }

  @Test
  public void testDate() throws Exception {
    final String lower = "1871-03-21";
    final String upper = "1888-01-01";
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

  /**
   * test all available search parameters and make sure we have a bundle entry for all possible enum values
   */
  @Test
  public void testAllParams() throws Exception {

    final String date = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
    List<Predicate> ands = Lists.newArrayList();
    for (OccurrenceSearchParameter p : OccurrenceSearchParameter.values()) {
      if (p.type().isEnum()) {
        if (p.type() == Country.class) {
          ands.add(new EqualsPredicate(p, Country.DENMARK.getIso2LetterCode()));

        } else {
          Class<Enum<?>> vocab = (Class<Enum<?>>) p.type();
          // add a comparison for every possible enum value to test the resource bundle for completeness
          List<Predicate> ors = Lists.newArrayList();
          for (Enum<?> e : vocab.getEnumConstants()) {
            ors.add(new EqualsPredicate(p, e.toString()));
          }
          ands.add(new DisjunctionPredicate(ors));
        }

      } else if (p.type() == Date.class) {
        ands.add(new EqualsPredicate(p, date));

      } else if (p.type() == Double.class) {
        ands.add(new EqualsPredicate(p, "12.478"));

      } else if (p.type() == Integer.class) {
        ands.add(new EqualsPredicate(p, "10"));

      } else if (p.type() == String.class) {
        if (p == OccurrenceSearchParameter.GEOMETRY) {
          ands.add(new EqualsPredicate(p, "POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))"));
        } else {
          ands.add(new EqualsPredicate(p, "Bernd Neumann"));
        }

      } else if (p.type() == Boolean.class) {
        ands.add(new EqualsPredicate(p, "true"));

      } else if (p.type() == UUID.class) {
        ands.add(new EqualsPredicate(p, UUID.randomUUID().toString()));

      } else {
        throw new IllegalStateException("Unknown SearchParameter type " + p.type());
      }
    }
    ConjunctionPredicate and = new ConjunctionPredicate(ands);

    Map<OccurrenceSearchParameter, LinkedList<String>> x = builder.humanFilter(and);
    for (OccurrenceSearchParameter p : x.keySet()) {
      System.out.print(p + "\t");
      System.out.println(x.get(p));
    }
  }

  @Test
  public void testHumanFilter() throws Exception {
    List<Predicate> ors = Lists.newArrayList();
    ors.add(new GreaterThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "2000"));
    ors.add(new GreaterThanPredicate(OccurrenceSearchParameter.YEAR, "2001"));
    ors.add(new LessThanPredicate(OccurrenceSearchParameter.YEAR, "1750"));
    ors.add(new LessThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "1760"));
    ors.add(new IsNotNullPredicate(OccurrenceSearchParameter.YEAR));
    DisjunctionPredicate or = new DisjunctionPredicate(ors);


    Map<OccurrenceSearchParameter, LinkedList<String>> x = builder.humanFilter(or);
    assertEquals(5, x.get(OccurrenceSearchParameter.YEAR).size());
    assertEquals(">= 2000", x.get(OccurrenceSearchParameter.YEAR).getFirst());
    assertEquals("> 2001", x.get(OccurrenceSearchParameter.YEAR).get(1));
    assertEquals("< 1750", x.get(OccurrenceSearchParameter.YEAR).get(2));
    assertEquals("<= 1760", x.get(OccurrenceSearchParameter.YEAR).get(3));
    assertEquals("is not null", x.get(OccurrenceSearchParameter.YEAR).getLast());
  }

  @Test
  public void testHumanFilterString() throws Exception {
    List<Predicate> ands = Lists.newArrayList();

    List<Predicate> years = Lists.newArrayList();
    years.add(new GreaterThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "2000"));
    years.add(new LessThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "1760"));
    ands.add(new DisjunctionPredicate(years));

    List<Predicate> depth = Lists.newArrayList();
    depth.add(new GreaterThanPredicate(OccurrenceSearchParameter.DEPTH, "2001"));
    depth.add(new LessThanPredicate(OccurrenceSearchParameter.DEPTH, "1750"));
    ands.add(new DisjunctionPredicate(depth));

    ands.add(new IsNotNullPredicate(OccurrenceSearchParameter.TYPE_STATUS));

    List<Predicate> countries = Lists.newArrayList();
    countries.add(new EqualsPredicate(OccurrenceSearchParameter.COUNTRY, Country.ANGOLA.getIso2LetterCode()));
    countries.add(new EqualsPredicate(OccurrenceSearchParameter.COUNTRY, Country.DENMARK.getIso2LetterCode()));
    ands.add(new DisjunctionPredicate(countries));

    assertEquals("Year: >= 2000 or <= 1760 \n"
                 + "Depth: > 2001m or < 1750m \n"
                 + "TypeStatus: is not null \n"
                 + "Country: Angola or Denmark"
                 , builder.humanFilterString(new ConjunctionPredicate(ands)));
  }

  @Test
  public void testHumanFilterNull() throws Exception {
    assertEquals(Maps.newLinkedHashMap(), builder.humanFilter(null));
  }

  @Test
  public void testHumanFilterStringNull() throws Exception {
    assertEquals("All data", builder.humanFilterString(null));
  }

  // http://www.gbif-dev.org/occurrence/search?COUNTRY=DE&BASIS_OF_RECORD=HUMAN_OBSERVATION
  // &DATASET_KEY=7a22e1e4-f762-11e1-a439-00145eb45e9a
  // &DATASET_KEY=78ff18a6-1c32-11e2-af65-00145eb45e9a
  // &DATASET_KEY=856734ce-f762-11e1-a439-00145eb45e9a
  // &DATASET_KEY=85685a84-f762-11e1-a439-00145eb45e9a
  // &DEPTH=0%2C*#
  @Test
  public void testHumanFilter2() {
    List<Predicate> ors = Lists.newArrayList();
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.DATASET_KEY, "7a22e1e4-f762-11e1-a439-00145eb45e9a"));
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.DATASET_KEY, "78ff18a6-1c32-11e2-af65-00145eb45e9a"));
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.DATASET_KEY, "856734ce-f762-11e1-a439-00145eb45e9a"));
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.DATASET_KEY, "85685a84-f762-11e1-a439-00145eb45e9a"));

    List<Predicate> ands = Lists.newArrayList();
    ands.add(new EqualsPredicate(OccurrenceSearchParameter.BASIS_OF_RECORD, "HUMAN_OBSERVATION"));
    ands.add(new EqualsPredicate(OccurrenceSearchParameter.COUNTRY, "DE"));
    ands.add(new DisjunctionPredicate(ors));
    ands.add(new GreaterThanOrEqualsPredicate(OccurrenceSearchParameter.DEPTH, "0"));

    Map<OccurrenceSearchParameter, LinkedList<String>> x = builder.humanFilter(new ConjunctionPredicate(ands));
    assertEquals(4, x.size());
    assertEquals("Human Observation", x.get(OccurrenceSearchParameter.BASIS_OF_RECORD).getFirst());
    assertEquals("Germany", x.get(OccurrenceSearchParameter.COUNTRY).getFirst());
    assertEquals(4, x.get(OccurrenceSearchParameter.DATASET_KEY).size());
    assertEquals(">= 0m", x.get(OccurrenceSearchParameter.DEPTH).getFirst());
  }

  @Test
  public void testHumanFilterXmlEscape() throws Exception {
    builder = new HumanFilterBuilder(null, null, true);
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
    assertEquals("&lt;= 1760", x.get(OccurrenceSearchParameter.YEAR).getLast());

    NotPredicate noBirds = new NotPredicate(new EqualsPredicate(OccurrenceSearchParameter.TAXON_KEY, "212"));
    ands.add(noBirds);

    x = builder.humanFilter(new ConjunctionPredicate(ands));
    assertEquals(3, x.size());
    assertEquals(3, x.get(OccurrenceSearchParameter.YEAR).size());
    assertEquals(1, x.get(OccurrenceSearchParameter.TAXON_KEY).size());
    assertEquals("not (212)", x.get(OccurrenceSearchParameter.TAXON_KEY).getLast());
  }


  @Test
  public void testPolygon() throws Exception {
    final String wkt = "POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))";
    Map<OccurrenceSearchParameter, LinkedList<String>> x =
      builder.humanFilter(new ConjunctionPredicate(Lists.<Predicate>newArrayList(new WithinPredicate(wkt))));
    assertEquals(1, x.size());
    assertEquals(1, x.get(OccurrenceSearchParameter.GEOMETRY).size());
    assertEquals(wkt, x.get(OccurrenceSearchParameter.GEOMETRY).getLast());
  }

  @Test
  public void testRange() throws Exception {
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

  @Test
  public void testIsNotNull() throws Exception {
    Map<OccurrenceSearchParameter, LinkedList<String>> x =
      builder.humanFilter(new IsNotNullPredicate(OccurrenceSearchParameter.MEDIA_TYPE));
    assertEquals(1, x.size());
    assertEquals(1, x.get(OccurrenceSearchParameter.MEDIA_TYPE).size());
    assertEquals("is not null", x.get(OccurrenceSearchParameter.MEDIA_TYPE).getLast());
  }

}
