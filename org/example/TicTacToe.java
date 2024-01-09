package org.example;

import org.example.PlayfieldsExceptions.PlayfieldsCharInputException;
import org.example.PlayfieldsExceptions.PlayfieldsDimensionException;
import org.example.PlayfieldsExceptions.PlayfieldsSetOccupiedException;

import java.util.Scanner;

public class TicTacToe {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;

    public TicTacToe(){
        this.player1 = new Player('X');
        this.player2 = new Player('O');
        this.board = new Board();
        this.currentPlayer = this.player1;
    }

    private int getRow(){
        Scanner line = new Scanner(System.in);
        String fetchedLine = line.nextLine();
        int row;
        try{
            row = Integer.parseInt(fetchedLine);
            if(row >=0 && row <= 2){
                return row;
            }else{
                System.out.println("Please input a valid number for row (0-2): ");
                getRow();
            }
        }catch(NumberFormatException e){
            System.out.println("Please input a valid number for row (0-2): ");
            getRow();
        }
        return 0;
    }

    private int getColumn(){
        Scanner line = new Scanner(System.in);
        String fetchedLine = line.nextLine();
        int column;
        try{
            column = Integer.parseInt(fetchedLine);
            if(column >=0 && column <= 2){
                return column;
            }else{
                System.out.println("Please input a valid number for column (0-2): ");
                getColumn();
            }
        }catch(NumberFormatException e){
            System.out.println("Please input a valid number for column (0-2): ");
            getColumn();
        }
        return 0;
    }

    void execution() throws PlayfieldsDimensionException, PlayfieldsCharInputException, PlayfieldsSetOccupiedException {
        System.out.println("\nIt's "+currentPlayer.getMarker()+" Players turn.");
        board.print();

        System.out.print("Enter row (0-2): ");
        int row = getRow();
        System.out.print("Enter column (0-2): ");
        int column = getColumn();

        if(board.isCellEmpty(row,column)){
            board.place(row, column, currentPlayer.getMarker());
        }else{
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

    private void switchCurrentPlayer(){
        if(this.currentPlayer == this.player1){
            this.currentPlayer = this.player2;
            return;
        }
        if(currentPlayer == this.player2){
            this.currentPlayer = this.player1;
            return;
        }
    }

    private boolean hasWinner() throws PlayfieldsDimensionException {
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
}
