package org.example;

//As a player, I want to be able to see the current state of the game, so that I can keep track of the moves made by both myself
public class Board {


    private int turnnumber;
    private char[][] cells;

    void printout_game() {
        System.out.printf("It's %c Players turn!\n", (turnnumber % 2) == 0 ? 'X' : 'O');
        System.out.printf("Turn number: %d\n _____________\n", turnnumber);

        for (int i = 0; i < 3; i++) {
            System.out.printf(" | %s | %s | %s |\n", cells[i][0], cells[i][1], cells[i][2]);
            System.out.printf(" -------------\n");
        }
    }


    public String get_cell(int row, int col) {
        if (row > 2 || col > 2) {
            return String.format("Error, one or both of your inputs (row = %d, col = %d) " +
                    "are outside the possible range of 0,1,2", row, col);
        }

        return String.valueOf(this.cells[row][col]);
    }

    public String set_cell(int row, int col, char input) {
        if (row > 2 || col > 2) {
            return String.format("Error, one or both of your inputs (row = %d, col = %d) " +
                    "are outside the possible range of 0,1,2", row, col);
        }
        if (!(input == 'x' || input == 'o' || input == ' ')) {
            return String.format("Error, your input (input = %s) is wrong - allowed are 'X' and 'O' (The letter)", input);
        } else {
            this.cells[row][col] = input;
            return String.valueOf(input);
        }

    }

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

    public Board(int turnnumber_input, char[][] gamefield_input) throws PlayfieldsErrorException {
        this(turnnumber_input);
        cells = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (!(Character.toLowerCase(gamefield_input[i][j]) == 'x' ||
                        Character.toLowerCase(gamefield_input[i][j]) == 'o' || Character.toLowerCase(gamefield_input[i][j]) == ' ')) {
                    throw new PlayfieldsErrorException("Your input for the Board contains Characters" +
                            " other than 'X' or 'O'");
                } else {
                    cells[i][j] = gamefield_input[i][j];
                }
            }
        }
    }


}
