package org.gbif.api.model.common;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.NotNull;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.SerializerBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class representing a single Digital Object Identifier (DOI) breaking it down to a prefix and suffix.
 * For the syntax of DOI names see the <a href="http://www.doi.org/doi_handbook/2_Numbering.html#2.2">DOI Handbook</a>.
 * All parsing is case insensitive and resulting components will all be upper cased.
 */
@JsonSerialize(using = DOI.Serializer.class)
@JsonDeserialize(using = DOI.Deserializer.class)
public class DOI {

  private static final Logger LOG = LoggerFactory.getLogger(DOI.class);

  /**
   * The DOI prefix registered with DataCite to be used by GBIF issued production DOIs.
   */
  public static final String GBIF_PREFIX = "10.15468";

  /**
   * A DOI prefix provided by DataCite to be used in tests.
   */
  public static final String TEST_PREFIX = "10.5072";

  private static final Pattern HTTP = Pattern.compile("^https?://(dx\\.)?doi\\.org/"
                                                      + "(urn:)?(doi:)?", Pattern.CASE_INSENSITIVE);
  private static final Pattern PARSER = Pattern.compile("^(?:urn:)?(?:doi:)?"           // optional
                                                        + "(10(?:\\.[0-9]+)+)"
                                                        + "/(.+)$", Pattern.CASE_INSENSITIVE);

  private static String RESOLVER = "http://doi.org/";
  private static String SCHEME = "doi:";
  private String prefix;
  private String suffix;

  /**
   * Returns true only if the source can be parsed into a DOI.
   */
  public static boolean isParsable(String source) {
    if (!Strings.isNullOrEmpty(source)) {
      try {
        return PARSER.matcher(decodeUrl(source)).find();
      }
      catch (IllegalArgumentException iaEx){
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
   * @param doi the full simple DOI string
   * @throws java.lang.IllegalArgumentException if invalid DOI string is passed
   */
  public DOI(String doi) {
    Preconditions.checkNotNull(doi, "DOI required");
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
   * @param prefix a simple DOI prefix starting with 10.
   * @param suffix arbitrary suffix part of the DOI
   * @throws java.lang.IllegalArgumentException if invalid DOI prefix is given
   */
  public DOI(String prefix, String suffix) {
    this.prefix = Preconditions.checkNotNull(prefix, "DOI prefix required").toLowerCase();
    Preconditions.checkArgument(prefix.startsWith("10."));
    this.suffix = Preconditions.checkNotNull(suffix, "DOI suffix required").toLowerCase();
  }

  /**
   * If the doi is encoded as a URL this method strips the resolver and decodes the URL encoded string entities.
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
      return URI.create(doi).getPath();
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
   * See <a href="http://www.doi.org/doi_handbook/2_Numbering.html#2.6">DOI Hanbook, Visual presentation and other representation of DOI names</a>.
   * @return the resolved DOI using http://dx.doi.org
   */
  public URI getUrl() {
    return URI.create(RESOLVER + prefix + '/' + suffix);
  }

  /**
   * @return the pure DOI name without any initial scheme name starting with the prefix, i.e. 10.
   */
  public String getDoiName() {
    return prefix + '/' + suffix;
  }

  @Override
  public String toString() {
    return SCHEME + prefix + '/' + suffix;
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
    // prefix and suffix are always upper cased so we can do simple equals here
    return Objects.equals(this.prefix, other.prefix) && Objects.equals(this.suffix, other.suffix);
  }



  /**
   * Serializes a DOI as doi name with a doi: scheme.
   * For example doi:10.1038/nature.2014.16460
   */
  public static class Serializer extends SerializerBase<DOI> {

    public Serializer() {
      super(DOI.class);
    }

    @Override
    public void serialize(DOI value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      jgen.writeString(value.toString());
    }
  }

  /**
   * Deserializes a DOI from various string based formats.
   * See DOI constructor for details.
   */
  public static class Deserializer extends JsonDeserializer<DOI> {

    @Override
    public DOI deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      if (jp != null && jp.getTextLength() > 0) {
        return new DOI(jp.getText());
      }
      return null;
    }
  }

}
