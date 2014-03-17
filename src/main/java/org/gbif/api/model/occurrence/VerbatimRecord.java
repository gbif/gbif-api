package org.gbif.api.model.occurrence;

import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.jackson.TermDeserializer;
import org.gbif.dwc.terms.jackson.TermKeyDeserializer;
import org.gbif.dwc.terms.jackson.TermSerializer;

import java.util.Map;

import com.google.common.collect.Maps;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 */
public class VerbatimRecord {
  private Map<Term, String> fields = Maps.newHashMap();

  @JsonSerialize(using = TermSerializer.class, keyUsing = TermSerializer.class)
  @JsonDeserialize(using = TermDeserializer.class, keyUsing = TermKeyDeserializer.class)
  public Map<Term, String> getFields() {
    return fields;
  }

  public void setFields(Map<Term, String> fields) {
    this.fields = fields;
  }
}
