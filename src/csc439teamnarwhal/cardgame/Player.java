/**
 * This is the class where the player is created and each is stored as a Player Object that has a
 * name represented as a string and the player's hand of cards represented as an ArrayList.
 * <p>
 * CSC 439 - Software Testing and Maintenance
 */

package csc439teamnarwhal.cardgame;

import java.util.ArrayList;

public class Player {

  private ArrayList<Card> hand;
  private String name;
  private int score;

  public Player(String name) {
    hand = null;
    this.name = name;
    score = 0;
  }

  public void setHand(ArrayList<Card> hand) {
    this.hand = hand;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public ArrayList<Card> getHand() {
    return hand;
  }

  public String toString() {
    return name;
  }


  /**
   * calculates the score of the players current hand and adds it to their total score for all
   * holes.
   * @return value/score of the hand is returned
   */
  public int scoreHand() {
    int handScore = 0;
    if (hand.get(0).getRank_value() != hand.get(3).getRank_value()) {
      handScore += hand.get(0).getRank_value() + hand.get(3).getRank_value();
    }

    if (hand.get(1).getRank_value() != hand.get(4).getRank_value()) {
      handScore += hand.get(1).getRank_value() + hand.get(4).getRank_value();
    }

    if (hand.get(2).getRank_value() != hand.get(5).getRank_value()) {
      handScore += hand.get(2).getRank_value() + hand.get(5).getRank_value();
    }

    score += handScore;
    return handScore;
  }

  /**
   * determines if all cards in a players hand are face up
   * @return true if all face up, false if at least one card is face down.
   */
  public boolean allCardsUp() {
    for (int i = 0; i < 6; i++) {
      if (!(hand.get(i).getFaceUp())) {
        return false;
      }
    }
    return true;
  }
}
