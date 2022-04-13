/**
 * CardGameView is the view aspect of the Model View Controller (MVC)
 * pattern that we have implemented to conduct the gameplay in our
 * 6-Card Golf Game. This class contains simply the methods that show
 * the information that is continuously procured and sent from
 * CardGameController during gameplay. CardGameView class is used to display
 * all the information that is needed for the players to see the game
 * as a pictorial representation, turn by turn. It is also how the user interacts with
 * the game through input.
 *
 * CSC 439 - Software Testing and Maintenance
 *
 */

package csc439teamnarwhal.cardgame;

import java.util.Scanner;

public class CardGameView {
    private final Scanner scanner;
    private String text;
    private String input;

    public CardGameView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Accepts a String and outputs the contents to the console
     * @param text will be displayed to console
     */
    public void setText(String text) {
        System.out.println(text);
        this.text = text;
    }

    /**
     * Gets input from player using Scanner.
     */
    public void setInput() {
        input = scanner.next();
    }

    public String getInput() {
        return input;
    }

    public String getText() {
        return this.text;
    }

    /**
     * Justifies the text to be shown on console in order
     * to make the card visually appear clean and organized on the console.
     * @param text
     */
    public void setCardRows(String text) {
        System.out.printf("%-10s", text);
    }


}