package org.gbif.api.model.occurrence.sql;

import org.gbif.api.model.occurrence.sql.Query.Issue;

import java.util.List;

/**
 * Result of a SQL Query Validation.
 */
public class   ValidationResult {

  private String sql;
  private List<Query.Issue> issues;
  private boolean success;
  private List<String> explain;

  public ValidationResult() {}

  /**
   * Full constructor.
   */
  public ValidationResult(String sql, List<Issue> issues, List<String> queryExplanation, boolean success) {
    this.sql = sql;
    this.issues = issues;
    this.success = success;
    this.explain = queryExplanation;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getSql() {
    return sql;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public List<Query.Issue> getIssues() {
    return issues;
  }

  public void setIssues(List<Query.Issue> issues) {
    this.issues = issues;
  }

  public List<String> getExplain() {
    return explain;
  }

  public void setExplain(List<String> explain) {
    this.explain = explain;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public static class Builder {

    private String sql;
    private List<Query.Issue> issues;
    private boolean success;
    private List<String> explain;

    public Builder sql(String sql) {
      this.sql = sql;
      return this;
    }

    public Builder issues(List<Query.Issue> issues) {
      this.issues = issues;
      return this;
    }

    public Builder success(boolean success) {
      this.success = success;
      return this;
    }

    public Builder explain(List<String> explain) {
      this.explain = explain;
      return this;
    }

    public ValidationResult build() {
      ValidationResult validationResult = new ValidationResult();
      validationResult.sql = sql;
      validationResult.issues = issues;
      validationResult.success = success;
      validationResult.explain = explain;
      return validationResult;
    }
  }
}
