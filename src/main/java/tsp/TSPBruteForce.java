package src.main.java.tsp;

public class TSPBruteForce implements TSPInterface {
  private Graph graph;
  private Integer inicio;
  private Integer[] visitados;
  public TSPBruteForce(Graph graph, Integer inicio) {
    this.graph = graph;
    this.inicio = inicio;
    visitados = new Integer[graph.getSize()];
  }
  public Integer run() {
    // Initialize the visited array
    for (int i = 0; i < visitados.length; i++) {
      visitados[i] = 0;
    }

    // Mark the starting node as visited
    visitados[inicio] = 1;

    // Start the recursive function
    return tspRecursive(inicio, 1, 0);
  }

  private Integer tspRecursive(Integer currentNode, Integer count, Integer cost) {
    // Base case: If all nodes have been visited, return the cost of the current path
    if (count == graph.getSize()) {
      return cost + graph.get(currentNode, inicio);
    }

    // Initialize the minimum cost
    Integer minCost = Integer.MAX_VALUE;

    // Try all unvisited nodes as the next node in the path
    for (int nextNode = 0; nextNode < graph.getSize(); nextNode++) {
      if (visitados[nextNode] == 0) {
        // Mark the next node as visited
        visitados[nextNode] = 1;

        // Calculate the cost of the current path
        Integer currentCost = tspRecursive(nextNode, count + 1, cost + graph.get(currentNode, nextNode));

        // Update the minimum cost
        minCost = Math.min(minCost, currentCost);

        // Mark the next node as unvisited (backtracking)
        visitados[nextNode] = 0;
      }
    }

    // Return the minimum cost
    return minCost;
  }
}
