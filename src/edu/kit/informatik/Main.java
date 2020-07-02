/**
 * This is an arithmetic scrabble. It is designed to be easily expanded to more than two player games
 * and different board sizes, which is most evident in the score method of the board class.
 */

package edu.kit.informatik;

import java.util.ArrayList;

public final class Main {
    private Main() {
        ;
    }

    public static void main(String[] args) throws IllegalMoveException, MiscellaneousPieceException, BoardCurruptionException {
        Terminal.printLine(ArihmUnit.calcVal("18-4*"));
        GameBoard b = new GameBoard();
        Player p1 = new Player("00000111112222233333444445555566666777778888899999++++++*****-----", 1);
        Player p2 = new Player("00000111112222233333444445555566666777778888899999++++++*****-----", 2);
        p1.place("99+", 0, 0, 'H', b);
        p1.place("97-", 2, 0, 'H', b);
        p2.place("99-", 4, 0, 'H', b);
        p1.place("09-", 6, 0, 'H', b);
        b.score().score();
        // score is "correct" meaning the calculation is correct given the incorrect assignment of ownership.
        Terminal.printLine(p1.getScore());
        Terminal.printLine(p2.getScore());
    }
}
