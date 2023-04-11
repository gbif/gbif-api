package org.gbif.api.model.collections.view;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

import lombok.NoArgsConstructor;

import org.gbif.api.model.collections.Batch;

@Data
@NoArgsConstructor
public class BatchView {

  @JsonUnwrapped
  private Batch batch;

  private String resultFileLink;

  public BatchView(Batch batch) {
    this.batch = batch;
  }
}
