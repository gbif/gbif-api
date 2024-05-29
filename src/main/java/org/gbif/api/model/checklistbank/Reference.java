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

import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Reference Model Object represents a literature reference stating a bibliography for a taxon.
 *
 * Since the initial release of the GBIF API version 1.0 ChecklistBank has been modified to only store the entire citation string of a reference and
 * none of the atomised fields which are now deprecated.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/references.xml">Reference Definition</a>
 */
@SuppressWarnings("unused")
public class Reference implements NameUsageExtension {

  private Integer taxonKey;
  private String citation;
  private String link;
  private String doi;
  private String type;
  private String remarks;
  private String source;
  private Integer sourceTaxonKey;

  @Deprecated
  private String title;
  @Deprecated
  private String author;
  @Deprecated
  private String date;

  /**
   * The name usage "taxon" key this description belongs to.
   */
  @Schema(description = "The name usage “taxon“ key to which this species profile belongs.")
  @Override
  public Integer getTaxonKey() {
    return taxonKey;
  }

  @Override
  public void setTaxonKey(Integer taxonKey) {
    this.taxonKey = taxonKey;
  }

  /**
   * The author or authors of the referenced work.
   * <blockquote>
   * <p>
   * <i>Example:</i> Patricia Hartge.
   * </p>
   * </blockquote>
   *
   * @return the author
   */
  @Schema(description = "The author or authors of the referenced work.")
  @Nullable
  @Deprecated
  public String getAuthor() {
    return author;
  }

  /**
   * @param author the author to set
   */
  @Deprecated
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * A text string referring to an un-parsed bibliographic citation.
   * The full citation given here should include all the details potentially found in the other atomised fields,
   * i.e. includes authorship, title, etc.
   * <p/>
   * <blockquote>
   * <p>
   * <i>Example:</i> Hartge, P., Genetics of reproductive lifespan. Nature Genetics 41, 637 - 638 (2009).
   * </p>
   * </blockquote>
   *
   * @return the citation
   */
  @Schema(description = "A text string referring to an un-parsed bibliographic citation.")
  @NotNull
  public String getCitation() {
    return citation;
  }

  /**
   * @param citation the citation to set
   */
  public void setCitation(String citation) {
    this.citation = citation;
  }

  /**
   * Date of publication, recommended ISO format YYYY or YYYY-MM-DD.
   *
   * @return the publication date
   */
  @Schema(description = "Date of publication.")
  @Nullable
  @Deprecated
  public String getDate() {
    return date;
  }

  @Deprecated
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * The pure DOI for the publication without potential http resolver or URI prefix.
   * For example {@code 10.1038/ng0609-637} instead of {@code doi:10.1038/ng0609-637} or {@code
   * http://dx.doi.org/10.1038/ng0609-637}. A valid DOI always starts with {@code 10.}.
   *
   * @return the doi
   *
   * @see <a href="http://www.crossref.org/01company/15doi_info.html">Crossref DOI Info</a>
   * @see <a href="http://www.doi.org/hb.html">DOI Handbook</a>
   * @see <a href="http://de.wikipedia.org/wiki/Digital_Object_Identifier">Wikipedia</a>
   */
  @Schema(
    description = "The pure DOI for the publication without potential http resolver or URI prefix.",
    example = "10.1038/ng0609-637"
  )
  @Nullable
  public String getDoi() {
    return doi;
  }

  public void setDoi(String doi) {
    this.doi = doi;
  }

  /**
   * Link.
   *
   * @return the link
   */
  @Schema(description = "The reference link.")
  @Nullable
  public String getLink() {
    return link;
  }

  /**
   * @param link the link to set
   */
  public void setLink(String link) {
    this.link = link;
  }

  /**
   * Annotation of taxon-specific information related to the referenced publication.
   * <p/>
   * <blockquote>
   * <p>
   * <i>Examples:</i> "transferred H. nigritarsus to Acanolonia"; "Type specimen is a skeleton"
   * </p>
   * </blockquote>
   *
   * @return the taxonomic remarks
   */
  @Schema(description = "Annotation of taxon-specific information related to the referenced publication.")
  @Nullable
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * Title of book or article.
   * <blockquote>
   * <p>
   * <i>Example:</i> "Genetics of reproductive lifespan", "Field Guide to Moths of Eastern North America".
   * </p>
   * </blockquote>
   *
   * @return the title
   */
  @Schema(description = "Title of book or article.")
  @Nullable
  @Deprecated
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  @Deprecated
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Used to assign a bibliographic reference to list of taxonomic or nomenclatural categories. Best practice is to use
   * a controlled vocabulary.
   * <blockquote>
   * <p>
   * <i>Examples:</i> Original publication of new combination (comb nov.)
   * </p>
   * </blockquote>
   *
   * @return the publication type
   */
  @Schema(description = "Used to assign a bibliographic reference to list of taxonomic or nomenclatural categories.")
  @Nullable
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Schema(description = "Bibliographic citation referencing a source for the reference.")
  @Nullable
  @Override
  public String getSource() {
    return source;
  }

  @Override
  public void setSource(String source) {
    this.source = source;
  }

  @Schema(description = "The name usage key of the taxon in the checklist including this reference.")
  @Nullable
  @Override
  public Integer getSourceTaxonKey() {
    return sourceTaxonKey;
  }

  @Override
  public void setSourceTaxonKey(Integer sourceTaxonKey) {
    this.sourceTaxonKey = sourceTaxonKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reference reference = (Reference) o;
    return Objects.equals(citation, reference.citation) &&
      Objects.equals(link, reference.link) &&
      Objects.equals(doi, reference.doi) &&
      Objects.equals(type, reference.type) &&
      Objects.equals(remarks, reference.remarks) &&
      Objects.equals(source, reference.source) &&
      Objects.equals(sourceTaxonKey, reference.sourceTaxonKey) &&
      Objects.equals(title, reference.title) &&
      Objects.equals(author, reference.author) &&
      Objects.equals(date, reference.date);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(citation, link, doi, type, remarks, source, sourceTaxonKey, title, author,
        date);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Reference.class.getSimpleName() + "[", "]")
      .add("taxonKey=" + taxonKey)
      .add("citation='" + citation + "'")
      .add("link='" + link + "'")
      .add("doi='" + doi + "'")
      .add("type='" + type + "'")
      .add("remarks='" + remarks + "'")
      .add("source='" + source + "'")
      .add("sourceTaxonKey=" + sourceTaxonKey)
      .add("title='" + title + "'")
      .add("author='" + author + "'")
      .add("date='" + date + "'")
      .toString();
  }
}
