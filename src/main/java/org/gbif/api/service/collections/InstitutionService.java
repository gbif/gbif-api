package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Institution;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.TagService;

/**
 * Service for institutions in the collections context.
 */
public interface InstitutionService
  extends CrudService<Institution>, ContactService, TagService, IdentifierService {

}
