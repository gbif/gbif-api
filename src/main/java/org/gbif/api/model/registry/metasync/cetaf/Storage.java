package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Storage {
    private Map<String, StorageCategory> methods = new HashMap<>();

    @JsonAnySetter
    public void setMethod(String name, StorageCategory value) {
        methods.put(name, value);
    }
}

