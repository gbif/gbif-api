package org.gbif.api.model.occurrence;

import org.gbif.api.util.IsoDateParsingUtils.IsoDateFormat;
import org.gbif.api.vocabulary.Extension;
import org.gbif.dwc.terms.DcTerm;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.GbifTerm;
import org.gbif.dwc.terms.IucnTerm;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;
import org.gbif.dwc.terms.UnknownTerm;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class VerbatimOccurrenceTest {

  @Test
  public void testFieldUpdate() {
    VerbatimOccurrence occ = new VerbatimOccurrence();
    String catNum = "abc123";
    occ.setVerbatimField(DwcTerm.catalogNumber, catNum);
    assertEquals(catNum, occ.getVerbatimField(DwcTerm.catalogNumber));

    String catNum2 = "qwer";
    occ.setVerbatimField(DwcTerm.catalogNumber, catNum2);
    assertEquals(catNum2, occ.getVerbatimField(DwcTerm.catalogNumber));
  }

  @Test
  public void testHasField() {
    VerbatimOccurrence occ = new VerbatimOccurrence();

    occ.setVerbatimField(DwcTerm.catalogNumber, "abc123");
    assertTrue(occ.hasVerbatimField(DwcTerm.catalogNumber));
    assertFalse(occ.hasVerbatimField(DwcTerm.institutionCode));

    occ.setVerbatimField(DwcTerm.catalogNumber, " ");
    assertTrue(occ.hasVerbatimField(DwcTerm.catalogNumber));

    occ.setVerbatimField(DwcTerm.catalogNumber, "");
    assertFalse(occ.hasVerbatimField(DwcTerm.catalogNumber));

    occ.setVerbatimField(DwcTerm.catalogNumber, null);
    assertFalse(occ.hasVerbatimField(DwcTerm.catalogNumber));
  }

  @Test
  public void testFieldFromTermFactory() {
    VerbatimOccurrence occ = new VerbatimOccurrence();
    String prefix = "t_";
    String colName = "t_dwc:basisOfRecord";
    Term term = TermFactory.instance().findTerm(colName.substring(prefix.length()));
    occ.setVerbatimField(term, "PreservedSpecimen");
    assertEquals(1, occ.getVerbatimFields().size());
    assertTrue(occ.hasVerbatimField(DwcTerm.basisOfRecord));
    assertEquals("PreservedSpecimen", occ.getVerbatimField(DwcTerm.basisOfRecord));
  }

  @Test
  public void testJsonSerde() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    VerbatimOccurrence verb = new VerbatimOccurrence();
    verb.setVerbatimField(DwcTerm.institutionCode, "IC");
    verb.setVerbatimField(DwcTerm.collectionCode, "BUGS");
    verb.setVerbatimField(DwcTerm.catalogNumber, "MD10782");

    String json = mapper.writeValueAsString(verb);
    VerbatimOccurrence deser = mapper.readValue(json, VerbatimOccurrence.class);
    assertEquals(verb, deser);
    for (DwcTerm term : DwcTerm.values()) {
      assertEquals(verb.getVerbatimField(term), deser.getVerbatimField(term));
    }
  }

  @Test
  public void testJsonSerdeAllFields() throws IOException {
    ObjectMapper mapper = new ObjectMapper();


    VerbatimOccurrence verb = new VerbatimOccurrence();
    verb.setKey(123);
    String termPrefix = "I am Jack's ";
    for (Term term : DwcTerm.values()) {
      verb.setVerbatimField(term, termPrefix + term);
    }
    for (Term term : DcTerm.values()) {
      verb.setVerbatimField(term, termPrefix + term);
    }
    for (Term term : GbifTerm.values()) {
      verb.setVerbatimField(term, termPrefix + term);
    }
    for (Term term : IucnTerm.values()) {
      verb.setVerbatimField(term, termPrefix + term);
    }

    String json = mapper.writeValueAsString(verb);
    VerbatimOccurrence deser = mapper.readValue(json, VerbatimOccurrence.class);
    assertEquals(verb, deser);
    for (Term term : DwcTerm.values()) {
      assertTrue(deser.hasVerbatimField(term));
      assertEquals(termPrefix + term, deser.getVerbatimField(term));
    }
    for (Term term : DcTerm.values()) {
      assertTrue(deser.hasVerbatimField(term));
      assertEquals(termPrefix + term, deser.getVerbatimField(term));
    }
    for (Term term : GbifTerm.values()) {
      assertTrue(deser.hasVerbatimField(term));
      assertEquals(termPrefix + term, deser.getVerbatimField(term));
    }
    for (Term term : IucnTerm.values()) {
      assertTrue(deser.hasVerbatimField(term));
      assertEquals(termPrefix + term, deser.getVerbatimField(term));
    }
  }

  @Test
  public void testVerbatimMapSerde() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    mapper.disable(SerializationConfig.Feature.WRITE_NULL_PROPERTIES);

    VerbatimOccurrence v = new VerbatimOccurrence();
    v.setKey(7);
    v.setLastParsed(new Date());
    v.setDatasetKey(UUID.randomUUID());


    for (Term term : DwcTerm.values()) {
      v.setVerbatimField(term, RandomStringUtils.randomAlphabetic(20));
    }
    final int numDwcTerms = v.getVerbatimFields().size();

    for (Term term : DcTerm.values()) {
      v.setVerbatimField(term, RandomStringUtils.randomAlphabetic(20));
    }
    final int numDcTerms = v.getVerbatimFields().size() - numDwcTerms;

    for (Term term : GbifTerm.values()) {
      v.setVerbatimField(term, RandomStringUtils.randomAlphabetic(20));
    }
    final int numGbifTerms = v.getVerbatimFields().size() - numDwcTerms - numDcTerms;

    for (Term term : IucnTerm.values()) {
      v.setVerbatimField(term, RandomStringUtils.randomAlphanumeric(20));
    }
    final int numIucnTerms = v.getVerbatimFields().size() - numDwcTerms - numDcTerms - numGbifTerms;

    v.setVerbatimField(DwcTerm.scientificName, "Abies alba");
    v.setVerbatimField(DwcTerm.collectionCode, "BUGS");
    v.setVerbatimField(DwcTerm.catalogNumber, "MD10782");
    v.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/temperatur"), RandomStringUtils.randomAlphabetic(30));
    v.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/co2"), RandomStringUtils.randomAlphabetic(30));
    v.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/modified"), new Date().toString());
    v.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/scientificName"),
      RandomStringUtils.randomAlphabetic(30));
    final int numOtherTerms = v.getVerbatimFields().size() - numDwcTerms - numDcTerms - numGbifTerms - numIucnTerms;

    final int numTerms = v.getVerbatimFields().size();

    assertEquals(numTerms, numDwcTerms + numDcTerms + numGbifTerms + numIucnTerms + numOtherTerms);

    String json = mapper.writeValueAsString(v);
    System.out.println(json);

    VerbatimOccurrence v2 = mapper.readValue(json, VerbatimOccurrence.class);
    // 5 terms
    assertEquals(numTerms, v2.getVerbatimFields().size());

  }


  @Test
  public void testVerbatimExtensionsMapSerde() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    mapper.disable(SerializationConfig.Feature.WRITE_NULL_PROPERTIES);

    Map<Term, String> verbatimRecord = new HashMap<Term, String>();
    Date today = new Date();
    verbatimRecord.put(DcTerm.created, IsoDateFormat.FULL.getDateFormat().format(today));
    verbatimRecord.put(DcTerm.creator, "fede");
    verbatimRecord.put(DcTerm.description, "testDescription");
    verbatimRecord.put(DcTerm.format, "jpg");
    verbatimRecord.put(DcTerm.license, "licenseTest");
    verbatimRecord.put(DcTerm.publisher, "publisherTest");
    verbatimRecord.put(DcTerm.title, "titleTest");
    verbatimRecord.put(DcTerm.references, "http://www.gbif.org/");
    verbatimRecord.put(DcTerm.identifier, "http://www.gbif.org/");

    VerbatimOccurrence v = new VerbatimOccurrence();
    v.setKey(7);
    v.setLastParsed(new Date());
    v.setDatasetKey(UUID.randomUUID());
    Map<Extension, List<Map<Term, String>>> extensions = new HashMap<Extension, List<Map<Term, String>>>();
    List<Map<Term, String>> verbatimRecords = Lists.newArrayList();
    verbatimRecords.add(verbatimRecord);
    extensions.put(Extension.MULTIMEDIA, verbatimRecords);
    v.setExtensions(extensions);


    String json = mapper.writeValueAsString(v);
    System.out.println(json);

    VerbatimOccurrence v2 = mapper.readValue(json, VerbatimOccurrence.class);
    assertNotNull(v2.getExtensions());
    assertTrue(!v2.getExtensions().get(Extension.MULTIMEDIA).isEmpty());
    assertEquals(v2.getExtensions().get(Extension.MULTIMEDIA).get(0), verbatimRecord);


  }

}
