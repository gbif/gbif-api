/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.registry;

import java.io.Serializable;
import javax.annotation.Nullable;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;


/**
 * A citation can have one or both of a textual component and an identifier which can be
 * of any form, but expected to be resolvable in some way such as an LSID, DOI or similar.
 */
public class Citation implements Serializable {

  private static final long serialVersionUID = 5587531070690709593L;

  @Nullable
  private String text;

  @Nullable
  @Size(min = 1, max = 100)
  private String identifier;

  public Citation() {
  }

  public Citation(String text, String identifier) {
    this.text = text;
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Citation) {
      Citation that = (Citation) obj;
      return Objects.equal(this.text, that.text) && Objects.equal(this.identifier, that.identifier);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(text, identifier);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("text", text)
      .add("identifier", identifier)
      .toString();
  }

}
