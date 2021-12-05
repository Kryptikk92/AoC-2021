package de.kryptikk.aoc.aoc05;

import de.kryptikk.aoc.PuzzleInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AOC05 {

  public static void main(String[] args) throws IOException {
    part1();
    part2();
  }

  static Function<String, Line> cvtToLine = s -> {
    String[] token = s.split(" -> ");
    String[] coord1 = token[0].split(",");
    String[] coord2 = token[1].split(",");
    int x1, x2, y1, y2;
    x1 = Integer.parseInt(coord1[0]);
    x2 = Integer.parseInt(coord2[0]);
    y1 = Integer.parseInt(coord1[1]);
    y2 = Integer.parseInt(coord2[1]);
    if (y1 == y2 && x2 < x1) {
      int tmp = x1;
      x1 = x2;
      x2 = tmp;
    }
    if (x1 == x2 && y2 < y1) {
      int tmp = y1;
      y1 = y2;
      y2 = tmp;
    }
    if (x1 > x2) {
      int tmp = x1;
      x1 = x2;
      x2 = tmp;
      tmp = y1;
      y1 = y2;
      y2 = tmp;
    }
    return new Line(x1, y1, x2, y2);
  };

  public static List<String> getList(String inputName) throws IOException {
    InputStream input = PuzzleInput.getInput(inputName);
    List<String> result = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
      for (String line; (line = reader.readLine()) != null; ) {
        result.add(line);
      }
    }
    return result;
  }

  private static void part1() throws IOException {
    List<String> list = getList("Input05.txt");
    List<Line> lines = list.stream().map(cvtToLine).toList();
    lines = lines.stream().filter(l -> l.x1() == l.x2() || l.y1() == l.y2()).toList();
    lines.forEach(System.out::println);
    int maxX = 0, maxY = 0;
    for (Line l : lines) {
      if (l.x1() > maxX)
        maxX = l.x1();
      if (l.x2() > maxX)
        maxX = l.x2();
      if (l.y1() > maxY)
        maxY = l.y1();
      if (l.y2() > maxY)
        maxY = l.y2();
    }

    int[][] grid = new int[maxX+1][maxY+1];
    for (Line l : lines) {
      for (int i = l.x1(); i <= l.x2(); i++) {
        for (int j = l.y1(); j <= l.y2(); j++) {
          grid[i][j]++;
        }
      }
    }

    int counter = 0;
    for (int i = 0; i < maxX+1; i++) {
      for (int j = 0; j < maxY+1; j++) {
        if (grid[i][j] > 1)
          counter++;
      }
    }
    System.out.println(counter);
  }

  private static void part2() throws IOException {
    List<String> list = getList("Input05.txt");
    List<Line> lines = list.stream().map(cvtToLine).toList();
    //lines.forEach(System.out::println);
    int maxX = 0, maxY = 0;
    for (Line l : lines) {
      if (l.x1() > maxX)
        maxX = l.x1();
      if (l.x2() > maxX)
        maxX = l.x2();
      if (l.y1() > maxY)
        maxY = l.y1();
      if (l.y2() > maxY)
        maxY = l.y2();
    }
    maxX = 1000;
    maxY = 1000;

    int[][] grid = new int[maxX+1][maxY+1];
    for (Line l : lines) {
      //System.out.println(l);
      if (l.x1() == l.x2() || l.y1() == l.y2()) {
        for (int i = l.x1(); i <= l.x2(); i++) {
          for (int j = l.y1(); j <= l.y2(); j++) {
            grid[i][j]++;
          }
        }
      } else {
        int length = Math.abs(l.x1() - l.x2());
        for (int i = 0; i <= length; i++) {
          int curX = i + l.x1();
          int curY = l.y1() < l.y2() ? l.y1() + i : l.y1() - i;
          grid[curX][curY]++;
        }
      }
      //printGrid(grid);
    }

    int counter = 0;
    for (int i = 0; i < maxX+1; i++) {
      for (int j = 0; j < maxY+1; j++) {
        if (grid[i][j] > 1)
          counter++;
      }
    }
    System.out.println(counter);
  }

  private static void printGrid(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[j][i] == 0)
          System.out.print(".");
        else
          System.out.print(grid[j][i]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
