import csc439teamnarwhal.cardgame.Card;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest {

  Card ace;
  Card king;
  Card queen;
  Card jack;
  Card ten;
  Card three;

  @BeforeEach
  void setup() {
    ace = new Card("Clubs", "Ace");
    king = new Card("Spades", "King");
    queen = new Card("Clubs", "Queen");
    jack = new Card("Clubs", "Jack");
    ten = new Card("Hearts", "10");
    three = new Card("Diamonds", "3");
  }

  @Test
  void suitTest() {
    assertThat(ace.getSuit()).isEqualTo("Clubs");
  }

  @Test
  void numberTest() {
    assertThat(ace.getNumber()).isEqualTo("Ace");
  }

  @Test
  void valueTestAce() {
    assertThat(ace.getValue()).isEqualTo(14);
  }

  @Test
  void valueTestKing() {
    assertThat(king.getValue()).isEqualTo(13);
  }

  @Test
  void valueTestQueen() {
    assertThat(queen.getValue()).isEqualTo(12);
  }

  @Test
  void valueTestJack() {
    assertThat(jack.getValue()).isEqualTo(11);
  }

  @Test
  void valueTestTen() {
    assertThat(ten.getValue()).isEqualTo(10);
  }

  @Test
  void valueTestThree() {
    assertThat(three.getValue()).isEqualTo(3);
  }

  @Test
  void toStringTest() {
    assertThat(ace.toString()).isEqualTo("Ace of Clubs");
  }

  @Test
  void isRedTest() {
    assertThat(ace.isRed()).isFalse();
  }

  @Test
  void isBlackTest() {
    assertThat(ace.isBlack()).isTrue();
  }

  @Test
  void setValue(){
    ace.setValue(0);
    assertThat(ace.getValue()).isEqualTo(0);
  }

  @Test
  void compareToTest() {
    assertThat(ace.compareTo(three)).isEqualTo(1);
  }

  @Test
  void equalCardsTestTrue(){
    Card newAce = new Card("Clubs", "Ace");
    assertThat(ace.equals(newAce)).isTrue();
  }
  @Test
  void equalCardsTestFalse(){
    Card newAce = new Card("Clubs", "Ace");
    assertThat(ace.equals(three)).isFalse();
  }
}
