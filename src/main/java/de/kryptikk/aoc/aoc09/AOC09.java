package de.kryptikk.aoc.aoc09;

import de.kryptikk.aoc.Pair;
import de.kryptikk.aoc.PuzzleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AOC09 {

    private static final String filename = "Input09.txt";

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

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

    private static int[][] getGrid() throws IOException {
        List<String> list = getList(filename);
        int y = list.size();
        int x = list.get(0).length();
        int[][] grid = new int[y][x];
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                grid[j][i] = Integer.parseInt(String.valueOf(list.get(j).charAt(i)));
            }
        }
        return grid;
    }

    private static void part1() throws IOException {
        int[][] grid = getGrid();
        int y = grid.length;
        int x = grid[0].length;
        int risk = 0;
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                int current = grid[j][i];
                int smaller = 0;
                if (j-1 >= 0 && current < grid[j-1][i])
                    smaller++;
                if (j+1<y && current < grid[j+1][i])
                    smaller++;
                if (i-1>=0 && current < grid[j][i-1])
                    smaller++;
                if(i+1<x && current < grid[j][i+1])
                    smaller++;
                if (smaller == 4 || (smaller == 3 && isEdge(j, i, grid)) || (smaller == 2 && isCorner(j, i, grid)))
                    risk += current + 1;
            }
        }
        System.out.println(risk);
    }

    private static boolean isCorner(int j, int i, int[][] grid) {
        int y = grid.length;
        int x = grid[0].length;
        boolean topLeft = j == 0 && i == 0;
        boolean topRight = j == 0 && i == x-1;
        boolean bottomLeft = j == y-1 && i == 0;
        boolean bottomRight = j == y-1 && i == x-1;
        return topLeft || topRight || bottomLeft || bottomRight;
    }

    private static boolean isEdge(int j, int i, int[][] grid) {
        int y = grid.length;
        int x = grid[0].length;
        boolean leftRight = (j > 0 && j < y-1) && (i == 0 || i == x-1);
        boolean topDown = (i > 0 && i < x-1) && (j == 0 || j == y-1);
        return leftRight || topDown;
    }

    private static void part2() throws IOException {
        int[][] grid = getGrid();
        int y = grid.length;
        int x = grid[0].length;
        List<List<Pair<Integer, Integer>>> listOfBasins = new ArrayList<>();
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                int current = grid[j][i];
                int smaller = 0;
                if (j-1 >= 0 && current < grid[j-1][i])
                    smaller++;
                if (j+1<y && current < grid[j+1][i])
                    smaller++;
                if (i-1>=0 && current < grid[j][i-1])
                    smaller++;
                if(i+1<x && current < grid[j][i+1])
                    smaller++;
                if (smaller == 4 || (smaller == 3 && isEdge(j, i, grid)) || (smaller == 2 && isCorner(j, i, grid))) {
                    List<Pair<Integer, Integer>> basin = new ArrayList<>();
                    basin.add(new Pair<>(j,i));
                    listOfBasins.add(basin);
                }
            }
        }
        for (List<Pair<Integer, Integer>> basin : listOfBasins) {
            Set<Pair<Integer, Integer>> toAdd = widenBasin(basin, grid);
            while (!toAdd.isEmpty()) {
                basin.addAll(toAdd);
                toAdd = widenBasin(basin, grid);
            }
        }
        listOfBasins = listOfBasins.stream().sorted(Comparator.comparingInt(List::size)).toList();
        System.out.println(listOfBasins.get(listOfBasins.size()-1).size() * listOfBasins.get(listOfBasins.size()-2).size() * listOfBasins.get(listOfBasins.size()-3).size());
    }

    private static Set<Pair<Integer, Integer>> widenBasin(List<Pair<Integer, Integer>> basin, int[][] grid) {
        Set<Pair<Integer, Integer>> toAdd = new HashSet<>();
        int y = grid.length;
        int x = grid[0].length;

        for (Pair<Integer, Integer> coord : basin) {
            int j = coord.first();
            int i = coord.second();
            int current = grid[coord.first()][coord.second()];
            if (!basin.contains(new Pair<>(j-1, i)) && j-1 >= 0 && current < grid[j-1][i] && grid[j-1][i] != 9)
                toAdd.add(new Pair<>(j-1, i));
            if (!basin.contains(new Pair<>(j+1, i)) && j+1<y && current < grid[j+1][i] && grid[j+1][i] != 9)
                toAdd.add(new Pair<>(j+1, i));
            if (!basin.contains(new Pair<>(j, i-1)) && i-1>=0 && current < grid[j][i-1] && grid[j][i-1] != 9)
                toAdd.add(new Pair<>(j, i-1));
            if(!basin.contains(new Pair<>(j, i+1)) && i+1<x && current < grid[j][i+1] && grid[j][i+1] != 9)
                toAdd.add(new Pair<>(j, i+1));
        }

        return toAdd;
    }
}
