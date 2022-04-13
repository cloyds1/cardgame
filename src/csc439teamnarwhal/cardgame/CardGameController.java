/**
 * CardGameController is the controller aspect of the Model View Controller
 * (MVC) pattern that we have implemented to conduct the gameplay in our
 * 6-Card Golf Game. This class contains all the methods that drive the logic
 * of the game. The information is continuously obtained and sent to
 * CardGameView during gameplay. CardGameView class displays
 * all the information that is needed for the players to see the game
 * as a pictorial representation, turn by turn.
 *
 * CSC 439 - Software Testing and Maintenance
 *
 * @author Clinton Schultz
 * @author Ellen Hokkanen
 * @version 1.0
 */

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
  boolean keepPlaying = true;

  public Deck getDeck() {
    return deck;
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

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
    //use two decks for more than 4 players
    if (numPlayers > 4) {
      createDoubleDeck(deck);
    }
    //shuffle the deck, create the players and deal them cards. Flip the top card over to create
    // the discard pile
    Collections.shuffle(deck.getDeck());
    createPlayers(numPlayers);
    deck.dealCards(players);
    deckIterator = deck.getDeck().listIterator();
    deck.flipTopCard(deckIterator);
  }

  /**
   * This method drives the play of the game. We start by determining who's turn it is. We then share
   * that players hand to the view along with the discard and offer the player the options of the
   * game. The player can draw from the discard and switch with their hand, or they can draw from
   * the deck. If drawing from deck, they must switch with their hand, or they can discard.
   */
  public void playGame() {

    //sets up current player turn. Shows hand and discard to player
    currentPlayer = players.get(playerTurn);
    view.setText("It is " + currentPlayer.getName() + "'s Turn");
    view.setText(currentPlayer.getName() + "'s Hand: ");
    displayHand(players);
    view.setText("The current discard is: ");
    displayDiscard(deckIterator);

    //player chooses what to do. Draw from deck, draw from discard, or end game
    playerOptions();

    //check for invalid entry
    while (!view.getInput().equals("2") && !view.getInput().equals("1") && !view.getInput()
        .equals("3")) {
      view.setText("You have entered an invalid choice");
      playerOptions();
    }

    //draw from deck
    if (view.getInput().equals("1")) {
      Card drawn = deck.drawCard(true, deckIterator);
      view.setText("The card you drew is: ");
      displayCard(drawn);
      view.setText(
          "Which card would you like to switch (Enter 1-6) to switch with your hand, or 0 to "
              + "discard the drawn card.");
      view.setInput();
      int choice = Integer.parseInt(view.getInput());

      //discard drawn card without switching player hand
      if (choice == 0) {
        deckIterator.previous();
        deckIterator.set(drawn);
        deckIterator.next();
      }
      //discard card from hand and swap
      else {
        switchCardInHand(choice, drawn, deckIterator, playerTurn);
      }

    }
    //switch a card with discard
    else if (view.getInput().equals("2")) {
      Card pickedup = deck.drawCard(false, deckIterator);
      view.setText("Which card would you like to switch (Enter 1-6)");
      view.setInput();
      int choice = Integer.parseInt(view.getInput());
      switchCardInHand(choice, pickedup, deckIterator, playerTurn);
    } else {
      keepPlaying = false;
      return;
    }

    //display new hand to player
    view.setText(currentPlayer.getName() + "'s New Hand: ");
    displayHand(players);
    view.setText("The new discard is: ");
    displayDiscard(deckIterator);

    //increment player turn up to total number of players (size of array).
    // Once all players have gone, reset to first player (0 for array)
    if (playerTurn < players.size() - 1) {
      playerTurn++;
    } else {
      playerTurn = 0;
    }

  }

  public boolean gameContinues() {
    return (keepPlaying);
  }

  /**
   * Method that creates a new Player (Object) and adds them
   * to the ArrayList, one by one, based on the user input
   * given when asked how many players will be playing the game.
   * @param numPlayers
   */
  public void createPlayers(int numPlayers) {
    for (int i = 1; i <= numPlayers; i++) {
      players.add(new Player("Player" + i));
    }
  }

  /**
   * createDoubleDeck is called in gameSetup() when the user input indicates
   * that there will be greater than 4 players playing the game.
   * It creates an additional deck of 52 cards to be added to the
   * existing deck in order to make it into a double deck.
   * @param deck
   */
  public void createDoubleDeck(Deck deck) {
    deck.getDeck().addAll(deck.getDeck());
  }

  /**
   * Method that creates the data that will be used by the View in order
   * for the players to be shown the gameplay displayed on the console, play
   * by play. displayHand() translates the data into a neatly organized and
   * enjoyable format that is easy for the players to see on the console.
   * @param player
   */
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

  /**
   * Method that uses the View to display the players options during
   * their turn and is called in playGame() whenever it is necessary.
   */
  public void playerOptions() {
    view.setText("Choose from one of the options below: ");
    view.setText("1: Draw from the deck");
    view.setText("2: Pick up the Discard");
    view.setText("3: End the game");
    view.setInput();
  }

  /**
   * Method that displays the card on the top of the discard pile
   * and returns it so that it can be swapped out for one of the cards
   * in the player's hand that is chosen.
   * @param deckIterator
   * @return
   */
  public Card displayDiscard(ListIterator deckIterator) {
    Card discard = deck.displayDiscard(deckIterator);
    displayCard(discard);
    return discard;
  }

  /**
   * Method that simply expresses the current card value in
   * a visual format that is pleasing to the user.
   * @param card
   */
  public void displayCard(Card card) {
    view.setText("┌─────┐");
    view.setText("|" + card.getSuit() + "    |");
    view.setText("|  " + card.getRank_name() + "  |");
    view.setText("|    " + card.getSuit() + "|");
    view.setText("└─────┘");
    view.setText("");
  }

  /**
   * Method that has a switch statement that checks for which card in
   * a player's hand of 6 cards will be swapped for another legal card.
   * @param i
   * @param card
   * @param iterator
   * @param playerTurn
   */
  public void switchCardInHand(int i, Card card, ListIterator<Card> iterator, int playerTurn) {
    Card switchCard = null;
    switch (i) {
      case 1:
        switchCard = players.get(playerTurn).getHand().get(0);
        break;
      case 2:
        switchCard = players.get(playerTurn).getHand().get(1);
        break;
      case 3:
        switchCard = players.get(playerTurn).getHand().get(2);
        break;
      case 4:
        switchCard = players.get(playerTurn).getHand().get(3);
        break;
      case 5:
        switchCard = players.get(playerTurn).getHand().get(4);
        break;
      case 6:
        switchCard = players.get(playerTurn).getHand().get(5);
        break;
      default:
        break;
    }
    card.faceUp();
    iterator.previous();
    iterator.set(switchCard);
    players.get(playerTurn).getHand().set(i - 1, card);
    iterator.next();

  }

  /**
   * Simple method that displays that the game has ended on the
   * console in text format for the user to see.
   */
  public void endGame() {
    view.setText("The game has ended.");
  }
}
