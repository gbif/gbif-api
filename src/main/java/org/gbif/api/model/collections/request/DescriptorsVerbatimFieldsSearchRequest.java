package org.gbif.api.model.collections.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.model.common.paging.PagingRequest;

import java.io.Serializable;

import javax.annotation.Nullable;

import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescriptorsVerbatimFieldsSearchRequest extends PageableBase implements Serializable {

  @Nullable private String query;
  @Nullable private Long recordKey;

  public Pageable getPage() {
    return new PagingRequest(getOffset(), getLimit());
  }
}
