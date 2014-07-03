/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.registry;

import org.gbif.api.vocabulary.Country;

import java.net.URI;
import java.util.List;
import javax.annotation.Nullable;
import javax.validation.constraints.Size;

/**
 * A package visible providing the commonality for addresses, including the constraint validations.
 */
public interface Address {

  /**
   * electronicMailAddress in EML
   */
  List<String> getEmail();

  void setEmail(List<String> email);

  /**
   * phone in EML
   */
  List<String> getPhone();

  void setPhone(List<String> phone);

  /**
   * deliveryPoint in EML
   */
  List<String> getAddress();

  void setAddress(List<String> address);

  /**
   * city in EML
   */
  @Nullable
  @Size(min = 1)
  String getCity();

  void setCity(String city);

  /**
   * administrativeArea in EML
   */
  @Nullable
  @Size(min = 1)
  String getProvince();

  void setProvince(String province);

  /**
   * country in EML
   */
  @Nullable
  Country getCountry();

  void setCountry(Country country);

  /**
   * postalCode in EML
   */
  @Nullable
  @Size(min = 1)
  String getPostalCode();

  void setPostalCode(String postalCode);

  /**
   * Institution name as part of the address
   */
  @Nullable
  @Size(min = 2)
  String getOrganization();

  void setOrganization(String organization);

  /**
   * List of homepage websites.
   */
  List<URI> getHomepage();

  void setHomepage(List<URI> homepage);
}
