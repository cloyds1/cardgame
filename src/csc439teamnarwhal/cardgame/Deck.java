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

  public void dealCards(Player player1, Player player2, Player player3, Player player4){

  }

}