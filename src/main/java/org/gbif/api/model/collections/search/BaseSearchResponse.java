package org.gbif.api.model.collections.search;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

import org.gbif.api.model.collections.AlternativeCode;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.collections.MasterSourceType;

@Data
public abstract class BaseSearchResponse {

  private UUID key;
  private String code;
  private String name;
  private List<AlternativeCode> alternativeCodes = new ArrayList<>();
  private String description;
  private Boolean active;
  private Boolean displayOnNHCPortal;
  private Country country;
  private Country mailingCountry;
  private String city;
  private String mailingCity;
  private String temporalCoverage;
  private License featuredImageLicense;
  private URI featuredImageUrl;
  private String featuredImageAttribution;
  private MasterSourceType masterSource;
  private Set<Highlight> highlights = new HashSet<>();
}
