/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
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
package org.gbif.api.model.registry.eml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class SamplingDescription implements Serializable {

  private static final long serialVersionUID = -9075523119279155795L;

  private String studyExtent;

  private String sampling;
  private String qualityControl;
  private List<String> methodSteps = new ArrayList<>();

  public SamplingDescription() {
  }

  public SamplingDescription(String studyExtent, String sampling, String qualityControl, List<String> methodSteps) {
    this.studyExtent = studyExtent;
    this.sampling = sampling;
    this.qualityControl = qualityControl;
    this.methodSteps = methodSteps;
  }

  public List<String> getMethodSteps() {
    return methodSteps;
  }

  public void setMethodSteps(List<String> methodSteps) {
    this.methodSteps = methodSteps;
  }

  public void addMethodStep(String methodStep) {
    this.methodSteps.add(methodStep);
  }

  public String getQualityControl() {
    return qualityControl;
  }

  public void setQualityControl(String qualityControl) {
    this.qualityControl = qualityControl;
  }

  public String getSampling() {
    return sampling;
  }

  public void setSampling(String sampling) {
    this.sampling = sampling;
  }

  public String getStudyExtent() {
    return studyExtent;
  }

  public void setStudyExtent(String studyExtent) {
    this.studyExtent = studyExtent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SamplingDescription that = (SamplingDescription) o;
    return Objects.equals(studyExtent, that.studyExtent) &&
      Objects.equals(sampling, that.sampling) &&
      Objects.equals(qualityControl, that.qualityControl) &&
      Objects.equals(methodSteps, that.methodSteps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studyExtent, sampling, qualityControl, methodSteps);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SamplingDescription.class.getSimpleName() + "[", "]")
      .add("studyExtent='" + studyExtent + "'")
      .add("sampling='" + sampling + "'")
      .add("qualityControl='" + qualityControl + "'")
      .add("methodSteps=" + methodSteps)
      .toString();
  }
}
