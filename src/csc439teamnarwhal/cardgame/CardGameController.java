/**
 * CardGameController is the controller aspect of the Model View Controller (MVC) pattern that we
 * have implemented to conduct the gameplay in our 6-Card Golf Game. This class contains all the
 * methods that drive the logic of the game. The information is continuously obtained and sent to
 * CardGameView during gameplay. CardGameView class displays all the information that is needed for
 * the players to see the game as a pictorial representation, turn by turn via the console.
 * <p>
 * CSC 439 - Software Testing and Maintenance
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
    int holesLeft = -1;
    int numHoles = 0;
    ListIterator<Card> deckIterator;
    boolean keepPlaying = true;
    boolean continueHole = true;
    ArrayList<Integer> scores = new ArrayList<>();
    ArrayList<String> namesInOrder = new ArrayList<>();

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setContinueHole(boolean continueHole) {
        this.continueHole = continueHole;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * This method creates the gameSetup before the players take their turns. It prompts the user for
     * the number of players, selects the deck size accordingly and shuffles the deck. The players are
     * created and their hands are dealt. The top card is flipped over to create the discard pile. An
     * iterator is introduced to navigate the deck.
     */
    public void gameSetup() {
        displayWelcomeToGame();
        view.setInput();
        numPlayers = Integer.parseInt(view.getInput());
        while (numPlayers < 2) {
            view.setText("Please enter an integer greater than 1: ");
            view.setInput();
            numPlayers = Integer.parseInt(view.getInput());
        }
        createPlayers(numPlayers);
        createAndDealDeck();

        view.setText("How many holes would you like to play?");
        view.setInput();
        holesLeft = Integer.parseInt(view.getInput());
        while (holesLeft < 1) {
            view.setText("Please enter an integer greater than 0: ");
            view.setInput();
            holesLeft = Integer.parseInt(view.getInput());

        }
        numHoles = holesLeft;


    }


    /**
     * This method drives the play of the game. We start by determining who's turn it is. We then
     * share that players hand to the view along with the discard and offer the player the options of
     * the game. The player can draw from the discard and switch with their hand, or they can draw
     * from the deck. If drawing from deck, they must switch with their hand, or they can discard.The
     * player also has the option to end the game on their turn. After the turn is over, we increment
     * to the next players turn using the PlayerTurn variable.
     */
    public void playGame() {

        //sets up current player turn. Shows hand and discard to player
        currentPlayer = players.get(playerTurn);
        view.setText("It is " + currentPlayer.getName() + "'s Turn");
        view.setText(currentPlayer.getName() + "'s Hand: ");
        displayHand(players);
        displayDeck();
        view.setText("The current discard is: ");
        displayDiscard(deckIterator);

        //player chooses what to do. Draw from deck, draw from discard, or end game
        playerOptions();
        while (!view.getInput().equals("1") && !view.getInput().equals("2") && !view.getInput()
                .equals("3") && !view.getInput().equals("4")) {
            view.setText("You have entered an invalid choice");
            playerOptions();
        }

        while (view.getInput().equals("3")) {
            displayScoreboard();
            playerOptions();
            //check for invalid entry
            while (!view.getInput().equals("1") && !view.getInput().equals("2") && !view.getInput()
                    .equals("3") && !view.getInput().equals("4")) {
                view.setText("You have entered an invalid choice");
                playerOptions();
            }
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
        } else if (view.getInput().equals("3")) {
            displayScoreboard();
        } else {
            keepPlaying = false;
            continueHole = false;
            return;
        }

        //display new hand to player
        view.setText(currentPlayer.getName() + "'s New Hand: ");
        displayHand(players);
        displayDeck();
        view.setText("The new discard is: ");
        displayDiscard(deckIterator);

        //increment player turn up to total number of players (size of array).
        // Once all players have gone, reset to first player (0 for array)
        if (playerTurn < players.size() - 1) {
            playerTurn++;
        } else {
            playerTurn = 0;
        }
        //checks if all cards are face up, and if so, the round is over, the deck is shuffled
        // and the next round can begin.
        if (currentPlayer.allCardsUp()) {
            for (int i = 0; i < players.size(); i++) {
                players.get(i).scoreHand();
            }
            displayScoreboard();
            continueHole = false;
            System.out.println("End of round");
            decrementHolesLeft();
            if (holesLeft == 0) {
                keepPlaying = false;
                return;
            }
            deck = new Deck();
            createAndDealDeck();
            while (!view.getInput().equals("Y")) {
                view.setText("Are you ready to start the next hole? Y/N");
                view.setInput();
            }

        }

    }

    /*
    public void showFinalScoreboard() {
        view.setText("\nThe game has ended with the final scoreboard: \n");
        displayScoreboard();
        String winningPlayer = namesInOrder.get(0);
        int winningScore = scores.get(0);
        view.setText("\n" + winningPlayer + " is the winner with a score of " + winningScore + ".");
        view.setText("\nThank you for playing 6-Card Golf by Team Narwhal!");
    }
    */

    /**
     * Method to create a single or double deck, shuffle it, and deal to the players.
     */
    public void createAndDealDeck() {
        //use two decks for more than 4 players
        if (numPlayers > 4) {
            createDoubleDeck(deck);
        }
        //shuffle the deck, create the players and deal them cards. Flip the top card over to create
        // the discard pile
        Collections.shuffle(deck.getDeck());

        deck.dealCards(players);
        deckIterator = deck.getDeck().listIterator();
        deck.flipTopCard(deckIterator);
    }

    /**
     * Counts down the number of holes remaining in the game.
     */
    public void decrementHolesLeft() {
        holesLeft--;
    }


    /**
     * Method to display welcome screen to player
     */
    private void displayWelcomeToGame() {

        view.setText("                                    @@@@@@");
        view.setText("                                     @@@@  ");
        view.setText("                                 @@@@@@@@@@@ ");
        view.setText("                                  @@@ @ @@@@ ");
        view.setText("                                  (((@@@(( ");
        view.setText("       #%%%%%%%%%%%%%      ((((((((((((((((((((((");
        view.setText("          %%%%%%%%%%%%% ((((((((((((((((((((((((((((.");
        view.setText("            %%%%%%%%%%%%%(((((((((((((((((((((((####(((");
        view.setText(
                "                   ((((((#((((((((((((((((((((#######((((               %%%%%%%%  ");
        view.setText(
                "                  (((((((((((((((((((((((((((((#####(((((((            %%%%%%%%%%% %%%%%%%%");
        view.setText(
                "                 ((((((((((((((((((((((((((((((((#########((           %%%%%%%%%%%%%%%%%%%%%");
        view.setText(
                "                (((((((((((((((((((((((((((((((((####(((((((((          %%%%%%%%%%%%%%%%%%%%");
        view.setText(
                "                (((((((((((((((((((((((((((((((((((((((((((((((           %%%%%%%%%%%%%%%%");
        view.setText(
                "                (((((((((((((((((((((((((((((((((((((((((((((((((          %%%%%%%%%%%%");
        view.setText(
                "                (((((((((((((@(((((((((((((((((((((((((((((((((((((        (%%%%%%%%  ");
        view.setText(
                "                 ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((%%%#( ");
        view.setText(
                "                  (((@(((((((((((((((((((((((((((((((((((((((((((((((((((((((((( ");
        view.setText(
                "                   (((((((((((((@@@@((@@@(((((((((((((((((((((((((((((((((((((( ");
        view.setText("                     ,(((((((((@@@@@@@@((((((((((((((((((((((((((((((((((((( ");
        view.setText("                        *(((((((@@@@@@@@@@.......................((((((((  ");
        view.setText("                             (((@@@@@@@@@@........................(((  ");
        view.setText("                                /@@@@@@@((((((((((((((((((((( ");
        view.setText("");
        view.setText("Welcome to the 6-Card Golf Game, brought to you in part by Team Narwhal inc.");
        view.setText("Please enter the number of players: ");


    }

    /**
     * @return boolean value to determine if game play should continue or not.
     */
    public boolean gameContinues() {
        return (keepPlaying);
    }

    /**
     * @return boolean value to determine if hole should be over or not.
     */
    public boolean holeContinues() {
        return continueHole;
    }

    public void setHolesLeft(int x) {
        this.holesLeft = holesLeft;
    }

    public int getHolesLeft() {
        return holesLeft;
    }

    /**
     * Method that creates a new Player (Object) and adds them to the ArrayList, one by one.
     *
     * @param numPlayers the number of players as provided by input
     */
    public void createPlayers(int numPlayers) {
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player" + i));
        }
    }

    /**
     * createDoubleDeck is called in gameSetup() when the user input indicates that there will be
     * greater than 4 players playing the game. It creates an additional deck of 52 cards to be added
     * to the existing deck in order to make it into a double-deck.
     *
     * @param deck the deck we will duplicate to make a double-deck
     */
    public void createDoubleDeck(Deck deck) {
        deck.getDeck().addAll(deck.getDeck());
    }

    /**
     * Method that creates the data that will be used by the View in order for the players to be shown
     * the gameplay displayed on the console, play by play. displayHand() translates the data into a
     * neatly organized and enjoyable format that is easy for the players to see on the console.
     *
     * @param player the players hand that we would like to display
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
     * Method that uses the View to display the players options during their turn and is called in
     * playGame() at the start of their turn.
     */
    public void playerOptions() {
        view.setText("Choose from one of the options below: ");
        view.setText("1: Draw from the deck");
        view.setText("2: Pick up the Discard");
        view.setText("3: Display Scoreboard");
        view.setText("4: End the game");
        view.setInput();
    }

    /**
     * Method that displays the card on the top of the discard pile so the player can see if they
     * would like to swap their card with discard.
     *
     * @param deckIterator the iterator needed to navigate the deck
     * @return the discard is returned to aid in testing and possible future need of the discard
     */
    public Card displayDiscard(ListIterator<Card> deckIterator) {
        Card discard = deck.displayDiscard(deckIterator);
        displayCard(discard);
        return discard;
    }

    /**
     * Method that simply expresses the current card value in a visual format that is pleasing to the
     * user.
     *
     * @param card card you would like to display on console
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
     * Displays the deck to the user
     */
    public void displayDeck() {
        view.setText("┌─────┐");
        view.setText("|     |");
        view.setText("|~DECK|");
        view.setText("|     |");
        view.setText("└─────┘");
        view.setText("");
    }

    /**
     * Method that has a switch statement that checks for which card in a player's hand of 6 cards
     * will be swapped for another legal card.
     *
     * @param i          choice of card to swap from player's hand
     * @param card       card to swap with player's hand
     * @param iterator   iterator used to navigate deck
     * @param playerTurn player who is switching cards in hand
     */
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


    /**
     * Simple method that displays that the game has ended on the console along with the winner of the
     * game and the final score
     */
    public void endGame() {
        view.setText("***************************************");
        view.setText("The game has ended.");

        view.setText("The final scores are: ");
        view.setText("");
        ArrayList<String> playerList = displayScoreboard();
        view.setText(playerList.get(0) + " is the winner!");
        view.setText("confetti and balloons");

    }

    /**
     * Displays the current scoreboard to the console in order of lowest score.
     *
     * @return returns the list of players in order of their score, lowest to highest
     */
    public ArrayList<String> displayScoreboard() {
        for (int i = 0; i < players.size(); i++) {
            scores.add(players.get(i).getScore());
        }
        Collections.sort(scores);
        for (int i = 0; i < scores.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (scores.get(i) == players.get(j).getScore()) {
                    namesInOrder.add(players.get(j).getName());
                }
            }
        }
        int currentHole = numHoles - holesLeft + 1;
        if (currentHole <= numHoles) {
            view.setText("");
            view.setText("Hole " + currentHole + " out of " + numHoles + " holes");
            view.setText("Scoreboard");
        }

        view.setText("-------------------------------");
        view.setText("");
        view.setText("     Player       Score");
        view.setText("");
        for (int i = 0; i < scores.size(); i++) {
            int position = i + 1;
            view.setText(position + ".   " + namesInOrder.get(i) + "      " + scores.get(i));
        }
        view.setText(" ");
        view.setText("-------------------------------");
        return namesInOrder;
    }
}
