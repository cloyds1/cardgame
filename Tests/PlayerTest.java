import csc439teamnarwhal.cardgame.Card;

import static com.google.common.truth.Truth.assertThat;

import csc439teamnarwhal.cardgame.Player;
import csc439teamnarwhal.cardgame.Rank;
import csc439teamnarwhal.cardgame.Suit;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test

  void scoreHandTest(){
    Player player1 = new Player("Player 1");
    ArrayList<Card> hand = new ArrayList<>();
    ArrayList<Card> hand2 = new ArrayList<>();

    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.QUEEN, Suit.DIAMONDS));
    hand.add(new Card(Rank.KING, Suit.SPADES));
    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.TWO, Suit.SPADES));
    hand.add(new Card(Rank.FOUR, Suit.HEARTS));

    hand2.add(new Card(Rank.ACE, Suit.HEARTS));
    hand2.add(new Card(Rank.FIVE, Suit.DIAMONDS));
    hand2.add(new Card(Rank.FIVE, Suit.SPADES));
    hand2.add(new Card(Rank.JACK, Suit.HEARTS));
    hand2.add(new Card(Rank.TWO, Suit.SPADES));
    hand2.add(new Card(Rank.FOUR, Suit.HEARTS));

    player1.setHand(hand);

    assertThat(player1.scoreHand()).isEqualTo(12);
    assertThat(player1.getScore()).isEqualTo(12);

    player1.setHand(hand2);
    assertThat(player1.scoreHand()).isEqualTo(23);
    assertThat(player1.getScore()).isEqualTo(35);
  }
}
