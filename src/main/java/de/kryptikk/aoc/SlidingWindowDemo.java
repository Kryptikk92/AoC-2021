package de.kryptikk.aoc;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SlidingWindowDemo {

    public static void main(String[] args) {
        List<Integer> listOfIntegers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Function<List<Integer>, Integer> sum = list -> list.stream().reduce(0, Integer::sum);
        System.out.println("Sliding summing window of 3 over " + listOfIntegers);
        slidingOperator(listOfIntegers, 3, sum).forEach(System.out::println);

        List<String> listOfStrings = List.of("A", "B", "C", "D", "E", "F", "G", "H");
        Function<List<String>, String> concat = list -> list.stream().reduce("", String::concat);
        System.out.println("Sliding concat window of 3 over " + listOfStrings);
        slidingOperator(listOfStrings, 4, concat).forEach(System.out::println);
    }

    private static <T> Stream<T> slidingOperator(List<T> list, int size, Function<List<T>, T> operation) {
        if (size > list.size()) {
            return Stream.empty();
        }
        return IntStream.range(0, list.size() - size + 1)
                .mapToObj(start -> list.subList(start, start + size))
                .map(operation);
    }
}
