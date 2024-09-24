package org.gbif.api.model.collections.descriptors;

import org.gbif.api.model.registry.LenientEquals;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DescriptorGroup implements LenientEquals<DescriptorGroup>, Serializable {

  private long key;
  @NotBlank private String title;
  private String description;
  @NotNull private UUID collectionKey;
  private Date created;
  private String createdBy;
  private Date modified;
  private String modifiedBy;
  private Date deleted;

  @Override
  public boolean lenientEquals(DescriptorGroup other) {
    if (this == other) {
      return true;
    }

    return key == other.key
        && Objects.equals(title, other.title)
        && Objects.equals(description, other.description)
        && Objects.equals(collectionKey, other.collectionKey);
  }
}
