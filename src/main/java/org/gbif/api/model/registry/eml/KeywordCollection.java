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
package org.gbif.api.model.registry.eml;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;


/**
 * Allows a collection of keywords which may declare a thesaurus from which they come from.
 */
public class KeywordCollection implements Serializable, Keywords {

  private static final long serialVersionUID = -4749948152622713533L;

  private String thesaurus;

  private Set<String> keywords = Sets.newHashSet();

  public KeywordCollection() {
  }

  public KeywordCollection(String thesaurus, Set<String> keywords) {
    this.thesaurus = thesaurus;
    this.keywords = keywords;
  }

  public Set<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(Set<String> keywords) {
    this.keywords = keywords;
  }

  public String getThesaurus() {
    return thesaurus;
  }

  public void setThesaurus(String thesaurus) {
    this.thesaurus = thesaurus;
  }

  @Override
  public Collection<String> toKeywords() {
    return keywords;
  }

  /**
   * Add keyword to keywords Set.
   */
  public void addKeyword(String keyword) {
    this.keywords.add(keyword);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof KeywordCollection)) {
      return false;
    }

    KeywordCollection that = (KeywordCollection) obj;
    return Objects.equal(this.thesaurus, that.thesaurus) && Objects.equal(this.keywords, that.keywords);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(thesaurus, keywords);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("thesaurus", thesaurus).add("keywords", keywords).toString();
  }

}
