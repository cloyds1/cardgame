package csc439teamnarwhal.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

  public void dealCards(ArrayList<Player> players, ArrayList<Card> deckShoe){
    /*

        draw 6 from the shoe/deck, add to each player

        randomly flip two cards: randomly select one, pop from list, noting the index, randomly select another,
        insert previous card back into list.
     */

    //random object
    Random rand = new Random();

    //slice a portion of cards from the deck for a player
    ArrayList<Card> hand = (ArrayList<Card>) deckShoe.subList(0, 5);

    //select a random card from the hand, set it to faceUp
    int tempInt = rand.nextInt(0, 5);
    hand.get(tempInt).faceUp();
    Card tempCard = hand.remove(tempInt);

    hand.get(rand.nextInt(0, 4)).faceUp();
    hand.add(tempInt, tempCard);





  }

}