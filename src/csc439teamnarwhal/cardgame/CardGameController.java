package csc439teamnarwhal.cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class CardGameController {

  CardGameView view = new CardGameView();
  ArrayList<Player> players = new ArrayList<>();
  Deck deck = new Deck();
  int numPlayers = 0;
  Player currentPlayer;
  int playerTurn = 0;


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
      createDoubleDeck();
    }
    Collections.shuffle(deck.getDeck());
    createPlayers(numPlayers, players);
    deck.dealCards(players);
  }

  public void playGame() {

    currentPlayer = players.get(playerTurn);
    view.setText("It is " + currentPlayer.getName() + "'s Turn");
    view.setText(currentPlayer.getName() + "'s Hand: ");
    displayHand(players);
    view.setText("Choose from one of the options below: ");
    view.setText("1: Draw from the deck");
    view.setText("2: Pick up the Discard Card");
    view.setText("3: End the game");
    view.setInput();
    while(view.getInput().equals("2") && view.getInput().equals("1")){
      view.setText("You have entered an invalid choice");
      view.setText("Choose from one of the options below: ");
      view.setText("1: Draw from the deck");
      view.setText("2: Pick up the Discard Card");
      view.setText("3: End the game");
      view.setInput();
    }
    //logic for draw or pick up card from Michael
  }

  public void createPlayers(int numPlayers, ArrayList<Player> players) {
    for (int i = 1; i <= numPlayers; i++) {
      players.add(new Player("Player" + i));
    }
  }

  public void createDoubleDeck() {
    Deck deck2 = new Deck();
    deck.getDeck().addAll(deck2.getDeck());
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
