package de.kryptikk.aoc.aoc03;

import de.kryptikk.aoc.PuzzleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AOC03 {

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
        System.out.println("Part 1");
        List<String> list = getList("Input3.txt");
        Map<Integer, Integer> bits = new HashMap<>();
        for (String line : list) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '1') {
                    bits.merge(i, 1, Integer::sum);
                }
            }
        }
        String result = bits.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getValue() > list.size()/2 ? 1 : 0)
                .map(i -> Integer.toString(i))
                .reduce("", String::concat);
        String result2 = result.chars()
                .map(c -> c == '1' ? '0' : '1')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(Integer.parseInt(result, 2) * Integer.parseInt(result2, 2));
    }

    private static void part2() throws IOException {
        System.out.println("Part 2");
        List<String> list = getList("Input3.txt");

        Stream<String> remaining = list.stream();
        int length = list.get(0).length();
        for (int i = 0; i < length; i++) {
            int counter1 = 0;
            int counter0 = 0;
            list = remaining.toList();
            if (list.size() == 1)
                break;
            for (String line : list) {
                if (line.charAt(i) == '1') {
                    counter1++;
                } else {
                    counter0++;
                }
            }
            remaining = list.stream();
            char check = counter1 >= counter0 ? '1' : '0';
            int finalI = i;
            remaining = remaining.filter(s -> s.charAt(finalI) == check);
        }

        String x;
        if (list.size() == 1) {
            x = list.get(0);
        } else {
            x = remaining.findFirst().get();
        }

        list = getList("Input3.txt");

        remaining = list.stream();
        length = list.get(0).length();
        for (int i = 0; i < length; i++) {
            int counter1 = 0;
            int counter0 = 0;
            list = remaining.toList();
            if (list.size() == 1)
                break;
            for (String line : list) {
                if (line.charAt(i) == '1') {
                    counter1++;
                } else {
                    counter0++;
                }
            }
            remaining = list.stream();
            char check = counter0 <= counter1 ? '0' : '1';
            int finalI = i;
            remaining = remaining.filter(s -> s.charAt(finalI) == check);
        }

        String y;
        if (list.size() == 1) {
            y = list.get(0);
        } else {
            y = remaining.findFirst().get();
        }

        System.out.println(Integer.parseInt(x, 2) * Integer.parseInt(y, 2));
    }
}
