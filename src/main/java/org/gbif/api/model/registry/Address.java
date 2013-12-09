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

import javax.annotation.Nullable;
import javax.validation.constraints.Size;

/**
 * A package visible providing the commonality for addresses, including the constraint validations.
 */
public interface Address {

  @Nullable
  @Size(min = 5)
  String getEmail();

  void setEmail(String email);

  @Nullable
  @Size(min = 5)
  String getPhone();

  void setPhone(String phone);

  @Nullable
  @Size(min = 1)
  String getAddress();

  void setAddress(String address);

  @Nullable
  @Size(min = 1)
  String getCity();

  void setCity(String city);

  @Nullable
  @Size(min = 1)
  String getProvince();

  void setProvince(String province);

  @Nullable
  Country getCountry();

  void setCountry(Country country);

  @Nullable
  @Size(min = 1)
  String getPostalCode();

  void setPostalCode(String postalCode);

}
