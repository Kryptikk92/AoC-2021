package de.kryptikk.aoc.aoc04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        boolean won = false;
        for (int x = 0; x < 5; x++) {
            int finalX = x;
            won = IntStream.range(0, 5).mapToObj(y -> boardCells.get(finalX + y*5).isChosen()).allMatch(b -> b);
            if (won)
                break;
        }
        if (!won) {
            for (int y = 0; y < 5; y++) {
                int finalY = y;
                won = IntStream.range(0, 5).mapToObj(x -> boardCells.get(x + finalY*5).isChosen()).allMatch(b -> b);
                if (won)
                    break;
            }
        }
        return won;
    }

    public List<BoardCell> getUnmarked() {
        return boardCells.stream().filter(c -> !c.isChosen()).toList();
    }
}
