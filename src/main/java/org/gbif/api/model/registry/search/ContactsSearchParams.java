package org.gbif.api.model.registry.search;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.vocabulary.ContactType;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.GbifRegion;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactsSearchParams extends PageableBase {

  private List<Country> country = new ArrayList<>();
  private List<ContactType> type = new ArrayList<>();
  private List<GbifRegion> gbifRegion = new ArrayList<>();

  public Pageable getPage() {
    return new PagingRequest(offset, limit);
  }
}
