package de.kryptikk.aoc;

import java.io.InputStream;

public class PuzzleInput {

    public static InputStream getInput(String url) {
        return PuzzleInput.class.getClassLoader().getResourceAsStream(url);
    }
}
