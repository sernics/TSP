package src.main.java.tsp;

public class Benchmark {
  public void run(Integer numberOfInstances) {
    System.out.println("Instancia, Valor Fuerza Bruta, Tiempo Fuerza Bruta (ms), Valor Greedy, Tiempo Greedy (ms), Valor Programación Dinámica, Tiempo Programación Dinámica (ms)");
    for (int i = 4; i <= numberOfInstances; i += 2) {
      System.out.print(i + " nodos,");
      Graph graph = new Graph(i);
      graph.generateRandomGraph();
      String outputFilename = "src/main/results/out-" + i + "-generated.txt";
      graph.toFile(outputFilename);
      // Para saber el tiempo que tardo en ejecutarse
      long startTime = System.nanoTime();
      TSPInterface tsp = new TSPBruteForce(graph, 0);
      System.out.print(tsp.run() + ",");
      long endTime = System.nanoTime();
      System.out.print((endTime - startTime) / 1000000 + "ms,");
      startTime = System.nanoTime();
      tsp = new TSPGreedy(graph, 0);
      System.out.print(tsp.run() + ",");
      endTime = System.nanoTime();
      System.out.print((endTime - startTime) / 1000000 + "ms,");
      startTime = System.nanoTime();
      tsp = new TSPDynamicProgramming(graph, 0);
      System.out.print(tsp.run() + ",");
      endTime = System.nanoTime();
      System.out.println((endTime - startTime) / 1000000 + "ms");
    }
  }
}
