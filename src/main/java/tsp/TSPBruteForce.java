package src.main.java.tsp;

import java.util.ArrayList;
import java.util.List;

public class TSPBruteForce implements TSPInterface {
  private static final long TIME_LIMIT = 6000; // 6 seconds
  private long startTime; // Execution start time
  private Graph graph;
  private Integer start;
  private Integer[] visited;
  private List<Integer> bestPath;
  private Integer bestCost;

  public TSPBruteForce(Graph graph, Integer start) {
    this.graph = graph;
    this.start = start;
    visited = new Integer[graph.getSize()];
    bestPath = new ArrayList<>();
    bestCost = Integer.MAX_VALUE;
  }

  public Integer run() {
    startTime = System.currentTimeMillis(); // Save the start time

    // Initialize the visited array
    for (int i = 0; i < visited.length; i++) {
      visited[i] = 0;
    }

    // Mark the starting node as visited
    visited[start] = 1;

    // Start the recursive function
    tspRecursive(start, 1, 0, new ArrayList<>());

    return bestCost;
  }

  private void tspRecursive(Integer currentNode, Integer count, Integer cost, List<Integer> path) {
    // Check if time limit exceeded
    if (System.currentTimeMillis() - startTime > TIME_LIMIT) {
      return;
    }

    // Base case: If all nodes have been visited, update the best path and cost
    if (count == graph.getSize()) {
      cost += graph.get(currentNode, start);
      if (cost < bestCost) {
        bestCost = cost;
        bestPath = new ArrayList<>(path);
        bestPath.add(start);
      }
      return;
    }

    // Try all unvisited nodes as the next node in the path
    for (int nextNode = 0; nextNode < graph.getSize(); nextNode++) {
      if (visited[nextNode] == 0) {
        // Mark the next node as visited
        visited[nextNode] = 1;

        // Calculate the cost of the current path
        Integer currentCost = cost + graph.get(currentNode, nextNode);

        // Add the current node to the path
        path.add(currentNode);

        // Recursive call
        tspRecursive(nextNode, count + 1, currentCost, path);

        // Remove the current node from the path (backtracking)
        path.remove(path.size() - 1);

        // Mark the next node as unvisited
        visited[nextNode] = 0;
      }
    }
  }

  public List<Integer> getPath() {
    return bestPath;
  }
}
