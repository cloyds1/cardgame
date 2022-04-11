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

  public Deck(ArrayList<Card> deck) {
    deckOfCards = deck;
  }

  public ArrayList<Card> getDeck() {
    return deckOfCards;
  }

  public void dealCards(ArrayList<Player> players) {
    /*

        draw 6 from the shoe/deck, add to each player

        randomly flip two cards: randomly select one, pop from list, noting the index, randomly select another,
        insert previous card back into list.
     */

    ArrayList<Card> hand = null;
    for (Player player : players) {

      //slice a portion of cards from the deck for a player
      if (deckOfCards.size() > 6)
        hand = new ArrayList<>(deckOfCards.subList(0, 6));
      else
        hand = new ArrayList<Card>(deckOfCards);

      //remove entire slice from deck
      for (int i = 0; i < 6; i++) {
        deckOfCards.remove(0);
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

  public Card drawCard(boolean drawFromDeck, ListIterator<Card> deck) {
    Card drawnCard;
    if (drawFromDeck) {
      drawnCard = deck.next();
    } else {
      drawnCard = deck.previous();
      deck.next();
    }

    return drawnCard;
  }

  public void flipTopCard(ListIterator<Card> deck){
    deck.next();
  }

  public Card displayDiscard(ListIterator<Card> deck){
    Card discard = deck.previous();
    deck.next();
    return discard;
  }


  public boolean equals(Deck o) {
    if (o.deckOfCards.size() != deckOfCards.size()){
      return false;
    }

    for (int i = 0; i < o.deckOfCards.size(); i++) {
      if (!((o.deckOfCards.get(i)).equals(deckOfCards.get(i)))) {
        return false;
      }
    }
    return true;
  }

}