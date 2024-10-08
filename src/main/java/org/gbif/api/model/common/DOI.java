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
package org.gbif.api.model.common;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static org.gbif.api.util.PreconditionUtils.checkArgument;

/**
 * Class representing a single Digital Object Identifier (DOI) breaking it down to a prefix and suffix.
 * For the syntax of DOI names see the <a href="http://www.doi.org/doi_handbook/2_Numbering.html#2.2">DOI Handbook</a>.
 * All parsing is case-insensitive and resulting components will all be uppercased.
 */
@Schema(
  description = "A Digital Object Identifier (DOI).",
  pattern = "(10[.][0-9]{2,}(?:[.][0-9]+)*/(?:(?![%\"#? ])\\\\S)+)",
  example = "10.15468/igasai",
  implementation = String.class
)
@JsonSerialize(using = DOI.DoiSerializer.class)
@JsonDeserialize(using = DOI.DoiDeserializer.class)
public class DOI implements Serializable {

  private static final Logger LOG = LoggerFactory.getLogger(DOI.class);

  /**
   * Encoding to create URLs.
   */
  private static final String CHAR_ENCODING = "UTF-8";

  /**
   * The DOI prefix registered with DataCite to be used by GBIF-issued production DOIs.
   */
  public static final String GBIF_PREFIX = "10.15468";

  /**
   * A DOI prefix provided by DataCite to be used in tests.
   */
  public static final String TEST_PREFIX = "10.21373";

  private static final Pattern HTTP = Pattern.compile("^https?://(dx\\.)?doi\\.org/"
    + "(urn:)?(doi:)?", Pattern.CASE_INSENSITIVE);
  private static final Pattern PARSER = Pattern.compile("^(?:urn:)?(?:doi:)?"           // optional
    + "(10(?:\\.[0-9]+)+)"
    + "/(.+)$", Pattern.CASE_INSENSITIVE);

  private static final String RESOLVER = "https://doi.org/";
  private static final String SCHEME = "doi:";
  private String prefix;
  private String suffix;

  /**
   * Returns true only if the source can be parsed into a DOI.
   */
  public static boolean isParsable(String source) {
    if (StringUtils.isNotEmpty(source)) {
      try {
        return PARSER.matcher(decodeUrl(source)).find();
      } catch (IllegalArgumentException iaEx) {
        LOG.debug("Can not decode URL from the following DOI: {}", source);
      }
    }
    return false;
  }

  /**
   * Do not use this constructor.
   * Required by JAXB to marshall this object without introducing adapters.
   */
  public DOI() {
  }

  /**
   * Parses a simple DOI string of various forms incl URN, URL or plain DOI names.
   *
   * @param doi the full simple DOI string
   * @throws java.lang.IllegalArgumentException if invalid DOI string is passed
   */
  public DOI(String doi) {
    Objects.requireNonNull(doi, "DOI required");
    Matcher m = PARSER.matcher(decodeUrl(doi));
    if (m.find()) {
      this.prefix = m.group(1).toLowerCase();
      this.suffix = m.group(2).toLowerCase();
    } else {
      throw new IllegalArgumentException(doi + " is not a valid DOI");
    }
  }

  /**
   * Parses a simple DOI string of various forms incl URN, URL or plain DOI names.
   *
   * @param prefix a simple DOI prefix starting with 10.
   * @param suffix arbitrary suffix part of the DOI
   * @throws java.lang.IllegalArgumentException if invalid DOI prefix is given
   */
  public DOI(String prefix, String suffix) {
    this.prefix = Objects.requireNonNull(prefix, "DOI prefix required").toLowerCase();
    checkArgument(prefix.startsWith("10."));
    this.suffix = Objects.requireNonNull(suffix, "DOI suffix required").toLowerCase();
  }

  /**
   * If the doi is encoded as a URL this method strips the resolver and decodes the URL encoded string entities.
   *
   * @param doi not null doi represented as a String
   * @return the path part if the doi is a URL otherwise the doi is returned as is.
   * @throws IllegalArgumentException
   */
  private static String decodeUrl(@NotNull String doi) {
    Matcher m = HTTP.matcher(doi);
    if (m.find()) {
      // strip resolver incl potentially starting paths using a badly encoded urn:doi
      // (the colon would need to be encoded in a proper URL)
      doi = m.replaceFirst("");
      // now decode the URL path, we cannot possibly have query parameters or anchors as the DOIs encoded as a URL
      // will just be a path
      try {
        return URI.create(URLEncoder.encode(doi, CHAR_ENCODING)).getPath();
      } catch (UnsupportedEncodingException e) {
        throw new IllegalArgumentException("Unsupported DOI encoding", e);
      }
    }
    return doi;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  /**
   * See <a href="http://www.doi.org/doi_handbook/2_Numbering.html#2.6">DOI Handbook, Visual presentation and other representation of DOI names</a>.
   *
   * @return the resolved DOI using https://doi.org/
   * @throws IllegalStateException if the encoding of the DOI is not supported
   */
  public URI getUrl() {
    try {
      return URI.create(RESOLVER + prefix + '/' + URLEncoder.encode(suffix, CHAR_ENCODING));
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("Unsupported DOI encoding", e);
    }
  }

  /**
   * @return the DOI name prefixed with "doi:", as recommended by the DOI Handbook.
   */
  public String getDoiString() {
    return SCHEME + getDoiName();
  }

  /**
   * @return the pure DOI name without any initial scheme name starting with the prefix, i.e. 10.
   */
  public String getDoiName() {
    return prefix + '/' + suffix;
  }

  @Override
  public String toString() {
    return getDoiName();
  }

  @Override
  public int hashCode() {
    return Objects.hash(prefix, suffix);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final DOI other = (DOI) obj;
    // prefix and suffix are always uppercased so we can do simple equals here
    return Objects.equals(this.prefix, other.prefix) && Objects.equals(this.suffix, other.suffix);
  }

  public static class DoiSerializer extends JsonSerializer<DOI> {

    @Override
    public void serialize(DOI value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeString(value.toString());
    }
  }

  public static class DoiDeserializer extends JsonDeserializer<DOI> {

    @Override
    public DOI deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p != null && p.getTextLength() > 0) {
        return new DOI(p.getText());
      }
      return null;
    }
  }
}
