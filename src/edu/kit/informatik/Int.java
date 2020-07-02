package edu.kit.informatik;

/**
 * array lists cant handle basic data types, therefore this is a container class for int.
 */
public class Int {
    private int num;

    public Int() {
        this.num = 0;
    }

    public int getNum() {
        return num;
    }

    public void addOne() {
        this.num = num + 1;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
