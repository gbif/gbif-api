package org.gbif.api.model.occurrence;

import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.jackson.TermDeserializer;
import org.gbif.dwc.terms.jackson.TermKeyDeserializer;
import org.gbif.dwc.terms.jackson.TermSerializer;

import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Encapsulation of a verbatim record of some sort.
 *
 * Primarily created to avoid (de)serialization problems with Jackson using the Term interface in a nested Map
 * in VerbatimOccurrence.extensions.
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

  public void setField(Term key, String value) {
    fields.put(key, value);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("fields", fields)
      .toString();
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(fields);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || VerbatimRecord.class != obj.getClass()) {
      return false;
    }
    final VerbatimRecord other = (VerbatimRecord) obj;
    return Objects.equal(this.fields, other.fields);
  }
}
