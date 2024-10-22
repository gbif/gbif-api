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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static org.gbif.api.model.pipelines.StepType.*;

public class PipelinesWorkflow {

  private PipelinesWorkflow() {
    // NOP
  }

  private static final Graph<StepType> OCCURRENCE_WF_GRAPH = new Graph<>();
  private static final Graph<StepType> EVENT_OCCURRENCE_WF_GRAPH = new Graph<>();
  private static final Graph<StepType> EVENT_WF_GRAPH = new Graph<>();
  private static final Graph<StepType> VALIDATON_WF_GRAPH = new Graph<>();

  static {
    // Pipelines occurrence workflow
    // 1
    OCCURRENCE_WF_GRAPH.addNode(DWCA_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    OCCURRENCE_WF_GRAPH.addNode(XML_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    OCCURRENCE_WF_GRAPH.addNode(ABCD_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    // 2
    OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_IDENTIFIER, VERBATIM_TO_INTERPRETED);
    // 3
    OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_INTERPRETED, INTERPRETED_TO_INDEX);
    OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_INTERPRETED, HDFS_VIEW);
    OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_INTERPRETED, FRAGMENTER);
    // 4
    OCCURRENCE_WF_GRAPH.addNode(HDFS_VIEW, DATA_WAREHOUSE);

