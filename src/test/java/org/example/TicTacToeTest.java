package org.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TicTacToeTest {

    private Board board;


    @Test
    public void testGameboardConstructor() {
        board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(" ", board.get_cell(i, j));
            }
        }
    }

    @Test
    public void testGameboardConstructorwithValidPlayfieldInput() {

        char[][] testinput = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (((j + i) % 2) == 0) {
                    testinput[i][j] = 'X';
                } else {
                    testinput[i][j] = 'O';
                }
            }
        }
        Assertions.assertDoesNotThrow(() -> {
            try {
                board = new Board(0, testinput);
            } catch (PlayfieldsErrorException e) {
                System.out.printf("%s", e.getMessage());
            }
        });
    }

    @Test
    public void testGameboardConstructorwithInvalidPlayfieldInput() {

        char[][] testinput = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (((j + i) % 2) == 0) {
                    testinput[i][j] = 'X';
                } else {
                    testinput[i][j] = 'S';
                }
            }
        }
        assertThrows(PlayfieldsErrorException.class, () -> {
                board = new Board(0, testinput);

        });

    }


}



