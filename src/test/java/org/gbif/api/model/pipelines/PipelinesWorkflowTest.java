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
package org.gbif.api.model.pipelines;

import org.gbif.api.model.pipelines.PipelinesWorkflow.Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.gbif.api.model.pipelines.StepType.*;


public class PipelinesWorkflowTest {

  private final BiPredicate<List<Graph<StepType>.Edge>, StepType> containsFn =
    (l, t) -> l.stream().anyMatch(x -> x.getNode().equals(t));

  @Test
  public void findAllStepsToRunTest() {
    Set<StepType> types = new HashSet<>(
      Arrays.asList(VERBATIM_TO_INTERPRETED, VERBATIM_TO_IDENTIFIER, HDFS_VIEW, INTERPRETED_TO_INDEX));

    Set<StepType> allNodesFor = PipelinesWorkflow.getOccurrenceWorkflow().getAllNodesFor(types);

    Assertions.assertEquals(5, allNodesFor.size());
    Assertions.assertTrue(allNodesFor.containsAll(
      Arrays.asList(VERBATIM_TO_IDENTIFIER, VERBATIM_TO_INTERPRETED, HDFS_VIEW, INTERPRETED_TO_INDEX, FRAGMENTER)));
  }

  @Test
  public void findAllStepsToRunSameLevelTest() {
    Set<StepType> types =
      new HashSet<>(Arrays.asList(HDFS_VIEW, INTERPRETED_TO_INDEX, EVENTS_VERBATIM_TO_INTERPRETED));

    Set<StepType> allNodesFor = PipelinesWorkflow.getEventOccurrenceWorkflow().getAllNodesFor(types);

    Assertions.assertEquals(5, allNodesFor.size());
    Assertions.assertTrue(allNodesFor.containsAll(
      Arrays.asList(HDFS_VIEW, INTERPRETED_TO_INDEX, EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_HDFS_VIEW,
        EVENTS_INTERPRETED_TO_INDEX)));
  }

  @Test
  public void findOneRootStepsToRunTest() {
    Set<StepType> types = new HashSet<>(
      Arrays.asList(DWCA_TO_VERBATIM, VERBATIM_TO_INTERPRETED, VERBATIM_TO_IDENTIFIER, HDFS_VIEW,
        INTERPRETED_TO_INDEX));

    Set<StepType> rootNodes = PipelinesWorkflow.getOccurrenceWorkflow().getRootNodesFor(types);

    Assertions.assertEquals(1, rootNodes.size());
    Assertions.assertTrue(rootNodes.contains(DWCA_TO_VERBATIM));
  }

  @Test
  public void findMainRootStepsToRunTest() {
    Set<StepType> types = new HashSet<>(
      Arrays.asList(VERBATIM_TO_INTERPRETED, VERBATIM_TO_IDENTIFIER, HDFS_VIEW, INTERPRETED_TO_INDEX));

    Set<StepType> rootNodes = PipelinesWorkflow.getOccurrenceWorkflow().getRootNodesFor(types);

    Assertions.assertEquals(1, rootNodes.size());
    Assertions.assertTrue(rootNodes.contains(VERBATIM_TO_IDENTIFIER));
  }

  @Test
  public void findRootStepsToRunSameLevelTest() {
    Set<StepType> types =
      new HashSet<>(Arrays.asList(HDFS_VIEW, INTERPRETED_TO_INDEX, EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_HDFS_VIEW,
        EVENTS_INTERPRETED_TO_INDEX));

    Set<StepType> rootNodes = PipelinesWorkflow.getEventOccurrenceWorkflow().getRootNodesFor(types);

    Assertions.assertEquals(3, rootNodes.size());
    Assertions.assertTrue(rootNodes.containsAll(
      Arrays.asList(HDFS_VIEW, INTERPRETED_TO_INDEX, EVENTS_VERBATIM_TO_INTERPRETED)));
  }

