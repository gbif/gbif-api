/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
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
package org.gbif.api.service.registry;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.model.registry.Installation;
import org.gbif.api.model.registry.Organization;
import org.gbif.api.model.registry.search.KeyTitleResult;
import org.gbif.api.model.registry.search.OrganizationRequestSearchParams;
import org.gbif.api.vocabulary.Country;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.geojson.FeatureCollection;

@SuppressWarnings("unused")
public interface OrganizationService extends NetworkEntityService<Organization> {

  /**
   * Provides paging service to list datasets hosted by, but not owned by, a specific organization.
   */
  PagingResponse<Dataset> hostedDatasets(@NotNull UUID organizationKey, @Nullable Pageable page);

  /** Provides paging service to list datasets published by a specific organization. */
  PagingResponse<Dataset> publishedDatasets(@NotNull UUID organizationKey, @Nullable Pageable page);

  /** Provides paging service to list installations for the organization. */
  PagingResponse<Installation> installations(
      @NotNull UUID organizationKey, @Nullable Pageable page);

  /** Provides access to all organizations from a country. */
  PagingResponse<Organization> listByCountry(Country country, @Nullable Pageable page);

  /** Provides access to deleted organizations. */
  PagingResponse<Organization> listDeleted(OrganizationRequestSearchParams searchParams);

  /** Provides access to organizations that are awaiting their endorsement approval. */
  PagingResponse<Organization> listPendingEndorsement(@Nullable Pageable page);

  /** Provides access to organizations that are not publishing (e.g. owning) any datasets. */
  PagingResponse<Organization> listNonPublishing(@Nullable Pageable page);

  /** Provides a simple suggest service. */
  List<KeyTitleResult> suggest(@Nullable String q);

  /**
   * Confirm the endorsement of a new {@link Organization} by providing a confirmationKey.
   * Confirming the endorsement of an {@link Organization} may not be required or possible depending
   * how the {@link Organization} was created.
   *
   * @param organizationKey key of the organization
   * @param confirmationKey (aka challenge code)
   * @return endorsement was confirmed using the provided keys
   */
  boolean confirmEndorsement(@NotNull UUID organizationKey, @NotNull UUID confirmationKey);

  /**
   * Confirm the endorsement of a new {@link Organization} without a confirmationKey. Confirming the
   * endorsement of an {@link Organization} may not be required or possible depending how the {@link
   * Organization} was created.
   *
   * @param organizationKey key of the organization
   * @return endorsement was confirmed using the provided keys
   */
  boolean confirmEndorsement(@NotNull UUID organizationKey);

  /**
   * Revoke the endorsement of the {@link Organization}.
   *
   * @param organizationKey key of the organization
   * @return endorsement was confirmed using the provided keys
   */
  boolean revokeEndorsement(@NotNull UUID organizationKey);

  /**
   * Provides paging service to list organizations that can be filtered by multiple parameters.
   *
   * @param searchParams {@link OrganizationRequestSearchParams}
   * @return list of organizations ordered by creation date with the latest coming first
   */
  PagingResponse<Organization> list(OrganizationRequestSearchParams searchParams);

  /**
   * Lists the organizations in GeoJson format.
   *
   * @param request parameters to filter the request
   * @return a {@link FeatureCollection} object that conforms with GeoJson
   */
  FeatureCollection listGeoJson(OrganizationRequestSearchParams request);
}
