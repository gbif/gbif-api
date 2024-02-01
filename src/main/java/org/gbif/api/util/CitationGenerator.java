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

import org.gbif.api.model.registry.Citation;
import org.gbif.api.model.registry.CitationContact;
import org.gbif.api.model.registry.Contact;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.model.registry.Endpoint;
import org.gbif.api.model.registry.Organization;
import org.gbif.api.vocabulary.ContactType;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Data;
import org.gbif.api.vocabulary.EndpointType;

/**
 * Helper class tha generates a Citation String from {@link Dataset} and {@link Organization}
 * objects. Documentation : /docs/citations.md
 */
public final class CitationGenerator {

  private static final ZoneId UTC = ZoneId.of("UTC");
  private static final ContactType MANDATORY_CONTACT_TYPE = ContactType.ORIGINATOR;
  private static final EnumSet<ContactType> AUTHOR_CONTACT_TYPE =
      EnumSet.of(ContactType.ORIGINATOR, ContactType.METADATA_AUTHOR);
  private static final EnumSet<ContactType> CAMTRAP_CITATION_CONTACT_TYPE =
      EnumSet.of(ContactType.POINT_OF_CONTACT, ContactType.PRINCIPAL_INVESTIGATOR, ContactType.CONTENT_PROVIDER);
  private static final Predicate<Contact> IS_NAME_PROVIDED_FCT =
      ctc -> StringUtils.isNotBlank(ctc.getLastName());
  private static final Predicate<CitationContact> IS_CONTACT_NAME_PROVIDED =
      ctc -> StringUtils.isNotBlank(ctc.getLastName());
  private static final Predicate<Contact> IS_ELIGIBLE_CONTACT_TYPE =
      ctc -> AUTHOR_CONTACT_TYPE.contains(ctc.getType());
  private static final Predicate<Contact> IS_ELIGIBLE_CAMTRAP_CONTACT_TYPE =
      ctc -> CAMTRAP_CITATION_CONTACT_TYPE.contains(ctc.getType());

  /** Utility class */
  private CitationGenerator() {}

  @SuppressWarnings("unused")
  public static CitationData generateCitation(Dataset dataset, Organization org) {
    Objects.requireNonNull(org, "Organization shall be provided");
    return generateCitation(dataset, org.getTitle());
  }

