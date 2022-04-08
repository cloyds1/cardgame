import csc439teamnarwhal.cardgame.Deck;
import csc439teamnarwhal.cardgame.Card;
import static com.google.common.truth.Truth.assertThat;

import csc439teamnarwhal.cardgame.Rank;
import csc439teamnarwhal.cardgame.Suit;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DeckTest {

  Deck newDeck = new Deck();
  ArrayList<Card> deck = newDeck.getDeck();

  @Test
  void suitTest() {
    Card fiveClubs = new Card(Rank.FIVE, Suit.CLUBS);
    assertThat(deck.get(4).equals(fiveClubs)).isTrue();
  }
}
