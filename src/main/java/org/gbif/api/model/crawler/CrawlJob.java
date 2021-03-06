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
package org.gbif.api.model.crawler;

import org.gbif.api.vocabulary.EndpointType;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.gbif.api.util.PreconditionUtils.checkArgument;

/**
 * This class represents a job to be worked on by a crawler. That can be either one of the XML based protocols
 * (BioCASe, DiGIR, TAPIR) or a DwC-Archive.
 * <p/>
 * For now this object will be used in JSON serialized form in ZooKeeper.
 */
@Immutable
@ThreadSafe
@SuppressWarnings("unused")
public class CrawlJob {

  private final UUID datasetKey;
  private final EndpointType endpointType;
  private final URI targetUrl;
  private final int attempt;
  private final Map<String, String> properties;

  /**
   * Creates a new crawl job.
   *
   * @param datasetKey   of the dataset to crawl
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
    this.datasetKey = Objects.requireNonNull(datasetKey);
    this.endpointType = Objects.requireNonNull(endpointType);
    this.targetUrl = Objects.requireNonNull(targetUrl);
    checkArgument(attempt > 0, "attempt has to be greater than 0");
    this.attempt = attempt;

    if (properties == null) {
      this.properties = Collections.emptyMap();
    } else {
      this.properties = Collections.unmodifiableMap(properties);
    }
  }

  /**
   * Constructor with mandatory fields.
   * Properties field is set to null.
   *
   * @param datasetKey   of the dataset to crawl
   * @param endpointType of the dataset
   * @param targetUrl    of the dataset
   * @param attempt      a monotonously increasing counter, increased every time we try to crawl a dataset whether that
   *                     attempt is successful or not
   */
  public CrawlJob(UUID datasetKey, Integer attempt, EndpointType endpointType, URI targetUrl) {
    //This constructor is used for the MyBatis persistence layer.
    this.datasetKey = datasetKey;
    this.attempt = attempt;
    this.endpointType = endpointType;
    this.targetUrl = targetUrl;
    this.properties = Collections.emptyMap();
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CrawlJob crawlJob = (CrawlJob) o;
    return attempt == crawlJob.attempt &&
      Objects.equals(datasetKey, crawlJob.datasetKey) &&
      endpointType == crawlJob.endpointType &&
      Objects.equals(targetUrl, crawlJob.targetUrl) &&
      Objects.equals(properties, crawlJob.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datasetKey, endpointType, targetUrl, attempt, properties);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CrawlJob.class.getSimpleName() + "[", "]")
      .add("datasetKey=" + datasetKey)
      .add("endpointType=" + endpointType)
      .add("targetUrl=" + targetUrl)
      .add("attempt=" + attempt)
      .add("properties=" + properties)
      .toString();
  }
}
