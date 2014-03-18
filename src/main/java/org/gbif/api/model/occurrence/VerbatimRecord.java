package org.gbif.api.model.occurrence;

import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.jackson.TermDeserializer;
import org.gbif.dwc.terms.jackson.TermKeyDeserializer;
import org.gbif.dwc.terms.jackson.TermSerializer;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Encapsulation of a verbatim record of some sort.
 *
 * Primarily created to avoid (de)serialization problems with Jackson using the Term interface in a nested Map
 * in VerbatimOccurrence.extensions.
 */
public class VerbatimRecord {
  private Map<Term, String> fields = Maps.newHashMap();

  public VerbatimRecord() {
  }

  public VerbatimRecord(Map<Term, String> fields) {
    this.fields = fields;
  }


  @JsonSerialize(using = TermSerializer.class, keyUsing = TermSerializer.class)
  @JsonDeserialize(using = TermDeserializer.class, keyUsing = TermKeyDeserializer.class)
  public Map<Term, String> getFields() {
    return fields;
  }

  public void setFields(Map<Term, String> fields) {
    this.fields = fields;
  }

  /**
   * For setting a specific field without having to replace the entire fields Map.
   *
   * @param term the field to set
   * @param value the field's value
   */
  public void setField(Term term, @Nullable String value) {
    checkNotNull(term, "term can't be null");
    fields.put(term, value);
  }

  /**
   * Get the value of a specific field (Term).
   */
  @Nullable
  public String getField(Term term) {
    checkNotNull(term, "term can't be null");
    return fields.get(term);
  }

  /**
   * @return true if a verbatim field exists and is not null or an empty string
   */
  public boolean hasField(Term term) {
    checkNotNull(term, "term can't be null");
    return !Strings.isNullOrEmpty(fields.get(term));
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
