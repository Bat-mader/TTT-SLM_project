package org.example;

import org.example.PlayfieldsExceptions.PlayfieldsCharInputException;
import org.example.PlayfieldsExceptions.PlayfieldsDimensionException;
import org.example.PlayfieldsExceptions.PlayfieldsSetOccupiedException;

public class Main {
    public static void main(String[] args) throws PlayfieldsDimensionException, PlayfieldsCharInputException, PlayfieldsSetOccupiedException {
        TicTacToe game = new TicTacToe();

        game.start();
    }
}