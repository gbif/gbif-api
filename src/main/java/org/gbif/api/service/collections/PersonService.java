package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Person;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.search.collections.PersonSuggestResult;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

/**
 * API Service for the collections-related staff.
 */
public interface PersonService extends CrudService<Person> {

  /**
   * Pages {@link Person} entities based on the parameters received.
   *
   * To iterate over <em>all</em> entities you can use code like this:
   * {@code
   * PagingRequest req = new PagingRequest();
   * PagingResponse<T> response;
   * do {
   *   response = service.list(req);
   *   for (T obj : response.getResults()) {
   *     doStuff();
   *   }
   *   req.nextPage();
   * } while (!response.isEndOfRecords());
   * }
   *
   * @param query          to make a full text search
   * @param institutionKey key of an institution to filter by
   * @param collectionKey  key of a collection to filter by
   * @param page           paging parameters
   *
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<Person> list(@Nullable String query, @Nullable UUID institutionKey, @Nullable UUID collectionKey, @Nullable Pageable page);

  /**
   * Provides a simple suggest service.
   */
  List<PersonSuggestResult> suggest(@Nullable String q);
}
