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
package org.gbif.api.vocabulary;

import org.gbif.api.util.VocabularyUtils;

import java.util.Optional;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enumeration of the set of licenses GBIF supports for applying to a dataset. The license provides a standardised way
 * to define appropriate uses of a dataset.
 * </br>
 * GBIF's recommended best practice is to use the most recent license version, which for CC-BY and CC-BY-NC is 4.0.
 * This is in line with the recommendation from Creative Commons.
 * The ordinal number in the Enum implicitly defines the level of restriction, see LicenseTest.
 *
 * @see <a href="https://creativecommons.org/faq/#why-should-i-use-the-latest-version-of-the-creative-commons-licenses">Creative
 * Commons recommendation</a>
 * @see <a href="http://www.gbif.org/terms/licences">GBIF Licensing</a>
 */
@Schema(
  externalDocs = @ExternalDocumentation(
    description = "API call to retrieve all official values.",
    url = "https://api.gbif.org/v1/enumeration/basic/License"
  )
)
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
  CC_BY_NC_4_0("Creative Commons Attribution Non Commercial (CC-BY-NC) 4.0", "http://creativecommons.org/licenses/by-nc/4.0/legalcode"),
  /**
   * No license has been specified.
   */
  UNSPECIFIED(null, null),
  /**
   * A license not supported by GBIF.
   */
  UNSUPPORTED(null, null);

  private final String licenseTitle;
  private final String licenseUrl;

  /**
   * Get an {@link License} from its name as String.
   *
   * @return instance of Optional, never null
   */
  public static Optional<License> fromString(String license) {
    return VocabularyUtils.lookup(license, License.class);
  }

  /**
   * Lookup a License by either its a) legal code URL or b) human readable summary URL, with HTTP or HTTPS.
   * For any parsing see LicenseParser in GBIF parsers project.
   *
   * @param licenseUrl the case insensitive URL for the license.
   *
   * @return instance of Optional, never null
   */
  public static Optional<License> fromLicenseUrl(String licenseUrl) {
    if (StringUtils.isNotEmpty(licenseUrl)) {
      licenseUrl = licenseUrl.trim().toLowerCase();
      licenseUrl = StringUtils.removeEnd(licenseUrl, "/");
      licenseUrl = licenseUrl.replace("https://", "http://");

      // do lookup by legal code URL or human readable summary URL (excluding "/legalcode")
      for (License license : License.values()) {
        if (license.getLicenseUrl() != null && (licenseUrl.equals(license.getLicenseUrl())
                || license.getLicenseUrl().startsWith(licenseUrl))) {
          return Optional.of(license);
        }
      }
    }
    return Optional.empty();
  }

  /**
   * Get the most restrictive license between the 2 provided licenses.
   * If one or the two licenses are null or not concrete, this method returns the fallback License.
   *
   * @param fallback License to return if one or the two licenses are null or not concrete
   *
   * @return the most restrictive License or the fallback License
   */
  public static License getMostRestrictive(License license1, License license2, License fallback) {
    if (license1 == null || license2 == null || !license1.isConcrete() || !license2.isConcrete()) {
      return fallback;
    }
    return (license1.compareTo(license2) > 0) ? license1 : license2;
  }

  License(@Nullable String licenseTitle, @Nullable String licenseUrl) {
    this.licenseTitle = licenseTitle;
    // ensure license information is kept in lowercase internally
    if (licenseUrl != null) {
      this.licenseUrl = licenseUrl.toLowerCase();
    } else {
      this.licenseUrl = null;
    }
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

  /**
   * Indicates if a license is a concrete license (true) or an abstracted license (false) like
   * UNSPECIFIED or UNSUPPORTED.
   * @return the license if concrete or not
   */
  public boolean isConcrete() {
    return licenseUrl != null;
  }
}
