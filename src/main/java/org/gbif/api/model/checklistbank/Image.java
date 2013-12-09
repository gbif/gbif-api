/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
 * Image Model Object represents a simple image (for species or occurrences).
 * TODO: make use of comomon-api Image class
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/images.xml">Image Definition</a>
 */
public class Image extends NameUsageComponent {

  private String image;
  private String link;
  private String title;
  private String description;
  private String license;
  private String publisher;
  private String creator;
  private String created;

  /**
   * The date and time this image was taken.
   * <blockquote>
   * <p>
   * <i>Examples:</i>
   * <ul>
   *   <li>2010-09-29</li>
   *   <li>unknown, before 1882 (date of publication)</li>
   * </ul>
   * </p>
   * </blockquote>
   *
   * @return the created
   */
  @Nullable
  public String getCreated() {
    return created;
  }

  /**
   * @param created the created to set
   */
  public void setCreated(String created) {
    this.created = created;
  }

  /**
   * The person that took the image.
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
   * A longer description for this image.
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

  /**
   * The public URL that identifies and locates the image.
   * <blockquote>
   * <p>
   * <i>Example:</i> <a
   * href="http://farm6.static.flickr.com/5127/5242866958_98afd8cbce_o.jpg">http://farm6.static.flickr
   * .com/5127/5242866958_98afd8cbce_o.jpg</a>
   * </p>
   * </blockquote>
   *
   * @return the image
   */
  @NotNull
  public String getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * License for this image. Can be text or a url like creative commons uses.
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
   * @return link to html webpage with the image on
   */
  public String getLink() {
    return link;
  }

  /**
   * @param link to html webpage
   */
  public void setLink(String link) {
    this.link = link;
  }

  /**
   * An entity responsible for making the image available.
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
   * The image title.
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

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Image)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    Image that = (Image) object;
    return Objects.equal(this.image, that.image)
           && Objects.equal(this.title, that.title)
           && Objects.equal(this.description, that.description)
           && Objects.equal(this.license, that.license)
           && Objects.equal(this.publisher, that.publisher)
           && Objects.equal(this.creator, that.creator)
           && Objects.equal(this.created, that.created)
           && Objects.equal(this.link, that.link);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(),
                            image,
                            title,
                            description,
                            license,
                            publisher,
                            creator,
                            created,
                            link);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("super", super.toString())
      .add("image", image)
      .add("title", title)
      .add("description", description)
      .add("license", license)
      .add("publisher", publisher)
      .add("creator", creator)
      .add("link", link)
      .add("created", created)
      .toString();
  }

}
