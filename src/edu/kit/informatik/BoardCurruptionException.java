package edu.kit.informatik;

/**
 * Is thrown in case of a corrupted board state, should the previous game state no longer be recoverable.
 */
public class BoardCurruptionException extends Exception {
    public BoardCurruptionException(String msg){
        super(msg);
    }
}
