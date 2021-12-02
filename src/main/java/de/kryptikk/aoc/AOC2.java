package de.kryptikk.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AOC2 {

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
        List<String> list = getList("Input2.txt");
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();
        list.forEach(s -> {
            String[] token = s.split(" ");
            int amount = Integer.parseInt(token[1]);
            switch (token[0]) {
                case "forward" -> x.addAndGet(amount);
                case "up" -> y.addAndGet(-amount);
                case "down" -> y.addAndGet(amount);
                default -> throw new RuntimeException("Fehlerhafte Parameter: " + token[0]);
            }
        });
        System.out.println("Ergebnis: " + x.get() * y.get());
    }

    private static void part2() throws IOException {
        List<String> list = getList("Input2.txt");
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();
        AtomicInteger aim = new AtomicInteger();
        list.forEach(s -> {
            String[] token = s.split(" ");
            int amount = Integer.parseInt(token[1]);
            switch (token[0]) {
                case "forward" -> {
                    x.addAndGet(amount);
                    y.addAndGet(amount * aim.get());
                }
                case "up" -> aim.addAndGet(-amount);
                case "down" -> aim.addAndGet(amount);
                default -> throw new RuntimeException("Fehlerhafte Parameter: " + token[0]);
            }
        });
        System.out.println("Ergebnis: " + x.get() * y.get());
    }
}
