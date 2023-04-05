package org.gbif.api.model.collections;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** A batch that can contain either institutions or collections to create or update them in bulk. */
@Data
public class Batch {

  @Schema(description = "Unique key for the batch.", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer key;

  @Schema(description = "State that indicates if the batch is being processed, finished or failed.")
  private State state;

  @Schema(
      description =
          "Operation that the batch carries out to indicate if it's an import or an update of existing data.")
  private Operation operation;

  @Schema(description = "Indicates if the batch is for institutions or for collections.")
  private CollectionEntityType entityType;

  @Hidden @JsonIgnore private String resultFilePath;

  @Schema(description = "Errors found that made the batch fail.")
  private List<String> errors = new ArrayList<>();

  @Schema(
      description = "Timestamp of when the batch was created in the GBIF registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Date created;

  @Schema(
      description = "The GBIF username of the creator of the batch in the GBIF registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
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
