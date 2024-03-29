/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.checklistbank;

import org.gbif.api.vocabulary.Extension;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@SuppressWarnings("unused")
public class VerbatimNameUsage {

  private Integer key;
  // the verbatim taxon fields for the usage
  private Map<Term, String> fields = new HashMap<>();
  // the verbatim extension records as read by a dwc star record, keyed on the extension
  private Map<Extension, List<Map<Term, String>>> extensions = new HashMap<>();

  private Date lastCrawled;

  /**
   * @return The name usage key
   */
  @Schema(description = "The name usage key that uniquely identifies this name usage.")
  @NotNull
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * A map of extension records, holding all verbatim extension terms.
   */
  @Schema(description = "Extension records, holding all verbatim extension terms.\n\n" +
    "A map keyed by extension term names, containing lists of property terms + values.")
  @NotNull
  public Map<Extension, List<Map<Term, String>>> getExtensions() {
    return extensions;
  }

  public void setExtensions(Map<Extension, List<Map<Term, String>>> extensions) {
    this.extensions = extensions;
  }

  /**
   * A map holding all verbatim core terms.
   */
  @NotNull
  @JsonIgnore
  public Map<Term, String> getFields() {
    return fields;
  }

  public void setFields(Map<Term, String> fields) {
    this.fields = fields;
  }

  /**
   * The date this record was last crawled during clb indexing.
   */
  @Schema(description = "The date this record was last crawled (downloaded from the source) during Checklistbank indexing.")
  @Nullable
  public Date getLastCrawled() {
    return lastCrawled == null ? null : new Date(lastCrawled.getTime());
  }

  public void setLastCrawled(@Nullable Date lastCrawled) {
    this.lastCrawled = lastCrawled == null ? null : new Date(lastCrawled.getTime());
  }

  /**
   * Get the value of a specific field (Term).
   */
  @Nullable
  public String getCoreField(Term term) {
    Objects.requireNonNull(term, "term can't be null");
    return fields.get(term);
  }

  /**
   * @return true if a verbatim field exists and is not null or an empty string
   */
  public boolean hasCoreField(Term term) {
    Objects.requireNonNull(term, "term can't be null");
    return StringUtils.isNotEmpty(fields.get(term));
  }

  /**
   * @return true if at least one extension record exists
   */
  public boolean hasExtension(Extension extension) {
    return extensions.containsKey(extension) && !extensions.get(extension).isEmpty();
  }

  public boolean hasExtension(Term term) {
    Objects.requireNonNull(term, "term can't be null");
    Extension ext = Extension.fromRowType(term.qualifiedName());

    return ext != null && hasExtension(ext);
  }

  /**
   * For setting a specific field without having to replace the entire fields Map.
   *
   * @param term       the field to set
   * @param fieldValue the field's value
   */
  public void setCoreField(Term term, @Nullable String fieldValue) {
    Objects.requireNonNull(term, "term can't be null");
    fields.put(term, fieldValue);
  }

  /**
   * This private method is only for deserialization via jackson and not exposed anywhere else!
   */
  @JsonAnySetter
  private void addJsonVerbatimField(String key, String value) {
    Term t = TermFactory.instance().findTerm(key);
    fields.put(t, value);
  }

  /**
   * This private method is only for serialization via jackson and not exposed anywhere else!
   * It maps the verbatimField terms into properties with their full qualified name.
   */
  @JsonAnyGetter
  private Map<String, String> jsonVerbatimFields() {
    Map<String, String> extendedProps = new HashMap<>();
    for (Map.Entry<Term, String> prop : fields.entrySet()) {
      extendedProps.put(prop.getKey().qualifiedName(), prop.getValue());
    }
    return extendedProps;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerbatimNameUsage that = (VerbatimNameUsage) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(fields, that.fields) &&
      Objects.equals(extensions, that.extensions) &&
      Objects.equals(lastCrawled, that.lastCrawled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, fields, extensions, lastCrawled);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", VerbatimNameUsage.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("fields=" + fields)
      .add("extensions=" + extensions)
      .add("lastCrawled=" + lastCrawled)
      .toString();
  }
}
