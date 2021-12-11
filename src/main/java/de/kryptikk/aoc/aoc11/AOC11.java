package de.kryptikk.aoc.aoc11;

import de.kryptikk.aoc.PuzzleInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AOC11 {

  private static final String filename = "Input11.txt";

  public static void main(String[] args) throws IOException {
    part1();
    part2();
  }

  public static List<String> getList() {
    InputStream input = PuzzleInput.getInput(filename);
    List<String> result = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
      for (String line; (line = reader.readLine()) != null; ) {
        result.add(line);
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return result;
  }

  private static Octopus[][] getOctopuses() {
    List<String> list = getList();
    int y = list.size();
    int x = list.get(0).length();
    Octopus[][] octopuses = new Octopus[y][x];
    for (int j = 0; j < y; j++) {
      for (int i = 0; i < x; i++) {
        octopuses[j][i] = new Octopus(Integer.parseInt(String.valueOf(list.get(j).charAt(i))));
      }
    }
    return octopuses;
  }

  private static void part1() {
    Octopus[][] octopuses = getOctopuses();
    int y = octopuses.length;
    int x = octopuses[0].length;
    int maxSteps = 10000;
    long flashCounter = 0;
    for (int step = 0; step < maxSteps; step++) {
      boolean everyoneFlashed = true;
      boolean someoneFlashed = true;
      //increase energy level
      for (Octopus[] octopus : octopuses) {
        for (int i = 0; i < x; i++) {
          Octopus current = octopus[i];
          current.increaseEnergy();
        }
      }
      //flash and increase adjacent
      while(someoneFlashed) {
        someoneFlashed = false;
        for (int j = 0; j < y; j++) {
          for (int i = 0; i < x; i++) {
            Octopus current = octopuses[j][i];
            if (current.getEnergy() > 9 && !current.isFlashed()) {
              current.setFlashed(true);
              someoneFlashed = true;
              for (int jOff = -1; jOff <= 1; jOff++) {
                for (int iOff = -1; iOff <= 1; iOff++) {
                  if (j+jOff >= 0 && j+jOff < y && i+iOff >= 0 && i+iOff < x) {
                    Octopus adjacent = octopuses[j+jOff][i+iOff];
                    adjacent.increaseEnergy();
                  }
                }
              }
            }
          }
        }
      }
      //everybody flashed
      for (Octopus[] octopus : octopuses) {
        for (int i = 0; i < x; i++) {
          Octopus current = octopus[i];
          everyoneFlashed &= current.isFlashed();
          if (current.isFlashed()) {
            current.setEnergy(0);
            current.setFlashed(false);
            flashCounter++;
          }
        }
      }
      if (everyoneFlashed) {
        System.out.println("Synchronized at step: " + (step+1));
        break;
      }
    }
    System.out.println(flashCounter);
  }

  private static void part2() {}
}
