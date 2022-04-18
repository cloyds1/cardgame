/**
 * Rank is an enum that stores an integer for the value of the Card
 * and a String for the name on the card, represented as a single
 * alphanumeric character.
 *
 * CSC 439 - Software Testing and Maintenance
 */

package csc439teamnarwhal.cardgame;

public enum Rank {

    ACE(1, "A"), TWO(-2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"),
    SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"), JACK(10, "J"),
    QUEEN(10, "Q"), KING(0, "K");

    final int value;
    final String name;

    Rank(final int value, final String name) {
      this.value = value;
      this.name = name;
    }

    public int getValue(){
      return value;
    }

    public String getName(){
      return name;
    }
  }

