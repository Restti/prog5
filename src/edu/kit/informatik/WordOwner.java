package edu.kit.informatik;

/**
 * this class is a container class for Gameboard.score().
 * It contains the owner of a word as well as the number of contributed pieces.
 */
public class WordOwner {
    private int pcsCnt;
    private Player ownr;

    public WordOwner(int pcsCnt, Player ownr){
        this.ownr = ownr;
        this.pcsCnt = pcsCnt;
    }

    public int getPcsCnt() {
        return pcsCnt;
    }

    public Player getOwnr() {
        return ownr;
    }

    public void setOwnr(Player ownr) {
        this.ownr = ownr;
    }

    public void setPcsCnt(int pcsCnt) {
        this.pcsCnt = pcsCnt;
    }
}
