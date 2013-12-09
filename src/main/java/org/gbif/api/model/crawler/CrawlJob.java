package org.gbif.api.model.crawler;

import org.gbif.api.vocabulary.EndpointType;

import java.net.URI;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class represents a job to be worked on by a crawler. That can be either one of the XML based protocols
 * (BioCASe, DiGIR, TAPIR) or a DwC-Archive.
 * <p/>
 * For now this object will be used in JSON serialized form in ZooKeeper.
 */
@Immutable
@ThreadSafe
public class CrawlJob {

  private final UUID datasetKey;
  private final EndpointType endpointType;
  private final URI targetUrl;
  private final int attempt;
  private final ImmutableMap<String, String> properties;

  /**
   * Creates a new crawl job.
   *
   * @param datasetKey  of the dataset to crawl
   * @param endpointType of the dataset
   * @param targetUrl    of the dataset
   * @param attempt      a monotonously increasing counter, increased every time we try to crawl a dataset whether that
   *                     attempt is successful or not
   * @param properties   a way to provide protocol or crawl specific options
   */
  @JsonCreator
  public CrawlJob(
    @JsonProperty("datasetKey") UUID datasetKey,
    @JsonProperty("endpointType") EndpointType endpointType,
    @JsonProperty("targetUrl") URI targetUrl,
    @JsonProperty("attempt") int attempt,
    @Nullable @JsonProperty("properties") Map<String, String> properties
  ) {
    this.datasetKey = checkNotNull(datasetKey);
    this.endpointType = checkNotNull(endpointType);
    this.targetUrl = checkNotNull(targetUrl);
    checkArgument(attempt > 0, "attempt has to be greater than 0");
    this.attempt = attempt;

    if (properties == null) {
      this.properties = ImmutableMap.of();
    } else {
      this.properties = ImmutableMap.copyOf(properties);
    }
  }

  /**
   * Constructor with mandatory fields.
   * Properties field is set to null.
   * @param datasetKey  of the dataset to crawl
   * @param endpointType of the dataset
   * @param targetUrl    of the dataset
   * @param attempt      a monotonously increasing counter, increased every time we try to crawl a dataset whether that
   *                     attempt is successful or not
   */
  public CrawlJob(UUID datasetKey, Integer attempt,EndpointType endpointType, URI targetUrl){
    //This constructor is used for the MyBatis persistence layer.
    this.datasetKey = datasetKey;
    this.attempt = attempt;
    this.endpointType = endpointType;
    this.targetUrl = targetUrl;
    this.properties = null;
  }

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public EndpointType getEndpointType() {
    return endpointType;
  }

  /**
   * Used to save protocol specific information (e.g. contentNamespace for TAPIR and BioCASe).
   *
   * @return an immutable map of all the properties
   */
  // NOTE: This should be an ImmutableMap but Jackson 1.x can't easily deserialize that
  public Map<String, String> getProperties() {
    return properties;
  }

  public URI getTargetUrl() {
    return targetUrl;
  }

  public int getAttempt() {
    return attempt;
  }

  @JsonIgnore
  public String getProperty(String name) {
    return properties.get(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CrawlJob)) {
      return false;
    }

    final CrawlJob other = (CrawlJob) obj;
    return Objects.equal(this.datasetKey, other.datasetKey)
           && Objects.equal(this.endpointType, other.endpointType)
           && Objects.equal(this.targetUrl, other.targetUrl)
           && Objects.equal(this.attempt, other.attempt)
           && Objects.equal(this.properties, other.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(datasetKey, endpointType, targetUrl, attempt, properties);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("datasetKey", datasetKey)
      .add("endpointType", endpointType)
      .add("targetUrl", targetUrl)
      .add("attempt", attempt)
      .add("properties", properties)
      .toString();
  }

}

