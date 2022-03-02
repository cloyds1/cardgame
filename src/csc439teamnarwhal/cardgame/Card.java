package csc439teamnarwhal.cardgame;

/**
 * CSC 439 - Software Testing and Maintenance Lab 4
 *
 * @author Ellen Hokkanen
 * @version 1.0
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

  /**
   * this method determines if the card is red (hearts or diamonds)
   *
   * @return boolean value true if card is red
   */
  public boolean isRed() {
    return (suit.equals("Hearts") || suit.equals("Diamonds"));
  }

  /**
   * this method determines if the card is black (clubs or spades)
   *
   * @return boolean value true if card is black
   */
  public boolean isBlack() {
    return (suit.equals("Clubs") || suit.equals("Spades"));
  }

  /**
   * Overrides the toString method to output the card
   *
   * @return string of card ex. Ace of Spades
   */
  @Override
  public String toString() {
    return number + " of " + suit;
  }

  /**
   * This method enables us to compare a card to another card by value where A is highest value and
   * 1 is lowest value
   *
   * @param c parameter for the card we are comparing to
   * @return 1 if greater than, 0 if equal, -1 if less than
   */
  public int compareTo(Card c) {
    return Integer.compare(this.value, c.value);
  }
}

