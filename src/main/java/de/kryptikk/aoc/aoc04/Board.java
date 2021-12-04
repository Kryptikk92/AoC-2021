package de.kryptikk.aoc.aoc04;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<BoardCell> boardCells;

    public Board(List<Integer> boardNumbers) {
        boardCells = new ArrayList<>();
        for (int number : boardNumbers) {
            boardCells.add(new BoardCell(number));
        }
    }

    public boolean choose(int number) {
        for (BoardCell cell : boardCells) {
            if (cell.getI() == number)
                cell.choose();
        }
        //TODO: check if won
        return false;
    }
}
