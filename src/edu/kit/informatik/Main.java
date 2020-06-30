package edu.kit.informatik;

public final class Main {
    private Main() {
        ;
    }

    public static void main(String[] args) throws IllegalMoveException, MiscellaneousPieceException {
        Player p = new Player("*****+++++-----00000111112222233333444445555566666777778888899999", "p");
        GameBoard g = new GameBoard();
        for (int i:p.getBag()) {
            Terminal.printLine(i);
        }
        p.place("48+", 0, 0, 'H', g);
        p.place("48+", 0, 5, 'H', g);
        Terminal.printLine("");
        for (int i:p.getBag()) {
            Terminal.printLine(i);
        }
    }
}
