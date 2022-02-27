package csc439teamcardvarks.cardgame;

/**
 *
 */
public class Card {
  private String suit;
  private String number;

  /**
   * @param suit parameter for the suit of the card
   * @param number parameter for the number of the card (A, 1, 2... to K)
   */
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

  public void setSuit(String suit){
    this.suit = suit;
  }
  public void setNumber(String number){
    this.number = number;
  }

  public boolean isRed(){
    return false;
  }

  public boolean isBlack(){
    return false;
  }

  public String toString(){
    return "";
  }
}
