/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 *
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

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;


/**
 * Reference Model Object represents a literature reference stating a bibliography for a taxon.
 *
 * Since the initial release of the GBIF API version 1.0 ChecklistBank has been modified to only store the entire citation string of a reference and
 * none of the atomised fields which are now deprecated.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/references.xml">Reference Definition</a>
 */
public class Reference implements NameUsageExtension {

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
   * The author or authors of the referenced work.
   * <blockquote>
   * <p>
   * <i>Example:</i> Patricia Hartge.
   * </p>
   * </blockquote>
   *
   * @return the author
   */
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
  @Nullable
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Nullable
  @Override
  public String getSource() {
    return source;
  }

  @Override
  public void setSource(String source) {
    this.source = source;
  }

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
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Reference)) {
      return false;
    }

    Reference that = (Reference) object;
    return Objects.equal(this.citation, that.citation)
           && Objects.equal(this.link, that.link)
           && Objects.equal(this.type, that.type)
           && Objects.equal(this.remarks, that.remarks)
           && Objects.equal(this.doi, that.doi)
           && Objects.equal(this.source, that.source)
           && Objects.equal(this.sourceTaxonKey, that.sourceTaxonKey)
           && Objects.equal(this.title, that.title)
           && Objects.equal(this.author, that.author)
           && Objects.equal(this.date, that.date);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(citation, link, doi, type, remarks, source, sourceTaxonKey, title, author, date);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("citation", citation)
      .add("link", link)
      .add("type", type)
      .add("remarks", remarks)
      .add("doi", doi)
      .add("source", source)
      .add("sourceTaxonKey", sourceTaxonKey)
      .toString();
  }

}
