package org.gbif.api.service.checklistbank;

import org.gbif.api.model.checklistbank.ParsedName;

/**
 * ChecklistBank service dealing with parsed names.
 */
public interface ParsedNameService {

  /**
   * Retrieves a parsed name by its name key.
   */
  ParsedName get(int nameKey);

}
