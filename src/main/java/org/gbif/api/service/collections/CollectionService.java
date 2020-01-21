package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Collection;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.search.collections.KeyCodeNameResult;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.TagService;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

/**
 * API Service to work with collections.
 */
public interface CollectionService
    extends CrudService<Collection>, ContactService, TagService, IdentifierService {

  /**
   * Pages {@link Collection} entities based on the parameters received.
   *
   * <p>To iterate over <em>all</em> entities you can use code like this: {@code PagingRequest req =
   * new PagingRequest(); PagingResponse<T> response; do { response = service.list(req); for (T obj
   * : response.getResults()) { doStuff(); } req.nextPage(); } while (!response.isEndOfRecords()); }
   *
   * @param query to make a full text search
   * @param institutionKey key of an collection to filter by
   * @param contactKey to filter by a contact
   * @param code code of the collection
   * @param name name of the collection
   * @param page paging parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<Collection> list(
      @Nullable String query,
      @Nullable UUID institutionKey,
      @Nullable UUID contactKey,
      @Nullable String code,
      @Nullable String name,
      @Nullable Pageable page);

  /**
   * Provides access to deleted collections.
   */
  PagingResponse<Collection> listDeleted(@Nullable Pageable page);

  /**
   * Provides a simple suggest service.
   */
  List<KeyCodeNameResult> suggest(@Nullable String q);
}
