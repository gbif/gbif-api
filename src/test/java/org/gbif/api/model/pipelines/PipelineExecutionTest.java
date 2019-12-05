package org.gbif.api.model.pipelines;

import java.time.LocalDateTime;
import java.util.Objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Tests the {@link PipelineExecution}. */
public class PipelineExecutionTest {

  @Test
  public void pipelineExecutionComparatorTest() {
    // process without steps
    PipelineExecution p1 = new PipelineExecution();
    PipelineExecution p2 = new PipelineExecution();
    assertEquals(
        0,
        Objects.compare(
            p1, p2, PipelineExecution.PIPELINE_EXECUTION_BY_LATEST_STEP_ASC.reversed()));

    // one process without steps
    PipelineStep p1s1 =
        new PipelineStep()
            .setStarted(LocalDateTime.now().minusMinutes(30))
            .setState(PipelineStep.Status.RUNNING);
    p1.addStep(p1s1);
    assertEquals(
        1,
        Objects.compare(
            p1, p2, PipelineExecution.PIPELINE_EXECUTION_BY_LATEST_STEP_ASC.reversed()));

    // both processes with steps
    PipelineStep p2s1 =
        new PipelineStep()
            .setStarted(LocalDateTime.now().minusMinutes(35))
            .setState(PipelineStep.Status.RUNNING);
    p2.addStep(p2s1);
    assertEquals(
        -1,
        Objects.compare(
            p1, p2, PipelineExecution.PIPELINE_EXECUTION_BY_LATEST_STEP_ASC.reversed()));

    // completed steps have less preference over running ones
    PipelineStep p1s2 =
        new PipelineStep()
            .setStarted(LocalDateTime.now().minusMinutes(40))
            .setState(PipelineStep.Status.COMPLETED);
    p1.addStep(p1s2);
    assertEquals(
        -1,
        Objects.compare(
            p1, p2, PipelineExecution.PIPELINE_EXECUTION_BY_LATEST_STEP_ASC.reversed()));

    // steps with null values
    PipelineStep p1s3 = new PipelineStep();
    p1.addStep(p1s3);
    assertEquals(
        -1,
        Objects.compare(
            p1, p2, PipelineExecution.PIPELINE_EXECUTION_BY_LATEST_STEP_ASC.reversed()));

    // steps with start date but no state
    PipelineStep p1s4 = new PipelineStep().setStarted(LocalDateTime.now().minusMinutes(15));
    p1.addStep(p1s4);
    assertEquals(
        -1,
        Objects.compare(
            p1, p2, PipelineExecution.PIPELINE_EXECUTION_BY_LATEST_STEP_ASC.reversed()));
  }
}
