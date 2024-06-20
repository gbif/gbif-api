package org.gbif.api.service.collections;

import org.gbif.api.model.collections.descriptors.Descriptor;
import org.gbif.api.model.collections.descriptors.DescriptorRecord;
import org.gbif.api.model.collections.request.DescriptorRecordsSearchRequest;
import org.gbif.api.model.collections.request.DescriptorsSearchRequest;
import org.gbif.api.model.common.export.ExportFormat;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** API service to work with collection descriptors. */
public interface DescriptorsService {

  /**
   * Creates a new descriptor.
   *
   * <p>// TODO
   *
   * @return key of the created descriptor.
   */
  long createDescriptor(
      @NotNull @Valid byte[] descriptorsFile,
      @NotNull ExportFormat format,
      @NotNull String title,
      String description,
      @NotNull UUID collectionKey);

  /**
   * Deletes an descriptor by key.
   *
   * @param key of the descriptor to be deleted.
   */
  void deleteDescriptor(@NotNull long key);

  /**
   * Retrieves an descriptor by its key.
   *
   * @param key of the descriptor to be retrieved.
   * @return the descriptor
   */
  Descriptor getDescriptor(@NotNull long key);

  /**
   * Updates an existing descriptor.
   *
   * @param // TODO
   */
  void updateDescriptor(
      @NotNull long descriptorKey,
      @NotNull byte[] descriptorsFile,
      @NotNull ExportFormat format,
      @NotNull String title,
      String description);

  /**
   * Pages {@link Descriptor} entities based on the parameters received.
   *
   * @param searchRequest {@link DescriptorsSearchRequest} with all the parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<Descriptor> listDescriptors(
      @NotNull UUID collectionKey, DescriptorsSearchRequest searchRequest);

  /**
   * Retrieves a descriptor record by its key.
   *
   * @param key of the descriptor record to be retrieved.
   * @return the descriptor record
   */
  DescriptorRecord getDescriptorRecord(@NotNull long key);

  /**
   * Pages {@link DescriptorRecord} entities based on the parameters received.
   *
   * @param searchRequest {@link DescriptorRecordsSearchRequest} with all the parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<DescriptorRecord> listDescriptorRecords(DescriptorRecordsSearchRequest searchRequest);
}
