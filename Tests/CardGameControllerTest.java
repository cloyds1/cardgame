
import csc439teamnarwhal.cardgame.Card;
import csc439teamnarwhal.cardgame.CardGameController;
import csc439teamnarwhal.cardgame.Deck;
import csc439teamnarwhal.cardgame.Player;
import csc439teamnarwhal.cardgame.Rank;
import csc439teamnarwhal.cardgame.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class CardGameControllerTest {
  CardGameController controller = new CardGameController();
  ArrayList<Player> players = new ArrayList<>();
  Deck deck1 = new Deck();
  Deck deck2 = new Deck();

  @Test
  void gameSetupTest(){
    //not sure how to test inputs from user
    //controller.gameSetup();
  }

  @Test
  void createPlayersTest(){
    controller.createPlayers(5);
    assertThat(controller.getPlayers().get(3).getName()).isEqualTo("Player4");
    assertThat(controller.getPlayers().size()).isEqualTo(5);
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
    //not sure how to test display from this method
  }

  @Test
  void playerOptions() {
    //not sure how to test display from this method
  }

  @Test
  void displayDiscard() {
    ListIterator<Card> deckIterator = controller.getDeck().getDeck().listIterator();
    deckIterator.next();
    Card discard = deckIterator.previous();
    deckIterator.next();
    controller.displayDiscard(deckIterator);

    assertThat(controller.displayDiscard(deckIterator).equals(discard)).isTrue();

  }

  @Test
  void displayCard() {
    Card card = new Card(Rank.JACK, Suit.CLUBS);
    controller.displayCard(card);
    //not sure how to test this further
  }

  @Test
  void switchCardInHandTest(){
    Card card = new Card(Rank.ACE, Suit.CLUBS);
    controller.createPlayers(5);
    Collections.shuffle(controller.getDeck().getDeck());
    controller.getDeck().dealCards(controller.getPlayers());
    ListIterator<Card> deckIterator = controller.getDeck().getDeck().listIterator();
    controller.getDeck().flipTopCard(deckIterator);
    controller.switchCardInHand(1, card, deckIterator, 0);
    assertThat(controller.getPlayers().get(0).getHand().get(0).equals(card)).isTrue();
    controller.switchCardInHand(2, card, deckIterator, 0);
    assertThat(controller.getPlayers().get(0).getHand().get(1).equals(card)).isTrue();
    controller.switchCardInHand(3, card, deckIterator, 0);
    assertThat(controller.getPlayers().get(0).getHand().get(2).equals(card)).isTrue();
    controller.switchCardInHand(4, card, deckIterator, 0);
    assertThat(controller.getPlayers().get(0).getHand().get(3).equals(card)).isTrue();
    controller.switchCardInHand(5, card, deckIterator, 0);
    assertThat(controller.getPlayers().get(0).getHand().get(4).equals(card)).isTrue();
    controller.switchCardInHand(6, card, deckIterator, 0);
    assertThat(controller.getPlayers().get(0).getHand().get(5).equals(card)).isTrue();
    }

  @Test
  void gameContinues() {
  }

  @Test
  void createAndDealDeckTest(){
    controller.setNumPlayers(4);
    controller.createPlayers(4);
    controller.createAndDealDeck();
    assertThat(controller.getDeck().getDeck().size()).isEqualTo(28);

    controller.setDeck(deck1);
    controller.createAndDealDeck();
    assertThat(controller.getDeck().getDeck().size()).isEqualTo(28);

  }

  @Test
  void createAndDealDeckDoubleTest() {
    controller.setNumPlayers(5);
    controller.createPlayers(5);
    controller.createAndDealDeck();
    assertThat(controller.getDeck().getDeck().size()).isEqualTo(74);
  }
}
