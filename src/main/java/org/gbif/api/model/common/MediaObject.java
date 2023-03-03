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
package org.gbif.api.model.common;

import org.gbif.api.vocabulary.MediaType;

import java.net.URI;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Metadata for a multimedia object representing an image, video or audio file.
 * Based on DublinCore and the Simple Multimedia dwc archive extension:
 *
 * @see <a href="https://rs.gbif.org/terms/1.0/Multimedia">Multimedia Definition</a>
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
  @Schema(description = "The format the image is exposed in.")
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
  @Schema(description = "The date and time this media item was created.")
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
  @Schema(description = "The person that created the media item.")
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
  @Schema(description = "A longer description for this media item.")
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

  @Schema(description = "The kind of media object.")
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
  @Schema(description = "The public URL that identifies and locates the media item.")
  @NotNull
  @JsonProperty("identifier")
  public URI getIdentifier() {
    return identifier;
  }

  public void setIdentifier(URI identifier) {
    this.identifier = identifier;
  }

  /**
   * Licence for this media item.
   * Can be text or an identifier like Creative Commons uses.
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
  @Schema(description = "Licence for this media item.")
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
  @Schema(description = "An HTML webpage that shows the image or its metadata.")
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
  @Schema(description = "An entity responsible for making the media item available.")
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
  @Schema(description = "The media item title.")
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

  @Schema(description = "A class or description for whom the image is intended or useful.")
  public String getAudience() {
    return audience;
  }

  public void setAudience(String audience) {
    this.audience = audience;
  }

  @Schema(description = "Any contributor in addition to the creator that helped in recording the media item.")
  public String getContributor() {
    return contributor;
  }

  public void setContributor(String contributor) {
    this.contributor = contributor;
  }

  @Schema(description = "A person or organization owning or managing rights over the media item.")
  public String getRightsHolder() {
    return rightsHolder;
  }

  public void setRightsHolder(String rightsHolder) {
    this.rightsHolder = rightsHolder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediaObject that = (MediaObject) o;
    return type == that.type &&
      Objects.equals(format, that.format) &&
      Objects.equals(identifier, that.identifier) &&
      Objects.equals(references, that.references) &&
      Objects.equals(title, that.title) &&
      Objects.equals(description, that.description) &&
      Objects.equals(source, that.source) &&
      Objects.equals(audience, that.audience) &&
      Objects.equals(created, that.created) &&
      Objects.equals(creator, that.creator) &&
      Objects.equals(contributor, that.contributor) &&
      Objects.equals(publisher, that.publisher) &&
      Objects.equals(license, that.license) &&
      Objects.equals(rightsHolder, that.rightsHolder);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(type, format, identifier, references, title, description, source, audience, created,
        creator, contributor, publisher, license, rightsHolder);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", MediaObject.class.getSimpleName() + "[", "]")
      .add("type=" + type)
      .add("format='" + format + "'")
      .add("identifier=" + identifier)
      .add("references=" + references)
      .add("title='" + title + "'")
      .add("description='" + description + "'")
      .add("source='" + source + "'")
      .add("audience='" + audience + "'")
      .add("created=" + created)
      .add("creator='" + creator + "'")
      .add("contributor='" + contributor + "'")
      .add("publisher='" + publisher + "'")
      .add("license='" + license + "'")
      .add("rightsHolder='" + rightsHolder + "'")
      .toString();
  }
}
