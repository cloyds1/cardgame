package csc439teamnarwhal.cardgame;

import java.util.ArrayList;
import java.util.ListIterator;

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

    //this method is not used at this time.

    /*public void addCardToHand(ListIterator<Card> deckIterator, Card card, int i){
        Card discard = hand.get(i);
        hand.add(i, card);
        deckIterator.previous();
        deckIterator.set(discard);
    }*/

    public String toString(){
        return name;
    }
}
