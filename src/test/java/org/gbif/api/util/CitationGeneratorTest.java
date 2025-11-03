/*
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
package org.gbif.api.util;

import org.gbif.api.model.common.DOI;
import org.gbif.api.model.registry.CitationContact;
import org.gbif.api.model.registry.Contact;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.model.registry.Endpoint;
import org.gbif.api.model.registry.Organization;
import org.gbif.api.vocabulary.ContactType;
import org.gbif.api.vocabulary.DatasetType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.gbif.api.vocabulary.EndpointType;
import org.junit.jupiter.api.Test;

import static org.gbif.api.model.common.DOI.TEST_PREFIX;
import static org.gbif.api.util.CitationGenerator.getAuthors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests related to {@link CitationGenerator}. */
public class CitationGeneratorTest {

  @Test
  public void testCamtrapCitation() {
    Organization org = new Organization();
    org.setTitle("Research Institute for Nature and Forest (INBO)");

    Dataset dataset = getCamtrapDataset();
    dataset.getContacts().add(createContact("Jim", "Casaer", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Niko", "Boone", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Jan", "Vercammen", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Sander", "Devisscher", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Lynn", "Pallemaerts", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Anneleen", "Rutten", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Martijn", "Bollen", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Peter", "Desmet", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Sanne", "Govaert", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Jim", "Casaer", ContactType.METADATA_AUTHOR));
    dataset.getContacts().add(createContact("Jim", "Casaer", ContactType.ADMINISTRATIVE_POINT_OF_CONTACT));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    String expectedCitation = "Casaer J, Boone N, Vercammen J, Devisscher S, Pallemaerts L, Rutten A, Bollen M, "
        + "Desmet P, Govaert S (2025). GMU8_LEUVEN - Camera trap observations in natural habitats south of Leuven "
        + "(Belgium). Research Institute for Nature and Forest (INBO). "
        + "Occurrence dataset https://doi.org/10.15468/4u3wm4 accessed via GBIF.org on "
        + LocalDate.now(ZoneId.of("UTC"))
        + ".";

    assertEquals(expectedCitation, citation.getCitation().getText());
  }

  @Test
  public void testAuthorNames() {
    Contact c = new Contact();
    c.setLastName("Doe");
    c.setFirstName("John D.");
    assertEquals("Doe J D", CitationGenerator.getAuthorName(c));
    assertEquals(0, getAuthors(Collections.singletonList(c)).size());

    // test with missing first name
    c = new Contact();
    c.setLastName("Doe");
    c.setOrganization("Awesome Organization");
    assertEquals("Doe", CitationGenerator.getAuthorName(c));
    assertEquals(0, getAuthors(Collections.singletonList(c)).size());

    // test with missing parts
    c = new Contact();
    c.setFirstName("John");
    c.setOrganization("Awesome Organization");
    assertEquals("Awesome Organization", CitationGenerator.getAuthorName(c));
    assertEquals(0, getAuthors(Collections.singletonList(c)).size());
  }

  @Test
  public void testCompleteCitation() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");

    Dataset dataset = getTestDatasetObject();
    dataset.getContacts().add(createContact("John D.", "Doe", ContactType.ORIGINATOR));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    assertEquals(
        "Doe J D (2009). Dataset to be cited. Version 2.1. Cited Organization. "
            + "Sampling event dataset https://doi.org/10.21373/abcd accessed via GBIF.org on "
            + LocalDate.now(ZoneId.of("UTC"))
            + ".",
        citation.getCitation().getText());
    assertEquals(1, citation.getContacts().size());
  }

  @Test
  public void testCompleteCitationUserWithoutName() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");

    Dataset dataset = getTestDatasetObject();
    dataset.getContacts().add(createContact("John D.", "Doe", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("  ", "Smith", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("John", null, ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact(null, "Mendez", ContactType.ORIGINATOR));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    assertEquals(
        "Doe J D, Smith, Mendez (2009). Dataset to be cited. Version 2.1. Cited Organization. "
            + "Sampling event dataset https://doi.org/10.21373/abcd accessed via GBIF.org on "
            + LocalDate.now(ZoneId.of("UTC"))
            + ".",
        citation.getCitation().getText());

    assertEquals(3, citation.getContacts().size());
  }

  @Test
  public void testCompleteCitationNoAuthors() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");

    Dataset dataset = getTestDatasetObject();
    dataset
        .getContacts()
        .add(
            createContact(
                null,
                null,
                "We are not using this field int the citation",
                ContactType.ORIGINATOR));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    assertEquals(
        "Cited Organization (2009). Dataset to be cited. Version 2.1. "
            + "Sampling event dataset https://doi.org/10.21373/abcd accessed via GBIF.org on "
            + LocalDate.now(ZoneId.of("UTC"))
            + ".",
        citation.getCitation().getText());

    assertEquals(0, citation.getContacts().size());
  }

  @Test
  public void testCompleteCitationNoYear() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");

    Dataset dataset = getTestDatasetObject();
    dataset.setPubDate(null);
    dataset.getContacts().add(createContact("John", "Doe", ContactType.ORIGINATOR));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    assertEquals(
        "Doe J. Dataset to be cited. Version 2.1. Cited Organization. "
            + "Sampling event dataset https://doi.org/10.21373/abcd accessed via GBIF.org on "
            + LocalDate.now(ZoneId.of("UTC"))
            + ".",
        citation.getCitation().getText());

    assertEquals(1, citation.getContacts().size());
  }

  @Test
  public void testCompleteCitationAuthorMultipleRoles() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");

    Dataset dataset = getTestDatasetObject();

    dataset.getContacts().add(createContact("John D.", "Doe", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("Jim", "Carey", ContactType.PROGRAMMER));
    dataset.getContacts().add(createContact("John D.", "Doe", ContactType.METADATA_AUTHOR));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    assertEquals(
        "Doe J D (2009). Dataset to be cited. Version 2.1. Cited Organization. "
            + "Sampling event dataset https://doi.org/10.21373/abcd accessed via GBIF.org on "
            + LocalDate.now(ZoneId.of("UTC"))
            + ".",
        citation.getCitation().getText());

    assertEquals(1, citation.getContacts().size());
  }

  @Test
  public void testCompleteCitationCamtrapAuthorMultipleRoles() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");

    Dataset dataset = getTestCamtrapOccurrenceDatasetObject();

    dataset.getContacts().add(createContact("Tim", "Robertson", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("John D.", "Doe", ContactType.POINT_OF_CONTACT));
    dataset.getContacts().add(createContact("Jim", "Carey", ContactType.PRINCIPAL_INVESTIGATOR));
    dataset.getContacts().add(createContact("Jack", "White", ContactType.CONTENT_PROVIDER));
    dataset.getContacts().add(createContact("", "Rights Holder", ContactType.OWNER));
    dataset.getContacts().add(createContact("", "Publisher", ContactType.DISTRIBUTOR));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    assertEquals(
        "Robertson T (2009). Dataset to be cited. Version 2.1. Cited Organization. "
            + "Occurrence dataset https://doi.org/10.21373/abcd accessed via GBIF.org on "
            + LocalDate.now(ZoneId.of("UTC"))
            + ".",
        citation.getCitation().getText());

    assertEquals(1, citation.getContacts().size());
  }

  @Test
  public void testCompleteCitationNoOriginator() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");
    Dataset dataset = getTestDatasetObject();
    dataset.getContacts().add(createContact("John D.", "Doe", ContactType.METADATA_AUTHOR));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    assertEquals(
        "Cited Organization (2009). Dataset to be cited. Version 2.1. "
            + "Sampling event dataset https://doi.org/10.21373/abcd accessed via GBIF.org on "
            + LocalDate.now(ZoneId.of("UTC"))
            + ".",
        citation.getCitation().getText());
    assertEquals(0, citation.getContacts().size());
  }

