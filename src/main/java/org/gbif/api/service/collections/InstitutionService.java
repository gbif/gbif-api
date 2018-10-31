package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Institution;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.TagService;

public interface InstitutionService
  extends CrudService<Institution>, ContactService, TagService, IdentifierService {

}
