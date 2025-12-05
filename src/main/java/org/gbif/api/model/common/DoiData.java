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
package org.gbif.api.model.common;

import java.net.URI;
import java.util.Objects;
import java.util.StringJoiner;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data about a DOI with a target URI and a status enumeration.
 */
public class DoiData {
  private final DoiStatus status;
  private final URI target;

  @JsonCreator
  public DoiData(@JsonProperty("status") DoiStatus status, @JsonProperty("target") URI target) {
    this.status = Objects.requireNonNull(status, "DOI status is required");
    this.target = target;
  }

  public DoiData(String ezidStatus, URI target) {
    this.status = Objects.requireNonNull(DoiStatus.fromString(ezidStatus));
    this.target = target;
  }

  public DoiData(DoiStatus status) {
    this.status = Objects.requireNonNull(status, "DOI status is required");
    this.target = null;
  }

  @NotNull
  public DoiStatus getStatus() {
    return status;
  }

  @Nullable
  public URI getTarget() {
    return target;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DoiData doiData = (DoiData) o;
    return status == doiData.status &&
      Objects.equals(target, doiData.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, target);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DoiData.class.getSimpleName() + "[", "]")
      .add("status=" + status)
      .add("target=" + target)
      .toString();
  }
}
