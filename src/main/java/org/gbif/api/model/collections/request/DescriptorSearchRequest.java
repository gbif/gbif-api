package org.gbif.api.model.collections.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.model.common.paging.PagingConstants;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Rank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescriptorSearchRequest extends PageableBase implements Serializable {

  @Nullable private String q;
  @Nullable private Long descriptorGroupKey;
  @Nullable private List<Integer> usageKey;
  @Nullable private List<String> usageName;
  @Nullable private List<Rank> usageRank;
  @Nullable private List<Integer> taxonKey;
  @Nullable private List<Country> country;
  @Nullable private String individualCount;
  @Nullable private List<String> identifiedBy;
  @Nullable private Date dateIdentified;
  @Nullable private Date dateIdentifiedFrom;
  @Nullable private Date dateIdentifiedBefore;
  @Nullable private List<String> typeStatus;
  @Nullable private List<String> recordedBy;
  @Nullable private List<String> discipline;
  @Nullable private List<String> objectClassification;
  @Nullable private List<String> issues;

  public Pageable getPage() {
    return new PagingRequest(getOffset(), getLimit());
  }

  public static DescriptorSearchRequestBuilder builder() {
    return new CustomBuilder();
  }

  public static class DescriptorSearchRequestBuilder {
    long offset = PagingConstants.DEFAULT_PARAM_OFFSET;
    int limit = PagingConstants.DEFAULT_PARAM_LIMIT;

    public DescriptorSearchRequestBuilder offset(long offset) {
      this.offset = offset;
      return this;
    }

    public DescriptorSearchRequestBuilder limit(int limit) {
      this.limit = limit;
      return this;
    }
  }

  public static class CustomBuilder extends DescriptorSearchRequestBuilder {

    @Override
    public DescriptorSearchRequest build() {
      DescriptorSearchRequest descriptorSearchRequest = super.build();
      descriptorSearchRequest.setOffset(offset);
      descriptorSearchRequest.setLimit(limit);
      return descriptorSearchRequest;
    }
  }
}
