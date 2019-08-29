package org.gbif.api.model;

import java.util.UUID;

/**
 * Encapsulate a challenge code.
 */
public class ChallengeCode {
  private Integer key;
  private UUID code;

  public static ChallengeCode newRandom() {
    ChallengeCode challengeCode = new ChallengeCode();
    challengeCode.setCode(UUID.randomUUID());
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
}

