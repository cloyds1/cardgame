package csc439teamnarwhal.cardgame;

/**
 * CSC 439 - Software Testing and Maintenance Lab 4
 *
 * This class creates a card object with a suit and number and has the following properties:
 * Methods isRed() and isBlack() that returns a boolean value depending on the color of the
 * suit of the card, a toString() method that returns a String with the information about the
 * card, a compareTo() method that compares the numbers of two cards and returns an integer
 * value 1 if greater than, 0 if equal, and -1 if less than, and an equals() method that returns
 * a boolean value of true if the cards are considered equal.
 *
 */
public class Card {

  private String suit;
  private String rank_name;
  private int rank_value;
  private boolean faceUp;

  /**
   * This constructor creates a playing card with a suit and number. It also creates the value of
   * the card based on A, 2,...K with values 1-10. All face cards have a value of 10.
   *
   * @param suit   parameter for the suit of the card
   * @param rank  parameter for the number of the card (A, 2... to K)
   */
  public Card(Rank rank, Suit suit) {
    this.suit = suit.getSuit();
    rank_name = rank.getName();
    rank_value = rank.getValue();
    faceUp = false;

  }

  public String getSuit() {
    return suit;
  }

  public String getRank_name() {
    return rank_name;
  }

  public int getRank_value() {
    return rank_value;
  }

  /**
   * this method determines if the card is red (hearts or diamonds)
   *
   * @return boolean value true if card is red
   */
  public boolean isRed() {
    return (suit.equals("H") || suit.equals("D"));
  }

  /**
   * this method determines if the card is black (clubs or spades)
   *
   * @return boolean value true if card is black
   */
  public boolean isBlack() {
    return (suit.equals("C") || suit.equals("S"));
  }

  /**
   * Overrides the toString method to output the card
   *
   * @return string of card ex. Ace of Spades
   */
  @Override
  public String toString() {
    return rank_name + " of " + suit;
  }

  /**
   * This method enables us to compare a card to another card by value where A is highest value and
   * 1 is lowest value
   *
   * @param c parameter for the card we are comparing to
   * @return 1 if greater than, 0 if equal, -1 if less than
   */
  public int compareTo(Card c) {
    return Integer.compare(this.rank_value, c.rank_value);
  }

  /**
   * This method compares two cards to see if they are the same suit and number
   *
   * @param c parameter for the card we are comparing to
   * @return true if cards are the same, return false if cards are different
   */
  public boolean equals(Card c) {
    return this.suit.equals(c.suit) && this.rank_name.equals(c.rank_name);
  }

  /**
   * This method turns a card to the faceUp position.
   */
  public void faceUp(){faceUp = true;}

  /**
   * This method turns a card to the faceDown position.
   */
  public void faceDown(){faceUp = false;}

  /**
   * @return returns true if card is faceup
   */
  public boolean getFaceUp(){return faceUp;}

}

