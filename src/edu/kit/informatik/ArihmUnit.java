package edu.kit.informatik;

/**
 * resoponsible for arithmetic calculation.
 */
public class ArihmUnit {

    /**
     * calculates and returns the point value of a word.
     * @param input
     * @return wordVal
     */
    public int calcVal(String input) throws BoardCurruptionException {
        String arithExpr = input;
        int secOp;
        int wordVal;
        OpStack op = new OpStack(0);
        for (int i = 0; i < arithExpr.length(); i++) {
            if (op.size() > 2) {
                // if to many numbers for calculation.
                throw new BoardCurruptionException("______________________");
            } else if (Character.toString(arithExpr.charAt(i)).matches("\\d")) {
                // pushes number onto OpStack.
                op.push(new OpStack(Integer.parseInt(String.valueOf(arithExpr.charAt(i)))));
            } else if (op.size() != 2) {
                // if not enough numbers for calculation.
                throw new BoardCurruptionException("______________________");
            } else {
                // arithmetic.
                secOp = op.pop().getOperand();
                if (arithExpr.charAt(i) == '-') {
                    // subtraction.
                    op.push(new OpStack(op.pop().getOperand() - secOp));
                } else if (arithExpr.charAt(i) == '+') {
                    // addition.
                    op.push(new OpStack(op.pop().getOperand() + secOp));
                } else if (arithExpr.charAt(i) == '*') {
                    // multiplication.
                    op.push(new OpStack(op.pop().getOperand() * secOp));
                } else {
                    // unknown character in word.
                    throw new BoardCurruptionException("______________________");
                }
            }
        }
        wordVal = op.peek().getOperand();
        return wordVal;
    }
}
