package src.main.java.tsp;

import java.util.ArrayList;

public class Benchmark {
  public void run(Integer numberOfInstances) {
    System.out.println("Instancia, Media Valor Fuerza Bruta, Media Tiempo Fuerza Bruta (ms), Media Valor Greedy, Media Tiempo Greedy (ms), Media Valor Programación Dinámica, Media Tiempo Programación Dinámica (ms)");
    for (int i = 4; i <= numberOfInstances; i += 2) {
      ArrayList<Integer> bruteForce = new ArrayList<>();
      ArrayList<Integer> timeBruteForce = new ArrayList<>();
      ArrayList<Integer> greedy = new ArrayList<>();
      ArrayList<Integer> timeGreedy = new ArrayList<>();
      ArrayList<Integer> dynamicProgramming = new ArrayList<>();
      ArrayList<Integer> timeDynamicProgramming = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        Graph graph = new Graph(i);
        graph.generateRandomGraph();
        long startTime = System.currentTimeMillis();
        TSPInterface tsp = new TSPBruteForce(graph, 0);
        bruteForce.add(tsp.run());
        long endTime = System.currentTimeMillis();
        timeBruteForce.add((int) (endTime - startTime));
        startTime = System.currentTimeMillis();
        tsp = new TSPGreedy(graph, 0);
        greedy.add(tsp.run());
        endTime = System.currentTimeMillis();
        timeGreedy.add((int) (endTime - startTime));
        startTime = System.currentTimeMillis();
        tsp = new TSPDynamicProgramming(graph, 0);
        dynamicProgramming.add(tsp.run());
        endTime = System.currentTimeMillis();
        timeDynamicProgramming.add((int) (endTime - startTime));
      }
      System.out.print(i + " nodos,");
      Graph graph = new Graph(i);
      graph.generateRandomGraph();
      String outputFilename = "src/main/results/out-" + i + "-generated.txt";
      graph.toFile(outputFilename);
      // Media de los valores obtenidos en bruteForce
      System.out.print(bruteForce.stream().mapToInt(Integer::intValue).sum() / bruteForce.size() + ",");
      // Media de los tiempos obtenidos en bruteForce
      System.out.print(timeBruteForce.stream().mapToInt(Integer::intValue).sum() / timeBruteForce.size() + "ms,");
      // Media de los tiempos obtenidos en greedy
      System.out.print(greedy.stream().mapToInt(Integer::intValue).sum() / greedy.size() + ",");
      // Media de los valores obtenidos en greedy
      System.out.print(timeGreedy.stream().mapToInt(Integer::intValue).sum() / timeGreedy.size() + "ms,");
      // Media de los valores obtenidos en dynamicProgramming
      System.out.print(dynamicProgramming.stream().mapToInt(Integer::intValue).sum() / dynamicProgramming.size() + ",");
      // Media de los tiempos obtenidos en dynamicProgramming
      System.out.print(timeDynamicProgramming.stream().mapToInt(Integer::intValue).sum() / timeDynamicProgramming.size() + "ms\n");
    }
  }
}
