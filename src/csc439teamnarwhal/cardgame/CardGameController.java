package csc439teamnarwhal.cardgame;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardGameController {

  CardGameView view = new CardGameView();
  ArrayList<Player> players = new ArrayList<>();
  Deck deck = new Deck();
  int numPlayers = 0;


  public void gameSetup() {
    view.setText("Welcome to the 6-Card Golf Game, brought to you in part by Team Narwhal inc.");
    view.setText("Please enter the number of players: ");
    view.setInput();
    numPlayers = Integer.parseInt(view.getNumPlayers());
    while (numPlayers < 2) {
      view.setText("Please enter an integer greater than 1: ");
      view.setInput();
      numPlayers = Integer.parseInt(view.getNumPlayers());
    }
  }

  public void createPlayers(int numPlayers, ArrayList<Player> players) {
    for (int i = 0; i < numPlayers; i++) {
      players.add(new Player("Player" + i));
    }

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
