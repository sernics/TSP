package src.main.java.tsp;

public class Main {
  public static void main(String[] args) {
    String filename = "src/main/resources/4-nodes.txt";
    String outputFilename = "src/main/results/out" + filename.substring(filename.lastIndexOf("/") + 1) + ".txt";
    System.out.println(outputFilename);
    Graph graph = new Graph(filename);
    graph.print();
    // graph.toFile(outputFilename);
  }
}