package csc439teamnarwhal.cardgame;

public enum Suit {
  CLUBS("Clubs"), SPADES("Spades"), DIAMONDS("Diamonds"), HEARTS("Hearts");

  final String suit;

  Suit(String suit) {
    this.suit = suit;
  }

  public String getSuit(){
    return suit;
  }

}
