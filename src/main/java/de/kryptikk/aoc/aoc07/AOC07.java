package de.kryptikk.aoc.aoc07;

import de.kryptikk.aoc.PuzzleInput;
import de.kryptikk.aoc.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class AOC07 {

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
        String input = getList("Input07.txt").get(0);
        List<Integer> crabs = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
        int max = crabs.stream().max(Comparator.naturalOrder()).orElseThrow();
        int min = crabs.stream().min(Comparator.naturalOrder()).orElseThrow();
        Pair<Integer, Integer> minimum = IntStream.range(min, max + 1)
                .mapToObj(i -> new Pair<>(i, crabs.stream().map(c -> Math.abs(c - i)).reduce(0, Integer::sum)))
                .min(Comparator.comparingInt(Pair::second)).orElseThrow();
        System.out.printf("Position %d: %d\n", minimum.first(), minimum.second());
    }

    private static void part2() throws IOException {
        String input = getList("Input07.txt").get(0);
        List<Integer> crabs = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
        int max = crabs.stream().max(Comparator.naturalOrder()).orElseThrow();
        int min = crabs.stream().min(Comparator.naturalOrder()).orElseThrow();
        Pair<Integer, Long> minimum = IntStream.range(min, max + 1)
                .mapToObj(i -> new Pair<>(i, crabs.stream().map(c -> Math.abs(c - i)).map(n -> Math.round(0.5 * n * (n + 1))).reduce(0L, Long::sum)))
                .min(Comparator.comparingLong(Pair::second)).orElseThrow();
        System.out.printf("Position %d: %d\n", minimum.first(), minimum.second());
    }
}
