package org.gbif.api.model.registry.view;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import org.gbif.api.model.registry.Contact;
import org.gbif.api.vocabulary.Country;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationContactView {

  @JsonUnwrapped private Contact contact;

  private UUID organizationKey;
  private Country organizationCountry;
  private String organizationTitle;
}
