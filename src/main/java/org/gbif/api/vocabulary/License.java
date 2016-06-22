package org.gbif.api.vocabulary;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

/**
 * Enumeration of the set of licenses GBIF supports for applying to a dataset. The license provides a standardised way
 * to define appropriate uses of a dataset.
 * </br>
 * GBIF's recommended best practice is to use the most recent license version, which for CC-BY and CC-BY-NC is 4.0.
 * This is in line with the recommendation from Creative Commons.
 *
 * @see <a href="https://creativecommons.org/faq/#why-should-i-use-the-latest-version-of-the-creative-commons-licenses">Creative Commons recommendation</a>
 * @see <a href="http://www.gbif.org/terms/licences">GBIF Licensing</a>
 */
public enum License {

  /**
   * Creative Commons Zero / Public Domain version 1.0. Technically a waiver, not a license.
   *
   * @see <a href="http://creativecommons.org/publicdomain/zero/1.0/legalcode">legal document</a>
   */
  CC0_1_0("Public Domain (CC0 1.0)", "http://creativecommons.org/publicdomain/zero/1.0/legalcode"),
  /**
   * Creative Commons Attribution version 4.0.
   *
   * @see <a href="http://creativecommons.org/licenses/by/4.0/legalcode">legal document</a>
   */
  CC_BY_4_0("Creative Commons Attribution (CC-BY) 4.0", "http://creativecommons.org/licenses/by/4.0/legalcode"),
  /**
   * Creative Commons Attribution-NonCommercial version 4.0.
   *
   * @see <a href="http://creativecommons.org/licenses/by-nc/4.0/legalcode">legal document</a>
   */
  CC_BY_NC_4_0("Creative Commons Attribution Non Commercial (CC-BY-NC) 4.0", "http://creativecommons.org/licenses/by-nc/4.0/legalcode");


  private final String licenseTitle;
  private final String licenseUrl;

  /**
   * Lookup a License by either its a) legal code URL or b) human readable summary URL.
   *
   * @param licenseUrl the case insensitive URL for the license.
   *
   * @return the matching License or null
   */
  public static License fromLicenseUrl(String licenseUrl) {
    if (!Strings.isNullOrEmpty(licenseUrl)) {
      // trim URL, and remove trailing forward slash ("/")
      licenseUrl = licenseUrl.trim();
      if (licenseUrl.endsWith("/")) {
        licenseUrl = StringUtils.removeEnd(licenseUrl, "/");
      }
      // do lookup by legal code URL or human readable summary URL (excluding "/legalcode")
      for (License license : License.values()) {
        if (licenseUrl.equalsIgnoreCase(license.getLicenseUrl())
            || license.getLicenseUrl().startsWith(licenseUrl)) {
          return license;
        }
      }
    }
    return null;
  }

  License(String licenseTitle, String licenseUrl) {
    this.licenseTitle = licenseTitle;
    this.licenseUrl = licenseUrl;
  }

  /**
   * @return the License URL
   */
  public String getLicenseUrl() {
    return licenseUrl;
  }

  /**
   * @return the License title
   */
  public String getLicenseTitle() {
    return licenseTitle;
  }
}
