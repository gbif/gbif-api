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
package org.gbif.api.model.crawler;

import java.util.Date;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.Min;

import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Information about a dataset that is currently being processed. That usually means that we are crawling it at the
 * moment or are in the process of persisting and interpreting its occurrences.
 */
public class DatasetProcessStatus {

  private UUID datasetKey;
  private CrawlJob crawlJob;
  private Date startedCrawling;
  private Date finishedCrawling;
  private String crawlContext;
  private FinishReason finishReason;
  private ProcessState processStateOccurrence;
  private ProcessState processStateChecklist;
  private ProcessState processStateSample;

  // Long instead of Optional<Long> because of JSON serialization issues, Jackson doesn't honor the NON_NULL setting
  // for the Guava extension
  private Long declaredCount;

  private long pagesCrawled;

  private long pagesFragmentedSuccessful;
  private long pagesFragmentedError;
  private long fragmentsEmitted;

  private long fragmentsReceived;
  private long rawOccurrencesPersistedNew;
  private long rawOccurrencesPersistedUpdated;
  private long rawOccurrencesPersistedUnchanged;
  private long rawOccurrencesPersistedError;
  private long fragmentsProcessed;

  private long verbatimOccurrencesPersistedSuccessful;
  private long verbatimOccurrencesPersistedError;

  private long interpretedOccurrencesPersistedSuccessful;
  private long interpretedOccurrencesPersistedError;

  public static Builder builder() {
    return new Builder();
  }

  public DatasetProcessStatus() {
    // This constructor is needed for Jackson deserialization
  }

  /**
   * Builds a new object from the builder validating it in the process.
   * <p/>
   * We only validate very little (all counts have to be greater than or equal to zero, a few null checks etc.) but not
   */
  public DatasetProcessStatus(Builder builder) {
    checkNotNull(builder, "builder can't be null");

    datasetKey = checkNotNull(builder.datasetKey, "datasetKey can't be null");
    crawlJob = checkNotNull(builder.crawlJob, "crawlJob can't be null");
    startedCrawling = builder.startedCrawling;
    finishedCrawling = builder.finishedCrawling;
    finishReason = builder.finishReason;
    processStateOccurrence = builder.processStateOccurrence;
    processStateChecklist = builder.processStateChecklist;
    processStateSample = builder.processStateSample;
    crawlContext = builder.crawlContext;

    declaredCount = builder.declaredCount;

    pagesCrawled = builder.pagesCrawled;

    pagesFragmentedSuccessful = builder.pagesFragmentedSuccessful;
    pagesFragmentedError = builder.pagesFragmentedError;
    fragmentsEmitted = builder.fragmentsEmitted;

    fragmentsReceived = builder.fragmentsReceived;
    rawOccurrencesPersistedNew = builder.rawOccurrencesPersistedNew;
    rawOccurrencesPersistedUpdated = builder.rawOccurrencesPersistedUpdated;
    rawOccurrencesPersistedUnchanged = builder.rawOccurrencesPersistedUnchanged;
    rawOccurrencesPersistedError = builder.rawOccurrencesPersistedError;
    fragmentsProcessed = builder.fragmentsProcessed;

    verbatimOccurrencesPersistedSuccessful = builder.verbatimOccurrencesPersistedSuccessful;
    verbatimOccurrencesPersistedError = builder.verbatimOccurrencesPersistedError;

    interpretedOccurrencesPersistedSuccessful = builder.interpretedOccurrencesPersistedSuccessful;
    interpretedOccurrencesPersistedError = builder.interpretedOccurrencesPersistedError;

    checkArgument(declaredCount == null || declaredCount >= 0,
                  "declaredCount must be either null or greater than or equal to zero");

    checkArgument(pagesCrawled >= 0, "pagesCrawled has to be greater than or equal to zero");

    checkArgument(pagesFragmentedSuccessful >= 0, "pagesFragmentedSuccessful");
    checkArgument(pagesFragmentedError >= 0, "pagesFragmentedError has to be greater than or equal to zero");
    checkArgument(fragmentsEmitted >= 0, "fragmentsEmitted has to be greater than or equal to zero");

    checkArgument(fragmentsReceived >= 0, "fragmentsReceived has to be greater than or equal to zero");
    checkArgument(rawOccurrencesPersistedNew >= 0,
                  "rawOccurrencesPersistedNew has to be greater than or equal to zero");
    checkArgument(rawOccurrencesPersistedUpdated >= 0,
                  "rawOccurrencesPersistedUpdated has to be greater than or equal to zero");
    checkArgument(rawOccurrencesPersistedUnchanged >= 0,
                  "rawOccurrencesPersistedUnchanged has to be greater than or equal to zero");
    checkArgument(rawOccurrencesPersistedError >= 0,
                  "rawOccurrencesPersistedError has to be greater than or equal to zero");
    checkArgument(fragmentsProcessed >= 0, "fragmentsProcessed has to be greater than or equal to zero");

    checkArgument(verbatimOccurrencesPersistedSuccessful >= 0,
                  "verbatimOccurrencesPersistedSuccessful has to be greater than or equal to zero");
    checkArgument(verbatimOccurrencesPersistedError >= 0,
                  "verbatimOccurrencesPersistedError has to be greater than or equal to zero");

    checkArgument(interpretedOccurrencesPersistedSuccessful >= 0,
                  "interpretedOccurrencesPersistedSuccessful has to be greater than or equal to zero");
    checkArgument(interpretedOccurrencesPersistedError >= 0,
                  "interpretedOccurrencesPersistedError has to be greater than or equal to zero");
  }

