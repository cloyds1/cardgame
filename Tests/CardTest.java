import csc439teamnarwhal.cardgame.Card;

import static com.google.common.truth.Truth.assertThat;

import csc439teamnarwhal.cardgame.Rank;
import csc439teamnarwhal.cardgame.Suit;
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
    ace = new Card(Rank.ACE, Suit.CLUBS);
    king = new Card(Rank.KING, Suit.HEARTS);
    queen = new Card(Rank.QUEEN, Suit.CLUBS);
    jack = new Card(Rank.JACK, Suit.CLUBS);
    ten = new Card(Rank.TEN, Suit.HEARTS);
    three = new Card(Rank.THREE, Suit.DIAMONDS);
  }

  @Test
  void suitTest() {
    assertThat(ace.getSuit()).isEqualTo("C");
  }

  @Test
  void nameTest() {
    assertThat(ace.getRank_name()).isEqualTo("A");
  }

  @Test
  void valueTestAce() {
    assertThat(ace.getRank_value()).isEqualTo(1);
  }

  @Test
  void valueTestKing() {
    assertThat(king.getRank_value()).isEqualTo(10);
  }

  @Test
  void valueTestQueen() {
    assertThat(queen.getRank_value()).isEqualTo(10);
  }

  @Test
  void valueTestJack() {
    assertThat(jack.getRank_value()).isEqualTo(10);
  }

  @Test
  void valueTestTen() {
    assertThat(ten.getRank_value()).isEqualTo(10);
  }

  @Test
  void valueTestThree() {
    assertThat(three.getRank_value()).isEqualTo(3);
  }

  @Test
  void toStringTest() {
    assertThat(ace.toString()).isEqualTo("A of C");
  }

  @Test
  void isRedTest() {
    assertThat(ten.isRed()).isTrue();
    assertThat(ace.isRed()).isFalse();
  }

  @Test
  void isBlackTest() {
    assertThat(ace.isBlack()).isTrue();
    assertThat(ten.isBlack()).isFalse();
  }

  @Test
  void compareToTest() {
    assertThat(ace.compareTo(three)).isEqualTo(-1);
  }

  @Test
  void equalCardsTestTrue(){
    Card newAce = new Card(Rank.ACE, Suit.CLUBS);
    assertThat(ace.equals(newAce)).isTrue();
  }
  @Test
  void equalCardsTestFalse(){
    assertThat(ace.equals(three)).isFalse();
  }

  @Test
  void faceUpTest(){
    ace.faceUp();
    assertThat(ace.getFaceUp()).isTrue();
  }

  @Test
  void faceDownTest(){
    ace.faceUp();
    ace.faceDown();
    assertThat(ace.getFaceUp()).isFalse();
  }
}
