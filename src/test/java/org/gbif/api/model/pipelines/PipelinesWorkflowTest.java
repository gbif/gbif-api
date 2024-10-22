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

    Assertions.assertEquals(6, allNodesFor.size());
    Assertions.assertTrue(allNodesFor.containsAll(
      Arrays.asList(VERBATIM_TO_IDENTIFIER, VERBATIM_TO_INTERPRETED, HDFS_VIEW, INTERPRETED_TO_INDEX, FRAGMENTER, DATA_WAREHOUSE)));
  }

  @Test
  public void findAllStepsToRunSameLevelTest() {
    Set<StepType> types =
      new HashSet<>(Arrays.asList(HDFS_VIEW, INTERPRETED_TO_INDEX, EVENTS_VERBATIM_TO_INTERPRETED));

    Set<StepType> allNodesFor = PipelinesWorkflow.getEventOccurrenceWorkflow().getAllNodesFor(types);

    Assertions.assertEquals(6, allNodesFor.size());
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

    Assertions.assertEquals(10, wf.getNodesQuantity());

    Assertions.assertEquals(1, wf.getLevel(DWCA_TO_VERBATIM));
    Assertions.assertEquals(2, wf.getLevel(VERBATIM_TO_IDENTIFIER));
    Assertions.assertEquals(3, wf.getLevel(VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(4, wf.getLevel(INTERPRETED_TO_INDEX));
    Assertions.assertEquals(4, wf.getLevel(HDFS_VIEW));
    Assertions.assertEquals(4, wf.getLevel(FRAGMENTER));
    Assertions.assertEquals(4, wf.getLevel(EVENTS_VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(5, wf.getLevel(EVENTS_INTERPRETED_TO_INDEX));
    Assertions.assertEquals(5, wf.getLevel(EVENTS_HDFS_VIEW));

    // DWCA_TO_VERBATIM -> VERBATIM_TO_IDENTIFIER
    List<Graph<StepType>.Edge> dwcaToVerbatimEdges = wf.getNodeEdges(DWCA_TO_VERBATIM);
    Assertions.assertEquals(1, dwcaToVerbatimEdges.size());
    Assertions.assertEquals(VERBATIM_TO_IDENTIFIER, dwcaToVerbatimEdges.get(0).getNode());

    // VERBATIM_TO_IDENTIFIER -> VERBATIM_TO_INTERPRETED
    List<Graph<StepType>.Edge> verbatimToIdentifierEdges = wf.getNodeEdges(VERBATIM_TO_IDENTIFIER);
    Assertions.assertEquals(1, verbatimToIdentifierEdges.size());
    Assertions.assertEquals(VERBATIM_TO_INTERPRETED, verbatimToIdentifierEdges.get(0).getNode());

    // VERBATIM_TO_INTERPRETED -> INTERPRETED_TO_INDEX & HDFS_VIEW & FRAGMENTER & EVENTS_VERBATIM_TO_INTERPRETED
    List<Graph<StepType>.Edge> verbatimToInterpretedEdges = wf.getNodeEdges(VERBATIM_TO_INTERPRETED);
    Assertions.assertEquals(4, verbatimToInterpretedEdges.size());
    Assertions.assertTrue(containsFn.test(verbatimToInterpretedEdges, INTERPRETED_TO_INDEX));
    Assertions.assertTrue(containsFn.test(verbatimToInterpretedEdges, HDFS_VIEW));
    Assertions.assertTrue(containsFn.test(verbatimToInterpretedEdges, FRAGMENTER));
    Assertions.assertTrue(containsFn.test(verbatimToInterpretedEdges, EVENTS_VERBATIM_TO_INTERPRETED));

    // INTERPRETED_TO_INDEX -> 0
    List<Graph<StepType>.Edge> interpretedToIndexEdges = wf.getNodeEdges(INTERPRETED_TO_INDEX);
    Assertions.assertEquals(0, interpretedToIndexEdges.size());

    // HDFS_VIEW -> 0
    List<Graph<StepType>.Edge> hdfsViewEdges = wf.getNodeEdges(HDFS_VIEW);
    Assertions.assertEquals(0, hdfsViewEdges.size());

    // FRAGMENTER -> 0
    List<Graph<StepType>.Edge> fragmenterEdges = wf.getNodeEdges(FRAGMENTER);
    Assertions.assertEquals(0, fragmenterEdges.size());

    // EVENTS_VERBATIM_TO_INTERPRETED -> EVENTS_INTERPRETED_TO_INDEX & EVENTS_HDFS_VIEW
    List<Graph<StepType>.Edge> interpretationNodeEdges = wf.getNodeEdges(EVENTS_VERBATIM_TO_INTERPRETED);
    Assertions.assertEquals(2, interpretationNodeEdges.size());
    Assertions.assertTrue(containsFn.test(interpretationNodeEdges, EVENTS_INTERPRETED_TO_INDEX));
    Assertions.assertTrue(containsFn.test(interpretationNodeEdges, EVENTS_HDFS_VIEW));

    // EVENTS_INTERPRETED_TO_INDEX -> 0
    List<Graph<StepType>.Edge> eventsInterpretedToIndexEdges = wf.getNodeEdges(EVENTS_INTERPRETED_TO_INDEX);
    Assertions.assertEquals(0, eventsInterpretedToIndexEdges.size());

    // EVENTS_HDFS_VIEW -> 0
    List<Graph<StepType>.Edge> eventsHdfsViewEdges = wf.getNodeEdges(EVENTS_HDFS_VIEW);
    Assertions.assertEquals(1, eventsHdfsViewEdges.size());
  }

  @Test
  public void eventOnlyWorkflowTest() {

    Graph<StepType> wf = PipelinesWorkflow.getEventWorkflow();

    Assertions.assertEquals(5, wf.getNodesQuantity());

    Assertions.assertEquals(1, wf.getLevel(DWCA_TO_VERBATIM));
    Assertions.assertEquals(2, wf.getLevel(EVENTS_VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(3, wf.getLevel(EVENTS_INTERPRETED_TO_INDEX));
    Assertions.assertEquals(3, wf.getLevel(EVENTS_HDFS_VIEW));
    Assertions.assertEquals(4, wf.getLevel(DATA_WAREHOUSE));

    // DWCA_TO_VERBATIM -> EVENTS_VERBATIM_TO_INTERPRETED
    List<Graph<StepType>.Edge> dwcaToVerbatimEdges = wf.getNodeEdges(DWCA_TO_VERBATIM);
    Assertions.assertEquals(1, dwcaToVerbatimEdges.size());
    Assertions.assertEquals(EVENTS_VERBATIM_TO_INTERPRETED, dwcaToVerbatimEdges.get(0).getNode());

    // EVENTS_VERBATIM_TO_INTERPRETED -> EVENTS_INTERPRETED_TO_INDEX & EVENTS_HDFS_VIEW
    List<Graph<StepType>.Edge> interpretationNodeEdges = wf.getNodeEdges(EVENTS_VERBATIM_TO_INTERPRETED);
    Assertions.assertEquals(2, interpretationNodeEdges.size());
    Assertions.assertTrue(containsFn.test(interpretationNodeEdges, EVENTS_INTERPRETED_TO_INDEX));
    Assertions.assertTrue(containsFn.test(interpretationNodeEdges, EVENTS_HDFS_VIEW));

    // EVENTS_INTERPRETED_TO_INDEX -> 0
    List<Graph<StepType>.Edge> eventsInterpretedToIndexEdges = wf.getNodeEdges(EVENTS_INTERPRETED_TO_INDEX);
    Assertions.assertEquals(0, eventsInterpretedToIndexEdges.size());

    // EVENTS_HDFS_VIEW -> 0
    List<Graph<StepType>.Edge> eventsHdfsViewEdges = wf.getNodeEdges(EVENTS_HDFS_VIEW);
    Assertions.assertEquals(1, eventsHdfsViewEdges.size());
  }

  @Test
  public void occurrenceWorkflowTest() {

    Graph<StepType> wf = PipelinesWorkflow.getOccurrenceWorkflow();

    Assertions.assertEquals(1, wf.getLevel(DWCA_TO_VERBATIM));
    Assertions.assertEquals(1, wf.getLevel(ABCD_TO_VERBATIM));
    Assertions.assertEquals(1, wf.getLevel(XML_TO_VERBATIM));
    Assertions.assertEquals(2, wf.getLevel(VERBATIM_TO_IDENTIFIER));
    Assertions.assertEquals(3, wf.getLevel(VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(4, wf.getLevel(INTERPRETED_TO_INDEX));
    Assertions.assertEquals(4, wf.getLevel(HDFS_VIEW));
    Assertions.assertEquals(4, wf.getLevel(FRAGMENTER));
    Assertions.assertEquals(5, wf.getLevel(DATA_WAREHOUSE));

    Assertions.assertEquals(9, wf.getNodesQuantity());

    // DWCA_TO_VERBATIM -> VERBATIM_TO_IDENTIFIER
    List<Graph<StepType>.Edge> dwcaToVerbatimEdges = wf.getNodeEdges(DWCA_TO_VERBATIM);
    Assertions.assertEquals(1, dwcaToVerbatimEdges.size());
    Assertions.assertEquals(VERBATIM_TO_IDENTIFIER, dwcaToVerbatimEdges.get(0).getNode());

    // ABCD_TO_VERBATIM -> VERBATIM_TO_IDENTIFIER
    List<Graph<StepType>.Edge> abcdToVerbatimEdges = wf.getNodeEdges(ABCD_TO_VERBATIM);
    Assertions.assertEquals(1, abcdToVerbatimEdges.size());
    Assertions.assertEquals(VERBATIM_TO_IDENTIFIER, abcdToVerbatimEdges.get(0).getNode());

    // XML_TO_VERBATIM -> VERBATIM_TO_IDENTIFIER
    List<Graph<StepType>.Edge> xmlToVerbatimEdges = wf.getNodeEdges(XML_TO_VERBATIM);
    Assertions.assertEquals(1, xmlToVerbatimEdges.size());
    Assertions.assertEquals(VERBATIM_TO_IDENTIFIER, xmlToVerbatimEdges.get(0).getNode());

    // VERBATIM_TO_IDENTIFIER -> VERBATIM_TO_INTERPRETED
    List<Graph<StepType>.Edge> verbatimToIdentifierEdges = wf.getNodeEdges(VERBATIM_TO_IDENTIFIER);
    Assertions.assertEquals(1, verbatimToIdentifierEdges.size());
    Assertions.assertEquals(VERBATIM_TO_INTERPRETED, verbatimToIdentifierEdges.get(0).getNode());

    // VERBATIM_TO_INTERPRETED -> INTERPRETED_TO_INDEX & HDFS_VIEW & FRAGMENTER
    List<Graph<StepType>.Edge> verbatimToInterpretedEdges = wf.getNodeEdges(VERBATIM_TO_INTERPRETED);
    Assertions.assertEquals(3, verbatimToInterpretedEdges.size());
    Assertions.assertTrue(containsFn.test(verbatimToInterpretedEdges, INTERPRETED_TO_INDEX));
    Assertions.assertTrue(containsFn.test(verbatimToInterpretedEdges, HDFS_VIEW));
    Assertions.assertTrue(containsFn.test(verbatimToInterpretedEdges, FRAGMENTER));

    // INTERPRETED_TO_INDEX -> 0
    List<Graph<StepType>.Edge> interpretedToIndexEdges = wf.getNodeEdges(INTERPRETED_TO_INDEX);
    Assertions.assertEquals(0, interpretedToIndexEdges.size());

    // HDFS_VIEW -> 0
    List<Graph<StepType>.Edge> hdfsViewEdges = wf.getNodeEdges(HDFS_VIEW);
    Assertions.assertEquals(1, hdfsViewEdges.size());

    // FRAGMENTER -> 0
    List<Graph<StepType>.Edge> fragmenterEdges = wf.getNodeEdges(FRAGMENTER);
    Assertions.assertEquals(0, fragmenterEdges.size());
  }

  @Test
  public void validatorWorkflowTest() {

    Graph<StepType> wf = PipelinesWorkflow.getValidatorWorkflow();

    Assertions.assertEquals(9, wf.getNodesQuantity());

    Assertions.assertEquals(1, wf.getLevel(VALIDATOR_UPLOAD_ARCHIVE));
    Assertions.assertEquals(2, wf.getLevel(VALIDATOR_VALIDATE_ARCHIVE));
    Assertions.assertEquals(3, wf.getLevel(VALIDATOR_DWCA_TO_VERBATIM));
    Assertions.assertEquals(3, wf.getLevel(VALIDATOR_ABCD_TO_VERBATIM));
    Assertions.assertEquals(3, wf.getLevel(VALIDATOR_XML_TO_VERBATIM));
    Assertions.assertEquals(3, wf.getLevel(VALIDATOR_TABULAR_TO_VERBATIM));
    Assertions.assertEquals(4, wf.getLevel(VALIDATOR_VERBATIM_TO_INTERPRETED));
    Assertions.assertEquals(5, wf.getLevel(VALIDATOR_INTERPRETED_TO_INDEX));
    Assertions.assertEquals(6, wf.getLevel(VALIDATOR_COLLECT_METRICS));

    // VALIDATOR_UPLOAD_ARCHIVE -> VALIDATOR_VALIDATE_ARCHIVE
    List<Graph<StepType>.Edge> validatorUploadArchiveEdges = wf.getNodeEdges(VALIDATOR_UPLOAD_ARCHIVE);
    Assertions.assertEquals(1, validatorUploadArchiveEdges.size());
    Assertions.assertEquals(VALIDATOR_VALIDATE_ARCHIVE, validatorUploadArchiveEdges.get(0).getNode());

    // VALIDATOR_VALIDATE_ARCHIVE -> VALIDATOR_DWCA_TO_VERBATIM & VALIDATOR_ABCD_TO_VERBATIM & VALIDATOR_XML_TO_VERBATIM & VALIDATOR_TABULAR_TO_VERBATIM
    List<Graph<StepType>.Edge> validatorValidateArchiveEdges = wf.getNodeEdges(VALIDATOR_VALIDATE_ARCHIVE);
    Assertions.assertEquals(4, validatorValidateArchiveEdges.size());
    Assertions.assertTrue(containsFn.test(validatorValidateArchiveEdges, VALIDATOR_DWCA_TO_VERBATIM));
    Assertions.assertTrue(containsFn.test(validatorValidateArchiveEdges, VALIDATOR_ABCD_TO_VERBATIM));
    Assertions.assertTrue(containsFn.test(validatorValidateArchiveEdges, VALIDATOR_XML_TO_VERBATIM));
    Assertions.assertTrue(containsFn.test(validatorValidateArchiveEdges, VALIDATOR_TABULAR_TO_VERBATIM));

    // VALIDATOR_DWCA_TO_VERBATIM -> VALIDATOR_VERBATIM_TO_INTERPRETED
    List<Graph<StepType>.Edge> dwcaToVerbatimEdges = wf.getNodeEdges(VALIDATOR_DWCA_TO_VERBATIM);
    Assertions.assertEquals(1, dwcaToVerbatimEdges.size());
    Assertions.assertEquals(VALIDATOR_VERBATIM_TO_INTERPRETED, dwcaToVerbatimEdges.get(0).getNode());

    // VALIDATOR_ABCD_TO_VERBATIM -> VALIDATOR_VERBATIM_TO_INTERPRETED
    List<Graph<StepType>.Edge> abcdToVerbatimEdges = wf.getNodeEdges(VALIDATOR_ABCD_TO_VERBATIM);
    Assertions.assertEquals(1, abcdToVerbatimEdges.size());
    Assertions.assertEquals(VALIDATOR_VERBATIM_TO_INTERPRETED, abcdToVerbatimEdges.get(0).getNode());

    // VALIDATOR_XML_TO_VERBATIM -> VALIDATOR_VERBATIM_TO_INTERPRETED
    List<Graph<StepType>.Edge> xmlToVerbatimEdges = wf.getNodeEdges(VALIDATOR_XML_TO_VERBATIM);
    Assertions.assertEquals(1, xmlToVerbatimEdges.size());
    Assertions.assertEquals(VALIDATOR_VERBATIM_TO_INTERPRETED, xmlToVerbatimEdges.get(0).getNode());

    // VALIDATOR_TABULAR_TO_VERBATIM -> VALIDATOR_VERBATIM_TO_INTERPRETED
    List<Graph<StepType>.Edge> validatorTabularToVerbatimEdges = wf.getNodeEdges(VALIDATOR_TABULAR_TO_VERBATIM);
    Assertions.assertEquals(1, validatorTabularToVerbatimEdges.size());
    Assertions.assertEquals(VALIDATOR_VERBATIM_TO_INTERPRETED, validatorTabularToVerbatimEdges.get(0).getNode());

    // VALIDATOR_VERBATIM_TO_INTERPRETED -> VALIDATOR_INTERPRETED_TO_INDEX
    List<Graph<StepType>.Edge> validatorVerbatimToInterpretedEdges = wf.getNodeEdges(VALIDATOR_VERBATIM_TO_INTERPRETED);
    Assertions.assertEquals(1, validatorVerbatimToInterpretedEdges.size());
    Assertions.assertEquals(VALIDATOR_INTERPRETED_TO_INDEX, validatorVerbatimToInterpretedEdges.get(0).getNode());

    // VALIDATOR_INTERPRETED_TO_INDEX ->
    List<Graph<StepType>.Edge> validatorInterpretedToIndexEdges = wf.getNodeEdges(VALIDATOR_INTERPRETED_TO_INDEX);
    Assertions.assertEquals(1, validatorInterpretedToIndexEdges.size());
    Assertions.assertEquals(VALIDATOR_COLLECT_METRICS, validatorInterpretedToIndexEdges.get(0).getNode());

    // VALIDATOR_COLLECT_METRICS -> 0
    List<Graph<StepType>.Edge> validatorCollectMetricsEdges = wf.getNodeEdges(VALIDATOR_COLLECT_METRICS);
    Assertions.assertEquals(0, validatorCollectMetricsEdges.size());
  }

}
