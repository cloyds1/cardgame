import csc439teamcardvarks.cardgame.Card;
import static com.google.common.truth.Truth.assertThat;
import org.junit.jupiter.api.Test;

public class CardTest {

  @Test
  void suitTest(){
    Card ace = new Card("Club", "Ace");
    assertThat(ace.getSuit()).isEqualTo("Club");
  }
  @Test
  void numberTest(){
    Card ace = new Card("Club", "Ace");
    assertThat(ace.getNumber()).isEqualTo("Ace");
  }
}