  @Test
  public void testCompleteCitationOriginatorNoName() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");
    Dataset dataset = getTestDatasetObject();

    dataset.getContacts().add(createContact(null, null, "Test Org.", ContactType.ORIGINATOR));
    dataset.getContacts().add(createContact("John D.", "Doe", ContactType.METADATA_AUTHOR));

    CitationGenerator.CitationData citation = CitationGenerator.generateCitation(dataset, org);

    assertEquals(
        "Cited Organization (2009). Dataset to be cited. Version 2.1. "
            + "Sampling event dataset https://doi.org/10.21373/abcd accessed via GBIF.org on "
            + LocalDate.now(ZoneId.of("UTC"))
            + ".",
        citation.getCitation().getText());

    assertEquals(0, citation.getContacts().size());
  }

  @Test
  public void testAuthors() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");

    Dataset dataset = getTestDatasetObject();

    dataset.getContacts().add(createContact("John D.", "Doe", ContactType.ORIGINATOR));
    dataset
        .getContacts()
        .add(createContact("John D.", "Doe", "Awesome Organization", ContactType.ORIGINATOR));
    // author with incomplete name
    dataset.getContacts().add(createContact("Programmer", "Last", ContactType.PROGRAMMER));

    // we expect 1 author since the names (first and last) are mandatory
    assertEquals(1, getAuthors(dataset.getContacts()).size());

    // but, we can only generate the name for one of them
    assertEquals(
        1, CitationGenerator.generateAuthorsName(getAuthors(dataset.getContacts())).size());
  }

  @Test
  public void testRepeatedAuthor() {
    Organization org = new Organization();
    org.setTitle("Cited Organization");

    Dataset dataset = getTestDatasetObject();
    Contact contact1 = createContact("John D.", "Doe", ContactType.ORIGINATOR);
    contact1.setUserId(Collections.singletonList("user1"));

    Contact contact2 = createContact("John D.", "Doe", ContactType.METADATA_AUTHOR);
    contact2.setUserId(Arrays.asList("user1", "user2"));

    dataset.getContacts().add(contact1);
    dataset.getContacts().add(contact2);

    List<CitationContact> authors = getAuthors(dataset.getContacts());

    // Only one author added
    assertEquals(1, authors.size());

    // The authors keep the 2 roles
    assertTrue(
        authors
            .get(0)
            .getRoles()
            .containsAll(EnumSet.of(ContactType.ORIGINATOR, ContactType.METADATA_AUTHOR)));

    Set<String> firstAuthorUserId = authors.get(0).getUserId();
    assertNotNull(firstAuthorUserId);

    // The author has 2 users
    assertTrue(firstAuthorUserId.containsAll(Arrays.asList("user1", "user2")));

    // Repeated user is not added twice
    assertEquals(2, firstAuthorUserId.size());

    // we can only generate the name for one of them
    assertEquals(
        1, CitationGenerator.generateAuthorsName(getAuthors(dataset.getContacts())).size());
  }

  private Dataset getCamtrapDataset() {
    Dataset dataset = new Dataset();
    dataset.setTitle("GMU8_LEUVEN - Camera trap observations in natural habitats south of Leuven (Belgium)");
    dataset.setDoi(new DOI("10.15468/4u3wm4"));
    dataset.setType(DatasetType.OCCURRENCE);
    dataset.setPubDate(
        new Date(
            LocalDate.of(2025, 9, 25).atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()));

    dataset.setPublishingOrganizationKey(UUID.fromString("1cd669d0-80ea-11de-a9d0-f1765f95f18b"));

    return dataset;
  }

  private Dataset getTestDatasetObject() {
    Dataset dataset = new Dataset();
    dataset.setTitle("Dataset to be cited");
    dataset.setVersion("2.1");
    dataset.setDoi(new DOI(TEST_PREFIX + "/abcd"));
    dataset.setPubDate(
        new Date(
            LocalDate.of(2009, 2, 8).atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()));

    dataset.setType(DatasetType.SAMPLING_EVENT);

    return dataset;
  }

  private Dataset getTestCamtrapOccurrenceDatasetObject() {
    Dataset dataset = new Dataset();
    dataset.setTitle("Dataset to be cited");
    dataset.setVersion("2.1");
    dataset.setDoi(new DOI(TEST_PREFIX + "/abcd"));
    dataset.setPubDate(
        new Date(
            LocalDate.of(2009, 2, 8).atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()));

    dataset.setType(DatasetType.OCCURRENCE);

    Endpoint camtrapEndpoint = new Endpoint();
    camtrapEndpoint.setType(EndpointType.CAMTRAP_DP);
    dataset.addEndpoint(camtrapEndpoint);

    return dataset;
  }

  private Contact createContact(String firstName, String lastName, ContactType ct) {
    return createContact(firstName, lastName, null, ct);
  }

  private Contact createContact(
      String firstName, String lastName, String organization, ContactType ct) {
    Contact c = new Contact();
    c.setFirstName(firstName);
    c.setLastName(lastName);
    c.setOrganization(organization);
    c.setType(ct);
    return c;
  }
}