  /**
   * Last successful crawl context, this is a JSON string.
   *
   * @return the last successful crawl context, this is a JSON string
   */
  @Nullable
  public String getCrawlContext() {
    return crawlContext;
  }

  public CrawlJob getCrawlJob() {
    return crawlJob;
  }

  /**
   * Key that identifies the Dataset.
   *
   * @return the UUID key that identifies the dataset
   */
  public UUID getDatasetKey() {
    return datasetKey;
  }

  @Nullable
  public Long getDeclaredCount() {
    return declaredCount;
  }

  /**
   * Timestamp of when the crawl was finished.
   *
   * @return the timestamp when the crawl finished
   */
  @Nullable
  public Date getFinishedCrawling() {
    return finishedCrawling;
  }

  /**
   * The reason a crawl is finished. Will be {@code null} if {@link #getFinishedCrawling()} returns {@code null}.
   *
   * @return the reason the crawl finished
   */
  @Nullable
  public FinishReason getFinishReason() {
    return finishReason;
  }

  @Nullable
  public ProcessState getProcessStateOccurrence() {
    return processStateOccurrence;
  }

  @Nullable
  public ProcessState getProcessStateChecklist() {
    return processStateChecklist;
  }

  @Nullable
  public ProcessState getProcessStateSample() {
    return processStateSample;
  }

  @Min(0)
  public long getFragmentsEmitted() {
    return fragmentsEmitted;
  }

  /**
   * Number of fragments that have been processed.
   *
   * @return the number of fragments that have been processed
   */
  @Min(0)
  public long getFragmentsProcessed() {
    return fragmentsProcessed;
  }

  @Min(0)
  public long getFragmentsReceived() {
    return fragmentsReceived;
  }

  @Min(0)
  public long getInterpretedOccurrencesPersistedError() {
    return interpretedOccurrencesPersistedError;
  }

  @Min(0)
  public long getInterpretedOccurrencesPersistedSuccessful() {
    return interpretedOccurrencesPersistedSuccessful;
  }

  /**
   * Number of pages crawled in total.
   *
   * @return number of pages crawled
   */
  @Min(0)
  public long getPagesCrawled() {
    return pagesCrawled;
  }

  @Min(0)
  public long getPagesFragmentedError() {
    return pagesFragmentedError;
  }

  /**
   * Number of pages that have been fragmented.
   *
   * @return the number of pages that have been fragmented
   */
  @Min(0)
  public long getPagesFragmentedSuccessful() {
    return pagesFragmentedSuccessful;
  }

  @Min(0)
  public long getRawOccurrencesPersistedError() {
    return rawOccurrencesPersistedError;
  }

  @Min(0)
  public long getRawOccurrencesPersistedNew() {
    return rawOccurrencesPersistedNew;
  }

  @Min(0)
  public long getRawOccurrencesPersistedUnchanged() {
    return rawOccurrencesPersistedUnchanged;
  }

  @Min(0)
  public long getRawOccurrencesPersistedUpdated() {
    return rawOccurrencesPersistedUpdated;
  }

  /**
   * Timestamp of when the crawl was actually started by a crawler.
   *
   * @return the timestamp when the crawl started
   */
  @Nullable
  public Date getStartedCrawling() {
    return startedCrawling;
  }

  @Min(0)
  public long getVerbatimOccurrencesPersistedError() {
    return verbatimOccurrencesPersistedError;
  }

