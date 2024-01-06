package org.example;

import org.example.PlayfieldsExceptions.PlayfieldsCharInputException;
import org.example.PlayfieldsExceptions.PlayfieldsClearFailed;
import org.example.PlayfieldsExceptions.PlayfieldsDimensionException;
import org.example.PlayfieldsExceptions.PlayfieldsSetOccupiedException;

//As a player, I want to be able to see the current state of the game, so that I can keep track of the moves made by both myself
public class Board {


    private char[][] cells;

    void printout_game() {
        System.out.printf("It's %c Players turn!\n", (turnnumber % 2) == 0 ? 'X' : 'O');
        System.out.printf("Turn number: %d\n _____________\n", turnnumber);

        for (int i = 0; i < 3; i++) {
            System.out.printf(" | %s | %s | %s |\n", cells[i][0], cells[i][1], cells[i][2]);
            System.out.print(" -------------\n");
        }
    }


    public char get_cell(int row, int col) throws PlayfieldsDimensionException {
        if (row > 2 || col > 2) {
            throw new PlayfieldsDimensionException(String.format("Error, one or both of your inputs (row = %d, col = %d) " +
                    "are outside the possible range of 0,1,2", row, col));
        }

        return this.cells[row][col];
    }

    public boolean isCellEmpty(int row, int col) throws PlayfieldsDimensionException {
        char cellcontent = '\u0000';
        try {
            cellcontent = this.get_cell(row, col);
        } catch (PlayfieldsDimensionException e) {
            System.out.printf("%s", e.getMessage());
        }
        return cellcontent == ' ';
    }

    public boolean isFull() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (Character.toLowerCase(cells[i][j])) {
                    case ' ':
                        break;
                    case 'x', 'o':
                        count++;
                        break;
                }
            }
        }
        return count == 9;
    }

    public void place(int row, int col, char marker) throws PlayfieldsDimensionException, PlayfieldsCharInputException, PlayfieldsSetOccupiedException {
        if (row > 2 || col > 2) {
            throw new PlayfieldsDimensionException(String.format("Error, one or both of your inputs (row = %d, col = %d) " +
                    "are outside the possible range of 0,1,2", row, col));
        }
        if (!(Character.toLowerCase(marker) == 'x' || Character.toLowerCase(marker) == 'o' || marker == ' ')) {
            throw new PlayfieldsCharInputException(String.format("Error, your input (input = %s) is wrong - allowed are 'X' and 'O' (The letter), or ' ' (Space)", marker));
        } else if (cells[row][col] != ' ') {
            throw new PlayfieldsSetOccupiedException(String.format("Error, your desired field %d / %d is already occupied with %c", row, col, cells[row][col]));
        } else {
            this.cells[row][col] = marker;
        }
    }

    public void clear() throws PlayfieldsClearFailed{
        boolean state = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    this.place(i, j, ' ');
                } catch (PlayfieldsDimensionException | PlayfieldsCharInputException |
                         PlayfieldsSetOccupiedException e) {
                    System.out.printf("%s", e.getMessage());
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    state = isCellEmpty(i, j);
                } catch (PlayfieldsDimensionException e) {
                    System.out.printf("%s", e.getMessage());
                }
                if (!state) {
                    throw new PlayfieldsClearFailed(String.format("Clearing the Gameboard failed at cell[%d][%d]", i, j));
                }
            }
        }
    }

    private int turnnumber;

    public Board() {
        cells = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
        turnnumber = 0;
    }

    public Board(int turnnumber_input) {
        this();
        turnnumber = turnnumber_input;
    }

    public Board(int turnnumber_input, char[][] gamefield_input) throws PlayfieldsCharInputException {
        this(turnnumber_input);
        cells = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (!(Character.toLowerCase(gamefield_input[i][j]) == 'x' ||
                        Character.toLowerCase(gamefield_input[i][j]) == 'o' || Character.toLowerCase(gamefield_input[i][j]) == ' ')) {
                    if (gamefield_input[i][j] == '\u0000') {
                        cells[i][j] = ' ';
                        continue;
                    }
                    throw new PlayfieldsCharInputException("Your input for the Board contains Characters" +
                            " other than 'X', 'O' or ' '");
                } else {
                    cells[i][j] = Character.toLowerCase(gamefield_input[i][j]);
                }
            }
        }
    }


}
