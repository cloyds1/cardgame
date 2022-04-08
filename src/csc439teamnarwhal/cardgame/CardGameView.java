package csc439teamnarwhal.cardgame;

import java.util.Scanner;

public class CardGameView {
    private final Scanner scanner;
    private String text;
    private String input;

    public CardGameView() {
        this.scanner = new Scanner(System.in);
    }

    public void setText(String text) {
        System.out.println(text);
        this.text = text;
    }

    public void setInput(){
        input = scanner.next();
    }

    public String getNumPlayers(){
        return input;
    }

    public String getText() {
        return this.text;
    }


}