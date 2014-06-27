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
package org.gbif.api.model.common.messaging;

import java.util.ArrayList;
import java.util.List;

/**
 * Response that can contain a list of related responses.
 *
 * @param <T> Content type of the root response
 * @param <R> Content type of each individual related response
 */
public class ResponseChain<T, R> extends Response<T> {

  private List<Response<R>> relatedResponses;

  public ResponseChain() {
    relatedResponses = new ArrayList<Response<R>>();
  }

  public ResponseChain(Response.Status status) {
    super(status);
    relatedResponses = new ArrayList<Response<R>>();
  }

  public ResponseChain(Response.Status status, String messageKey) {
    super(status, messageKey);
    relatedResponses = new ArrayList<Response<R>>();
  }

  public ResponseChain(Response.Status status, T content, String messageKey) {
    super(status, content, messageKey);
    relatedResponses = new ArrayList<Response<R>>();
  }

  /**
   * @return the relatedResponses
   */
  public List<Response<R>> getRelatedResponses() {
    return relatedResponses;
  }

  /**
   * @param relatedResponses the relatedResponses to set
   */
  public void setRelatedResponses(List<Response<R>> relatedResponses) {
    this.relatedResponses = relatedResponses;
  }

  /**
   * Adds a response to the list of related response.
   */
  public void append(Response<R> response) {
    relatedResponses.add(response);
  }

}
