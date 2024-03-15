package src.main.java.tsp;

public class TSPGreedy implements TSPInterface {
  private Graph graph;
  private Integer inicio;
  private Boolean[] visitados;
  public TSPGreedy(Graph graph, Integer inicio) {
    this.graph = graph;
    this.inicio = inicio;
    visitados = new Boolean[graph.getSize()];
    for (int i = 0; i < graph.getSize(); i++) {
      visitados[i] = false;
    }
  }
  public Integer run() {
    Integer[] path = new Integer[graph.getSize()];
    Integer[] distances = graph.get(inicio);
    visitados[inicio] = true;
    path[0] = inicio;
    for (int i = 1; i < graph.getSize(); i++) {
      Integer nearest = nearestCity(distances);
      visitados[nearest] = true;
      path[i] = nearest;
      distances = graph.get(nearest);
    }
    Integer total = 0;
    for (int i = 0; i < graph.getSize() - 1; i++) {
      total += graph.get(path[i], path[i + 1]);
    }
    total += graph.get(path[graph.getSize() - 1], path[0]);
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
}
