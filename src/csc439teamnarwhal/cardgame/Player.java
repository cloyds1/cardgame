/**
 * This is the class where the player is created and each is stored
 * as a Player Object that has a name represented as a string and the
 * player's hand of cards represented as an ArrayList.
 *
 * CSC 439 - Software Testing and Maintenance
 */

package csc439teamnarwhal.cardgame;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private String name;
    public Player(String name){
        hand = null;
        this.name = name;
    }

    public void setHand(ArrayList<Card> hand){
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
