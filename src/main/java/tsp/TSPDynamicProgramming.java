package src.main.java.tsp;

import java.util.ArrayList;
import java.util.List;

public class TSPDynamicProgramming implements TSPInterface {
  private static final long TIME_LIMIT = 300000; // 5 minutes in milliseconds
  private long startTime; // Execution start time
  private Graph graph;
  private Integer start;
  private int[][] matrix;

  public TSPDynamicProgramming(Graph graph, Integer start) {
    this.graph = graph;
    this.start = start;
    this.matrix = new int[graph.getSize()][1 << graph.getSize()];
  }

  public Integer run() {
    startTime = System.currentTimeMillis(); // Save the start time

    int mask = (1 << graph.getSize()) - 1;
    return tsp(start, mask);
  }

  private int tsp(int current, int mask) {
    // Check if time limit exceeded
    if (System.currentTimeMillis() - startTime > TIME_LIMIT) {
      return Integer.MAX_VALUE; // Indicate that the time limit has been exceeded
    }

    if (mask == 0) {
      return graph.get(current, start);
    }

    if (matrix[current][mask] != 0) {
      return matrix[current][mask];
    }

    int minDistance = Integer.MAX_VALUE;

    for (int next = 0; next < graph.getSize(); next++) {
      if ((mask & (1 << next)) != 0) {
        int distance = graph.get(current, next) + tsp(next, mask ^ (1 << next));
        minDistance = Math.min(minDistance, distance);
      }
    }

    matrix[current][mask] = minDistance;
    return minDistance;
  }

  public List<Integer> getPath() {
    List<Integer> path = new ArrayList<>();
    int mask = (1 << graph.getSize()) - 1;
    int current = start;

    while (mask != 0) {
      int next = -1;
      int minDistance = Integer.MAX_VALUE;

      for (int i = 0; i < graph.getSize(); i++) {
        if ((mask & (1 << i)) != 0) {
          int distance = graph.get(current, i) + matrix[i][mask ^ (1 << i)];
          if (distance < minDistance) {
            minDistance = distance;
            next = i;
          }
        }
      }

      current = next;
      mask ^= (1 << next);
      path.add(current);

      // Check if time limit exceeded
      if (System.currentTimeMillis() - startTime > TIME_LIMIT) {
        return null; // Indicate that the time limit has been exceeded
      }
    }

    path.add(start);
    return path;
  }
}
