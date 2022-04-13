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
   * This method drive the play of the game. We start by determining who's turn it is. We then share
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
      Card pickedUp = deck.drawCard(false, deckIterator);
      view.setText("Which card would you like to switch (Enter 1-6)");
      view.setInput();
      int choice = Integer.parseInt(view.getInput());
      switchCardInHand(choice, pickedUp, deckIterator, playerTurn);
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

  public void createPlayers(int numPlayers) {
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

  public Card displayDiscard(ListIterator deckIterator) {
    Card discard = deck.displayDiscard(deckIterator);
    displayCard(discard);
    return discard;
  }

  public void displayCard(Card card) {
    view.setText("┌─────┐");
    view.setText("|" + card.getSuit() + "    |");
    view.setText("|  " + card.getRank_name() + "  |");
    view.setText("|    " + card.getSuit() + "|");
    view.setText("└─────┘");
    view.setText("");
  }

  public void switchCardInHand(int i, Card card, ListIterator<Card> iterator, int playerTurn) {
    Card switchCard = null;
    switch (i) {
      case 1 -> switchCard = players.get(playerTurn).getHand().get(0);
      case 2 -> switchCard = players.get(playerTurn).getHand().get(1);
      case 3 -> switchCard = players.get(playerTurn).getHand().get(2);
      case 4 -> switchCard = players.get(playerTurn).getHand().get(3);
      case 5 -> switchCard = players.get(playerTurn).getHand().get(4);
      case 6 -> switchCard = players.get(playerTurn).getHand().get(5);
      default -> {
      }
    }
    card.faceUp();
    iterator.previous();
    iterator.set(switchCard);
    players.get(playerTurn).getHand().set(i - 1, card);
    iterator.next();

  }

  public void endGame() {
    view.setText("The game has ended.");
  }
}
