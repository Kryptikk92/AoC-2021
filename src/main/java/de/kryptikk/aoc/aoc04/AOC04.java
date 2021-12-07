package de.kryptikk.aoc.aoc04;

import de.kryptikk.aoc.Pair;
import de.kryptikk.aoc.PuzzleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AOC04 {

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

    private static void part1() throws IOException {
        List<String> list = getList("Input4.txt");
        List<Integer> numbers = Arrays.stream(list.get(0).split(",")).map(Integer::parseInt).toList();
        List<Board> boards = new ArrayList<>();
        for (int i = 1; i + 6 <= list.size(); i += 6) {
            List<Integer> boardNumbers = Arrays.stream(String.join(" ", list.subList(i, i + 6))
                                                         .split("\s+"))
              .filter(s -> !"".equals(s))
              .map(s -> Integer.parseInt(s.trim()))
              .toList();
            boards.add(new Board(boardNumbers));
        }
        boolean someoneWon = false;
        for (int i : numbers) {
            for (Board b : boards)
                if (!someoneWon && b.choose(i)) {
                    printScore(b, i);
                    someoneWon = true;
                }
        }
    }

    private static void printScore(Board b, int i) {
        int score = b.getUnmarked().stream().map(BoardCell::getI).reduce(0, Integer::sum) * i;
        System.out.printf("Won with Score: %d\n", score);
    }

    private static void part2() throws IOException {
        List<String> list = getList("Input4.txt");
        List<Integer> numbers = Arrays.stream(list.get(0).split(",")).map(Integer::parseInt).toList();
        List<Board> boards = new ArrayList<>();
        for (int i = 1; i + 6 <= list.size(); i += 6) {
            List<Integer> boardNumbers = Arrays.stream(String.join(" ", list.subList(i, i + 6))
                                                         .split("\s+"))
              .filter(s -> !"".equals(s))
              .map(s -> Integer.parseInt(s.trim()))
              .toList();
            boards.add(new Board(boardNumbers));
        }
        List<Pair<Board, Integer>> wonBoards = new ArrayList<>();

        for (int i : numbers) {
            for (Board b : boards)
                if ((!wonBoards.stream().map(Pair::first).toList().contains(b)) && b.choose(i)) {
                    wonBoards.add(new Pair<>(b, i));
                }
        }
        Pair<Board, Integer> lastWon = wonBoards.get(wonBoards.size()-1);
        printScore(lastWon.first(), lastWon.second());
    }
}
