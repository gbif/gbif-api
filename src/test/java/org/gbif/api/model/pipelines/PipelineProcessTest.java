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
package org.gbif.api.model.pipelines;

import java.time.OffsetDateTime;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests the {@link PipelineProcess}. */
public class PipelineProcessTest {

  @Test
  public void pipelineProcessComparatorByLatestStepTest() {
    PipelineProcess p1 = new PipelineProcess();
    PipelineProcess p2 = new PipelineProcess();

    // process without steps
    PipelineExecution e1 = new PipelineExecution().setCreated(OffsetDateTime.now());
    p1.addExecution(e1);
    PipelineExecution e2 = new PipelineExecution().setCreated(OffsetDateTime.now());
    p2.addExecution(e2);
    assertEquals(
        0, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));

    // one process without steps
    PipelineStep p1e1s1 =
        new PipelineStep()
            .setStarted(OffsetDateTime.now().minusMinutes(30))
            .setState(PipelineStep.Status.RUNNING);
    e1.addStep(p1e1s1);
    assertEquals(
        -1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));

    // both processes with steps
    PipelineStep p2e2s1 =
        new PipelineStep()
            .setStarted(OffsetDateTime.now().minusMinutes(35))
            .setState(PipelineStep.Status.RUNNING);
    e2.addStep(p2e2s1);
    assertEquals(
        1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));

    // completed steps have less preference over running ones
    PipelineStep p1e1s2 =
        new PipelineStep()
            .setStarted(OffsetDateTime.now().minusMinutes(40))
            .setState(PipelineStep.Status.COMPLETED);
    e1.addStep(p1e1s2);
    assertEquals(
        1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));

    // steps with null values
    PipelineStep p1e1s3 = new PipelineStep();
    e1.addStep(p1e1s3);
    assertEquals(
        1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));

    // steps with start date but no state
    PipelineStep p1e1s4 = new PipelineStep().setStarted(OffsetDateTime.now().minusMinutes(15));
    e1.addStep(p1e1s4);
    assertEquals(
        1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));
  }

  @Test
  public void pipelineProcessComparatorByLatestExecutionTest() {
    PipelineProcess p1 = new PipelineProcess();
    PipelineProcess p2 = new PipelineProcess();

    assertEquals(0, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_EXEUCTION_ASC));

    PipelineExecution e1 = new PipelineExecution().setCreated(OffsetDateTime.now().minusMinutes(20));
    p1.addExecution(e1);
    assertEquals(-1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_EXEUCTION_ASC));

    PipelineExecution e2 = new PipelineExecution().setCreated(OffsetDateTime.now().minusMinutes(30));
    p2.addExecution(e2);
    assertEquals(1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_EXEUCTION_ASC));

    PipelineExecution e3 = new PipelineExecution().setCreated(OffsetDateTime.now().minusMinutes(10));
    p2.addExecution(e3);
    assertEquals(-1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_EXEUCTION_ASC));
  }
}
