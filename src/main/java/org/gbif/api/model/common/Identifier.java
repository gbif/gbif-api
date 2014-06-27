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

import org.gbif.api.util.IdentifierUtils;
import org.gbif.api.vocabulary.IdentifierType;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

/**
 * Identifier Model Object represents an alternative identifier for an occurrence or name usage.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/identifier.xml">Identifier Definition</a>
 */
public class Identifier {

  private String identifier;
  private String title;
  private IdentifierType type;

  /**
   * Other known identifier used for the same taxon. Can be a URL pointing to a webpage, an xml or rdf document, a DOI,
   * UUID or any other identifer.
   * <blockquote>
   * <p>
   * <i>Example:</i> urn:lsid:ipni.org:names:692570-1:1.4
   * </p>
   * </blockquote>
   *
   * @return the identifier.
   */
  @NotNull
  public String getIdentifier() {
    return identifier;
  }

  /**
   * @param identifier the identifier to set
   */
  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  /**
   * The optional title of an identifier, mostly for linking.
   *
   * @return the title
   */
  @Nullable
  public String getTitle() {
    return title;
  }

  /**
   * @param title the identifier title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Type of identifier.
   *
   * @return the type
   *
   * @see IdentifierType
   */
  @NotNull
  public IdentifierType getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(IdentifierType type) {
    this.type = type;
  }

  /**
   * Creates a http link for an identifier if possible by passing it to some known resolvers for the specific id type.
   * If no link can be constructed, null is returned.
   *
   * @return the url or null if it cannot be created
   *
   * @see org.gbif.api.util.IdentifierUtils#getIdentifierLink(String, org.gbif.api.vocabulary.IdentifierType)
   */
  @Nullable
  public String getIdentifierLink() {
    return IdentifierUtils.getIdentifierLink(identifier, type);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Identifier)) {
      return false;
    }

    Identifier that = (Identifier) obj;
    return Objects.equal(this.identifier, that.identifier)
           && Objects.equal(this.type, that.type)
           && Objects.equal(this.title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(identifier, type, title);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("identifier", identifier)
      .add("type", type)
      .add("title", title)
      .toString();
  }

}
