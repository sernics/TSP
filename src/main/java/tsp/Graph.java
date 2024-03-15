package src.main.java.tsp;

// Importar todo lo necesario para leer archivos
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Graph {
  Integer[][] graph;
  Integer size;
  Graph(String filename) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      size = Integer.parseInt(reader.readLine());
      // Crear un grafo de tamaño size x size con todos los valores en 0
      graph = new Integer[size][size];
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          graph[i][j] = 0;
        }
      }
      String line;
      while ((line = reader.readLine()) != null) {
        String[] values = line.split(" ");
        // values[0] es A -> convertirlo a 0. B -> 1, etc.
        int i = charToInteger(values[0].charAt(0));
        int j = charToInteger(values[1].charAt(0));
        int value = Integer.parseInt(values[2]);
        graph[i][j] = value;
        graph[j][i] = value;
      }
      reader.close();
    } catch (Exception e) {
      System.out.println("Error al leer el archivo");
    }
  }
  public Integer get(Integer i, Integer j) {
    return graph[i][j];
  }
  public Integer[] get(Integer i) {
    return graph[i];
  }
  public Integer getSize() {
    return size;
  }
  public void print() {
    // Imprimir A B C D ...
    System.out.print("  ");
    for (int i = 0; i < size; i++) {
      System.out.print(integerToChar(i) + "  ");
    }
    System.out.println();
    for (int i = 0; i < size; i++) {
      System.out.print(integerToChar(i) + " ");
      for (int j = 0; j < size; j++) {
        System.out.print(graph[i][j] + " ");
      }
      System.out.println();
    }
  }
  public void toFile(String filename) {
  try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    writer.write(",");
    for (int i = 0; i < size; i++) {
      writer.write(integerToChar(i) + ",");
    }
    writer.newLine();
    for (int i = 0; i < size; i++) {
      writer.write(integerToChar(i) + ",");
      for (int j = 0; j < size; j++) {
        writer.write(graph[i][j] + ",");
      }
      writer.newLine();
    }
    writer.close();
  } catch (IOException e) {
    System.out.println("Error al escribir en el archivo");
  }
}
  private Integer charToInteger(char c) {
    return c - 'A';
  }
  private char integerToChar(Integer i) {
    return (char) (i + 'A');
  }
}
