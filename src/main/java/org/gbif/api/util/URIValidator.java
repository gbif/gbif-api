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

import java.net.URI;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Constrain validator that validates URI objects with a @HttpURI annotation.
 */
public class URIValidator implements ConstraintValidator<HttpURI, URI> {

  private static final String HTTP_PROTOCOL = "http";
  private static final String HTTPS_PROTOCOL = "https";

  /**
   * Initializes the validator in preparation for
   * {@link #isValid(URI, javax.validation.ConstraintValidatorContext)} calls.
   * The constraint annotation for a given constraint declaration
   * is passed.
   * This method is guaranteed to be called before any use of this instance for
   * validation.
   *
   * @param uri annotation instance for a given constraint declaration
   */
  @Override
  public void initialize(HttpURI uri) {
  }

  /**
   * Implements the validation logic, checking if a URI is valid or not.
   * The state of {@code value} must not be altered.
   * This method can be accessed concurrently, thread-safety must be ensured
   * by the implementation.
   *
   * @param uri   object to validate
   * @param context context in which the constraint is evaluated
   *
   * @return {@code false} if {@code value} does not pass the constraint
   */
  @Override
  public boolean isValid(URI uri, ConstraintValidatorContext context) {
    // only validate non-null URIs
    if (uri == null) {
      return true;
    }

    // URI scheme can not be null
    if (uri.getScheme() == null) {
      return false;
    }

    // URI must begin with either: http or https
    // otherwise, the URI is invalid
    return uri.getScheme().equalsIgnoreCase(HTTP_PROTOCOL) || uri.getScheme().equalsIgnoreCase(HTTPS_PROTOCOL);
  }
}
