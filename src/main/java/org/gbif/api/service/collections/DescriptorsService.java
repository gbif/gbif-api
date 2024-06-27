package org.gbif.api.service.collections;

import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.gbif.api.model.collections.descriptors.Descriptor;
import org.gbif.api.model.collections.descriptors.DescriptorSet;
import org.gbif.api.model.collections.request.DescriptorSearchRequest;
import org.gbif.api.model.collections.request.DescriptorSetSearchRequest;
import org.gbif.api.model.common.export.ExportFormat;
import org.gbif.api.model.common.paging.PagingResponse;

/** API service to work with collection descriptors. */
public interface DescriptorsService {

  /**
   * Creates a new descriptor set.
   *
   * <p>// TODO
   *
   * @return key of the created descriptor set.
   */
  long createDescriptorSet(
      @NotNull @Valid byte[] descriptorsSetFile,
      @NotNull ExportFormat format,
      @NotNull String title,
      String description,
      @NotNull UUID collectionKey);

  /**
   * Deletes a descriptor set by key.
   *
   * @param key of the descriptor set to be deleted.
   */
  void deleteDescriptorSet(@NotNull long key);

  /**
   * Retrieves a descriptor set by its key.
   *
   * @param key of the descriptor set to be retrieved.
   * @return the descriptor set
   */
  DescriptorSet getDescriptorSet(@NotNull long key);

  /**
   * Updates an existing descriptor set.
   *
   * @param // TODO
   */
  void updateDescriptorSet(
      @NotNull long descriptorSetKey,
      @NotNull byte[] descriptorsSetFile,
      @NotNull ExportFormat format,
      @NotNull String title,
      String description);

  /**
   * Pages {@link DescriptorSet} entities based on the parameters received.
   *
   * @param searchRequest {@link DescriptorSetSearchRequest} with all the parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<DescriptorSet> listDescriptorSets(
      @NotNull UUID collectionKey, DescriptorSetSearchRequest searchRequest);

  /**
   * Retrieves a descriptor by its key.
   *
   * @param key of the descriptor to be retrieved.
   * @return the descriptor
   */
  Descriptor getDescriptor(@NotNull long key);

  /**
   * Pages {@link Descriptor} entities based on the parameters received.
   *
   * @param searchRequest {@link DescriptorSearchRequest} with all the parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<Descriptor> listDescriptors(DescriptorSearchRequest searchRequest);
}
