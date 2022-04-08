import static com.google.common.truth.Truth.assertThat;

import csc439teamnarwhal.cardgame.CardGameView;
import org.junit.jupiter.api.Test;


class CardGameViewTest {

    CardGameView view = new CardGameView();

    @Test
    void setTextTest(){
        view.setText("Welcome to Java");
        assertThat("Welcome to Java").isEqualTo(view.getText());
    }
}