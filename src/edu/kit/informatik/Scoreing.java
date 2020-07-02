package edu.kit.informatik;

import java.util.ArrayList;

public class Scoreing {
    private ArrayList<ArrayList<String>> words;
    private ArrayList<Player> players;

    public Scoreing(ArrayList<ArrayList<String>> words, ArrayList<Player> players) {
        this.words = words;
        this.players = players;
    }

    public void score() throws BoardCurruptionException {
        Terminal.printLine("p1 has " + words.get(0).size() + " words.");
        Terminal.printLine("p2 has " + words.get(1).size() + " words.");

        int score = 0;
        if (words.size() == players.size()) {
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).size() != 0) {
                    for (String word : words.get(i)) {
                        score = score + ArihmUnit.calcVal(word);
                    }
                    players.get(i).setScore(score);
                    score = 0;
                }
            }
        } else {
            throw new BoardCurruptionException("number of players corrupted.");
        }
    }
}
