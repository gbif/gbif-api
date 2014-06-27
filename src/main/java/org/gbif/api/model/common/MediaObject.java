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
package org.gbif.api.model.common;

import org.gbif.api.vocabulary.MediaType;

import java.net.URI;
import java.util.Date;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Metadata for a multimedia object representing an image, video or audio file.
 * Based on DublinCore and the Simple Multimedia dwc archive extension:
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/multimedia.xml">Multimedia Definition</a>
 */
public class MediaObject {
  private MediaType type;
  private String format;
  private URI identifier;
  private URI references;
  private String title;
  private String description;
  private String source;
  private String audience;
  private Date created;
  private String creator;
  private String contributor;
  private String publisher;
  private String license;
  private String rightsHolder;

  /**
   * @return mime type of the file
   */
  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  /**
   * The date and time this media item was taken.
   * <blockquote>
   * <p>
   * <i>Example:</i> 2010-09-29.
   * </p>
   * </blockquote>
   *
   * @return the created
   */
  @Nullable
  public Date getCreated() {
    return created;
  }

  /**
   * @param created the created to set
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * The person that created the media item.
   * <blockquote>
   * <p>
   * <i>Example:</i> David Remsen.
   * </p>
   * </blockquote>
   *
   * @return the creator
   */
  @Nullable
  public String getCreator() {
    return creator;
  }

  /**
   * @param creator the creator to set
   */
  public void setCreator(String creator) {
    this.creator = creator;
  }

  /**
   * A longer description for this media item.
   * <blockquote>
   * <p>
   * <i>Example:</i> Female Tachycineta albiventer photographed in the Amazon, Brazil, in November 2010.
   * </p>
   * </blockquote>
   *
   * @return the description
   */
  @Nullable
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @NotNull
  public MediaType getType() {
    return type;
  }

  public void setType(MediaType type) {
    this.type = type;
  }

  /**
   * The public URL that identifies and locates the media item.
   * <blockquote>
   * <p>
   * <i>Example:</i> <a
   * href="http://farm6.static.flickr.com/5127/5242866958_98afd8cbce_o.jpg">http://farm6.static.flickr.com/5127/5242866958_98afd8cbce_o.jpg</a>
   * </p>
   * </blockquote>
   */
  @NotNull
  @JsonProperty("identifier")
  public URI getIdentifier() {
    return identifier;
  }

  public void setIdentifier(URI identifier) {
    this.identifier = identifier;
  }

  /**
   * License for this media item.
   * Can be text or a identifier like creative commons uses.
   * <blockquote>
   * <p>
   * <i>Example:</i> <a
   * href="http://creativecommons.org/licenses/by-nc-sa/2.0/deed.en">http://creativecommons.org/licenses
   * /by-nc-sa/2.0/deed.en</a>
   * </p>
   * </blockquote>
   *
   * @return the license
   */
  @Nullable
  public String getLicense() {
    return license;
  }

  /**
   * @param license the license to set
   */
  public void setLicense(String license) {
    this.license = license;
  }

  /**
   * @return link to html webpage with the media item on
   */
  public URI getReferences() {
    return references;
  }

  public void setReferences(URI references) {
    this.references = references;
  }

  /**
   * An entity responsible for making the media item available.
   * <blockquote>
   * <p>
   * <i>Example:</i> Encyclopedia of Life.
   * </p>
   * </blockquote>
   *
   * @return the publisher
   */
  @Nullable
  public String getPublisher() {
    return publisher;
  }

  /**
   * @param publisher the publisher to set
   */
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  /**
   * The media item title.
   * <blockquote>
   * <p>
   * <i>Example:</i> Andorinha-do-rio (Tachycineta albiventer).
   * </p>
   * </blockquote>
   *
   * @return the title.
   */
  @Nullable
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getAudience() {
    return audience;
  }

  public void setAudience(String audience) {
    this.audience = audience;
  }

  public String getContributor() {
    return contributor;
  }

  public void setContributor(String contributor) {
    this.contributor = contributor;
  }

  public String getRightsHolder() {
    return rightsHolder;
  }

  public void setRightsHolder(String rightsHolder) {
    this.rightsHolder = rightsHolder;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MediaObject)) {
      return false;
    }
    MediaObject that = (MediaObject) obj;
    return Objects.equal(this.identifier, that.identifier)
           && Objects.equal(this.type, that.type)
           && Objects.equal(this.format, that.format)
           && Objects.equal(this.references, that.references)
           && Objects.equal(this.title, that.title)
           && Objects.equal(this.description, that.description)
           && Objects.equal(this.license, that.license)
           && Objects.equal(this.publisher, that.publisher)
           && Objects.equal(this.source, that.source)
           && Objects.equal(this.audience, that.audience)
           && Objects.equal(this.contributor, that.contributor)
           && Objects.equal(this.rightsHolder, that.rightsHolder)
           && Objects.equal(this.creator, that.creator)
           && Objects.equal(this.created, that.created);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(type,
                            format, identifier,
                            references,
                            title,
                            description,
                            license,
                            publisher,
                            source,
                            audience, contributor,
                            rightsHolder,
                            creator,
                            created);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("type", type)
      .add("format", format)
      .add("identifier", identifier)
      .add("references", references)
      .add("title", title)
      .add("description", description)
      .add("license", license)
      .add("publisher", publisher)
      .add("source", source)
      .add("audience", audience)
      .add("contributor", contributor)
      .add("rightsHolder", rightsHolder)
      .add("creator", creator)
      .add("created", created)
      .toString();
  }

}
