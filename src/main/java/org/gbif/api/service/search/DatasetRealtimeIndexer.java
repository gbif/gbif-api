package org.gbif.api.service.search;

import org.gbif.api.model.registry.Dataset;
import org.gbif.api.model.registry.Installation;
import org.gbif.api.model.registry.Organization;

public interface DatasetRealtimeIndexer {

  /**
   * Creates or Updates asynchronously an existing dataset in Elasticsearch.
   */
  void index(Dataset dataset);

  void index(Iterable<Dataset> datasets);

  void index(Organization organization);

  void index(Installation installation);

  /**
   * Deletes asynchronously a dataset from the Elasticsearch index if it exists.
   */
  void delete(Dataset dataset);
}
