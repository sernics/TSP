package src.main.java.tsp;

import java.util.ArrayList;
import java.util.List;

public class TSPBruteForce implements TSPInterface {
  private Graph graph;
  private Integer inicio;
  private Integer[] visitados;
  private List<Integer> bestPath;
  private Integer bestCost;

  public TSPBruteForce(Graph graph, Integer inicio) {
    this.graph = graph;
    this.inicio = inicio;
    visitados = new Integer[graph.getSize()];
    bestPath = new ArrayList<>();
    bestCost = Integer.MAX_VALUE;
  }

  public Integer run() {
    // Initialize the visited array
    for (int i = 0; i < visitados.length; i++) {
      visitados[i] = 0;
    }

    // Mark the starting node as visited
    visitados[inicio] = 1;

    // Start the recursive function
    tspRecursive(inicio, 1, 0, new ArrayList<>());

    return bestCost;
  }

  private void tspRecursive(Integer currentNode, Integer count, Integer cost, List<Integer> path) {
    // Base case: If all nodes have been visited, update the best path and cost
    if (count == graph.getSize()) {
      cost += graph.get(currentNode, inicio);
      if (cost < bestCost) {
        bestCost = cost;
        bestPath = new ArrayList<>(path);
        bestPath.add(inicio);
      }
      return;
    }

    // Try all unvisited nodes as the next node in the path
    for (int nextNode = 0; nextNode < graph.getSize(); nextNode++) {
      if (visitados[nextNode] == 0) {
        // Mark the next node as visited
        visitados[nextNode] = 1;

        // Calculate the cost of the current path
        Integer currentCost = cost + graph.get(currentNode, nextNode);

        // Add the current node to the path
        path.add(currentNode);

        // Recursive call
        tspRecursive(nextNode, count + 1, currentCost, path);

        // Remove the current node from the path (backtracking)
        path.remove(path.size() - 1);

        // Mark the next node as unvisited
        visitados[nextNode] = 0;
      }
    }
  }

  public List<Integer> getPath() {
    return bestPath;
  }
}
