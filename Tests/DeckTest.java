import csc439teamnarwhal.cardgame.Deck;
import csc439teamnarwhal.cardgame.Card;
import static com.google.common.truth.Truth.assertThat;

import csc439teamnarwhal.cardgame.Rank;
import csc439teamnarwhal.cardgame.Suit;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DeckTest {

  Deck newDeck = new Deck();
  Deck newDeck2 = new Deck();
  ArrayList<Card> deck = newDeck.getDeck();
  ArrayList<Card> deck2 = newDeck2.getDeck();

  @Test
  void deckCreationTest() {
    Card fiveClubs = new Card(Rank.FIVE, Suit.CLUBS);
    assertThat(deck.get(4).equals(fiveClubs)).isTrue();
  }

  @Test
  void shuffleTest(){
    assertThat(deck.get(5).equals(deck2.get(5))).isTrue();
    Deck.shuffle(deck);
    assertThat(deck.get(5).equals(deck2.get(5))).isFalse();
  }
}
