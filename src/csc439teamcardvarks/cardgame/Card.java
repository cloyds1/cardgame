package csc439teamcardvarks.cardgame;

public class Card {
  private String suit;
  private String number;

  public Card(String suit, String number){
    this.suit = suit;
    this.number = number;
  }

  public String getSuit(){
    return suit;
  }

  public String getNumber(){
    return number;
  }
}
