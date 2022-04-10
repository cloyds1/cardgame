
import csc439teamnarwhal.cardgame.CardGameController;
import csc439teamnarwhal.cardgame.Deck;
import csc439teamnarwhal.cardgame.Player;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class CardGameControllerTest {
  CardGameController controller = new CardGameController();
  ArrayList<Player> players = new ArrayList<>();
  Deck deck1 = new Deck();
  Deck deck2 = new Deck();
  Deck deck3 = new Deck();

  @Test
  void gameSetupTest(){
    controller.gameSetup();
  }

  @Test
  void createPlayersTest(){
    controller.createPlayers(5, players);
    assertThat(players.get(3).getName()).isEqualTo("Player4");
  }


  @Test
  void playGame() {
    //not sure how to test inputs from user
  }

  @Test
  void createDoubleDeck() {
    deck2.getDeck().addAll(deck1.getDeck());
    controller.createDoubleDeck(deck1);
    assertThat(deck2.equals(deck1)).isTrue();
  }

  @Test
  void displayHand() {
  }

  @Test
  void playerOptions() {
  }

  @Test
  void displayDiscard() {
  }

  @Test
  void displayCard() {
  }
}
