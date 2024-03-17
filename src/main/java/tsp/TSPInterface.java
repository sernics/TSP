package src.main.java.tsp;

import java.util.List;

public interface TSPInterface {
  Integer run();
  List<Integer> getPath();
  public default char integerToChar(int i) {
    return (char) (i + 'A');
  }
  public default void printPath(List<Integer> path) {
    for (int i = 0; i < path.size(); i++) {
      System.out.print(integerToChar(path.get(i)));
      if (i < path.size() - 1) {
        System.out.print(" -> ");
      }
    }
    System.out.println();
  }
}
