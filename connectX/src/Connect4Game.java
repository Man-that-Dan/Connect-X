import java.util.Scanner;
import java.lang.System;
import java.lang.String;
import java.lang.Character;
import java.util.List;
import java.util.LinkedList;

public class Connect4Game {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);  // Reading from System.in
        boolean stop = false; //boolean variable to stop playing or not

        while(!stop){
            int columns = -1;
            int rows = -1;
            int winsec = -1;
            int players = -1;
            List<Character> tokens = new LinkedList<>();
            char implement = 'l';

            while(players < 2  || players > 10) {

                System.out.println("How many players? (input an integer between 2 and 10)");
                players= input.nextInt();
                if(players < 2){
                    System.out.println("Must have at least 2 players");
                }
                if(players > 10){
                    System.out.println("Can have at most 10 players");
                }
            }
            for(int i = 0; i < players; i++){
                String lineinput;
                char ChosenToken;
                boolean unique = false;
                while(!unique) {
                    int j = i + 1;
                    System.out.println("Enter the character to represent player " + j );

                    ChosenToken = Character.toUpperCase(input.next().charAt(0));

                    if(tokens.contains(ChosenToken)){
                        System.out.println(ChosenToken + "is already taken as a player token!");
                    } else {
                        tokens.add(ChosenToken);
                        unique = true;
                    }
                }
            };

            //set up rows
            while(rows < 3  || rows > 100) {

                System.out.println("How many rows should be on the board? (input an integer between 3 and 100)");
                rows = input.nextInt();
                if(rows < 3){
                    System.out.println("Must have at least 3 rows");
                }
                if(rows > 100){
                    System.out.println("Can have at most 100 rows");
                }
            }
            //set up columns
            while(columns < 3  || columns > 100) {

                System.out.println("How many columns should be on the board? (input an integer between 3 and 100)");
                columns = input.nextInt();
                if(columns < 3){
                    System.out.println("Must have at least 3 columns");
                }
                if(columns > 100){
                    System.out.println("Can have at most 100 columns");
                }
            }

            //set up number to win
            while(winsec < 3  || winsec > 25) {

                System.out.println("How many in a row to win? (input an integer between 3 and 25)");
                winsec = input.nextInt();
                if(winsec < 3){
                    System.out.println("Must have at least 3 in a row to win");
                }
                if(winsec > 25){
                    System.out.println("Can have at most 25 in a row to win");
                }
            }


            //set up board from interface
            //had to initialize here to something or compiler complained
            IGameBoard currentGame = new Gameboard(4, 4, 4);

            //Choose implementation
            while(implement != 'F' && implement != 'f' && implement != 'M' && implement !='m') {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                implement = input.next().charAt(0);
                if (implement == 'F' || implement == 'f') {
                    currentGame = new Gameboard(columns, rows, winsec);
                }
                if(implement == 'M' || implement == 'm'){
                    currentGame = new GameBoardMem(columns, rows, winsec);
                }
            }

            //message to be displayed upon game ending
            String message = "";
            String actualBoard;



            int maxcolumns = currentGame.getNumColumns();
            boolean win = false;
            boolean contn = false;
            char again = 'A';
            int c;
            actualBoard = currentGame.toString();
            System.out.println(actualBoard);
            int i = 0;
            while(!win) {

                //player turn
                while(!contn){
                    char currtoken = tokens.get(i);
                    System.out.println("Player " + currtoken + ", what column do you want to place your marker in?");
                    c = input.nextInt();
                    while(c < 0 || c > (maxcolumns - 1)){
                        System.out.println("Invalid column.");
                        System.out.println("Player " + currtoken + ", what column do you want to place your marker in?");
                        c = input.nextInt();
                    }
                    if (currentGame.checkIfFree(c)) {
                        currentGame.placeToken(currtoken, c);
                        win = currentGame.checkForWin(c);
                        if(win){
                            message = "Player " + currtoken + " Won!";
                        };
                        if(!win){
                          win = currentGame.checkTie();
                          if(win){
                              message = "It's a Tie!";
                          }
                        };

                        contn = true;
                    } else {
                        System.out.println("Column is full");
                    }
                };
                actualBoard = currentGame.toString();
                System.out.println(actualBoard);
                contn = false;

                //next player
                int maxind = players - 1;
                i++;
                if(i > maxind){
                  i = 0;
                };

            }
            //print results
            System.out.println(message);
            //ask to play again
            while(again != 'Y' && again != 'y' && again != 'N' && again !='n') {
                System.out.println("Would you like to play again? Y/N");
                again = input.next().charAt(0);
                if (again == 'N' || again == 'n') {
                    stop = true;
                }
            }





        }
        input.close();

    }

}
