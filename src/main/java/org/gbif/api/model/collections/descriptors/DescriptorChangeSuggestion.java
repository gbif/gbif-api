package org.gbif.api.model.collections.descriptors;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import lombok.NoArgsConstructor;

import org.gbif.api.model.collections.suggestions.Status;
import org.gbif.api.model.collections.suggestions.Type;
import org.gbif.api.model.common.export.ExportFormat;
import org.gbif.api.vocabulary.Country;

/**
 * Domain model representing a change suggestion for a descriptor.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DescriptorChangeSuggestion {
  /**
   * Auto-generated key from the database sequence.
   */
  private long key;

  /**
   * Collection identifier to which this change suggestion applies.
   */
  private UUID collectionKey;

  /**
   * Descriptor Group identifier to which this change suggestion applies.
   */
  private Long descriptorGroupKey;

  /**
   * Format of the Descriptor Group
   */
  private ExportFormat format;

  /**
   * The type of change: CREATE, UPDATE, or DELETE.
   */
  private Type type;

  /**
   * Title of the Descriptor Group
   */
  private String title;

  /**
   * Description of the Descriptor Group
   */
  private String description;

  /**
   * The current status: PENDING, APPROVED, or DISCARDED.
   */
  private Status status;

  /**
   * Timestamp when the suggestion was proposed.
   */
  private Date proposed;

  /**
   * Username of the proposer.
   */
  private String proposedBy;

  /**
   * Email address of the proposer.
   */
  private String proposerEmail;

  /**
   * Timestamp when the suggestion was applied.
   */
  private Date applied;

  /**
   * Username who applied the suggestion.
   */
  private String appliedBy;

  /**
   * Timestamp when the suggestion was discarded.
   */
  private Date discarded;

  /**
   * Username who discarded the suggestion.
   */
  private String discardedBy;

  /**
   * Path to the suggested file stored on disk.
   */
  private String suggestedFile;

  /**
   * Optional comments.
   */
  private List<String> comments;

  /**
   * Country of the related collection
   */
  private Country country;

  /**
   * Timestamp when the suggestion was last modified.
   */
  private Date modified;

  /**
   * Username who last modified the suggestion.
   */
  private String modifiedBy;
}

