package org.example;

import src.main.java.org.example.PlayfieldsExceptions.PlayfieldsCharInputException;
import src.main.java.org.example.PlayfieldsExceptions.PlayfieldsDimensionException;
import src.main.java.org.example.PlayfieldsExceptions.PlayfieldsSetOccupiedException;

public class Main {
    public static void main(String[] args) throws PlayfieldsDimensionException, PlayfieldsCharInputException, PlayfieldsSetOccupiedException {
        TicTacToe game = new TicTacToe();

        game.start();
    }
}