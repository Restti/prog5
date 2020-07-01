package edu.kit.informatik;

import java.util.ArrayList;

public final class Main {
    private Main() {
        ;
    }

    public static void main(String[] args) throws IllegalMoveException, MiscellaneousPieceException {
        Player p1 = new Player("", "p1");
        Player p2 = new Player("", "p2");
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<String> b = new ArrayList<String>();
        a.add("34+");
        a.add("37*");
        a.add("90*8-6-7-");
        b.add("34-");
        b.add("67-");
        ArrayList<Player> p= new ArrayList<>();
        p.add(p1);
        p.add(p2);
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        test.add(a);
        test.add(b);
        Scoreing t = new Scoreing(test,p);
        try {
            t.score();
        } catch (Exception e){
            Terminal.printLine(e.getCause());
        }
        Terminal.printLine(p1.getScore());
        Terminal.printLine(p2.getScore());
    }
}
