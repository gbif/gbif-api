package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Data;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectQuantity {
  @JsonProperty("SUM")
  private Integer sum;

  @JsonProperty("DETAIL")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  private Map<String, Integer> detail;

  @JsonSetter("DETAIL")
  public void setDetail(Map<String, Object> detail) {
    if (detail != null) {
      this.detail = detail.entrySet().stream()
        .collect(java.util.stream.Collectors.toMap(
          Map.Entry::getKey,
          e -> {
            Object value = e.getValue();
            if (value == null || value.toString().trim().isEmpty()) {
              return 0;
            }
            try {
              return Integer.parseInt(value.toString().trim());
            } catch (NumberFormatException ex) {
              return 0;
            }
          }
        ));
    }
  }
}
