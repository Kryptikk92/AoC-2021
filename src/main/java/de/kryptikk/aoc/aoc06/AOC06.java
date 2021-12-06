package de.kryptikk.aoc.aoc06;

import de.kryptikk.aoc.PuzzleInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AOC06 {
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


    private static int simulateFish(List<Lanternfish> fish, int days) {
        List<Lanternfish> nextGeneration;
        for (int i = 0; i < days; i++) {
            nextGeneration = new ArrayList<>();
            for (Lanternfish f : fish) {
                nextGeneration.addAll(f.updateLife());
            }
            fish = nextGeneration;
        }
        return fish.size();
    }

    private static void part1() throws IOException {
        String input = getList("Input06.txt").get(0);
        List<Lanternfish> fish = Arrays.stream(input.split(",")).map(Integer::parseInt).map(Lanternfish::new).toList();
        System.out.println(simulateFish(fish, 80));
    }

    private static void part2() throws IOException {
        String input = getList("Input06.txt").get(0);
        List<Integer> fish = Arrays.stream(input.split(",")).map(Integer::parseInt).sorted().toList();
        List<FishGroup> fishGroups = new ArrayList<>();
        for (int i : fish) {
            FishGroup fishGroup = null;
            for (FishGroup fg : fishGroups) {
                if (fg.age == i) {
                    fishGroup = fg;
                    break;
                }
            }
            if (fishGroup == null) {
                fishGroup = new FishGroup(i, 1);
                fishGroups.add(fishGroup);
            }
            else
                fishGroup.count++;
        }

        for (int i = 0; i < 256; i++) {
            FishGroup six1 = null;
            FishGroup six2 = null;
            FishGroup newFG = null;
            for (FishGroup fg : fishGroups) {
                boolean spawned = fg.round();
                if (spawned) {
                    six1 = fg;
                    newFG = new FishGroup(8, fg.count);
                } else if (fg.age == 6) {
                    six2 = fg;
                }
            }
            if (six1 != null && six2 != null) {
                fishGroups.remove(six2);
                six1.count += six2.count;
            }
            if (newFG != null)
                fishGroups.add(newFG);
        }

        long sum = fishGroups.stream().map(fg -> fg.count).reduce(0L, Long::sum);
        System.out.println(sum);
    }
}
