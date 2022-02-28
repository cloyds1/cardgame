package csc439teamcardvarks.cardgame;

/**
 *CSC 439 - Software Testing and Maintenance
 *@author Ellen Hokkanen
 *@version 1.0
 */
public class Card {

  private String suit;
  private String number;
  private int value;

  /**
   * @param suit   parameter for the suit of the card
   * @param number parameter for the number of the card (A, 1, 2... to K)
   */
  public Card(String suit, String number) {
    this.suit = suit;
    this.number = number;

    switch (number) {
      case "Ace":
        this.value = 14;
        break;
      case "King":
        this.value = 13;
        break;
      case "Queen":
        this.value = 12;
        break;
      case "Jack":
        this.value = 11;
        break;
      default:
        this.value = Integer.parseInt(number);
        break;
    }
  }

  public String getSuit() {
    return suit;
  }

  public String getNumber() {
    return number;
  }

  public int getValue() {
    return value;
  }

  public void setSuit(String suit) {
    this.suit = suit;
  }

  public void setNumber(String number) {
    this.number = number;

    switch (number) {
      case "Ace":
        this.value = 14;
        break;
      case "King":
        this.value = 13;
        break;
      case "Queen":
        this.value = 12;
        break;
      case "Jack":
        this.value = 11;
        break;
      default:
        this.value = Integer.parseInt(number);
        break;
    }
  }

  public void setValue(int value) {
    this.value = value;
  }

  public boolean isRed() {
    return false;
  }

  public boolean isBlack() {
    return false;
  }

  public String toString() {
    return number + " of " + suit;
  }

  public int compareTo(Card c){
    return Integer.compare(this.value, c.value);
  }
}

