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
}