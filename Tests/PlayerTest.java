import csc439teamnarwhal.cardgame.Card;

import static com.google.common.truth.Truth.assertThat;

import csc439teamnarwhal.cardgame.Player;
import csc439teamnarwhal.cardgame.Rank;
import csc439teamnarwhal.cardgame.Suit;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  Player player1;
  ArrayList<Card> hand;

  @BeforeEach
  void setUp() {
    player1 = new Player("Player 1");
    hand = new ArrayList<>();

    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.QUEEN, Suit.DIAMONDS));
    hand.add(new Card(Rank.KING, Suit.SPADES));
    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.TWO, Suit.SPADES));
    hand.add(new Card(Rank.FOUR, Suit.HEARTS));
  }

  @Test
  void scoreHandTest(){
    ArrayList<Card> hand2 = new ArrayList<>();
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

  @Test
  void allCardsUpTest(){
    player1.setHand(hand);
    for (int i = 0; i < hand.size(); i++) {
      player1.getHand().get(i).faceUp();
    }

    assertThat(player1.allCardsUp()).isTrue();

    player1.getHand().get(0).faceDown();
    assertThat(player1.allCardsUp()).isFalse();

  }
}
