package de.kryptikk.aoc.aoc04;

public class BoardCell {

    private final int i;
    private boolean chosen = false;

    public BoardCell(Integer i) {
        this.i = 0;
    }

    public int getI() {
        return i;
    }

    public void choose() {
        this.chosen = true;
    }

    public boolean isChosen() {
        return chosen;
    }
}
