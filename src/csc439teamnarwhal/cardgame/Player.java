package csc439teamnarwhal.cardgame;

import java.util.ArrayList;
import java.util.List;

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

    public String getName(){
        return name;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public String toString(){
        return name;
    }
}
