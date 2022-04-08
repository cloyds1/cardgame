package csc439teamnarwhal.cardgame;

import java.util.*;

public class Deck {

    private ArrayList<Card> deckOfCards = new ArrayList<>();

    public Deck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                deckOfCards.add(new Card(r, s));
            }
        }
    }

    public Deck(ArrayList<Card> deck){
        deckOfCards = deck;
    }

    public ArrayList<Card> getDeck() {
        return deckOfCards;
    }

    public void dealCards(ArrayList<Player> players, ArrayList<Card> deckShoe){
    /*

        draw 6 from the shoe/deck, add to each player

        randomly flip two cards: randomly select one, pop from list, noting the index, randomly select another,
        insert previous card back into list.
     */

        ArrayList<Card> hand = null;
        for(Player player: players) {

            //slice a portion of cards from the deck for a player
            if(deckShoe.size() > 6)
                hand = new ArrayList<>(deckShoe.subList(0, 6));
            else
                hand = new ArrayList<Card>(deckShoe);

            //remove entire slice from deck
            for (int i = 0; i < 6; i++) {
                deckShoe.remove(0);
            }

            //random object
            Random rand = new Random();

            //select a random card from the hand, set it to faceUp
            int tempInt = rand.nextInt(5);
            hand.get(tempInt).faceUp();
            Card tempCard = hand.remove(tempInt);

            //randomly select another, set it to face up, re-add other card
            hand.get(rand.nextInt(4)).faceUp();
            hand.add(tempInt, tempCard);

            //give the player the dealt cards
            player.acceptCards(hand);
        }
    }
    public ArrayList<Card> drawPile;
    public ArrayList<Card> discardPile;

    public Card drawCard(boolean drawFromPile){
        if (drawFromPile){
            System.out.println(drawPile.get(0));
            return drawPile.get(0);
        }
        else{
            System.out.println(discardPile.size()-1);
            return discardPile.get(discardPile.size()-1);
        }
    }


}