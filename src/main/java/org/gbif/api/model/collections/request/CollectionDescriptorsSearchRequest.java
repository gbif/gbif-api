package org.gbif.api.model.collections.request;

import java.time.LocalDate;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gbif.api.util.Range;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TypeStatus;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class CollectionDescriptorsSearchRequest extends CollectionSearchRequest {

  @Nullable private List<Integer> usageKey;
  @Nullable private List<String> usageName;
  @Nullable private List<Rank> usageRank;
  @Nullable private List<Integer> taxonKey;
  @Nullable private List<Country> descriptorCountry;
  @Nullable private String individualCount;
  @Nullable private List<String> identifiedBy;
  @Nullable private Range<LocalDate> dateIdentified;
  @Nullable private List<String> typeStatus;
  @Nullable private List<String> recordedBy;
  @Nullable private List<String> discipline;
  @Nullable private List<String> objectClassification;
  @Nullable private List<String> issue;
}
