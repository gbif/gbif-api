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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;

/**
 * Class that represents a generic invocation of operation.
 * This class encapsulates the parameters required to perform the operation by the request consumer.
 *
 * @param <T> content type of the request object
 */
public class Request<T> {

  private OperationType operationType;

  private Map<String, String> context;

  private T content;

  /**
   * @return the content
   */
  public T getContent() {
    return content;
  }

  /**
   * Context contains additional request parameters.
   *
   * @return the context
   */
  public Map<String, String> getContext() {
    return context;
  }

  /**
   * @param context the context to set
   */
  public void setContext(Map<String, String> context) {
    this.context = context;
  }

  /**
   * Type of the operation to be performed.
   * This field is optional.
   *
   * @return the operationType
   */
  protected OperationType getOperationType() {
    return operationType;
  }

  /**
   * @param operationType the operationType to set
   */
  protected void setOperationType(OperationType operationType) {
    this.operationType = operationType;
  }

  /**
   * @param content the content to set
   */
  public void setParameter(T content) {
    this.content = content;
  }

  /**
   * CRUD operation types.
   */
  public enum OperationType {

    CREATE, RETRIEVE, UPDATE, DELETE, SELECT

  }

  /**
   * Builder class for {@link Request} instances.
   */
  public static class Builder<T> {

    private OperationType operationType;
    private Map<String, String> context;
    private T content;

    public Request<T> build() {
      Request<T> request = new Request<T>();
      request.operationType = operationType;
      request.content = content;
      request.context = new ImmutableMap.Builder<String, String>().putAll(context).build();
      return request;
    }

    public Builder<T> content(T content) {
      this.content = content;
      context = new HashMap<String, String>();
      return this;
    }

    public Builder<T> context(Map<String, String> context) {
      this.context = context;
      return this;
    }

    public Builder<T> contextParam(Entry<String, String> entry) {
      context.entrySet().add(entry);
      return this;
    }

    public Builder<T> contextParam(String key, String value) {
      context.put(key, value);
      return this;
    }

    public Builder<T> operationType(OperationType operationType) {
      this.operationType = operationType;
      return this;
    }

  }

}
