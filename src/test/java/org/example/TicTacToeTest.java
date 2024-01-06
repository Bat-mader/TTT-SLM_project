package org.example;


import org.example.PlayfieldsExceptions.PlayfieldsCharInputException;
import org.example.PlayfieldsExceptions.PlayfieldsClearFailed;
import org.example.PlayfieldsExceptions.PlayfieldsDimensionException;
import org.example.PlayfieldsExceptions.PlayfieldsSetOccupiedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TicTacToeTest {

    private Board board;

    @Test
    public void testClearMethod_successEmptyBoard(){
        board = new Board();
        Assertions.assertDoesNotThrow(() -> {
            try {
                board.clear();
            } catch (PlayfieldsClearFailed e) {
                System.out.printf("%s", e.getMessage());
            }
        });
    }

    @Test
    public void testClearMethod_successFullBoard(){
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
        try{board = new Board(0, testinput);}
        catch(PlayfieldsCharInputException e){
            System.out.printf("%s", e.getMessage());
        }
        Assertions.assertDoesNotThrow(() -> {
            try {
                board.clear();
            } catch (PlayfieldsClearFailed e) {
                System.out.printf("%s", e.getMessage());
            }
        });
    }

    @Test
    public void testPlacemethodCharInputValid(){
        //testen auf Dimension, Char input, Occupied
        char [][] testinput = new char[3][3];
        testinput[0][0] = 'x';
        testinput[0][1] = 'X';
        testinput[0][2] = ' ';

        try{
        board = new Board(0, testinput);}
        catch (PlayfieldsCharInputException e){
            System.out.printf("%s", e.getMessage());
        }
        Assertions.assertDoesNotThrow(() -> {
            try {
                board.place(1,1,'x');
            } catch (PlayfieldsCharInputException e) {
                System.out.printf("%s", e.getMessage());
            }
        });
    }

    @Test
    public void testPlacemethodCharInputInvalid(){
        //testen auf Dimension, Char input, Occupied
        char [][] testinput = new char[3][3];
        testinput[0][0] = 'x';
        testinput[0][1] = 'X';
        testinput[0][2] = ' ';

        try{
            board = new Board(0, testinput);}
        catch (PlayfieldsCharInputException e){
            System.out.printf("%s", e.getMessage());
        }
        assertThrows(PlayfieldsCharInputException.class, () -> {
            board.place(1,0,'k');
        });
    }

    @Test
    public void testIsEmptyMethod_empty(){
        board = new Board();
        Assertions.assertDoesNotThrow(()->{
            board.isCellEmpty(0,0);
            assertTrue(board.isCellEmpty(0, 0));
        });

    }

    @Test
    public void testIsEmptyMethod_full(){
        board = new Board();
        Assertions.assertDoesNotThrow(()->{
            try{board.place(0,0,'X');
        } catch (PlayfieldsCharInputException | PlayfieldsDimensionException  | PlayfieldsSetOccupiedException e){
                System.out.printf("%s", e.getMessage());
            }
            board.isCellEmpty(0,0);
            assertFalse(board.isCellEmpty(0,0));
        }
        );
    }

    @Test
    public void testIsFullMethod_empty(){
        board = new Board();
        assertFalse(board.isFull());
    }

    @Test
    public void testIsFullMethod_full(){
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
        try{board = new Board(0, testinput);}
        catch(PlayfieldsCharInputException e){
            System.out.printf("%s", e.getMessage());
        }
        assertTrue(board.isFull());
    }

    @Test
    public void testGameboardConstructor() {
        board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try{
                    assertEquals(' ', board.get_cell(i, j));
                }
                catch (PlayfieldsDimensionException e){
                        System.out.printf("%s", e.getMessage());
                    }
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
            } catch (PlayfieldsCharInputException e) {
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
        assertThrows(PlayfieldsCharInputException.class, () -> {
                board = new Board(0, testinput);

        });

    }


}



