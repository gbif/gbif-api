/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
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

import org.gbif.api.vocabulary.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * A simple class to represent a table of contents for multiple languages.
 * It is used by species pages to generate a table of contents menu with asynchronous loading of full descriptions.
 */
public class TableOfContents {
  private static final String DEFAULT_TOPIC = "general";

  private final Map<Language, Map<String, List<Integer>>> toc = Maps.newTreeMap();

  public void addDescription(int key, Language lang, String topic) {
    topic = Strings.isNullOrEmpty(topic) ? DEFAULT_TOPIC : topic.toLowerCase().trim();

    if (lang == null) {
      // default to english
      lang = Language.ENGLISH;
    }

    if (!toc.containsKey(lang)) {
      toc.put(lang, Maps.<String, List<Integer>>newTreeMap());
    }
    if (!toc.get(lang).containsKey(topic)) {
      toc.get(lang).put(topic, new ArrayList<>());
    }
    toc.get(lang).get(topic).add(key);
  }

  @JsonIgnore
  public boolean isEmpty() {
    return toc.isEmpty();
  }

  /**
   * @return list of all languages available for this ToC
   */
  @JsonIgnore
  public List<Language> listLanguages() {
    return Lists.newArrayList(toc.keySet());
  }

  /**
   * @return map of all topics for a given language with a list of entry keys for each language
   */
  @JsonIgnore
  public Map<String, List<Integer>> listTopicEntries(Language lang) {
    if (toc.containsKey(lang)) {
      return toc.get(lang);
    }
    return Maps.newHashMap();
  }

  public Map<Language, Map<String, List<Integer>>> getToc() {
    return toc;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof TableOfContents)) {
      return false;
    }

    TableOfContents that = (TableOfContents) object;
    return Objects.equal(this.toc, that.toc);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(toc);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("toc", toc)
      .toString();
  }
}