  @Min(0)
  public long getVerbatimOccurrencesPersistedSuccessful() {
    return verbatimOccurrencesPersistedSuccessful;
  }


  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }


  public void setCrawlJob(CrawlJob crawlJob) {
    this.crawlJob = crawlJob;
  }


  public void setStartedCrawling(Date startedCrawling) {
    this.startedCrawling = startedCrawling;
  }


  public void setFinishedCrawling(Date finishedCrawling) {
    this.finishedCrawling = finishedCrawling;
  }


  public void setCrawlContext(String crawlContext) {
    this.crawlContext = crawlContext;
  }


  public void setFinishReason(FinishReason finishReason) {
    this.finishReason = finishReason;
  }

  public void setProcessStateOccurrence(ProcessState processStateOccurrence) {
    this.processStateOccurrence = processStateOccurrence;
  }

  public void setProcessStateChecklist(ProcessState processStateChecklist) {
    this.processStateChecklist = processStateChecklist;
  }

  public void setProcessStateSample(ProcessState processStateSample) {
    this.processStateSample = processStateSample;
  }

  public void setDeclaredCount(Long declaredCount) {
    this.declaredCount = declaredCount;
  }


  public void setPagesCrawled(long pagesCrawled) {
    this.pagesCrawled = pagesCrawled;
  }


  public void setPagesFragmentedSuccessful(long pagesFragmentedSuccessful) {
    this.pagesFragmentedSuccessful = pagesFragmentedSuccessful;
  }


  public void setPagesFragmentedError(long pagesFragmentedError) {
    this.pagesFragmentedError = pagesFragmentedError;
  }


  public void setFragmentsEmitted(long fragmentsEmitted) {
    this.fragmentsEmitted = fragmentsEmitted;
  }


  public void setFragmentsReceived(long fragmentsReceived) {
    this.fragmentsReceived = fragmentsReceived;
  }


  public void setRawOccurrencesPersistedNew(long rawOccurrencesPersistedNew) {
    this.rawOccurrencesPersistedNew = rawOccurrencesPersistedNew;
  }


  public void setRawOccurrencesPersistedUpdated(long rawOccurrencesPersistedUpdated) {
    this.rawOccurrencesPersistedUpdated = rawOccurrencesPersistedUpdated;
  }


  public void setRawOccurrencesPersistedUnchanged(long rawOccurrencesPersistedUnchanged) {
    this.rawOccurrencesPersistedUnchanged = rawOccurrencesPersistedUnchanged;
  }


  public void setRawOccurrencesPersistedError(long rawOccurrencesPersistedError) {
    this.rawOccurrencesPersistedError = rawOccurrencesPersistedError;
  }


  public void setFragmentsProcessed(long fragmentsProcessed) {
    this.fragmentsProcessed = fragmentsProcessed;
  }


  public void setVerbatimOccurrencesPersistedSuccessful(long verbatimOccurrencesPersistedSuccessful) {
    this.verbatimOccurrencesPersistedSuccessful = verbatimOccurrencesPersistedSuccessful;
  }


  public void setVerbatimOccurrencesPersistedError(long verbatimOccurrencesPersistedError) {
    this.verbatimOccurrencesPersistedError = verbatimOccurrencesPersistedError;
  }


  public void setInterpretedOccurrencesPersistedSuccessful(long interpretedOccurrencesPersistedSuccessful) {
    this.interpretedOccurrencesPersistedSuccessful = interpretedOccurrencesPersistedSuccessful;
  }


  public void setInterpretedOccurrencesPersistedError(long interpretedOccurrencesPersistedError) {
    this.interpretedOccurrencesPersistedError = interpretedOccurrencesPersistedError;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final DatasetProcessStatus other = (DatasetProcessStatus) obj;
    return Objects.equal(this.datasetKey, other.datasetKey)
           && Objects.equal(this.crawlJob, other.crawlJob)
           && Objects.equal(this.startedCrawling, other.startedCrawling)
           && Objects.equal(this.finishedCrawling, other.finishedCrawling)
           && Objects.equal(this.finishReason, other.finishReason)
           && Objects.equal(this.processStateOccurrence, other.processStateOccurrence)
           && Objects.equal(this.processStateChecklist, other.processStateChecklist)
           && Objects.equal(this.processStateSample, other.processStateSample)
           && Objects.equal(this.crawlContext, other.crawlContext)
           && Objects.equal(this.declaredCount, other.declaredCount)
           && Objects.equal(this.pagesCrawled, other.pagesCrawled)
           && Objects.equal(this.pagesFragmentedSuccessful, other.pagesFragmentedSuccessful)
           && Objects.equal(this.pagesFragmentedError, other.pagesFragmentedError)
           && Objects.equal(this.fragmentsEmitted, other.fragmentsEmitted)
           && Objects.equal(this.fragmentsReceived, other.fragmentsReceived)
           && Objects.equal(this.rawOccurrencesPersistedNew, other.rawOccurrencesPersistedNew)
           && Objects.equal(this.rawOccurrencesPersistedUpdated, other.rawOccurrencesPersistedUpdated)
           && Objects.equal(this.rawOccurrencesPersistedUnchanged, other.rawOccurrencesPersistedUnchanged)
           && Objects.equal(this.rawOccurrencesPersistedError, other.rawOccurrencesPersistedError)
           && Objects.equal(this.fragmentsProcessed, other.fragmentsProcessed)
           && Objects.equal(this.verbatimOccurrencesPersistedSuccessful, other.verbatimOccurrencesPersistedSuccessful)
           && Objects.equal(this.verbatimOccurrencesPersistedError, other.verbatimOccurrencesPersistedError)
           && Objects.equal(this.interpretedOccurrencesPersistedSuccessful,
                            other.interpretedOccurrencesPersistedSuccessful)
           && Objects.equal(this.interpretedOccurrencesPersistedError, other.interpretedOccurrencesPersistedError);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(datasetKey,
                            crawlJob,
                            startedCrawling,
                            finishedCrawling,
                            finishReason,
                            processStateOccurrence,
                            processStateChecklist,
                            processStateSample,
                            crawlContext,
                            declaredCount,
                            pagesCrawled,
                            pagesFragmentedSuccessful,
                            pagesFragmentedError,
                            fragmentsEmitted,
                            fragmentsReceived,
                            rawOccurrencesPersistedNew,
                            rawOccurrencesPersistedUpdated,
                            rawOccurrencesPersistedUnchanged,
                            rawOccurrencesPersistedError,
                            fragmentsProcessed,
                            verbatimOccurrencesPersistedSuccessful,
                            verbatimOccurrencesPersistedError,
                            interpretedOccurrencesPersistedSuccessful,
                            interpretedOccurrencesPersistedError);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("datasetKey", datasetKey)
      .add("crawlJob", crawlJob)
      .add("startedCrawling", startedCrawling)
      .add("finishedCrawling", finishedCrawling)
      .add("finisReason", finishReason)
      .add("processStateOccurrence", processStateOccurrence)
      .add("processStateChecklist", processStateChecklist)
      .add("processStateSample", processStateSample)
      .add("crawlContext", crawlContext)
      .add("declaredCount", declaredCount)
      .add("pagesCrawled", pagesCrawled)
      .add("pagesFragmentedSuccessful", pagesFragmentedSuccessful)
      .add("pagesFragmentedError", pagesFragmentedError)
      .add("fragmentsEmitted", fragmentsEmitted)
      .add("fragmentsReceived", fragmentsReceived)
      .add("rawOccurrencesPersistedNew", rawOccurrencesPersistedNew)
      .add("rawOccurrencesPersistedUpdated", rawOccurrencesPersistedUpdated)
      .add("rawOccurrencesPersistedUnchanged", rawOccurrencesPersistedUnchanged)
      .add("rawOccurrencesPersistedError", rawOccurrencesPersistedError)
      .add("fragmentsProcessed", fragmentsProcessed)
      .add("verbatimOccurrencesPersistedSuccessful", verbatimOccurrencesPersistedSuccessful)
      .add("verbatimOccurrencesPersistedError", verbatimOccurrencesPersistedError)
      .add("interpretedOccurrencesPersistedSuccessful", interpretedOccurrencesPersistedSuccessful)
      .add("interpretedOccurrencesPersistedError", interpretedOccurrencesPersistedError)
      .toString();
  }

  public static class Builder {

    private UUID datasetKey;
    private CrawlJob crawlJob;
    private Date startedCrawling;
    private Date finishedCrawling;
    private String crawlContext;
    private FinishReason finishReason;
    private ProcessState processStateOccurrence;
    private ProcessState processStateChecklist;
    private ProcessState processStateSample;
    private Long declaredCount;
    private long pagesCrawled;
    private long pagesFragmentedSuccessful;
    private long pagesFragmentedError;
    private long fragmentsEmitted;
    private long fragmentsReceived;
    private long rawOccurrencesPersistedNew;
    private long rawOccurrencesPersistedUpdated;
    private long rawOccurrencesPersistedUnchanged;
    private long rawOccurrencesPersistedError;
    private long fragmentsProcessed;
    private long verbatimOccurrencesPersistedSuccessful;
    private long verbatimOccurrencesPersistedError;
    private long interpretedOccurrencesPersistedSuccessful;
    private long interpretedOccurrencesPersistedError;

    public DatasetProcessStatus build() {
      return new DatasetProcessStatus(this);
    }

    public Builder crawlContext(String crawlContext) {
      this.crawlContext = crawlContext;
      return this;
    }

    public Builder crawlJob(CrawlJob crawlJob) {
      this.crawlJob = crawlJob;
      return this;
    }

    public Builder datasetKey(UUID datasetKey) {
      this.datasetKey = datasetKey;
      return this;
    }

    public Builder declaredCount(Long declaredCount) {
      this.declaredCount = declaredCount;
      return this;
    }

    public Builder finishedCrawling(Date finishedCrawling) {
      this.finishedCrawling = finishedCrawling;
      return this;
    }

    public Builder finishReason(FinishReason finishReason) {
      this.finishReason = finishReason;
      return this;
    }

    public Builder processStateOccurrence(ProcessState processStateOccurrence) {
      this.processStateOccurrence = processStateOccurrence;
      return this;
    }

    public Builder processStateChecklist(ProcessState processStateChecklist) {
      this.processStateChecklist = processStateChecklist;
      return this;
    }

    public Builder processStateSample(ProcessState processStateSample) {
      this.processStateSample = processStateSample;
      return this;
    }

    public Builder fragmentsEmitted(long fragmentsEmitted) {
      this.fragmentsEmitted = fragmentsEmitted;
      return this;
    }

    public Builder fragmentsProcessed(long fragmentsProcessed) {
      this.fragmentsProcessed = fragmentsProcessed;
      return this;
    }

    public Builder fragmentsReceived(long fragmentsReceived) {
      this.fragmentsReceived = fragmentsReceived;
      return this;
    }

    public Builder interpretedOccurrencesPersistedError(long interpretedOccurrencesPersistedError) {
      this.interpretedOccurrencesPersistedError = interpretedOccurrencesPersistedError;
      return this;
    }

    public Builder interpretedOccurrencesPersistedSuccessful(long interpretedOccurrencesPersistedSuccessful) {
      this.interpretedOccurrencesPersistedSuccessful = interpretedOccurrencesPersistedSuccessful;
      return this;
    }

    public Builder pagesCrawled(long pagesCrawled) {
      this.pagesCrawled = pagesCrawled;
      return this;
    }

    public Builder pagesFragmentedError(long pagesFragmentedError) {
      this.pagesFragmentedError = pagesFragmentedError;
      return this;
    }

    public Builder pagesFragmentedSuccessful(long pagesFragmentedSuccessful) {
      this.pagesFragmentedSuccessful = pagesFragmentedSuccessful;
      return this;
    }

    public Builder rawOccurrencesPersistedError(long rawOccurrencesPersistedError) {
      this.rawOccurrencesPersistedError = rawOccurrencesPersistedError;
      return this;
    }

    public Builder rawOccurrencesPersistedNew(long rawOccurrencesPersistedNew) {
      this.rawOccurrencesPersistedNew = rawOccurrencesPersistedNew;
      return this;
    }

    public Builder rawOccurrencesPersistedUnchanged(long rawOccurrencesPersistedUnchanged) {
      this.rawOccurrencesPersistedUnchanged = rawOccurrencesPersistedUnchanged;
      return this;
    }

    public Builder rawOccurrencesPersistedUpdated(long rawOccurrencesPersistedUpdated) {
      this.rawOccurrencesPersistedUpdated = rawOccurrencesPersistedUpdated;
      return this;
    }

    public Builder startedCrawling(Date startedCrawling) {
      this.startedCrawling = startedCrawling;
      return this;
    }

    public Builder verbatimOccurrencesPersistedError(long verbatimOccurrencesPersistedError) {
      this.verbatimOccurrencesPersistedError = verbatimOccurrencesPersistedError;
      return this;
    }

    public Builder verbatimOccurrencesPersistedSuccessful(long verbatimOccurrencesPersistedSuccessful) {
      this.verbatimOccurrencesPersistedSuccessful = verbatimOccurrencesPersistedSuccessful;
      return this;
    }
  }
}
