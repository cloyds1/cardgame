package csc439teamnarwhal.cardgame;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private String name;

    public Player(String name){
        hand = null;
        this.name = name;
    }

    public void acceptCards(ArrayList<Card> hand){
        this.hand = hand;
    }

}
