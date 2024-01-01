package org.example;

//As a player, I want to be able to see the current state of the game, so that I can keep track of the moves made by both myself
public class Gameboard{

    private int turnnumber;
    private char [][] playfields;

    void printout_game(){
        System.out.printf("It's %c Players turn!\n", (turnnumber%2)==0 ? 'X' : 'O');
        System.out.printf("Turn number: %d\n _____________\n",turnnumber);

            for(int i = 0; i < 3; i++){
                System.out.printf(" | %c | %c | %c |\n", playfields[i][0],playfields[i][1],playfields[i][2]);
                System.out.printf(" -------------\n");
            }
    }

    public Gameboard(){
        playfields = new char[3][3];
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){

                playfields[i][j] = ' ';
            }
        }
        turnnumber = 0;
    }

    public Gameboard(int turnnumber_input){
        this();
        turnnumber = turnnumber_input;
    }
    public Gameboard(int turnnumber_input, char [][] gamefield_input){
        this(turnnumber_input);
        if(gamefield_input.length > 2 || gamefield_input[0].length > 2 ){
            playfields = new char[3][3];
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                playfields[j][i] = gamefield_input[j][i];}
            }
        }
        else {playfields = gamefield_input;}
    }

}
