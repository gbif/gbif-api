package org.gbif.api.service.collections;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.gbif.api.model.collections.descriptors.DescriptorChangeSuggestion;
import org.gbif.api.model.collections.descriptors.DescriptorChangeSuggestionRequest;
import org.gbif.api.model.collections.suggestions.Status;
import org.gbif.api.model.collections.suggestions.Type;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.vocabulary.Country;

/**
 * Service interface for managing descriptor change suggestions in collections.
 * This service handles the creation, updating, and management of descriptor change suggestions,
 * including file handling and status management.
 *
 * Note: File storage is handled internally using unique identifiers. The provided filename
 * is used only for display and metadata purposes.
 */
public interface DescriptorChangeSuggestionService {

  /**
   * Creates a new descriptor change suggestion.
   *
   * @param fileStream The input stream containing the descriptor data
   * @param filename The original filename (used for display and metadata only)
   * @param request The suggestion request containing metadata
   * @return The created DescriptorChangeSuggestion
   * @throws IOException If there's an error processing the file
   * @throws IllegalArgumentException If the request is invalid
   */
  DescriptorChangeSuggestion createSuggestion(InputStream fileStream, String filename, DescriptorChangeSuggestionRequest request) throws IOException;

  /**
   * Updates an existing descriptor change suggestion.
   * Only pending suggestions can be updated.
   *
   * @param key The key of the suggestion to update
   * @param request The updated suggestion request
   * @param fileStream Optional new file stream to replace the existing one
   * @param filename The original filename if fileStream is provided (used for display and metadata only)
   * @return The updated DescriptorChangeSuggestion
   * @throws IOException If there's an error processing the file
   * @throws IllegalArgumentException If the suggestion is not in PENDING state
   * @throws IllegalStateException If the suggestion cannot be updated
   */
  DescriptorChangeSuggestion updateSuggestion(long key, DescriptorChangeSuggestionRequest request, InputStream fileStream, String filename) throws IOException;

  /**
   * Retrieves a descriptor change suggestion by its key.
   *
   * @param key The key of the suggestion to retrieve
   * @return The DescriptorChangeSuggestion or null if not found
   */
  DescriptorChangeSuggestion getSuggestion(long key);

  /**
   * Retrieves the file associated with a descriptor change suggestion.
   *
   * @param key The key of the suggestion
   * @return InputStream containing the suggestion file data
   * @throws IllegalArgumentException If the suggestion is not found
   * @throws IOException If there's an error reading the file
   */
  InputStream getSuggestionFile(long key) throws IOException;

  /**
   * Applies a pending descriptor change suggestion.
   * This will create or update the descriptor group based on the suggestion.
   *
   * @param key The key of the suggestion to apply
   * @throws IOException If there's an error processing the file
   * @throws IllegalArgumentException If the suggestion is not in PENDING state
   * @throws IllegalStateException If the suggestion cannot be applied
   */
  void applySuggestion(long key) throws IOException;

  /**
   * Discards a pending descriptor change suggestion.
   *
   * @param key The key of the suggestion to discard
   * @throws IllegalArgumentException If the suggestion is not in PENDING state
   * @throws IllegalStateException If the suggestion cannot be discarded
   */
  void discardSuggestion(long key);


  /**
   * Lists descriptor change suggestions with optional filters.
   *
   * @param pageable Pagination details
   * @param status Filter by status (PENDING, APPROVED, DISCARDED)
   * @param type Filter by type (CREATE, UPDATE, DELETE)
   * @param proposerEmail Filter by proposer's email
   * @param collectionKey Filter by collection key
   * @param country Filter by collection's country
   * @return PagingResponse containing the filtered suggestions
   */
  PagingResponse<DescriptorChangeSuggestion> list(Pageable pageable, Status status, Type type, String proposerEmail, UUID collectionKey, Country country);

  /**
   * Counts the total number of descriptor change suggestions based on filters.
   *
   * @param status Filter by status
   * @param type Filter by type
   * @param proposerEmail Filter by proposer's email
   * @param collectionKey Filter by collection key
   * @param country Filter by collection's country
   * @return Total number of matching suggestions
   */
  long count(Status status, Type type, String proposerEmail, UUID collectionKey, Country country);
}
