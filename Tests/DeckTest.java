import csc439teamnarwhal.cardgame.*;

import static com.google.common.truth.Truth.assertThat;
import java.util.Collections;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

public class DeckTest {

  Deck deck = new Deck();
  Deck deck2 = new Deck();

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

    ArrayList<Card> testDeck = new ArrayList<>();
    ArrayList<Player> testPlayers = new ArrayList<Player>();

    testPlayers.add(new Player("player1"));
    testPlayers.add(new Player("player2"));

    Deck deckTest = new Deck(testDeck);

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

    Collections.shuffle(testDeck);
    deckTest.dealCards(testPlayers, testDeck);

    assertThat(testPlayers.get(0).getHand().size()).isEqualTo(6);

  }
}
