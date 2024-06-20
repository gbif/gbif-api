package org.gbif.api.model.collections.descriptors;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Descriptor implements Serializable {

  private long key;
  @NotBlank private String title;
  private String description;
  @NotNull private UUID collectionKey;
  private Date created;
  private String createdBy;
  private Date modified;
  private String modifiedBy;
  private Date deleted;
}
