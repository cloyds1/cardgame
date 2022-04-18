package csc439teamnarwhal.cardgame;


/**
 * CSC 439 - Software Testing and Maintenance
 * Golf Cardgame Project
 *
 * @version 2.0
 */
public class Main {

  /**
   * The main method runs the program by implementing the CardGameController with a
   * Model-View-Controller pattern
   * @param args parameters for main method
   */
  public static void main(String[] args) {
    CardGameController controller = new CardGameController();
    controller.gameSetup();
    while(controller.gameContinues() && controller.numRounds != 0){
      controller.playGame();
    }
    controller.endGame();

  }

}
