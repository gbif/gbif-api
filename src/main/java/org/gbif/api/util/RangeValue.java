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
package org.gbif.api.util;

import java.util.Objects;

import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A range used for predicates allowing including or excluding the end values.
 */
public class RangeValue {

  @JsonCreator
  public RangeValue(
      @Nullable @JsonProperty("gte") String gte,
      @Nullable @JsonProperty("gt") String gt,
      @Nullable @JsonProperty("lte") String lte,
      @Nullable @JsonProperty("lt") String lt) {

    if (Objects.isNull(gte) && Objects.isNull(gt)) {
      throw new IllegalArgumentException("Specify gte or gt");
    }
    if (Objects.isNull(lte) && Objects.isNull(lt)) {
      throw new IllegalArgumentException("Specify lte or lt");
    }
    if (!Objects.isNull(gte) && !Objects.isNull(gt)) {
      throw new IllegalArgumentException("Specify gte or gt, not both");
    }
    if (lte != null && lt != null) {
      throw new IllegalArgumentException("Specify lte or lt, not both");
    }
    this.gte = gte;
    this.lte = lte;
    this.gt = gt;
    this.lt = lt;
  }

  @JsonProperty("gt")
  String gt;

  @JsonProperty("lt")
  String lt;

  @JsonProperty("gte")
  String gte;

  @JsonProperty("lte")
  String lte;

  public String getGte() {
    return gte;
  }

  public String getLte() {
    return lte;
  }

  public String getGt() {
    return gt;
  }

  public String getLt() {
    return lt;
  }
}
