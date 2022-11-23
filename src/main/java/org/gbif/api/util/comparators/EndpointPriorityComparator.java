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
package org.gbif.api.util.comparators;

import org.gbif.api.model.registry.Endpoint;
import org.gbif.api.vocabulary.EndpointType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Compares two Endpoints.
 * <p></p>
 * It does so by using a priority list of Endpoint Types. This Comparator will throw a {@link
 * ClassCastException} exception if a non supported EndpointType is passed in. It also does not
 * support {@code null} values.
 * <p></p>
 * The priority list is as follows (most to least important):
 * <ol>
 * <li>CamtrapDP</li>
 * <li>DwC-A</li>
 * <li>TAPIR</li>
 * <li>BioCASe</li>
 * <li>DiGIR</li>
 * <li>DiGIR (Manis)</li>
 * <li>EML</li>
 * </ol>
 */
public class EndpointPriorityComparator implements Comparator<Endpoint>, Serializable {

  /* Note: Due to legacy reasons this is a Comparator itself instead of just using the PRIORITY_COMPARATOR.
   It should be easy to remove this class or change it into a factory */

  // Priorities from lowest to highest
  public static final List<EndpointType> PRIORITIES = Collections.unmodifiableList(
    Arrays.asList(
      EndpointType.EML,
      EndpointType.DIGIR_MANIS,
      EndpointType.DIGIR,
      EndpointType.BIOCASE,
      EndpointType.TAPIR,
      EndpointType.BIOCASE_XML_ARCHIVE,
      EndpointType.DWC_ARCHIVE,
      EndpointType.CAMTRAP_DP_v_0_4
    ));

  private static final long serialVersionUID = 8085216142750609841L;

  @Override
  public int compare(Endpoint endpoint1, Endpoint endpoint2) {
    int firstEndpointTypeIndex = PRIORITIES.indexOf(endpoint1.getType());
    int secondEndpointTypeIndex = PRIORITIES.indexOf(endpoint2.getType());

    if (firstEndpointTypeIndex == -1) {
      throw new ClassCastException("Cannot compare value: " + endpoint1.getType());
    } else if (secondEndpointTypeIndex == -1) {
      throw new ClassCastException("Cannot compare value: " + endpoint2.getType());
    } else {
      return Integer.compare(firstEndpointTypeIndex, secondEndpointTypeIndex);
    }
  }
}
