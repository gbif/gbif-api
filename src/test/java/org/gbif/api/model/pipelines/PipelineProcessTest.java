package org.gbif.api.model.pipelines;

import java.time.LocalDateTime;
import java.util.Objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Tests the {@link PipelineProcess}. */
public class PipelineProcessTest {

  @Test
  public void pipelineProcessComparatorTest() {
    PipelineProcess p1 = new PipelineProcess();
    PipelineProcess p2 = new PipelineProcess();

    // process without steps
    PipelineExecution e1 = new PipelineExecution().setCreated(LocalDateTime.now());
    p1.addExecution(e1);
    PipelineExecution e2 = new PipelineExecution().setCreated(LocalDateTime.now());
    p2.addExecution(e2);
    assertEquals(
        0, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));

    // one process without steps
    PipelineStep p1e1s1 =
        new PipelineStep()
            .setStarted(LocalDateTime.now().minusMinutes(30))
            .setState(PipelineStep.Status.RUNNING);
    e1.addStep(p1e1s1);
    assertEquals(
        -1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));

    // both processes with steps
    PipelineStep p2e2s1 =
        new PipelineStep()
            .setStarted(LocalDateTime.now().minusMinutes(35))
            .setState(PipelineStep.Status.RUNNING);
    e2.addStep(p2e2s1);
    assertEquals(
        1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));

    // completed steps have less preference over running ones
    PipelineStep p1e1s2 =
        new PipelineStep()
            .setStarted(LocalDateTime.now().minusMinutes(40))
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
    PipelineStep p1e1s4 = new PipelineStep().setStarted(LocalDateTime.now().minusMinutes(15));
    e1.addStep(p1e1s4);
    assertEquals(
        1, Objects.compare(p1, p2, PipelineProcess.PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC));
  }
}
