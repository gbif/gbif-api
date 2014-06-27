/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.registry.eml;

import java.io.Serializable;
import java.net.URI;

import com.google.common.base.Objects;


/**
 * Describes the format of externally available data on a URL.
 */
public class DataDescription implements Serializable {

  private static final long serialVersionUID = 6872006284909021347L;

  private String name;
  private String charset;
  private URI url;
  private String format;
  private String formatVersion;

  public DataDescription() {
  }

  public DataDescription(String name, String charset, URI url, String format, String formatVersion) {
    this.name = name;
    this.charset = charset;
    this.url = url;
    this.format = format;
    this.formatVersion = formatVersion;
  }

  /**
   * The name of the character encoding. This is typically ASCII or UTF-8, or one of the other common encodings.
   *
   * @return the character encoding
   */
  public String getCharset() {
    return charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  /**
   * The name of the format of the data object, e.g., Microsoft Excel.
   *
   * @return the data format
   */
  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  /**
   * The version of the format of the data object, e.g., 2000 (9.0.2720).
   *
   * @return the data format version
   */
  public String getFormatVersion() {
    return formatVersion;
  }

  public void setFormatVersion(String formatVersion) {
    this.formatVersion = formatVersion;
  }

  /**
   * The name representing the data object being described.
   *
   * @return the data object name
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * The URL to download the resource in the mentioned format.
   *
   * @return the download URL
   */
  public URI getUrl() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DataDescription)) {
      return false;
    }

    DataDescription that = (DataDescription) obj;
    return Objects.equal(this.name, that.name)
           && Objects.equal(this.charset, that.charset)
           && Objects.equal(this.url, that.url)
           && Objects.equal(this.format, that.format)
           && Objects.equal(this.formatVersion, that.formatVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, charset, url, format, formatVersion);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("name", name)
      .add("charset", charset)
      .add("url", url)
      .add("format", format)
      .add("formatVersion", formatVersion)
      .toString();
  }

}
