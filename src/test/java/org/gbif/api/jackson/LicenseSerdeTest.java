package org.gbif.api.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.gbif.api.vocabulary.License;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test {@link License} serde using LicenseJsonSerializer and LicenseJsonDeserializer.
 *
 */
public class LicenseSerdeTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void testLicenseSerde() throws IOException {

    LicenseWrapper license = new LicenseWrapper(License.CC0_1_0, "cc0");
    String json = MAPPER.writeValueAsString(license);

    LicenseWrapper rebuiltLicense = MAPPER.readValue(json, LicenseWrapper.class);
    assertEquals(license.getLicense(), rebuiltLicense.getLicense());
  }

  /**
   * Check the 3 cases where we can not serialize a URL for the license UNSPECIFIED, UNSUPPORTED and null.
   * @throws IOException
   */
  @Test
  public void testLicenseSerdeNoUrl() throws IOException {
    LicenseWrapper license = new LicenseWrapper(License.UNSPECIFIED, "cc0");
    String json = MAPPER.writeValueAsString(license);
    assertTrue(json.contains("\"license\":\"unspecified\""));
    LicenseWrapper rebuiltLicense = MAPPER.readValue(json, LicenseWrapper.class);
    assertEquals(license.getLicense(), rebuiltLicense.getLicense());

    license = new LicenseWrapper(License.UNSUPPORTED, "cc0");
    json = MAPPER.writeValueAsString(license);
    assertTrue(json.contains("\"license\":\"unsupported\""));
    rebuiltLicense = MAPPER.readValue(json, LicenseWrapper.class);
    assertEquals(license.getLicense(), rebuiltLicense.getLicense());

    license = new LicenseWrapper(null, "cc0");
    json = MAPPER.writeValueAsString(license);
    assertTrue(json.contains("\"license\":null"));
    rebuiltLicense = MAPPER.readValue(json, LicenseWrapper.class);
    assertEquals(license.getLicense(), rebuiltLicense.getLicense());
  }

  @Test
  public void testFromLicenseName() throws IOException {
    String json = "{\"license\":\"CC0_1_0\",\"text\":\"cc0\"}";
    LicenseWrapper rebuiltLicense = MAPPER.readValue(json, LicenseWrapper.class);
    assertEquals(License.CC0_1_0, rebuiltLicense.getLicense());
  }

  public static class LicenseWrapper {
    private License license;
    private String text;

    public LicenseWrapper(){}

    public LicenseWrapper(License license, String text){
      this.license = license;
      this.text = text;
    }

    @JsonSerialize(using = LicenseSerde.LicenseJsonSerializer.class)
    @JsonDeserialize(using = LicenseSerde.LicenseJsonDeserializer.class)
    public License getLicense() {
      return license;
    }

    public void setLicense(License license) {
      this.license = license;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

  }
}
