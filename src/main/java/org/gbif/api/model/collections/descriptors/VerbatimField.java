package org.gbif.api.model.collections.descriptors;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class VerbatimField implements Serializable {

  private long key;
  private long recordKey;
  @NotBlank private String fieldName;
  @NotBlank private String fieldValue;
}
