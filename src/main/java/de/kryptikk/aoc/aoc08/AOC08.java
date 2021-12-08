package de.kryptikk.aoc.aoc08;

import de.kryptikk.aoc.PuzzleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AOC08 {

    private static final String filename = "Input08.txt";

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
        List<String> list = getList(filename);
        long count = list.stream()
                .map(l -> l.split("\\| ")[1].split(" "))
                .flatMap(Arrays::stream)
                .filter(s -> List.of(2, 3, 4, 7).contains(s.length()))
                .count();
        System.out.println(count);
    }

    private static void part2() throws IOException {
        List<String> list = getList(filename);
        int sum = list.stream().map(line -> {
            List<String> numberStrings = Arrays.stream(line.split(" ")).filter(s -> !s.equals("|")).toList();
            Optional<String> maybeOne = numberStrings.stream().filter(s -> s.length() == 2).findFirst();
            String one = maybeOne.orElse(null);
            Optional<String> maybeFour = numberStrings.stream().filter(s -> s.length() == 4).findFirst();
            String four = maybeFour.orElse(null);
            Map<String, Integer> patternToNumber = new HashMap<>();
            for (String number : numberStrings) {
                String pattern = number
                        .chars()
                        .sorted()
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                switch (pattern.length()) {
                    case 2 -> patternToNumber.put(pattern, 1);
                    case 3 -> patternToNumber.put(pattern, 7);
                    case 4 -> patternToNumber.put(pattern, 4);
                    case 5 -> {
                        if (overlap(pattern, one) == 2) {
                            patternToNumber.put(pattern, 3);
                        } else if (overlap(pattern, four) == 2) {
                            patternToNumber.put(pattern, 2);
                        } else {
                            patternToNumber.put(pattern, 5);
                        }
                    }
                    case 6 -> {
                        if (overlap(pattern, one) == 1) {
                            patternToNumber.put(pattern, 6);
                        } else if (overlap(pattern, four) == 4) {
                            patternToNumber.put(pattern, 9);
                        } else {
                            patternToNumber.put(pattern, 0);
                        }
                    }
                    case 7 -> patternToNumber.put(pattern, 8);
                }
            }

            int result = 0;

            for (int i = 0; i+10 < numberStrings.size(); i++) {
                int number = patternToNumber.get(numberStrings.get(i+10)
                        .chars()
                        .sorted()
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString());
                result *= 10;
                result += number;
            }

            return result;
        }).reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static Integer overlap(String pattern, String check) {
        int c = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (check.indexOf(pattern.charAt(i)) >= 0)
                c++;
        }
        return c;
    }
}
