package src.main.java.tsp;

import java.util.ArrayList;
import java.util.List;

public class TSPGreedy implements TSPInterface {
  private static final long TIME_LIMIT = 300000; // 5 minutes in milliseconds
  private long startTime; // Execution start time
  private Graph graph;
  private Integer start;
  private Boolean[] visited;
  private List<Integer> path;

  public TSPGreedy(Graph graph, Integer start) {
    this.graph = graph;
    this.start = start;
    visited = new Boolean[graph.getSize()];
    for (int i = 0; i < graph.getSize(); i++) {
      visited[i] = false;
    }
    path = new ArrayList<>();
  }

  public Integer run() {
    startTime = System.currentTimeMillis(); // Save the start time

    Integer[] distances = graph.get(start);
    visited[start] = true;
    path.add(start); // Add start to path
    for (int i = 1; i < graph.getSize(); i++) {
      Integer nearest = nearestCity(distances);
      visited[nearest] = true;
      path.add(nearest); // Add nearest to path
      distances = graph.get(nearest);

      // Check if time limit exceeded
      if (System.currentTimeMillis() - startTime > TIME_LIMIT) {
        return null; // Returning null to indicate that the execution exceeded time limit
      }
    }

    Integer total = 0;
    for (int i = 0; i < graph.getSize() - 1; i++) {
      total += graph.get(path.get(i), path.get(i + 1)); // Access elements from path using get() method
    }
    total += graph.get(path.get(graph.getSize() - 1), path.get(0)); // Access elements from path using get() method
    return total;
  }

  private Integer nearestCity(Integer[] distances) {
    Integer min = Integer.MAX_VALUE;
    Integer index = 0;
    for (int i = 0; i < distances.length; i++) {
      if (distances[i] != null && distances[i] < min && !visited[i]) {
        min = distances[i];
        index = i;
      }
    }
    return index;
  }

  public List<Integer> getPath() {
    return path;
  }
}
