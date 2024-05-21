package org.gbif.api.model.collections.latimercore;

import org.gbif.api.vocabulary.Country;

import lombok.Data;

@Data
public class Address {

  private String addressType;
  private String streetAddress;
  private String postalCode;
  private String addressLocality;
  private String addressRegion;
  private Country addressCountry;
}
