package de.kryptikk.aoc.aoc04;

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
        for (int i : numbers) {
            for (Board b : boards)
                if (b.choose(i))
                    System.out.println("Something won");
        }
    }

    private static void part2() {
    }
}
