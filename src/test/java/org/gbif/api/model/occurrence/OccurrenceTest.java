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

import org.gbif.api.model.common.MediaObject;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.MediaType;
import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.api.vocabulary.Rank;
import org.gbif.dwc.terms.DcTerm;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.GbifTerm;
import org.gbif.dwc.terms.IucnTerm;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;
import org.gbif.dwc.terms.UnknownTerm;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OccurrenceTest {

  private ObjectMapper mapper;

  private final UUID datasetKey = UUID.randomUUID();
  private final Country country = Country.ALGERIA;
  private final Date interpreted = new Date();
  private final Date crawled = new Date(interpreted.getTime() - 99999);

  @BeforeEach
  public void init() {
    mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  private Occurrence buildTestOccurrence() {
    Occurrence o = new Occurrence();
    Long key = 321L;
    o.setKey(key);
    o.setDatasetKey(datasetKey);
    String sciName = "Abies alba";
    o.setScientificName(sciName);
    o.setCountry(country);
    o.setLastInterpreted(interpreted);
    o.setLastCrawled(crawled);
    return o;
  }

  @Test
  public void testEquals() {
    Occurrence o1 = buildTestOccurrence();
    Occurrence o2 = buildTestOccurrence();
    Occurrence o3 = buildTestOccurrence();
    o3.setCountry(Country.POLAND);

    // All of them are equal to themselves
    assertEquals(o1, o1);
    assertEquals(o2, o2);
    assertEquals(o3, o3);

    // But not always equal among themselves
    assertEquals(o1, o2);
    assertNotEquals(o1, o3);
    assertNotEquals(o2, o3);
  }

  @Test
  public void testHashcode() {
    Occurrence o1 = buildTestOccurrence();
    Occurrence o2 = buildTestOccurrence();
    Occurrence o3 = buildTestOccurrence();
    o3.setCountry(Country.POLAND);

    assertEquals(o1.hashCode(), o2.hashCode());
    assertNotEquals(o1.hashCode(), o3.hashCode());
    assertNotEquals(o2.hashCode(), o3.hashCode());
  }

  @Test
  public void testNullConstructor() {
    Occurrence o = new Occurrence(null);
    assertNotNull(o);
  }

  @Test
  public void testGetHigherClassificationMap() {
    Occurrence occ = new Occurrence();
    occ.setFamily("Plants family");
    occ.setFamilyKey(16);
    occ.setKingdom("Plants");
    occ.setKingdomKey(6);

    assertEquals(2, occ.getHigherClassificationMap().size());
    assertTrue(occ.getHigherClassificationMap().containsKey(16));
    assertTrue(occ.getHigherClassificationMap().containsKey(6));
    assertTrue(occ.getHigherClassificationMap().containsValue("Plants"));
    assertTrue(occ.getHigherClassificationMap().containsValue("Plants family"));

    occ.setTaxonKey(200);
    assertEquals(2, occ.getHigherClassificationMap().size());

    occ.setTaxonKey(16);
    assertEquals(1, occ.getHigherClassificationMap().size());
    assertTrue(occ.getHigherClassificationMap().containsKey(6));
    assertTrue(occ.getHigherClassificationMap().containsValue("Plants"));
    assertFalse(occ.getHigherClassificationMap().containsKey(16));
    assertFalse(occ.getHigherClassificationMap().containsValue("Plants family"));
  }

  @Test
  public void testGetHigherTaxon() {
    Occurrence occ = new Occurrence();
    occ.setFamily("Plants family");
    occ.setFamilyKey(16);
    occ.setKingdom("Plants");
    occ.setKingdomKey(6);

    assertEquals(6, occ.getHigherRankKey(Rank.KINGDOM));
    assertEquals("Plants", occ.getHigherRank(Rank.KINGDOM));
  }

  @Test
  public void testNullDates() {
    Occurrence occ = new Occurrence();
    occ.setEventDate(null);
    assertNull(occ.getEventDate());
    occ.setDateIdentified(null);
    assertNull(occ.getDateIdentified());
    occ.setModified(null);
    assertNull(occ.getModified());
    occ.setLastInterpreted(null);
    assertNull(occ.getLastInterpreted());
  }

  @Test
  public void testProtocolAndPublishingCountry() {
    Occurrence occ = new Occurrence();
    occ.setKey(1L);
    occ.setDatasetKey(UUID.randomUUID());
    occ.setProtocol(EndpointType.BIOCASE);
    occ.setPublishingCountry(Country.AFGHANISTAN);

    assertEquals(EndpointType.BIOCASE, occ.getProtocol());
    assertEquals(Country.AFGHANISTAN, occ.getPublishingCountry());
  }

  @Test
  public void addIssues() {
    Occurrence occ = new Occurrence();
    assertTrue(occ.getIssues().isEmpty());
    assertFalse(occ.hasSpatialIssue());

    occ.addIssue(OccurrenceIssue.ELEVATION_NON_NUMERIC);
    assertEquals(1, occ.getIssues().size());
    assertFalse(occ.hasSpatialIssue());

    occ.addIssue(OccurrenceIssue.COORDINATE_OUT_OF_RANGE);
    assertEquals(2, occ.getIssues().size());
    assertTrue(occ.hasSpatialIssue());
  }

  @Test
  public void testJsonSerde() throws IOException {
    Occurrence occ = new Occurrence();
    occ.setFamily("Plants family");
    occ.setFamilyKey(16);
    occ.setKingdom("Plants");
    occ.setKingdomKey(6);
    occ.setCoordinateUncertaintyInMeters(25.5);

    occ.getVerbatimFields().put(DwcTerm.catalogNumber, "MD10782");
    occ.addIssue(OccurrenceIssue.COORDINATE_OUT_OF_RANGE);

    String json = mapper.writeValueAsString(occ);
    System.out.println(json);
    Occurrence occ2 = mapper.readValue(json, Occurrence.class);
    assertEquals(occ, occ2);
  }

  @Test
  public void testVerbatimConstructor() {
    VerbatimOccurrence verb = new VerbatimOccurrence();
    verb.setKey(123L);
    verb.setDatasetKey(UUID.randomUUID());
    verb.setPublishingOrgKey(UUID.randomUUID());
    verb.setPublishingCountry(Country.AFGHANISTAN);
    verb.setLastCrawled(new Date());
    verb.setProtocol(EndpointType.BIOCASE);
    for (Term term : DwcTerm.values()) {
      verb.setVerbatimField(term, "I am " + term);
    }

    Occurrence occ = new Occurrence(verb);
    assertEquals(occ.getKey(), verb.getKey());
    assertEquals(occ.getDatasetKey(), verb.getDatasetKey());
    assertEquals(occ.getPublishingOrgKey(), verb.getPublishingOrgKey());
    assertEquals(occ.getPublishingCountry(), verb.getPublishingCountry());
    assertEquals(occ.getProtocol(), verb.getProtocol());
    assertEquals(occ.getLastCrawled(), verb.getLastCrawled());
    assertEquals(occ.getVerbatimFields().size(), verb.getVerbatimFields().size());
    assertEquals(occ.getVerbatimField(DwcTerm.acceptedNameUsage), verb.getVerbatimField(DwcTerm.acceptedNameUsage));
  }

  @Test
  public void testBuildFromNullVerbatim() {
    Occurrence occ = new Occurrence(null);
    assertNotNull(occ);

    VerbatimOccurrence verb = new VerbatimOccurrence();
    occ = new Occurrence(verb);
    assertNotNull(occ);
  }

  @Test
  public void testVerbatimMapSerde() throws Exception {
    Occurrence o = new Occurrence();
    o.setKey(7L);
    o.setLastParsed(new Date());
    o.setDatasetKey(UUID.randomUUID());
    o.setCountry(Country.ALBANIA);
    o.setContinent(Continent.AFRICA);
    o.setBasisOfRecord(BasisOfRecord.OBSERVATION);
    o.setTaxonKey(212);
    o.setDay(21);
    o.setMonth(1);
    o.setYear(1973);
    o.setCoordinateAccuracy(0.002);

    char [][] alphabeticPairs = {{'a','z'},{'A','Z'}};
    RandomStringGenerator generatorAlphabetic = new RandomStringGenerator.Builder()
        .withinRange(alphabeticPairs)
        .build();

    for (DwcTerm term : DwcTerm.values()) {
      if (!term.isClass()) {
        o.setVerbatimField(term, generatorAlphabetic.generate(20));
      }
    }

    for (DcTerm term : DcTerm.values()) {
      if (!term.isClass()) {
        o.setVerbatimField(term, generatorAlphabetic.generate(20));
      }
    }

    for (GbifTerm term : GbifTerm.values()) {
      if (!term.isClass()) {
        o.setVerbatimField(term, generatorAlphabetic.generate(20));
      }
    }

    for (IucnTerm term : IucnTerm.values()) {
      if (!term.isClass()) {
        o.setVerbatimField(term, generatorAlphabetic.generate(20));
      }
    }

    o.setVerbatimField(DwcTerm.scientificName, "Abies alba");
    o.setVerbatimField(DwcTerm.collectionCode, "BUGS");
    o.setVerbatimField(DwcTerm.catalogNumber, "MD10782");
    o.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/temperatur"), generatorAlphabetic.generate(30));
    o.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/co2"), generatorAlphabetic.generate(30));
    o.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/modified"), new Date().toString());
    o.setVerbatimField(UnknownTerm.build("http://rs.un.org/terms/scientificName"),
        generatorAlphabetic.generate(30));

    String json = mapper.writeValueAsString(o);
    System.out.println(json);

    Occurrence o2 = mapper.readValue(json, Occurrence.class);

    Set<Term> diff = new HashSet<>(o.getVerbatimFields().keySet());
    diff.removeAll(o2.getVerbatimFields().keySet());

    for (Term t : diff) {
      System.out.println("TERM DIFF: " + t.qualifiedName());
    }

    assertNull(o2.getVerbatimField(TermFactory.instance().findTerm("coordinateAccuracyInMeters")));
    assertEquals(o.getVerbatimFields().size() - 1, o2.getVerbatimFields().size());
    assertEquals(1, diff.size());
    assertTrue(diff.contains(GbifTerm.verbatimScientificName));
  }

  /**
   * checks that countryCode, geodeticDatum and class are nicely exposed in json
   */
  @Test
  public void testCustomSerlializations() throws Exception {
    Occurrence o = new Occurrence();
    o.setKey(7L);
    o.setCountry(Country.ALGERIA);
    o.setClassKey(999);
    o.setClazz("Insecta");
    o.setVerbatimField(DwcTerm.recordedBy, "Sankt Nikolaus");

    String json = mapper.writeValueAsString(o);
    System.out.println(json);
    assertTrue(json.contains("\"class\" : \"Insecta\""));
    assertTrue(json.contains("\"countryCode\" : \"DZ\""));
    assertTrue(json.contains("\"country\" : \"Algeria\""));
    assertTrue(json.contains("\"gbifRegion\" : \"AFRICA\""));

    Occurrence o2 = mapper.readValue(json, Occurrence.class);

    assertEquals(Country.ALGERIA, o2.getCountry());
    assertEquals(GbifRegion.AFRICA, o2.getGbifRegion());
    assertEquals("Insecta", o2.getClazz());
    assertNull(o2.getGeodeticDatum());

    // now add coords, datum shows
    o.setDecimalLatitude(12d);
    o.setDecimalLongitude(23d);
    json = mapper.writeValueAsString(o);
    System.out.println(json);
    assertTrue(json.contains("\"geodeticDatum\" : \"WGS84\""));

    o2 = mapper.readValue(json, Occurrence.class);
    assertEquals("WGS84", o2.getGeodeticDatum());

    Set<Term> diff = new HashSet<>(o.getVerbatimFields().keySet());
    diff.removeAll(o2.getVerbatimFields().keySet());

    for (Term t : diff) {
      System.out.println(t.qualifiedName());
    }

    assertEquals(o.getVerbatimFields().size(), o2.getVerbatimFields().size());
    assertTrue(diff.isEmpty());
  }

  /**
   * checks that countryCode, geodeticDatum and class are nicely exposed in json
   */
  @Test
  public void testExtensionsSerializations() throws Exception {
    Occurrence o = new Occurrence();
    o.setKey(7L);
    o.setCountry(Country.ALGERIA);
    o.setClassKey(999);
    o.setClazz("Insecta");
    o.setVerbatimField(DwcTerm.recordedBy, "Sankt Nikolaus");
    MediaObject mediaObject = new MediaObject();
    mediaObject.setCreated(new Date());
    mediaObject.setCreator("fede");
    mediaObject.setFormat("jpeg");
    mediaObject.setLicense("testLicense");
    mediaObject.setPublisher("testPublisher");
    mediaObject.setReferences(new URI("http://www.gbif.org"));
    mediaObject.setTitle("mediaTestTitle");
    mediaObject.setType(MediaType.StillImage);
    mediaObject.setIdentifier(new URI("http://www.gbif.org"));
    List<MediaObject> medias = new ArrayList<>();
    medias.add(mediaObject);
    o.setMedia(medias);
    String json = mapper.writeValueAsString(o);
    System.out.println(json);
    assertTrue(json.contains("\"country\" : \"Algeria\""));

    Occurrence o2 = mapper.readValue(json, Occurrence.class);

    assertEquals(o2.getMedia().get(0), mediaObject);
  }
}
