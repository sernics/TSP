package src.main.java.tsp;

public class Main {
  public static void main(String[] args) {
    Integer numberOfInstances = 24;
    Benchmark benchmark = new Benchmark();
    benchmark.run(numberOfInstances);
  }
}