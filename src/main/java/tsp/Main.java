package src.main.java.tsp;

public class Main {
  public static void main(String[] args) {
    String filename = "src/main/resources/4-nodes.txt";
    String outputFilename = "src/main/results/out" + filename.substring(filename.lastIndexOf("/") + 1) + ".txt";
    System.out.println(outputFilename);
    Graph graph = new Graph(filename);
    TSPInterface tsp = new TSPBruteForce(graph, 0);
    System.out.println("Resultado TSP Brute Force: " + tsp.run());
    System.out.print("Path: ");
    tsp.printPath(tsp.getPath());
    tsp = new TSPGreedy(graph, 0);
    System.out.println("Resultado TSP Greedy: " + tsp.run());
    System.out.print("Path: ");
    tsp.printPath(tsp.getPath());
    tsp = new TSPDynamicProgramming(graph, 0);
    System.out.println("Resultado TSP Dynamic Programming: " + tsp.run());
    System.out.print("Path: ");
    tsp.printPath(tsp.getPath());
    graph.toFile(outputFilename);
  }  
}