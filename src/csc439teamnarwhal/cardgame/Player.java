package csc439teamnarwhal.cardgame;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;

    public Player(){
        hand = null;
    }

    public void acceptCards(ArrayList<Card> hand){
        this.hand = hand;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }
}
