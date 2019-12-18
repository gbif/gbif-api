package org.gbif.api.model.pipelines;

import java.time.LocalDateTime;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Tests the {@link PipelineExecution}. */
public class PipelineExecutionTest {

  @Test
  public void pipelineExecutionComparatorTest() {
    PipelineExecution ex1 = new PipelineExecution();
    PipelineExecution ex2 = new PipelineExecution();

    assertEquals(0, Objects.compare(ex1, ex2, PipelineExecution.PIPELINE_EXECUTION_BY_CREATED_ASC));

    // add creation dates
    ex1.setCreated(LocalDateTime.now());
    assertEquals(-1, Objects.compare(ex1, ex2, PipelineExecution.PIPELINE_EXECUTION_BY_CREATED_ASC));

    ex2.setCreated(LocalDateTime.now().minusMinutes(30));
    assertEquals(1, Objects.compare(ex1, ex2, PipelineExecution.PIPELINE_EXECUTION_BY_CREATED_ASC));
  }
}
