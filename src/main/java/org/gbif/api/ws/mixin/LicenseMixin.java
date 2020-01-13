package org.gbif.api.ws.mixin;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.gbif.api.jackson.LicenseSerde;
import org.gbif.api.vocabulary.License;

/**
 * Mixin interface used to serialize license enums into urls.
 */
public interface LicenseMixin {

  @JsonSerialize(using = LicenseSerde.LicenseJsonSerializer.class)
  @JsonDeserialize(using = LicenseSerde.LicenseJsonDeserializer.class)
  License getLicense();
}
