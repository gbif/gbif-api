package org.gbif.api.ws.mixin;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.gbif.api.jackson.DateSerde;

import java.util.Date;

public interface OccurrenceMixin extends LicenseMixin {

  @JsonSerialize(using = DateSerde.NoTimezoneDateJsonSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
  Date getDateIdentified();

  @JsonSerialize(using = DateSerde.NoTimezoneDateJsonSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
  Date getEventDate();
}
