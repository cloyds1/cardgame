/**
 * This is the class where the deck of cards is created and each is stored
 * as a Card Object in an ArrayList of 52 total Card Objects created in the
 * Card class.
 *
 * CSC 439 - Software Testing and Maintenance
 *
 * @version 1.0
 */

package csc439teamnarwhal.cardgame;

import java.util.*;

public class Deck {

  private ArrayList<Card> deckOfCards = new ArrayList<>();

  /**
   * Create the deck of cards one by one in a nested for-loop, one card suit at a time
   * in rank order.
   */
  public Deck() {
    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        deckOfCards.add(new Card(r, s));
      }
    }
  }

  //this method is not currently used
  /*public Deck(ArrayList<Card> deck) {
    deckOfCards = deck;
  }*/

  public ArrayList<Card> getDeck() {
    return deckOfCards;
  }

  /**
   * Method that deals out the cards to the players. The method
   * deals in slices of 6 at a time per player, in order to deal faster and
   * more efficiently for the system.
   * @param players the array list of players for the game
   */
  public void dealCards(ArrayList<Player> players) {
    /*

        draw 6 from the shoe/deck, add to each player

        randomly flip two cards: randomly select one, pop from list, noting the index, randomly select another,
        insert previous card back into list.
     */

    ArrayList<Card> hand;
    for (Player player : players) {

      //slice a portion of cards from the deck for a player
      if (deckOfCards.size() > 6)
        hand = new ArrayList<>(deckOfCards.subList(0, 6));
      else
        hand = new ArrayList<>(deckOfCards);

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
      player.setHand(hand);
    }
  }

  /**
   * Method that draws a card from either the deck or the discard pile.
   *
   * @param drawFromDeck boolean to draw from deck (true) or from discard (false)
   * @param deck the iterator to traverse the deck.
   * @return returns the card that was chosen from the deck or discard pile.
   */
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

  /**
   * Method that flips the top card over that is next in the deck.
   * @param deck iterator to traverse deck
   * @return returns the next card in the deck
   */
  public Card flipTopCard(ListIterator<Card> deck){
    return deck.next();
  }

  /**
   * Method that returns the card that is the discard to the user.
   * @param deck iterator to traverse deck
   * @return returns the card that is in the discard pile
   */
  public Card displayDiscard(ListIterator<Card> deck){
    Card discard = deck.previous();
    deck.next();
    return discard;
  }

  /**
   * Method to check if two decks are equal, primarily for testing purposes.
   * @param o second deck to compare
   * @return true if equal decks, false if different decks
   */
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