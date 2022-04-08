package csc439teamnarwhal.cardgame;

import java.util.Scanner;

public class CardGameView {
    private final Scanner scanner;
    private String text;

    public CardGameView() {
        this.scanner = new Scanner(System.in);
    }

    public void setText(String text) {
        System.out.println(text);
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}