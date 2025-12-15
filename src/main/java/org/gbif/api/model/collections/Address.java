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
package org.gbif.api.model.collections;

import org.gbif.api.model.registry.LenientEquals;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.vocabulary.Country;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;

/** The particulars of the place where a institution of collection is situated. */
@SuppressWarnings("unused")
public class Address implements Serializable, LenientEquals<Address> {

  private Integer key;
  private String address;
  private String city;
  private String province;
  private String postalCode;
  private Country country;

  /** Unique identifier, assigned by the persistence store. */
  @Null(groups = {PrePersist.class})
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  /** Textual direction of this address. */
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  /** City where this address is located. */
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  /** Province, region or area where this address is located. */
  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  /** International postal code of this address. */
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /** Country where this address is located. */
  @Nullable
  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address1 = (Address) o;
    return Objects.equals(key, address1.key)
        && Objects.equals(address, address1.address)
        && Objects.equals(city, address1.city)
        && Objects.equals(province, address1.province)
        && Objects.equals(postalCode, address1.postalCode)
        && country == address1.country;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, address, city, province, postalCode, country);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("address='" + address + "'")
        .add("city='" + city + "'")
        .add("province='" + province + "'")
        .add("postalCode='" + postalCode + "'")
        .add("country=" + country)
        .toString();
  }

  @Override
  public boolean lenientEquals(Address other) {
    if (this == other) {
      return true;
    }
    if (other == null) return false;
    return Objects.equals(address, other.address)
        && Objects.equals(city, other.city)
        && Objects.equals(province, other.province)
        && Objects.equals(postalCode, other.postalCode)
        && country == other.country;
  }
}
