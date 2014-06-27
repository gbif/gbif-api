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

import java.util.List;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryParameterFilterBuilderTest {

  @Test
  public void testMultiValues() throws Exception {
    QueryParameterFilterBuilder builder = new QueryParameterFilterBuilder();

    Predicate p = new EqualsPredicate(OccurrenceSearchParameter.COUNTRY, Country.AFGHANISTAN.getIso2LetterCode());

    String query = builder.queryFilter(p);
    assertEquals("COUNTRY=AF", query);

    List<Predicate> ors = Lists.newArrayList();
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.YEAR, "2000"));
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.YEAR, "2001"));
    ors.add(new EqualsPredicate(OccurrenceSearchParameter.YEAR, "2002"));
    DisjunctionPredicate or = new DisjunctionPredicate(ors);

    query = builder.queryFilter(or);
    assertEquals("YEAR=2000&YEAR=2001&YEAR=2002", query);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNot() throws Exception {
    QueryParameterFilterBuilder builder = new QueryParameterFilterBuilder();
    NotPredicate noBirds = new NotPredicate(new EqualsPredicate(OccurrenceSearchParameter.TAXON_KEY, "212"));
    builder.queryFilter(noBirds);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLess() throws Exception {
    QueryParameterFilterBuilder builder = new QueryParameterFilterBuilder();
    builder.queryFilter(new LessThanPredicate(OccurrenceSearchParameter.YEAR, "1900"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreater() throws Exception {
    QueryParameterFilterBuilder builder = new QueryParameterFilterBuilder();
    builder.queryFilter(new GreaterThanPredicate(OccurrenceSearchParameter.YEAR, "1900"));
  }

  @Test
  public void testPolygon() throws Exception {
    QueryParameterFilterBuilder builder = new QueryParameterFilterBuilder();
    final String wkt = "POLYGON((30 10,10 20,20 40,40 40,30 10))";
    String query = builder.queryFilter(new WithinPredicate(wkt));
    assertEquals("GEOMETRY=30+10%2C10+20%2C20+40%2C40+40%2C30+10", query);
  }

  @Test
  public void testRange() throws Exception {
    QueryParameterFilterBuilder builder = new QueryParameterFilterBuilder();

    List<Predicate> rangeAnd = Lists.newArrayList();
    rangeAnd.add(new GreaterThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "2000"));
    rangeAnd.add(new LessThanOrEqualsPredicate(OccurrenceSearchParameter.YEAR, "2011"));
    Predicate range = new ConjunctionPredicate(rangeAnd);

    String query = builder.queryFilter(range);
    assertEquals("YEAR=2000%2C2011", query);
  }

  @Test
  public void testIsNotNull() throws Exception {
    QueryParameterFilterBuilder builder = new QueryParameterFilterBuilder();
    String query = builder.queryFilter(new IsNotNullPredicate(OccurrenceSearchParameter.MEDIA_TYPE));
    assertEquals("MEDIA_TYPE=*", query);
  }
}
