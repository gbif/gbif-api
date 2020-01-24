package org.gbif.api.model.collections;

import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.vocabulary.Country;

import java.util.Objects;
import java.util.StringJoiner;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * The particulars of the place where a institution of collection is situated.
 */
public class Address {

  private Integer key;
  private String address;
  private String city;
  private String province;
  private String postalCode;
  private Country country;

  /**
   * Unique identifier, assigned by the persistence store.
   */
  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * Textual direction of this address.
   */
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * City where this address is located.
   */
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Province, region or area where this address is located.
   */
  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  /**
   * International postal code of this address.
   */
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * Country where this address is located.
   */
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
    return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]").add("key=" + key)
      .add("address='" + address + "'")
      .add("city='" + city + "'")
      .add("province='" + province + "'")
      .add("postalCode='" + postalCode + "'")
      .add("country=" + country)
      .toString();
  }
}
