package org.gbif.api.model.collections;

import java.util.Objects;
import java.util.StringJoiner;

/** Models an alternative code for a collections entity. */
public class AlternativeCode {

  /** Alternative code. */
  private String code;

  private String comment;

  /** Alternative code for a collections entity. */
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  /** Comment to clarify where the alternative code comes from or why it's needed. */
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AlternativeCode that = (AlternativeCode) o;
    return Objects.equals(code, that.code) && Objects.equals(comment, that.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, comment);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AlternativeCode.class.getSimpleName() + "[", "]")
        .add("code='" + code + "'")
        .add("comment='" + comment + "'")
        .toString();
  }
}
