/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.service.collections;

import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.model.collections.Institution;
import org.gbif.api.model.collections.latimercore.OrganisationalUnit;
import org.gbif.api.model.collections.request.InstitutionSearchRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.search.collections.KeyCodeNameResult;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.geojson.FeatureCollection;

/** Service for institutions in the collections context. */
public interface InstitutionService extends CollectionEntityService<Institution> {

  /**
   * Pages {@link Institution} entities based on the parameters received.
   *
   * <p>To iterate over <em>all</em> entities you can use code like this: {@code PagingRequest req =
   * new PagingRequest(); PagingResponse<T> response; do { response = service.list(req); for (T obj
   * : response.getResults()) { doStuff(); } req.nextPage(); } while (!response.isEndOfRecords()); }
   *
   * @param searchRequest {@link InstitutionSearchRequest} with all the parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<Institution> list(InstitutionSearchRequest searchRequest);

  /**
   * Similar to the {@link #list(InstitutionSearchRequest)} method but returns the results in
   * Latimer Core format.
   */
  PagingResponse<OrganisationalUnit> listAsLatimerCore(InstitutionSearchRequest searchRequest);

  /** Similar to the {@link #get(UUID)} method but returns the results in Latimer Core format. */
  OrganisationalUnit getAsLatimerCore(@NotNull UUID key);

  /** Similar to {@link #create(CollectionEntity)} but it accepts Latimer Core. */
  UUID createFromLatimerCore(@NotNull @Valid OrganisationalUnit organisationalUnit);

  /** Similar to {@link #update(CollectionEntity)})} but it accepts Latimer Core. */
  void updateFromLatimerCore(@NotNull @Valid OrganisationalUnit entity);

  /** Provides access to deleted institutions. */
  PagingResponse<Institution> listDeleted(InstitutionSearchRequest searchRequest);

  /** Provides a simple suggest service. */
  List<KeyCodeNameResult> suggest(@Nullable String q);

  /** Converts an institution into a collection. The institution converted is deleted. */
  void convertToCollection(UUID targetEntityKey, UUID collectionKey);

  /**
   * Creates a {@link Institution} from a {@link org.gbif.api.model.registry.Organization}.
   *
   * @param organizationKey key of the dataset to create the institution from
   * @param institutionCode the code to assign to the institution since it can't be inferred from
   *     the organization
   * @return UUID of the created institution
   */
  UUID createFromOrganization(UUID organizationKey, String institutionCode);

  /**
   * Lists the institutions in GeoJson format.
   *
   * @param searchRequest parameters to filter the request
   * @return a {@link FeatureCollection} object that conforms with GeoJson
   */
  FeatureCollection listGeojson(InstitutionSearchRequest searchRequest);
}
