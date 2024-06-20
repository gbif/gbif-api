package org.gbif.api.model.collections.request;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.model.common.paging.PagingRequest;

import java.io.Serializable;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescriptorsSearchRequest extends PageableBase implements Serializable {

  @Nullable private String query;
  @Nullable private String title;
  @Nullable private String description;

  public Pageable getPage() {
    return new PagingRequest(getOffset(), getLimit());
  }
}
