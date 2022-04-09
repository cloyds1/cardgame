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
