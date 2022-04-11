

import csc439teamnarwhal.cardgame.CardGameView;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;


class CardGameViewTest {

    CardGameView view = new CardGameView();

    @Test
    void setTextTest(){
        view.setText("Welcome to Java");
        assertThat("Welcome to Java").isEqualTo(view.getText());
    }
    @Test
    void setInput(){
           }
    @Test
    void getInput(){

    }
    @Test
    void getText() {

    }
    @Test
    void setCardRows(String text){

    }

}