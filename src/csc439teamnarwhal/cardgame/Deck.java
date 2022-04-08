package csc439teamnarwhal.cardgame;

import java.util.ArrayList;

public class Deck {

  private ArrayList<Card> deckOfCards = new ArrayList<>();

  public Deck() {
    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        deckOfCards.add(new Card(r, s));
      }
    }
  }

  public ArrayList<Card> getDeck() {
    return deckOfCards;
  }

  public static ArrayList<Card> shuffle(ArrayList<Card> deck){
    for(int i = 0; i < deck.size(); i++){
      int cardSwap = (int)(Math.random() * deck.size());
      Card temp = deck.get(i);
      deck.set(i, deck.get(cardSwap));
      deck.set(cardSwap, temp);
    }
    return deck;
  }

  public void dealCards(ArrayList<Player> players){
    /*
        players > 4, then create two decks for a shoe, merging two decks into one arraylist

        draw 6 from the shoe/deck, add to each player

        randomly flip
     */
  }

}