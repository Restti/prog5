package edu.kit.informatik;

/**
 * This acceptor class is responsible for checking if a word is accepted by the context free grammar or not.
 */
public class Acc {
    private Acc(){
        ;
    }

    /**
     * checks if expression word is accepted by context free grammar.
     * @param input
     * @return accepted by grammar
     */
    public static boolean grCheck(String input) {
        String arithExpr = input;
        String acc = "([0-9E]{2}[-(\\+)(\\*)])|[0-9-(\\+)(\\*)]";
        String rgxPttrn = ".*" + acc + ".*";
        // replaces all expressions [0-9E]{2}[-)*] with E, recursion until no longer possible.
        while (arithExpr.matches(rgxPttrn)) {
            arithExpr = arithExpr.replaceFirst(acc, "E");
        }
        // checks for acceptance.
        if (arithExpr.equals("E")) {
            return true;
        } else {
            return false;
        }
    }
}
