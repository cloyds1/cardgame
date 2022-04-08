
import csc439teamnarwhal.cardgame.CardGameController;
import csc439teamnarwhal.cardgame.Player;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class CardGameControllerTest {
  CardGameController controller = new CardGameController();
  ArrayList<Player> players = new ArrayList<>();
  @Test
  void gameSetupTest(){
    controller.gameSetup();
  }

  @Test
  void createPlayersTest(){
    controller.createPlayers(5, players);
    assertThat(players.get(3).getName()).isEqualTo("Player4");
  }


  }
