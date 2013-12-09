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

import org.gbif.api.vocabulary.Extension;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class VerbatimNameUsage {

  private Integer key;

  // the verbatim taxon fields for the usage
  private Map<String, String> fields = Maps.newHashMap();

  // the verbatim extension records as read by a dwc star record, keyed on the extension
  private Map<Extension, List<Map<String, String>>> extensions = Maps.newHashMap();

  public Map<Extension, List<Map<String, String>>> getExtensions() {
    return extensions;
  }

  public void setExtensions(Map<Extension, List<Map<String, String>>> extensions) {
    this.extensions = extensions;
  }

  public Map<String, String> getFields() {
    return fields;
  }

  public void setFields(Map<String, String> fields) {
    this.fields = fields;
  }

  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

}
