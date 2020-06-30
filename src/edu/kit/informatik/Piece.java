package edu.kit.informatik;

/**
 * this class is a carrier class for piece value and owner of the piece.
 */
public class Piece {
    private String val;
    private Player owner;

    public Piece(Player owner, String val){
        this.owner = owner;
        this.val = val;
    }

    public Player getOwner() {
        return owner;
    }

    public String getVal() {
        return val;
    }
}
