package de.kryptikk.aoc.aoc01;

import de.kryptikk.aoc.PuzzleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AOC01 {

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    public static List<Integer> getListInteger(String inputName) throws IOException {
        InputStream input = PuzzleInput.getInput(inputName);
        List<Integer> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.add(Integer.parseInt(line));
            }
        }
        return result;
    }

    private static void part1() throws IOException {
        List<Integer> list = getListInteger("Input1.txt");
        AtomicInteger counter = new AtomicInteger();
        IntStream.range(0, list.size() - 1).forEach(i -> {
                    if (list.get(i) < list.get(i + 1)) {
                        counter.getAndIncrement();
                    }
                }
        );
        System.out.println(counter.get());
    }

    private static void part2() throws IOException {
        List<Integer> list = getListInteger("Input1.txt");
        List<Integer> sums = slidingSum(list, 3).toList();
        AtomicInteger counter = new AtomicInteger();
        IntStream.range(0, sums.size() - 1)
                .forEach(i -> {
                            if (sums.get(i) < sums.get(i + 1)) {
                                counter.getAndIncrement();
                            }
                        }
                );
        System.out.println(counter.get());
    }

    private static Stream<Integer> slidingSum(List<Integer> list, int size) {
        if (size > list.size()) {
            return Stream.empty();
        }
        return IntStream.range(0, list.size() - size + 1)
                .mapToObj(start -> list.subList(start, start + size).stream().reduce(0, Integer::sum));
    }
}
