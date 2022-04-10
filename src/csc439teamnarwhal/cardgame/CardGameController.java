package csc439teamnarwhal.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class CardGameController {

  CardGameView view = new CardGameView();
  ArrayList<Player> players = new ArrayList<>();
  Deck deck = new Deck();
  int numPlayers = 0;
  Player currentPlayer;
  int playerTurn = 0;
  ListIterator<Card> deckIterator;


  /**
   * This method creates the gameSetup before the players take their turns. It prompts the user for
   * the number of players and selects the deck size accordingly. The players are created and their
   * hands are dealt. The top card is flipped over to create the discard pile.
   */
  public void gameSetup() {
    view.setText("Welcome to the 6-Card Golf Game, brought to you in part by Team Narwhal inc.");
    view.setText("Please enter the number of players: ");
    view.setInput();
    numPlayers = Integer.parseInt(view.getInput());
    while (numPlayers < 2) {
      view.setText("Please enter an integer greater than 1: ");
      view.setInput();
      numPlayers = Integer.parseInt(view.getInput());
    }
    if (numPlayers > 4) {
      createDoubleDeck(deck);
    }
    Collections.shuffle(deck.getDeck());
    createPlayers(numPlayers, players);
    deck.dealCards(players);
    deckIterator = deck.getDeck().listIterator();
    deck.flipTopCard(deckIterator);

  }

  /**
   * This method drive the play of the game. We start by determining who's turn it is. We then share
   * that players hand to the view along with the discard and offer the player the options of the
   * game.
   */
  public void playGame() {

    currentPlayer = players.get(playerTurn);
    view.setText("It is " + currentPlayer.getName() + "'s Turn");
    view.setText(currentPlayer.getName() + "'s Hand: ");
    displayHand(players);
    displayDiscard();

    playerOptions();
    while (!view.getInput().equals("2") && !view.getInput().equals("1") && !view.getInput()
        .equals("3")) {
      view.setText("You have entered an invalid choice");
      playerOptions();
    }

    if (view.getInput().equals("1")) {
      Card drawn = deck.drawCard(true, deckIterator);
      view.setText("The card you drew is: ");
      displayCard(drawn);
      view.setText("Which card would you like to switch (Enter 1-6)");
    } else if (view.getInput().equals("2")) {
      deck.drawCard(false, deckIterator);
    } else
    //write method here
    {
      System.out.println("endgame");
    }

  }

  public void createPlayers(int numPlayers, ArrayList<Player> players) {
    for (int i = 1; i <= numPlayers; i++) {
      players.add(new Player("Player" + i));
    }
  }

  public void createDoubleDeck(Deck deck) {
    deck.getDeck().addAll(deck.getDeck());
  }

  public void displayHand(ArrayList<Player> player) {
    ArrayList<Card> currentHand = player.get(playerTurn).getHand();

    view.setCardRows("┌─────┐");
    view.setCardRows("┌─────┐");
    view.setCardRows("┌─────┐");
    view.setText("");
    for (int i = 0; i < 3; i++) {
      Card currentCard = currentHand.get(i);
      if (currentHand.get(i).getFaceUp()) {
        view.setCardRows("|" + currentCard.getSuit() + "    |");
      } else {
        view.setCardRows("|?   ?|");
      }
    }
    view.setText("");
    for (int i = 0; i < 3; i++) {
      Card currentCard = currentHand.get(i);
      if (currentHand.get(i).getFaceUp()) {
        view.setCardRows("|  " + currentCard.getRank_name() + "  |");
      } else {
        view.setCardRows("|  ?  |");
      }
    }
    view.setText("");
    for (int i = 0; i < 3; i++) {
      Card currentCard = currentHand.get(i);
      if (currentHand.get(i).getFaceUp()) {
        view.setCardRows("|    " + currentCard.getSuit() + "|");
      } else {
        view.setCardRows("|?   ?|");
      }
    }
    view.setText("");
    view.setCardRows("└─────┘");
    view.setCardRows("└─────┘");
    view.setCardRows("└─────┘");
    view.setText("");

    view.setCardRows(" Card 1");
    view.setCardRows(" Card 2");
    view.setCardRows(" Card 3");
    view.setText("");
    view.setText("");

    view.setCardRows("┌─────┐");
    view.setCardRows("┌─────┐");
    view.setCardRows("┌─────┐");
    view.setText("");

    for (int i = 3; i < 6; i++) {
      Card currentCard = currentHand.get(i);
      if (currentHand.get(i).getFaceUp()) {
        view.setCardRows("|" + currentCard.getSuit() + "    |");
      } else {
        view.setCardRows("|?   ?|");
      }
    }
    view.setText("");
    for (int i = 3; i < 6; i++) {
      Card currentCard = currentHand.get(i);
      if (currentHand.get(i).getFaceUp()) {
        view.setCardRows("|  " + currentCard.getRank_name() + "  |");
      } else {
        view.setCardRows("|  ?  |");
      }
    }
    view.setText("");
    for (int i = 3; i < 6; i++) {
      Card currentCard = currentHand.get(i);
      if (currentHand.get(i).getFaceUp()) {
        view.setCardRows("|    " + currentCard.getSuit() + "|");
      } else {
        view.setCardRows("|?   ?|");
      }
    }
    view.setText("");
    view.setCardRows("└─────┘");
    view.setCardRows("└─────┘");
    view.setCardRows("└─────┘");
    view.setText("");

    view.setCardRows(" Card 4");
    view.setCardRows(" Card 5");
    view.setCardRows(" Card 6");
    view.setText("");
    view.setText("");

  }

  public void playerOptions() {
    view.setText("Choose from one of the options below: ");
    view.setText("1: Draw from the deck");
    view.setText("2: Pick up the Discard");
    view.setText("3: End the game");
    view.setInput();
  }

  public void displayDiscard() {
    Card discard = deck.displayDiscard(deckIterator);
    displayCard(discard);
  }

  public void displayCard(Card card) {
    view.setText("┌─────┐");
    view.setText("|" + card.getSuit() + "    |");
    view.setText("|  " + card.getRank_name() + "  |");
    view.setText("|    " + card.getSuit() + "|");
    view.setText("└─────┘");
    view.setText("");
  }

}

 /* CountModel model = new CountModel(0);
  CountView view = new CountView();
  public void askForInput() {
    view.setText("What color is the sky?");
    view.getText();
    model.setCount(model.getCount() + 1);
  }
  public void displayCount() {
    view.setText("The number of times you answered is " + model.getCount());
  }
  public boolean canAskAgain() {
    return model.getCount() < 3;
}*/
