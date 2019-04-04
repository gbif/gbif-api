package org.gbif.api.model.sql;

import org.gbif.api.model.occurrence.sql.GBIFSqlQuery;
import org.gbif.api.model.occurrence.sql.SqlDownloadExportFormat;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Testing GBIFSqlQuery to verify parsing of SQL and formats expected.
 */
@RunWith(Parameterized.class)
public class GBIFSqlQueryTest {

  public static final String VALIDATION_ERROR_MESSAGE =
    "Failure reading export format please specify TSV or AVRO";

  @Parameterized.Parameters
  public static Collection<Object[]> inputs() {
    return Arrays.asList(new Object[][] {
      {"SELECT * from occ WHERE X > 23 EXPORT AS CSV", "", null},
      {"SELECT * from occ WHERE X > 23 ", "SELECT * from occ WHERE X > 23", SqlDownloadExportFormat.TSV},
      {"SELECT * from occ WHERE X > 23 EXPORT AS TSV", "SELECT * from occ WHERE X > 23", SqlDownloadExportFormat.TSV },
      {"SELECT * from occ WHERE X > 23 EXPORT   AS    TSV", "SELECT * from occ WHERE X > 23", SqlDownloadExportFormat.TSV },
      {"SELECT * from occ WHERE X > 23 EXPORT   AS    tsv", "SELECT * from occ WHERE X > 23", SqlDownloadExportFormat.TSV },
      {"SELECT * from occ WHERE X > 23 EXPORT AS AVRO", "SELECT * from occ WHERE X > 23", SqlDownloadExportFormat.AVRO },
      {"SELECT * from occ WHERE X > 23 EXPORT   AS    AVRO", "SELECT * from occ WHERE X > 23", SqlDownloadExportFormat.AVRO },
      {"SELECT * from occ WHERE X > 23 EXPORT   AS    Avro", "SELECT * from occ WHERE X > 23", SqlDownloadExportFormat.AVRO }
    });
  }

  private String gbifSql;
  private String uncheckedHiveSQL;
  private SqlDownloadExportFormat format;

  public GBIFSqlQueryTest(String gbifSql, String uncheckedHiveSQL, SqlDownloadExportFormat format){
    this.gbifSql = gbifSql;
    this.uncheckedHiveSQL = uncheckedHiveSQL;
    this.format = format;
  }

  @Test
  public void testExport(){
    GBIFSqlQuery query = null;
    try {
      query = GBIFSqlQuery.create(gbifSql);
    }
    catch(Exception exception){
      Assert.assertEquals(format, query);
      Assert.assertTrue(exception instanceof ValidationException);
      Assert.assertEquals(VALIDATION_ERROR_MESSAGE, exception.getMessage());
      return;
    }
    System.out.println(query.getUncheckedHiveQuery());
    System.out.println(query.getSqlDownloadExportFormat());
    System.out.println(query.getSqlDownloadExportFormat().getTemplate());

    Assert.assertEquals(uncheckedHiveSQL, query.getUncheckedHiveQuery());
    Assert.assertEquals(format, query.getSqlDownloadExportFormat());
  }
}
