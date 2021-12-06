package de.kryptikk.aoc.aoc06;

public class FishGroup {

    int age;
    long count;

    public FishGroup(int age, long count) {
        this.age = age;
        this.count = count;
    }

    public boolean round() {
        age--;
        if (age < 0) {
            age = 6;
            return true;
        }
        return false;
    }
}