  /**
   * Generate a citation for a {@link Dataset} using the publisher's provided citation.
   * @param dataset dataset
   * @return generated citation as {@link String}
   */
  @SuppressWarnings("unused")
  public static String generatePublisherProvidedCitation(Dataset dataset) {
    Objects.requireNonNull(dataset, "Dataset shall be provided");
    Objects.requireNonNull(dataset.getCitation(), "Dataset.citation shall be provided");
    String originalCitationText = dataset.getCitation().getText();
    Objects.requireNonNull(originalCitationText, "Dataset.citation.text shall be provided");

    StringJoiner joiner = new StringJoiner(" ");

    joiner.add(originalCitationText);

    // Check DOI exists, and append it if it doesn't.
    if (!originalCitationText.toLowerCase().contains("doi.org")
        && !originalCitationText.toLowerCase().contains("doi:")) {
      try {
        joiner.add(URLDecoder.decode(dataset.getDoi().getUrl().toString(), "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        throw new IllegalArgumentException("Couldn't decode DOI URL", e);
      }
    }

    joiner.add("accessed via GBIF.org on " + LocalDate.now(UTC) + ".");

    return joiner.toString();
  }

  /**
   * Generate a citation for a {@link Dataset} and its {@link Organization}. TODO add support for
   * i18n
   * @return generated citation as {@link String}
   */
  public static CitationData generateCitation(Dataset dataset, String organizationTitle) {

    Objects.requireNonNull(dataset, "Dataset shall be provided");
    Objects.requireNonNull(organizationTitle, "Organization title shall be provided");

    Citation citation = new Citation();

    List<CitationContact> contacts = getAuthors(dataset);

    StringJoiner joiner = new StringJoiner(" ");
    List<String> authorsName = generateAuthorsName(contacts);
    String authors = String.join(", ", authorsName);

    boolean authorsNameAvailable = StringUtils.isNotBlank(authors);
    authors = authorsNameAvailable ? authors : organizationTitle;

    // only add a dot if we are not going to add it with the year
    authors += dataset.getPubDate() == null ? "." : "";
    joiner.add(authors);

    if (dataset.getPubDate() != null) {
      joiner.add("(" + dataset.getPubDate().toInstant().atZone(UTC).getYear() + ").");
    }

    // add title
    joiner.add(StringUtils.trim(dataset.getTitle()) + ".");

    // add version
    if (dataset.getVersion() != null) {
      joiner.add("Version " + dataset.getVersion() + ".");
    }

    // add publisher except if it was used instead of the authors
    if (authorsNameAvailable) {
      joiner.add(StringUtils.trim(organizationTitle) + ".");
    }

    if (dataset.getType() != null) {
      joiner.add(StringUtils.capitalize(dataset.getType().name().replace('_', ' ').toLowerCase()));
    }
    joiner.add("dataset");

    // add DOI as the identifier.
    if (dataset.getDoi() != null) {
      try {
        joiner.add(
            URLDecoder.decode(dataset.getDoi().getUrl().toString(), StandardCharsets.UTF_8.name()));
      } catch (UnsupportedEncodingException e) {
        throw new IllegalArgumentException("Couldn't decode DOI URL", e);
      }
    }

    joiner.add("accessed via GBIF.org on " + LocalDate.now(UTC) + ".");

    citation.setText(joiner.toString());
    citation.setCitationProvidedBySource(false);

    return CitationData.builder().citation(citation).contacts(contacts).build();
  }

  public static List<CitationContact> getAuthors(Dataset dataset) {
    boolean isCamtrap = dataset.getEndpoints().stream()
        .map(Endpoint::getType)
        .anyMatch(e -> e == EndpointType.CAMTRAP_DP);

    return isCamtrap ? getAuthorsForCamtrap(dataset.getContacts()) : getAuthors(dataset.getContacts());
  }

  public static List<CitationContact> getAuthorsForCamtrap(List<Contact> contacts) {
    if (contacts == null || contacts.isEmpty()) {
      return Collections.emptyList();
    }

    return getUniqueAuthors(
        contacts, ctc -> IS_NAME_PROVIDED_FCT.and(IS_ELIGIBLE_CAMTRAP_CONTACT_TYPE).test(ctc));
  }

  /**
   * Extracts an ordered list of unique authors from a list of contacts. A {@link Contact} is
   * identified as an author when his {@link ContactType} is contained in {@link
   * #AUTHOR_CONTACT_TYPE}. But, we shall at least have one contact of type MANDATORY_CONTACT_TYPE.
   *
   * @param contacts list of contacts available
   * @return ordered list of authors or empty list, never null
   */
  public static List<CitationContact> getAuthors(List<Contact> contacts) {
    if (contacts == null || contacts.isEmpty()) {
      return Collections.emptyList();
    }

    List<CitationContact> uniqueContacts =
        getUniqueAuthors(
            contacts, ctc -> IS_NAME_PROVIDED_FCT.and(IS_ELIGIBLE_CONTACT_TYPE).test(ctc));

    // make sure we have at least one instance of {@link #MANDATORY_CONTACT_TYPE}
    Optional<CitationContact> firstOriginator =
        uniqueContacts.stream()
            .filter(ctc -> ctc.getRoles().contains(MANDATORY_CONTACT_TYPE))
            .findFirst();

    if (firstOriginator.isPresent()) {
      return uniqueContacts;
    }
    return Collections.emptyList();
  }

  /**
   * Given a list of authors, generates a {@link List} of {@link String} representing the authors
   * name. If a contact doesn't have a first AND last name it will not be included.
   *
   * @param authors ordered list of authors
   * @return list of author names (if it can be generated) or empty list, never null
   */
  public static List<String> generateAuthorsName(List<CitationContact> authors) {
    if (authors == null || authors.isEmpty()) {
      return Collections.emptyList();
    }

    return authors.stream()
        .filter(IS_CONTACT_NAME_PROVIDED)
        .map(CitationContact::getAbbreviatedName)
        .collect(Collectors.toList());
  }

  /**
   * This method is used to get the list of "unique" authors. Currently, uniqueness is based on
   * lastName + firstNames. The order of the provided list will be preserved which also means the
   * first {@link ContactType} found for a contact is the one that will be used for this contact
   * (after applying the filter).
   *
   * @param authors a list of contacts representing possible authors
   * @param filter {@link Predicate} used to pre-filter contacts
   * @return list of contacts
   */
  private static List<CitationContact> getUniqueAuthors(
      List<Contact> authors, Predicate<Contact> filter) {
    List<CitationContact> uniqueContact = new ArrayList<>();
    if (authors != null) {
      authors.forEach(
          ctc -> {
            if (filter.test(ctc)) {
              Optional<CitationContact> author = findInAuthorList(ctc, uniqueContact);
              if (!author.isPresent()) {
                HashSet<ContactType> contactTypes = new HashSet<>();
                if (ctc.getType() != null) {
                  contactTypes.add(ctc.getType());
                }
                HashSet<String> userIds = new HashSet<>();
                if (ctc.getUserId() != null && !ctc.getUserId().isEmpty()) {
                  userIds.addAll(ctc.getUserId());
                }
                uniqueContact.add(
                    new CitationContact(
                        ctc.getKey(),
                        getAuthorName(ctc),
                        ctc.getFirstName(),
                        ctc.getLastName(),
                        contactTypes,
                        userIds));
              } else {
                author.ifPresent(
                    a -> {
                      a.getRoles().add(ctc.getType());
                      if (ctc.getUserId() != null) {
                        a.getUserId().addAll(ctc.getUserId());
                      }
                    });
              }
            }
          });
    }
    return uniqueContact;
  }

  /**
   * Check if a specific {@link Contact} is NOT already in the list of "unique" contact. Currently,
   * uniqueness is based on the comparisons of lastName and firstNames.
   *
   * @param ctc contact to check
   * @param uniqueContacts list of contacts
   * @return contact wrapped in optional
   */
  private static Optional<CitationContact> findInAuthorList(
      Contact ctc, List<CitationContact> uniqueContacts) {
    return uniqueContacts.stream()
        .filter(
            author ->
                StringUtils.equalsIgnoreCase(ctc.getLastName(), author.getLastName())
                    && StringUtils.equalsIgnoreCase(ctc.getFirstName(), author.getFirstName()))
        .findFirst();
  }

  /**
   * Given a {@link Contact}, generates a String for that contact for citation purpose. The
   * organization will be used (if present) in case we don't have both lastName and firstNames of
   * the contact.
   *
   * @param creator contact object
   * @return name
   */
  public static String getAuthorName(Contact creator) {
    StringBuilder sb = new StringBuilder();
    String lastName = StringUtils.trimToNull(creator.getLastName());
    String firstNames = StringUtils.trimToNull(creator.getFirstName());
    String organization = StringUtils.trimToNull(creator.getOrganization());

    if (lastName != null && firstNames != null) {
      sb.append(lastName);
      sb.append(" ");
      // add first initial of each first name, capitalized
      String[] names = firstNames.split("\\s+");

      sb.append(
          Arrays.stream(names)
              .filter(str -> !StringUtils.isBlank(str))
              .map(str -> StringUtils.upperCase(String.valueOf(str.charAt(0))))
              .collect(Collectors.joining(" ")));
    } else if (lastName != null) {
      sb.append(lastName);
    } else if (organization != null) {
      sb.append(organization);
    }
    return sb.toString();
  }

  @Data
  @Builder
  public static class CitationData {

    private final Citation citation;
    private final List<CitationContact> contacts;
  }
}
