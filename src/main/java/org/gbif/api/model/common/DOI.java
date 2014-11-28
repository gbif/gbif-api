package org.gbif.api.model.common;

import java.net.URI;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

/**
 * Class representing a single Digital Object Identifier (DOI) breaking it down to a prefix and suffix.
 * For the syntax of DOI names see the <a href="http://www.doi.org/doi_handbook/2_Numbering.html#2.2">DOI Handbook</a>.
 * All parsing is case insensitive and resulting components will all be upper cased.
 */
public class DOI {
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
   * Parses a simple DOI string of various forms incl URN, URL or plain DOI names.
   * @param doi the full simple DOI string
   * @throws java.lang.IllegalArgumentException if invalid DOI string is passed
   */
  public DOI(String doi) {
    Preconditions.checkNotNull("DOI required", doi);
    Matcher m = PARSER.matcher(decodeUrl(doi));
    if (m.find()) {
      this.prefix = m.group(1).toUpperCase();
      this.suffix = m.group(2).toUpperCase();
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
    this.prefix = Preconditions.checkNotNull("DOI prefix required", prefix).toUpperCase();
    Preconditions.checkArgument(prefix.startsWith("10."));
    this.suffix = Preconditions.checkNotNull("DOI suffix required", suffix).toUpperCase();
  }

  /**
   * If the doi is encoded as a URL this method strips the resolver and decodes the URL encoded string entities.
   */
  private static String decodeUrl(String doi) {
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
}
