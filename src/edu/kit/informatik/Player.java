package edu.kit.informatik;

/**
 * this class holds the functionality of the players.
 */
public class Player {
    private static final int MINUS = 10;
    private static final int PLUS = 11;
    private static final int TIMES = 12;
    private static final int PIECES = 13;

    private int[] bag;
    private String name;
    private int score;

    /**
     * this class is used to read the command line arguments and memorizing the amount of each piece in the players bag.
     * Pieces are not generated yet, since there is no need to, but when they are placed.
     *
     * @param inBag
     * @throws MiscellaneousPieceException
     */
    public Player(String inBag, int num) throws MiscellaneousPieceException {
        if (inBag.matches("[0-9\\+\\-\\*]*")) {
            // checks for incorect characters in the input string.
            this.bag = new int[PIECES];
            for (int i = 0; i < inBag.length(); i++) {

                if (Character.toString(inBag.charAt(i)).matches("[0-9]")) {
                    this.bag[Integer.parseInt(Character.toString(inBag.charAt(i)))]++;
                } else if (inBag.charAt(i) == '-') {
                    this.bag[MINUS]++;
                } else if (inBag.charAt(i) == '+') {
                    this.bag[PLUS]++;
                } else if (inBag.charAt(i) == '*') {
                    this.bag[TIMES]++;
                }
            }
        } else {
            throw new MiscellaneousPieceException("unknown piece found in the bag.");
        }
        this.name = "p" + num;
    }

    /**
     * places pieces from the bag to the board, if possible and legal.
     * @param pieces
     * @param row
     * @param col
     * @param ori
     * @param board
     * @throws IllegalMoveException
     */
    public void place(String pieces, int row, int col, char ori, GameBoard board) throws IllegalMoveException {
        boolean hor;
        // checks if first piece is placed on board.
        if (col >= GameBoard.getSIZE() || row >= GameBoard.getSIZE()) {
            throw new IllegalMoveException("the boardsize is" + GameBoard.getSIZE()
                    + " only rows and columns between 0 and " + (GameBoard.getSIZE() - 1) + " exist.");
        }
        if (pieces.matches("[0-9\\+\\-\\*]{1,3}")) {
            // checks if all pieces are defined.
            int[] pcs = new int[PIECES];
            for (int i = 0; i < pieces.length(); i++) {
                // checks if enough pieces are in the bag of the player.
                if (Character.toString(pieces.charAt(i)).matches("[0-9]")) {
                    pcs[Integer.parseInt(Character.toString(pieces.charAt(i)))]++;
                } else if (pieces.charAt(i) == '-') {
                    pcs[MINUS]++;
                } else if (pieces.charAt(i) == '+') {
                    pcs[PLUS]++;
                } else if (pieces.charAt(i) == '*') {
                    pcs[TIMES]++;
                }
            }
            for (int i = 0; i < PIECES; i++) {
                if (bag[i] < pcs[i]) {
                    throw new IllegalMoveException(name + "'s bag lacks some of the specified pieces.");
                }
            }
        } else {
            throw new IllegalMoveException("some of the specified pieces do not exist "
                    + "or the number of pieces is incorrect.");
        }
        if (ori != 'H' && ori != 'V') {
            // checks if appropriate orientation is established.
            throw new IllegalMoveException(ori + " is not a valid orientation.");
        }
        try {
            // checks if all pieces are placed on the board.
            if (ori == 'H') {
                hor = true;
            } else {
                hor = false;
            }
            if (board.gatedAdd(hor, pieces, row, col, this)) {
                for (int i = 0; i < pieces.length(); i++) {
                    if (Character.toString(pieces.charAt(i)).matches("[0-9]")) {
                        this.bag[Integer.parseInt(Character.toString(pieces.charAt(i)))]--;
                    } else if (pieces.charAt(i) == '-') {
                        this.bag[MINUS]--;
                    } else if (pieces.charAt(i) == '+') {
                        this.bag[PLUS]--;
                    } else if (pieces.charAt(i) == '*') {
                        this.bag[TIMES]--;
                    } else{ Terminal.printError("WAS IST HIER LOS???");}
                }
            }
        } catch (NullPointerException e) {
            throw new IllegalMoveException("pieces can't be placed outside the game board. The game board has "
                    + GameBoard.getSIZE() + " rows and columns, numbered from 0 to " + (GameBoard.getSIZE() - 1) + ".");
        }
    }

    public int[] getBag() {
        return bag;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
