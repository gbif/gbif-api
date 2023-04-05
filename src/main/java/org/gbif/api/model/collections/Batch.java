package org.gbif.api.model.collections;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/** A batch that can contain either institutions or collections to create or update them in bulk. */
@Data
public class Batch {

  // TODO: documentation

  private Integer key;
  private State state;
  private Operation operation;
  private CollectionEntityType entityType;
  @JsonIgnore private String resultFilePath;
  private List<String> errors = new ArrayList<>();
  private Date created;
  private String createdBy;

  public enum Operation {
    CREATE,
    UPDATE;
  }

  public enum State {
    IN_PROGRESS,
    FAILED,
    FINISHED;
  }
}
