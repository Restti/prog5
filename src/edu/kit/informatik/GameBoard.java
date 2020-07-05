package edu.kit.informatik;

import java.util.ArrayList;

/**
 * this class holds the functionality of the game board.
 */
public class GameBoard {
    private static final int NOT_FOUND = -1;
    private static final int SIZE = 10;
    Piece[][] board;

    /**
     * the board is initialized as a square.
     */
    public GameBoard() {
        this.board = new Piece[SIZE][SIZE];
    }

    public static int getSIZE() {
        return SIZE;
    }

    public Piece[][] getState() {
        Piece[][] clBoard = new Piece[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                clBoard[i][j] = board[i][j];
            }
        }
        return clBoard;
    }

    // test functionality.

    /**
     * adds Pieces to the board, only if the squares, they are added to are empty and no illegal words are formed.
     *
     * @param hor
     * @param pieces
     * @param row
     * @param col
     * @param pl
     * @return success status.
     * @throws IllegalMoveException
     */
    public boolean gatedAdd(boolean hor, String pieces, int row, int col, Player pl) throws IllegalMoveException {
        String arCheck = "";
        boolean success = true;
        if (hor) {
            for (int i = 0; i < pieces.length(); i++) {
                if (board[row][col + i] != null) {
                    // checks if the pieces are being placed on empty squares.
                    throw new IllegalMoveException("pieces can only be placed on empty squares.");
                }
                board[row][col + i] = new Piece(pl, Character.toString(pieces.charAt(i)));
            }
            for (int x = 0; x < GameBoard.getSIZE(); x++) {
                // checks if expressions are legal after placing new pieces for the modified row.
                if (board[row][x] != null) {
                    arCheck = arCheck + board[row][x].getVal();
                    ;
                } else {
                    if (Acc.grCheck(arCheck) || arCheck.equals("")) {
                        arCheck = "";
                    } else {
                        for (int i = 0; i < pieces.length(); i++) {
                            board[row][col + i] = null;
                        }
                        success = false;
                        Terminal.printLine(success); //TODO
                    }
                }
            }
            for (int i = 0; i < pieces.length(); i++) {
                // checks if expressions are legal after placing new pieces for the modified columns.
                for (int y = 0; y < GameBoard.getSIZE(); y++) {
                    if (board[y][col + i] != null) {
                        arCheck = arCheck + board[y][col + i].getVal();
                    } else {
                        if (Acc.grCheck(arCheck) || arCheck.equals("")) {
                            arCheck = "";
                        } else {
                            for (int k = 0; k < pieces.length(); k++) {
                                board[row][col + k] = null;
                            }
                            success = false;
                            Terminal.printLine(success); //TODO
                        }
                    }
                }
            }
        } else {
            for (int j = 0; j < pieces.length(); j++) {
                if (board[row + j][col] != null) {
                    // checks if the pieces are being placed on empty squares.
                    throw new IllegalMoveException("pieces can only be placed on empty squares.");
                }
                board[row + j][col] = new Piece(pl, Character.toString(pieces.charAt(j)));
            }
            for (int y = 0; y < GameBoard.getSIZE(); y++) {
                // checks if expressions are legal after placing new pieces for the modified column.
                if (board[y][col] != null) {
                    arCheck = arCheck + board[y][col].getVal();
                } else {
                    if (Acc.grCheck(arCheck) || arCheck.equals("")) {
                        arCheck = "";
                    } else {
                        for (int j = 0; j < pieces.length(); j++) {
                            board[row + j][col] = null;
                        }
                        success = false;
                        Terminal.printLine(success); //TODO
                    }
                }
            }
            for (int i = 0; i < pieces.length(); i++) {
                // checks if expressions are legal after placing new pieces for the modified rows.
                for (int x = 0; x < GameBoard.getSIZE(); x++) {
                    if (board[row + i][x] != null) {
                        arCheck = arCheck + board[row + i][x].getVal();
                    } else {
                        if (Acc.grCheck(arCheck) || arCheck.equals("")) {
                            arCheck = "";
                        } else {
                            for (int j = 0; j < pieces.length(); j++) {
                                board[row + j][col] = null;
                            }
                            success = false;
                            Terminal.printLine(success); //TODO
                        }
                    }
                }
            }
        }
        if (!success) {
            throw new IllegalMoveException("the pieces you are trying to place"
                    + " would result in an illegal word.");
        }
        //rework unfinished.
        if (pieces.length() == 1) {
            // checks if the newly placed pieces share edges with other pieces.
            if (row == 0 && col == 0) {
                if (board[row + 1][col] == null && board[row][col + 1] == null) {
                    board[row][col] = null;
                    success = false;
                }
            } else if (row == 9 && col == 9) {
                if (board[row - 1][col] == null && board[row][col - 1] == null) {
                    board[row][col] = null;
                    success = false;
                }
            } else if (row == 0 && col == 9) {
                if (board[row + 1][col] == null && board[row][col - 1] == null) {
                    board[row][col] = null;
                    success = false;
                }
            } else if (row == 9 && col == 0) {
                if (board[row - 1][col] == null && board[row][col + 1] == null) {
                    board[row][col] = null;
                    success = false;
                }
            } else {
                if (board[row - 1][col] == null && board[row + 1][col] == null
                        && board[row][col - 1] == null && board[row][col + 1] == null) {
                    board[row][col] = null;
                    success = false;
                }
            }
        }
        if (!success) {
            throw new IllegalMoveException("no piece can stand alone.");
        }
        return true;
    }

    public Scoreing score() {
        // indexes of all array lists are shared by the same player.
        ArrayList<ArrayList<String>> words = new ArrayList<ArrayList<String>>();
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Int> owner = new ArrayList<Int>();
        String word = "";
        WordOwner own = new WordOwner(0, null);
        // initializing lists, to carry all players. TODO: works as intended.
        for (int i = 0; i < GameBoard.getSIZE(); i++) {
            for (int j = 0; j < GameBoard.getSIZE(); j++) {
                if (board[i][j] != null) {
                    if (!players.contains(board[i][j].getOwner())) {
                        players.add(board[i][j].getOwner());
                        words.add(new ArrayList<String>());
                        owner.add(new Int());
                    }
                }
            }
        }
        Terminal.printLine("p1 = " + players.get(0));
        Terminal.printLine("p2 = " + players.get(1));
        // checking for words horrizontaly.
        for (int i = 0; i < GameBoard.getSIZE(); i++) {
            for (int j = 0; j < GameBoard.getSIZE(); j++) {
                if (board[i][j] != null) {
                    // builds word from pieces and counts how many pieces every player has submitted to the word. TODO: works as intended.
                    word = word + board[i][j].getVal();
                    owner.get(players.indexOf(board[i][j].getOwner())).addOne();
                    Terminal.printLine("p1 has contributed " + owner.get(0).getNum() + " pieces.");
                    Terminal.printLine("p2 has contributed " + owner.get(1).getNum() + " pieces.");
                    if(j == GameBoard.getSIZE() - 1){
                        word = assignWordHor(words, players, owner, word, own);
                    }
                } else {
                    // checks if word is according to specs and determines ownership. TODO: ERROR always determines p1 (k = 0) as owner.
                    word = assignWordHor(words, players, owner, word, own);
                    Terminal.printLine("p1's contributed pieces has been set to " + owner.get(0).getNum() + " for next word.");
                    Terminal.printLine("p2's contributed pieces has been set to " + owner.get(1).getNum() + " for next word.");
                }
            }
        }
        // checking for words vertically.
        for (int j = 0; j < GameBoard.getSIZE(); j++) {
            for (int i = 0; i < GameBoard.getSIZE(); i++) {
                if (board[i][j] != null) {
                    word = word + board[i][j].getVal();
                    owner.get(players.indexOf(board[i][j].getOwner())).addOne();
                    if(i == GameBoard.getSIZE() - 1){
                        word = assignWordVert(words, players, owner, word, own);
                    }
                } else {
                    word = assignWordVert(words, players, owner, word, own);
                }
            }
        }
        return new Scoreing(words, players);
    }

    private String assignWordHor(ArrayList<ArrayList<String>> words, ArrayList<Player> players,
                                 ArrayList<Int> owner, String word, WordOwner own) {
        if (word.length() > 2) {
            for (int k = 0; k < owner.size(); k++) {
                if (own.getPcsCnt() <= owner.get(k).getNum()) {
                    own.setOwnr(players.get(k));
                    own.setPcsCnt(owner.get(k).getNum());
                }
            }
            words.get(players.indexOf(own.getOwnr())).add(word);
            word = "";
        }
        for (Int pl : owner) {
            pl.setNum(0);
        }
        return word;
    }

    private String assignWordVert(ArrayList<ArrayList<String>> words, ArrayList<Player> players,
                                  ArrayList<Int> owner, String word, WordOwner own) {
        if (!word.equals("")) {
            for (int k = 0; k < owner.size(); k++) {
                if (owner.get(k).getNum() >= own.getPcsCnt()) {
                    own.setPcsCnt(owner.get(k).getNum());
                    own.setOwnr(players.get(k));
                }
            }
            if (word.length() > 2) {
                words.get(players.indexOf(own.getOwnr())).add(word);
            }
            word = "";
            for (Int pl : owner) {
                pl.setNum(0);
            }
        }
        return word;
    }
}
