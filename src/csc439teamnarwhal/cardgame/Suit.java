/**
 * Suit is an enum that contains a String represented as a
 * single alphanumeric character that identifies what suit
 * a particular Card Object belongs with.
 *
 * CSC 439 - Software Testing and Maintenance
 *
 * @author Clinton Schultz
 * @author Ellen Hokkanen
 * @version 1.0
 */

package csc439teamnarwhal.cardgame;

public enum Suit {
  CLUBS("C"), SPADES("S"), DIAMONDS("D"), HEARTS("H");

  final String suit;

  Suit(String suit) {
    this.suit = suit;
  }

  public String getSuit(){
    return suit;
  }

}
