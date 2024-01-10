package org.example;

import src.main.java.org.example.PlayfieldsExceptions.PlayfieldsCharInputException;
import src.main.java.org.example.PlayfieldsExceptions.PlayfieldsDimensionException;
import src.main.java.org.example.PlayfieldsExceptions.PlayfieldsSetOccupiedException;

import java.util.Scanner;

public class TicTacToe {
    private final Player player1;
    private final Player player2;
    Player currentPlayer;
    final Board board;
    private final Scanner scanner;


    public TicTacToe(){
        this.player1 = new Player('X');
        this.player2 = new Player('O');
        this.board = new Board();
        this.currentPlayer = this.player1;
        this.scanner = new Scanner(System.in);

    }

    public int[] getCoordinates() {
        Scanner scanner = new Scanner(System.in);
        int row = -1, column = -1;

        while (row < 0 || row > 2 || column < 0 || column > 2) {
            System.out.print("Enter row and column (0-2), separated by a space: ");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
            }
            if (scanner.hasNextInt()) {
                column = scanner.nextInt();
            }
            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("Invalid input. Please enter valid row and column numbers.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        return new int[]{row, column};
    }


    void execution() throws PlayfieldsDimensionException, PlayfieldsCharInputException, PlayfieldsSetOccupiedException {
        System.out.println("\nIt's " + currentPlayer.getMarker() + " Player's turn.");
        board.print();

        int[] coordinates = getCoordinates();
        int row = coordinates[0];
        int column = coordinates[1];

        if (board.isCellEmpty(row, column)) {
            board.place(row, column, currentPlayer.getMarker());
        } else {
            System.out.println("This cell is already occupied.");
            execution();
        }

        this.switchCurrentPlayer();
    }


    public void start() throws PlayfieldsDimensionException, PlayfieldsCharInputException, PlayfieldsSetOccupiedException {
        while(!board.isFull() && !this.hasWinner()){

            this.execution();
        }
        if(this.hasWinner()){
            this.switchCurrentPlayer();
            board.print();
            System.out.println("Game ended. " + this.currentPlayer.getMarker() + " wins!.");
        }else{
            board.print();
            System.out.println("Game ended in a draw!");
        }
    }

    public void restart_game_wrapper() {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            TicTacToe game = new TicTacToe();
            try {
                game.start();
            } catch (PlayfieldsDimensionException | PlayfieldsCharInputException | PlayfieldsSetOccupiedException e) {
                e.printStackTrace();
            }

            System.out.println("Do you want to play again? (yes/no)");
            playAgain = scanner.nextLine();
        } while (playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y"));
        System.out.println("Thank you for playing!");
    }

    void switchCurrentPlayer(){
        if(this.currentPlayer == this.player1){
            this.currentPlayer = this.player2;
            return;
        }
        if(currentPlayer == this.player2){
            this.currentPlayer = this.player1;
            return;
        }
    }

    boolean hasWinner() throws PlayfieldsDimensionException {
        for (int i = 0; i < 3; i++) {
            if ((board.get_cell(i,0) != ' ' && board.get_cell(i,0) == board.get_cell(i,1) && board.get_cell(i,1) == board.get_cell(i,2)) ||
                    (board.get_cell(0,i) != ' ' && board.get_cell(0,i) == board.get_cell(1,i) && board.get_cell(1,i) == board.get_cell(2,i))) {
                return true;
            }
        }

        // Check diagonals
        return (board.get_cell(0,0) != ' ' && board.get_cell(0,0) == board.get_cell(1,1) && board.get_cell(1,1) == board.get_cell(2,2)) ||
                (board.get_cell(0,2) != ' ' && board.get_cell(0,2) == board.get_cell(1,1) && board.get_cell(1,1) == board.get_cell(2,0));
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

}
