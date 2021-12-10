package de.kryptikk.aoc.aoc10;

import de.kryptikk.aoc.PuzzleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AOC10 {

    private static final String filename = "Input10.txt";

    static List<Character> openers = Arrays.asList('(', '[', '{', '<');

    static Map<Character, Character> openToClose = new HashMap<>() {{
        put('(', ')');
        put('[', ']');
        put('{', '}');
        put('<', '>');
    }};

    static Map<Character, Integer> score1 = new HashMap<>() {{
        put(')', 3);
        put(']', 57);
        put('}', 1197);
        put('>', 25137);
    }};

    static Map<Character, Integer> score2 = new HashMap<>() {{
        put(')', 1);
        put(']', 2);
        put('}', 3);
        put('>', 4);
    }};

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    public static List<String> getList() throws IOException {
        InputStream input = PuzzleInput.getInput(filename);
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.add(line);
            }
        }
        return result;
    }

    private static void part1() throws IOException {
        List<String> list = getList();

        int finalScore = 0;

        for (String line : list) {
            Stack<Character> chunks = new Stack<>();
            for (char c : line.toCharArray()) {
                if (openers.contains(c))
                    chunks.push(c);
                else {
                    if (chunks.empty())
                        break;
                    if (c != openToClose.get(chunks.pop())) {
                        finalScore += score1.get(c);
                        break;
                    }
                }
            }
        }
        System.out.println(finalScore);
    }

    private static void part2() throws IOException {
        List<String> list = getList();

        List<Long> scores = new ArrayList<>();

        for (String line : list) {
            Stack<Character> chunks = new Stack<>();
            boolean invalid = false;
            for (char c : line.toCharArray()) {
                if (openers.contains(c))
                    chunks.push(c);
                else {
                    if (c != openToClose.get(chunks.pop())) {
                        invalid = true;
                        break;
                    }
                }
            }
            long score = 0;
            if (!invalid && !chunks.isEmpty()) {
                while (!chunks.isEmpty()) {
                    long charScore = score2.get(openToClose.get(chunks.pop()));
                    score = score * 5 + charScore;
                }
                scores.add(score);
            }
        }

        scores.sort(Comparator.naturalOrder());

        System.out.println(scores.get((scores.size()-1)/2));
    }
}
