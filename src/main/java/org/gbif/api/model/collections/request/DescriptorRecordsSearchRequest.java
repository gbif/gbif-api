package org.gbif.api.model.collections.request;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.TypeStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescriptorRecordsSearchRequest extends PageableBase implements Serializable {

  @Nullable private String query;
  @Nullable private Long descriptorKey;
  @Nullable private List<String> scientificName;
  @Nullable private List<Country> country;
  @Nullable private String individualCount;
  @Nullable private List<String> identifiedBy;
  @Nullable private Date dateIdentified;
  @Nullable private Date dateIdentifiedFrom;
  @Nullable private Date dateIdentifiedBefore;
  @Nullable private List<TypeStatus> typeStatus;
  @Nullable private List<String> recordedBy;
  @Nullable private List<String> discipline;
  @Nullable private List<String> objectClassification;
  @Nullable private List<String> issues;

  public Pageable getPage() {
    return new PagingRequest(getOffset(), getLimit());
  }
}