    // Pipelines event-occurrence workflow
    // 1
    EVENT_OCCURRENCE_WF_GRAPH.addNode(DWCA_TO_VERBATIM, VERBATIM_TO_IDENTIFIER);
    // 2
    EVENT_OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_IDENTIFIER, VERBATIM_TO_INTERPRETED);
    // 3
    EVENT_OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_INTERPRETED, INTERPRETED_TO_INDEX);
    EVENT_OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_INTERPRETED, HDFS_VIEW);
    EVENT_OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_INTERPRETED, FRAGMENTER);
    EVENT_OCCURRENCE_WF_GRAPH.addNode(VERBATIM_TO_INTERPRETED, EVENTS_VERBATIM_TO_INTERPRETED);
    // 4
    EVENT_OCCURRENCE_WF_GRAPH.addNode(EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_INTERPRETED_TO_INDEX);
    EVENT_OCCURRENCE_WF_GRAPH.addNode(EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_HDFS_VIEW);
    // 5
    EVENT_OCCURRENCE_WF_GRAPH.addNode(EVENTS_HDFS_VIEW, DATA_WAREHOUSE);

    // Pipelines event only workflow
    // 1
    EVENT_WF_GRAPH.addNode(DWCA_TO_VERBATIM, EVENTS_VERBATIM_TO_INTERPRETED);
    // 2
    EVENT_WF_GRAPH.addNode(EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_INTERPRETED_TO_INDEX);
    EVENT_WF_GRAPH.addNode(EVENTS_VERBATIM_TO_INTERPRETED, EVENTS_HDFS_VIEW);
    // 3
    EVENT_WF_GRAPH.addNode(EVENTS_HDFS_VIEW, DATA_WAREHOUSE);

    // Pipelines validator workflow
    // 1
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_UPLOAD_ARCHIVE, VALIDATOR_VALIDATE_ARCHIVE);
    // 2
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_VALIDATE_ARCHIVE, VALIDATOR_DWCA_TO_VERBATIM);
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_VALIDATE_ARCHIVE, VALIDATOR_XML_TO_VERBATIM);
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_VALIDATE_ARCHIVE, VALIDATOR_ABCD_TO_VERBATIM);
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_VALIDATE_ARCHIVE, VALIDATOR_TABULAR_TO_VERBATIM);
    // 3
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_DWCA_TO_VERBATIM, VALIDATOR_VERBATIM_TO_INTERPRETED);
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_XML_TO_VERBATIM, VALIDATOR_VERBATIM_TO_INTERPRETED);
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_ABCD_TO_VERBATIM, VALIDATOR_VERBATIM_TO_INTERPRETED);
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_TABULAR_TO_VERBATIM, VALIDATOR_VERBATIM_TO_INTERPRETED);
    // 4
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_VERBATIM_TO_INTERPRETED, VALIDATOR_INTERPRETED_TO_INDEX);
    // 5
    VALIDATON_WF_GRAPH.addNode(VALIDATOR_INTERPRETED_TO_INDEX, VALIDATOR_COLLECT_METRICS);
  }

  public static Graph<StepType> getOccurrenceWorkflow() {
    return OCCURRENCE_WF_GRAPH;
  }

  public static Graph<StepType> getEventOccurrenceWorkflow() {
    return EVENT_OCCURRENCE_WF_GRAPH;
  }

  public static Graph<StepType> getEventWorkflow() {
    return EVENT_WF_GRAPH;
  }

  public static Graph<StepType> getValidatorWorkflow() {
    return VALIDATON_WF_GRAPH;
  }

  public static Graph<StepType> getWorkflow(boolean containsOccurrences, boolean containsEvents) {
    if(containsOccurrences && containsEvents){
      return getEventOccurrenceWorkflow();
    } else if(containsOccurrences){
      return getOccurrenceWorkflow();
    } else if (containsEvents){
      return getEventWorkflow();
    }
    return new Graph<>();
  }

  public static class Graph<T> {

    public class Edge {
      private final T node;

      public Edge(T node) {
        this.node = node;
      }

      public T getNode() {
        return node;
      }
    }

    private final Map<T, LinkedList<Edge>> nodes = new HashMap<>();

    private final Map<T, Integer> levels = new HashMap<>();

    private final ToIntFunction<T> calculateLevelFn = t -> levels.get(t) != null ? levels.get(t) + 1 : 1;

    private final ToIntFunction<T> findLevelFn = t -> nodes.values()
      .stream()
      .flatMap(Collection::stream)
      .filter(x->x.getNode().equals(t))
      .findAny()
      .map(x->levels.get(x.getNode()))
      .orElse(1);

    public int getNodesQuantity() {
      return nodes.size();
    }

    public List<Edge> getNodeEdges(T node) {
      return nodes.get(node);
    }

    public Set<T> getAllNodes() {
      return nodes.keySet();
    }

    public Set<T> getAllNodesFor(Set<T> fromTypesSet) {
      return fromTypesSet.stream()
        .map(ft -> bfs(this, ft))
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
    }

    public int getLevel(T t){
      return levels.get(t);
    }

    public Set<T> getRootNodesFor(Set<T> fromTypesSet) {
      Map<T, Set<T>> map = fromTypesSet.stream()
        .collect(Collectors.toMap(Function.identity(), ft -> bfs(this, ft)));

      Set<T> result = new LinkedHashSet<>();

      fromTypesSet.forEach(ts -> {
        result.add(ts);
        map.forEach((key, value) -> {
          if (key != ts && value.contains(ts)) {
            result.remove(ts);
          }
        });
      });

      return result;
    }

    private void addNode(T fromNode, T toNode) {
      if (nodes.containsKey(fromNode)) {
        LinkedList<Edge> edges = nodes.get(fromNode);

        int order;
        if (edges.isEmpty()) {
          order = calculateLevelFn.applyAsInt(fromNode);
        } else {
          order = levels.get(edges.getLast().getNode());
        }

        edges.add(new Edge(toNode));
        levels.put(toNode, order);
      } else {
        int order = calculateLevelFn.applyAsInt(fromNode);
        nodes.put(fromNode, new LinkedList<>(Collections.singletonList(new Edge(toNode))));
        levels.put(fromNode, order);
        levels.put(toNode, order + 1);
      }

      nodes.computeIfAbsent(toNode, n -> new LinkedList<>(Collections.emptyList()));
      levels.put(toNode, findLevelFn.applyAsInt(toNode));
    }

    private static <T> Set<T> bfs(Graph<T> graph, T startNode) {

      Set<T> resultSet = new HashSet<>();
      Set<T> visitedNodes = new HashSet<>(graph.getNodesQuantity());
      Queue<T> queue = new LinkedList<>();
      T currentNode;

      visitedNodes.add(startNode);
      queue.add(startNode);

      while (!queue.isEmpty()) {
        currentNode = queue.poll();
        resultSet.add(currentNode);

        graph.getNodeEdges(currentNode).forEach(edge -> {
          T node = edge.getNode();
          if (visitedNodes.contains(node)) {
            return;
          }
          visitedNodes.add(node);
          queue.add(node);
        });
      }

      return resultSet;
    }

  }
}
