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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    verb.setKey(123L);
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
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    VerbatimOccurrence v = new VerbatimOccurrence();
    v.setKey(7L);
    v.setLastParsed(new Date());
    v.setDatasetKey(UUID.randomUUID());

    char [][] alphabeticPairs = {{'a','z'},{'A','Z'}};
    char [][] alphanumericPairs = {{'a','z'},{'A','Z'},{'0','9'}};
    RandomStringGenerator generatorAlphabetic = new RandomStringGenerator.Builder()
        .withinRange(alphabeticPairs)
        .build();
    RandomStringGenerator generatorAlphanumeric = new RandomStringGenerator.Builder()
        .withinRange(alphanumericPairs)
        .build();

    for (Term term : DwcTerm.values()) {

      v.setVerbatimField(term, generatorAlphabetic.generate(20));
    }
    final int numDwcTerms = v.getVerbatimFields().size();

    for (Term term : DcTerm.values()) {
      v.setVerbatimField(term, generatorAlphabetic.generate(20));
    }
    final int numDcTerms = v.getVerbatimFields().size() - numDwcTerms;

    for (Term term : GbifTerm.values()) {
      v.setVerbatimField(term, generatorAlphabetic.generate(20));
    }
    final int numGbifTerms = v.getVerbatimFields().size() - numDwcTerms - numDcTerms;

    for (Term term : IucnTerm.values()) {
      v.setVerbatimField(term, generatorAlphanumeric.generate(20));
    }
    final int numIucnTerms = v.getVerbatimFields().size() - numDwcTerms - numDcTerms - numGbifTerms;

    v.setVerbatimField(DwcTerm.scientificName, "Abies alba");
    v.setVerbatimField(DwcTerm.collectionCode, "BUGS");
    v.setVerbatimField(DwcTerm.catalogNumber, "MD10782");
    v.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/temperatur"), generatorAlphabetic.generate(30));
    v.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/co2"), generatorAlphabetic.generate(30));
    v.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/modified"), new Date().toString());
    v.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/scientificName"),
      generatorAlphabetic.generate(30));
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
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    Map<Term, String> verbatimRecord = new HashMap<Term, String>();
    verbatimRecord.put(DcTerm.created, "2021-10-25");
    verbatimRecord.put(DcTerm.creator, "fede");
    verbatimRecord.put(DcTerm.description, "testDescription");
    verbatimRecord.put(DcTerm.format, "jpg");
    verbatimRecord.put(DcTerm.license, "licenseTest");
    verbatimRecord.put(DcTerm.publisher, "publisherTest");
    verbatimRecord.put(DcTerm.title, "titleTest");
    verbatimRecord.put(DcTerm.references, "http://www.gbif.org/");
    verbatimRecord.put(DcTerm.identifier, "http://www.gbif.org/");

    VerbatimOccurrence v = new VerbatimOccurrence();
    v.setKey(7L);
    v.setLastParsed(new Date());
    v.setDatasetKey(UUID.randomUUID());
    Map<String, List<Map<Term, String>>> extensions = new HashMap<>();
    List<Map<Term, String>> verbatimRecords = new ArrayList<>();
    verbatimRecords.add(verbatimRecord);
    extensions.put(Extension.MULTIMEDIA.getRowType(), verbatimRecords);
    v.setExtensions(extensions);

    String json = mapper.writeValueAsString(v);
    System.out.println(json);

    VerbatimOccurrence v2 = mapper.readValue(json, VerbatimOccurrence.class);
    assertNotNull(v2.getExtensions());
    assertFalse(v2.getExtensions().get(Extension.MULTIMEDIA.getRowType()).isEmpty());
    assertEquals(v2.getExtensions().get(Extension.MULTIMEDIA.getRowType()).get(0), verbatimRecord);
  }
}
