import csc439teamcardvarks.cardgame.Card;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest {

  Card ace;

  @BeforeEach
  void setup() {
    ace = new Card("Clubs", "Ace");
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
  void valueTest() {
    assertThat(ace.getValue()).isEqualTo(14);
  }

  @Test
  void toStringTest() {
    assertThat(ace.toString()).isEqualTo("Ace of Clubs");
  }
}
