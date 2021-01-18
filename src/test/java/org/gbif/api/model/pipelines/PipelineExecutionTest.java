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

import java.time.LocalDateTime;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
