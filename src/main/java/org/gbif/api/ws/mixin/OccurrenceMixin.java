package org.gbif.api.ws.mixin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.gbif.api.jackson.DateSerde;

import java.util.Date;

public interface OccurrenceMixin extends LicenseMixin {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonSerialize(using = DateSerde.NoTimezoneDateJsonSerializer.class, keyUsing = DateSerde.NoTimezoneDateJsonSerializer.class)
  Date getDateIdentified();

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonSerialize(using = DateSerde.NoTimezoneDateJsonSerializer.class, keyUsing = DateSerde.NoTimezoneDateJsonSerializer.class)
  Date getEventDate();
}
