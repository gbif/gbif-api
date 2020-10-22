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
package org.gbif.api.model;

import java.util.StringJoiner;
import java.util.UUID;

/**
 * Encapsulate a challenge code.
 */
public class ChallengeCode {

  private Integer key;
  private UUID code;
  // additional data (may be an email address or something)
  private String data;

  public static ChallengeCode newRandom() {
    ChallengeCode challengeCode = new ChallengeCode();
    challengeCode.setCode(UUID.randomUUID());
    return challengeCode;
  }

  public static ChallengeCode newRandom(String data) {
    ChallengeCode challengeCode = newRandom();
    challengeCode.setData(data);
    return challengeCode;
  }

  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  public UUID getCode() {
    return code;
  }

  public void setCode(UUID code) {
    this.code = code;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ChallengeCode.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("code=" + code)
        .add("data='" + data + "'")
        .toString();
  }
}
