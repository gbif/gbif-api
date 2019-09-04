package org.gbif.api.model.pipelines.ws;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class to send the parameters of a {@link org.gbif.api.model.pipelines.PipelineProcess}.
 */
public class PipelineProcessParameters implements Serializable {

  private UUID datasetKey;
  private int attempt;

  public PipelineProcessParameters() {
    // needed for Jackson
  }

  public PipelineProcessParameters(UUID datasetKey, int attempt) {
    this.datasetKey = datasetKey;
    this.attempt = attempt;
  }

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public int getAttempt() {
    return attempt;
  }

  public void setAttempt(int attempt) {
    this.attempt = attempt;
  }
}
