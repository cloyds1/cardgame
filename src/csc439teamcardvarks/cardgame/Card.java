package csc439teamcardvarks.cardgame;

/**
 *
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

    if (number.equals("King") || number.equals("Queen") || number.equals("Jack")) {
      this.value = 10;
    } else if (number.equals("Ace")) {
      this.value = 11;
    } else {
      this.value = Integer.parseInt(number);
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
}
