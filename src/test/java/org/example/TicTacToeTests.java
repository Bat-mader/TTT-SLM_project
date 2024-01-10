package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.org.example.PlayfieldsExceptions.PlayfieldsDimensionException;
import src.main.java.org.example.PlayfieldsExceptions.PlayfieldsSetOccupiedException;


import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTests {
    private TicTacToe game;

    @BeforeEach
    void setUp() {
        game = new TicTacToe();
    }

    @Test
    void testSwitchCurrentPlayer_Positive() {
        TicTacToe game = new TicTacToe();
        Player initialPlayer = game.getCurrentPlayer(); // Assuming you have a getter for currentPlayer
        game.switchCurrentPlayer();
        assertNotSame(initialPlayer, game.getCurrentPlayer());
    }

    @Test
    void testSwitchCurrentPlayer_Negative() {
        char initialMarker = game.currentPlayer.getMarker();
        game.switchCurrentPlayer();
        char newMarker = game.currentPlayer.getMarker();
        assertNotEquals(initialMarker, newMarker);
    }

    @Test
    void testHasWinner_Positive() throws PlayfieldsDimensionException, PlayfieldsSetOccupiedException, src.main.java.org.example.PlayfieldsExceptions.PlayfieldsCharInputException {
        // Set up the board with a winning condition
        Board board = game.getBoard(); // Access using getter
        board.place(0, 0, 'X');
        board.place(1, 1, 'X');
        board.place(2, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void testHasWinner_Negative() throws PlayfieldsDimensionException, PlayfieldsSetOccupiedException, src.main.java.org.example.PlayfieldsExceptions.PlayfieldsCharInputException {
        // Set up the board without a winning condition
        Board board = game.getBoard(); // Access using getter
        board.place(0, 0, 'X');
        board.place(1, 1, 'O'); // Different marker
        board.place(2, 2, 'X');
        assertFalse(game.hasWinner());
    }
}
