package org.gbif.api.model.occurrence.sql;

import org.gbif.api.model.occurrence.sql.serde.QueryIssueSerde;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class Query {

  private static final String FORMAT =
    "Format of LEGAL query is : SELECT ... \nFROM occurrence \nWHERE ... \nGROUP BY ...";

  @JsonSerialize(using = QueryIssueSerde.class)
  public enum Issue {

    NO_ISSUE("No Issue."),
    EMPTY_SQL("SQL query cannot be empty."),
    ONLY_ONE_SELECT_ALLOWED("SQL query should be a SELECT query with SELECT keyword used only once."),
    DDL_JOINS_UNION_NOT_ALLOWED("SQL query cannot use ORDER BY, INSERT, UPDATE, DELETE, MERGE, PROCEDURE_CALL, EXCEPT, INTERSECT, UNION, JOIN or AS."),
    DATASET_AND_LICENSE_REQUIRED("SQL query should select on 'datasetkey' and 'license' fields as they are required for citations."),
    CANNOT_EXECUTE("Query cannot be executed because of"),
    TABLE_NAME_NOT_OCCURRENCE("Query should have table name as 'occurrence'."),
    PARSE_FAILED(String.format("Cannot parse the query, the sql query is invalid, Make sure provided query should follow the format %n %s.", FORMAT)),
    CANNOT_USE_ALLFIELDS("Usage of '*' for selecting all fields not allowed, Please explicitly add the desired fields, refer the fields from occurrence/download/request/sql/describe endpoint."),
    HAVING_CLAUSE_NOT_SUPPORTED("SQL Download API donot support query with HAVING clause");

    Issue(String description) {
      this.description = description;
    }

    private final String description;
    private String comment = "";

    public String description() {
      return description;
    }

    public String comment() {
      return comment;
    }

    public Issue withComment(String comment) {
      this.comment = comment;
      return this;
    }
  }
}
