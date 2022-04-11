import csc439teamnarwhal.cardgame.*;

import static com.google.common.truth.Truth.assertThat;
import java.util.Collections;

import java.util.ArrayList;

import java.util.ListIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {

  ArrayList<Card> testDeck = new ArrayList<>();
  ArrayList<Player> testPlayers = new ArrayList<>();
  Deck deckTest;

  Deck deck = new Deck();
  Deck deck2 = new Deck();




  @BeforeEach
  void initEach(){

    deckTest = new Deck();
    testPlayers.add(new Player("player1"));
    testPlayers.add(new Player("player2"));
    testPlayers.add(new Player("player3"));
    testPlayers.add(new Player("player4"));

    testDeck.add(new Card(Rank.ACE, Suit.CLUBS));
    testDeck.add(new Card(Rank.JACK, Suit.HEARTS));
    testDeck.add(new Card(Rank.QUEEN, Suit.DIAMONDS));
    testDeck.add(new Card(Rank.KING, Suit.SPADES));
    testDeck.add(new Card(Rank.TWO, Suit.HEARTS));
    testDeck.add(new Card(Rank.THREE, Suit.SPADES));
    testDeck.add(new Card(Rank.FOUR, Suit.HEARTS));
    testDeck.add(new Card(Rank.FIVE, Suit.DIAMONDS));
    testDeck.add(new Card(Rank.SIX, Suit.CLUBS));
    testDeck.add(new Card(Rank.SEVEN, Suit.HEARTS));
    testDeck.add(new Card(Rank.NINE, Suit.DIAMONDS));
    testDeck.add(new Card(Rank.THREE, Suit.CLUBS));

  }

  @Test
  void deckCreationTest() {
    Card fiveClubs = new Card(Rank.FIVE, Suit.CLUBS);
    assertThat(deck.getDeck().get(4).equals(fiveClubs)).isTrue();
  }

  @Test
  void shuffleTest(){
    assertThat(deck.getDeck().get(5).equals(deck2.getDeck().get(5))).isTrue();
    Collections.shuffle(deck.getDeck());
    assertThat(deck.getDeck().get(5).equals(deck2.getDeck().get(5))).isFalse();
  }

  @Test
  void dealCards() {

    Collections.shuffle(testDeck);
    deckTest.dealCards(testPlayers);

    assertThat(testPlayers.get(0).getHand().size()).isEqualTo(6);

  }

  @Test
  void dealCardsTwoUp() {

    Collections.shuffle(testDeck);
    deckTest.dealCards(testPlayers);

    int faceDownCount = 0;
    int faceUpCount = 0;

    for(Card card: testPlayers.get(0).getHand()){
      if(card.getFaceUp())
        faceUpCount++;
      else
        faceDownCount++;
    }

    assertThat(faceDownCount).isEqualTo(4);
    assertThat(faceUpCount).isEqualTo(2);

    faceDownCount = 0;
    faceUpCount = 0;

    for(Card card: testPlayers.get(1).getHand()){
      if(card.getFaceUp())
        faceUpCount++;
      else
        faceDownCount++;
    }

    assertThat(faceDownCount).isEqualTo(4);
    assertThat(faceUpCount).isEqualTo(2);

    //System.out.println(deckTest.getDeck().size());

   /* for(Player player: testPlayers) {
      for (Card card : player.getHand())
        System.out.print(card.toString());
      System.out.println("\n");

    }*/
  }

  @Test
  void equalsIsTrue(){
    assertThat(deck2.equals(deck)).isTrue();
  }

  @Test
  void equalsIsFalse(){
    Collections.shuffle(deck.getDeck());
    assertThat(deck.equals(deck2)).isFalse();
  }

  @Test
  void drawCardFromDeck(){
    ListIterator<Card> deckIterator = deckTest.getDeck().listIterator();
    deckIterator.next();
    Card drawn = deckTest.drawCard(true, deckIterator);
    Card compare = new Card(Rank.TWO, Suit.CLUBS);
    Card compare2 = new Card(Rank.FIVE, Suit.CLUBS);
    assertThat(drawn.equals(compare)).isTrue();
    assertThat(drawn.equals(compare2)).isFalse();
  }

  @Test
  void drawCardFromDiscard(){
    ListIterator<Card> deckIterator = deckTest.getDeck().listIterator();
    deckIterator.next();
    Card drawn = deckTest.drawCard(false, deckIterator);
    Card compare = new Card(Rank.ACE, Suit.CLUBS);
    Card compare2 = new Card(Rank.FIVE, Suit.CLUBS);
    assertThat(drawn.equals(compare)).isTrue();
    assertThat(drawn.equals(compare2)).isFalse();
  }
  @Test
  void flipTopCardTest(){
    ListIterator<Card> deckIterator = deckTest.getDeck().listIterator();
    Card topCard = deckTest.flipTopCard(deckIterator);
    Card compare = new Card(Rank.ACE, Suit.CLUBS);
    Card compare2 = new Card(Rank.FIVE, Suit.CLUBS);
    assertThat(topCard.equals(compare)).isTrue();
    assertThat(topCard.equals(compare2)).isFalse();
  }

  @Test
  void displayDiscardTest(){
    ListIterator<Card> deckIterator = deckTest.getDeck().listIterator();
    deckIterator.next();
    Card discard = deckTest.displayDiscard(deckIterator);
    Card compare = new Card(Rank.ACE, Suit.CLUBS);
    Card compare2 = new Card(Rank.FIVE, Suit.CLUBS);
    assertThat(discard.equals(compare)).isTrue();
    assertThat(discard.equals(compare2)).isFalse();

  }

}
