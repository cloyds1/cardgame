
import csc439teamnarwhal.cardgame.CardGameController;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class CardGameControllerTest {
  CardGameController controller = new CardGameController();

  @Test
  void gameSetupTest(){
    controller.gameSetup();
  }


  }
