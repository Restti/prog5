package edu.kit.informatik;

/**
 * Is thrown, if unknown pieces are inside the bag.
 */
public class MiscellaneousPieceException extends Exception{
    public MiscellaneousPieceException(String msg) {
        super(msg);
    }
}