  @Test
  public void eventOccurrenceWorkflowTest() {
    Graph<StepType> wf = PipelinesWorkflow.getEventOccurrenceWorkflow();

    Assertions.assertEquals(11, wf.getNodesQuantity());

    Assertions.assertEquals(1, wf.getLevel(DWCA_TO_VERBATIM));
    Assertions.assertEquals(2, wf.getLevel(DWCDP_TO_VERBATIM));
    Assertions.assertEquals(2, wf.getLevel(VERBATIM_TO_IDENTIFIER));
    Assertions.assertEquals(3, wf.getLevel(VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(4, wf.getLevel(INTERPRETED_TO_INDEX));
    Assertions.assertEquals(4, wf.getLevel(HDFS_VIEW));
    Assertions.assertEquals(4, wf.getLevel(FRAGMENTER));
    Assertions.assertEquals(4, wf.getLevel(EVENTS_VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(5, wf.getLevel(EVENTS_INTERPRETED_TO_INDEX));
    Assertions.assertEquals(5, wf.getLevel(EVENTS_HDFS_VIEW));

    assertConnection(wf, DWCDP_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, DWCA_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, VERBATIM_TO_IDENTIFIER, VERBATIM_TO_INTERPRETED);
    assertConnection(wf, VERBATIM_TO_INTERPRETED, INTERPRETED_TO_INDEX, HDFS_VIEW, FRAGMENTER, EVENTS_VERBATIM_TO_INTERPRETED);
    assertConnection(wf, INTERPRETED_TO_INDEX);
    assertConnection(wf, HDFS_VIEW);
    assertConnection(wf, FRAGMENTER);
    assertConnection(wf, EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_INTERPRETED_TO_INDEX, EVENTS_HDFS_VIEW);
    assertConnection(wf, EVENTS_INTERPRETED_TO_INDEX);
    assertConnection(wf, EVENTS_HDFS_VIEW);
  }

  @Test
  public void eventOnlyWorkflowTest() {
    Graph<StepType> wf = PipelinesWorkflow.getEventWorkflow();

    Assertions.assertEquals(6, wf.getNodesQuantity());

    Assertions.assertEquals(1, wf.getLevel(NFS_TO_HDFS));
    Assertions.assertEquals(1, wf.getLevel(DWCA_TO_VERBATIM));
    Assertions.assertEquals(2, wf.getLevel(DWCDP_TO_VERBATIM));
    Assertions.assertEquals(2, wf.getLevel(EVENTS_VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(3, wf.getLevel(EVENTS_INTERPRETED_TO_INDEX));
    Assertions.assertEquals(3, wf.getLevel(EVENTS_HDFS_VIEW));

    assertConnection(wf, NFS_TO_HDFS, DWCDP_TO_VERBATIM);
    assertConnection(wf, DWCA_TO_VERBATIM, EVENTS_VERBATIM_TO_INTERPRETED);
    assertConnection(wf, DWCDP_TO_VERBATIM, EVENTS_VERBATIM_TO_INTERPRETED);
    assertConnection(wf, EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_INTERPRETED_TO_INDEX, EVENTS_HDFS_VIEW);
    assertConnection(wf, EVENTS_INTERPRETED_TO_INDEX);
    assertConnection(wf, EVENTS_HDFS_VIEW);
  }

  @Test
  public void occurrenceWorkflowTest() {
    Graph<StepType> wf = PipelinesWorkflow.getOccurrenceWorkflow();

    Assertions.assertEquals(10, wf.getNodesQuantity());

    Assertions.assertEquals(1, wf.getLevel(DWCA_TO_VERBATIM));
    Assertions.assertEquals(1, wf.getLevel(ABCD_TO_VERBATIM));
    Assertions.assertEquals(1, wf.getLevel(XML_TO_VERBATIM));
    Assertions.assertEquals(2, wf.getLevel(VERBATIM_TO_IDENTIFIER));
    Assertions.assertEquals(3, wf.getLevel(VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(4, wf.getLevel(INTERPRETED_TO_INDEX));
    Assertions.assertEquals(4, wf.getLevel(HDFS_VIEW));
    Assertions.assertEquals(4, wf.getLevel(FRAGMENTER));

    assertConnection(wf, DWCDP_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, DWCA_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, ABCD_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, XML_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, VERBATIM_TO_IDENTIFIER, VERBATIM_TO_INTERPRETED);
    assertConnection(wf, VERBATIM_TO_INTERPRETED, INTERPRETED_TO_INDEX, HDFS_VIEW, FRAGMENTER);
    assertConnection(wf, INTERPRETED_TO_INDEX);
    assertConnection(wf, HDFS_VIEW);
    assertConnection(wf, FRAGMENTER);
  }

  @Test
  public void validatorWorkflowTest() {
    Graph<StepType> wf = PipelinesWorkflow.getValidatorWorkflow();

    Assertions.assertEquals(10, wf.getNodesQuantity());

    Assertions.assertEquals(1, wf.getLevel(VALIDATOR_UPLOAD_ARCHIVE));
    Assertions.assertEquals(2, wf.getLevel(VALIDATOR_VALIDATE_ARCHIVE));
    Assertions.assertEquals(3, wf.getLevel(VALIDATOR_DWCA_TO_VERBATIM));
    Assertions.assertEquals(3, wf.getLevel(VALIDATOR_ABCD_TO_VERBATIM));
    Assertions.assertEquals(3, wf.getLevel(VALIDATOR_XML_TO_VERBATIM));
    Assertions.assertEquals(3, wf.getLevel(VALIDATOR_TABULAR_TO_VERBATIM));
    Assertions.assertEquals(4, wf.getLevel(VALIDATOR_VERBATIM_TO_IDENTIFIER));
    Assertions.assertEquals(5, wf.getLevel(VALIDATOR_VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(6, wf.getLevel(VALIDATOR_INTERPRETED_TO_INDEX));
    Assertions.assertEquals(7, wf.getLevel(VALIDATOR_COLLECT_METRICS));

    assertConnection(wf, VALIDATOR_UPLOAD_ARCHIVE, VALIDATOR_VALIDATE_ARCHIVE);
    assertConnection(wf, VALIDATOR_VALIDATE_ARCHIVE,
      VALIDATOR_DWCA_TO_VERBATIM, VALIDATOR_ABCD_TO_VERBATIM,
      VALIDATOR_XML_TO_VERBATIM, VALIDATOR_TABULAR_TO_VERBATIM);
    assertConnection(wf, VALIDATOR_DWCA_TO_VERBATIM, VALIDATOR_VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, VALIDATOR_ABCD_TO_VERBATIM, VALIDATOR_VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, VALIDATOR_XML_TO_VERBATIM, VALIDATOR_VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, VALIDATOR_TABULAR_TO_VERBATIM, VALIDATOR_VERBATIM_TO_IDENTIFIER);
    assertConnection(wf, VALIDATOR_VERBATIM_TO_IDENTIFIER, VALIDATOR_VERBATIM_TO_INTERPRETED);
    assertConnection(wf, VALIDATOR_VERBATIM_TO_INTERPRETED, VALIDATOR_INTERPRETED_TO_INDEX);
    assertConnection(wf, VALIDATOR_INTERPRETED_TO_INDEX, VALIDATOR_COLLECT_METRICS);
    assertConnection(wf, VALIDATOR_COLLECT_METRICS);
  }

  private void assertConnection(Graph<StepType> wf, StepType sourceNode, StepType... expectedTargetNodes) {
    List<Graph<StepType>.Edge> edges = wf.getNodeEdges(sourceNode);
    Assertions.assertEquals(expectedTargetNodes.length, edges.size());
    Set<StepType> expectedNodes = new HashSet<>(Arrays.asList(expectedTargetNodes));
    Set<StepType> actualNodes = edges.stream().map(Graph.Edge::getNode).collect(Collectors.toSet());
    Assertions.assertEquals(expectedNodes, actualNodes);
  }

}
