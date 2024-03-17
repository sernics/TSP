package src.main.java.tsp;

import java.util.ArrayList;
import java.util.List;

public class TSPGreedy implements TSPInterface {
  private Graph graph;
  private Integer inicio;
  private Boolean[] visitados;
  private List<Integer> path; 

  public TSPGreedy(Graph graph, Integer inicio) {
    this.graph = graph;
    this.inicio = inicio;
    visitados = new Boolean[graph.getSize()];
    for (int i = 0; i < graph.getSize(); i++) {
      visitados[i] = false;
    }
    path = new ArrayList<Integer>();
  }

  public Integer run() {
    Integer[] distances = graph.get(inicio);
    visitados[inicio] = true;
    path.add(inicio); // Add inicio to path
    for (int i = 1; i < graph.getSize(); i++) {
      Integer nearest = nearestCity(distances);
      visitados[nearest] = true;
      path.add(nearest); // Add nearest to path
      distances = graph.get(nearest);
    }
    Integer total = 0;
    for (int i = 0; i < graph.getSize() - 1; i++) {
      total += graph.get(path.get(i), path.get(i + 1)); // Access elements from path using get() method
    }
    total += graph.get(path.get(graph.getSize() - 1), path.get(0)); // Access elements from path using get() method
    path.add(inicio); // Add inicio to path again to complete the cycle
    return total;
  }

  private Integer nearestCity(Integer[] distances) {
    Integer min = Integer.MAX_VALUE;
    Integer index = 0;
    for (int i = 0; i < distances.length; i++) {
      if (distances[i] != null && distances[i] < min && !visitados[i]) {
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
