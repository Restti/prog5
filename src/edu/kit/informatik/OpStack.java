package edu.kit.informatik;

/**
 * Class Op Stack, relevant for ArithmUnit and its calculations, holds operand's value.
 * Otherwise a Stack, integrating pop(), push(), peek().
 */
public class OpStack {
    private int operand;
    private OpStack next;

    public OpStack(int operand){
        this.operand = operand;
    }

    /**
     * adds a new element to end of list.
     * @param last
     */
    public void push(OpStack last){
        OpStack current = this;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.next = last;
    }

    /**
     * removes and
     * @return last element of list.
     */
    public OpStack pop() {
        OpStack retVal;
        OpStack current = this;
        while(current.getNext().getNext() != null) {
            current = current.getNext();
        }
        retVal = current.getNext();
        current.setNext(null);
        return retVal;
    }

    /**
     * @return last element of list.
     */
    public OpStack peek(){
        OpStack current = this;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * returns the length of the list - 1, since a dummy element is necessary, it does not count towards size.
     * @return length - 1
     */
    public int size(){
        int size = 0;
        OpStack current = this;
        while(current.getNext() != null) {
            current = current.getNext();
            size++;
        }
        return size;
    }

    public int getOperand() {
        return operand;
    }

    private void setNext(OpStack next) {
        this.next = next;
    }

    private OpStack getNext() {
        return next;
    }
}
