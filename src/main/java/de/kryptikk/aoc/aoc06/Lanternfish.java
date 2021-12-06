package de.kryptikk.aoc.aoc06;

import java.util.ArrayList;
import java.util.List;

public class Lanternfish {

    int c;

    public Lanternfish(int life) {
        this.c = life;
    }

    public List<Lanternfish> updateLife() {
        List<Lanternfish> retVal = new ArrayList<>();
        retVal.add(this);
        this.c--;
        if (c < 0) {
            retVal.add(new Lanternfish(8));
            this.c = 6;
        }
        return retVal;
    }
}
