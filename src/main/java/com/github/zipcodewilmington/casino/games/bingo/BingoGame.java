package com.github.zipcodewilmington.casino.games.bingo;

import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BingoGame extends CasinoAccountManager implements GameInterface {


    private ArrayList<Integer> numbers = new ArrayList<>();


    public BingoGame() {}

    @Override
    public void run(){
        boolean playAgain = true;
        Scanner scan = new Scanner(System.in);

        BingoGame bingoGame = new BingoGame();
        while (playAgain) {


            int[][] playerBoard = bingoGame.makeBoard();
            int fiveInARow = 0;
            bingoGame.fillNumberArray();
            System.out.println("Your current board is:");
            bingoGame.outputBoard(playerBoard);
            playerBoard[2][2] = 0;
            System.out.println("You get one free space in the middle! Any space occupied with a space you \"stamped\" " +
                    "will be represented with a 0. ");
            bingoGame.outputBoard(playerBoard);
            System.out.println("Ok, we are now going to play. At this time, you can choose how many " +
                    "random numbers get picked before the game is over. Good luck!\n");
            System.out.println("Please choose a number between 4 and 60: ");
            int roundsToPlay = scan.nextInt();
            while ((roundsToPlay < 4) || (60 < roundsToPlay)) {
                System.out.println("You have to select a number between 4 and 60, please try again: ");
                roundsToPlay = scan.nextInt();
            }
            if ((roundsToPlay >= 4) && (60 >= roundsToPlay)) {
                for (int i = 0; i < roundsToPlay; i++) {
                    int randomNumber = bingoGame.chooseRandomNumber();
                    System.out.println("The number randomly selected was: " + formatChosenNumber(randomNumber));
                    bingoGame.updateBoard(playerBoard, randomNumber);
                    System.out.println("Your board now looks like this: ");
                    bingoGame.outputBoard(playerBoard);
                    fiveInARow = bingoGame.checkFiveInARow(playerBoard);
                    if (playerWins(fiveInARow)) {
                        System.out.println("Bingo! You won!");
                        System.out.println("Would you like to play again? ");
                        if (!scan.nextLine().equalsIgnoreCase("yes")){
                            playAgain = false;
                            System.out.println("Too da loo");
                        }
                    }
                }
                if (!playerWins(fiveInARow)) {
                    System.out.println("Womp womp.");
                    if (!scan.nextLine().equalsIgnoreCase("yes")){
                        playAgain = false;
                        System.out.println("Too da loo");
                    }
                }
            }
        }
    }

    public void setNumbers(ArrayList<Integer> num, int start, int end) {
        for (int i = start; i < end; i++) {
            num.add(i);
        }
    }

    public int[][] makeBoard(){

        BingoGame bingoGame = new BingoGame();

        ArrayList<Integer> firstColumn = new ArrayList<>();
        ArrayList<Integer> secondColumn = new ArrayList<>();
        ArrayList<Integer> thirdColumn = new ArrayList<>();
        ArrayList<Integer> fourthColumn = new ArrayList<>();
        ArrayList<Integer> fifthColumn = new ArrayList<>();

        bingoGame.setNumbers(firstColumn, 1, 15);
        bingoGame.setNumbers(secondColumn, 16, 30);
        bingoGame.setNumbers(thirdColumn, 31, 45);
        bingoGame.setNumbers(fourthColumn, 46, 60);
        bingoGame.setNumbers(fifthColumn, 61, 75);

        Random random = new Random();
        int randomIndex;
        int numberForSlot;

        int[][] board = new int[5][5];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (i == 0){
                    randomIndex = random.nextInt(firstColumn.size());
                    numberForSlot = firstColumn.get(randomIndex);
                    board[j][i] = numberForSlot;
                    firstColumn.remove(randomIndex);
                }
                if (i == 1){
                    randomIndex = random.nextInt(secondColumn.size());
                    numberForSlot = secondColumn.get(randomIndex);
                    board[j][i] = numberForSlot;
                    secondColumn.remove(randomIndex);
                }
                if (i == 2){
                    randomIndex = random.nextInt(thirdColumn.size());
                    numberForSlot = thirdColumn.get(randomIndex);
                    board[j][i] = numberForSlot;
                    thirdColumn.remove(randomIndex);
                }
                if (i == 3){
                    randomIndex = random.nextInt(fourthColumn.size());
                    numberForSlot = fourthColumn.get(randomIndex);
                    board[j][i] = numberForSlot;
                    fourthColumn.remove(randomIndex);
                }
                if (i == 4){
                    randomIndex = random.nextInt(fifthColumn.size());
                    numberForSlot = fifthColumn.get(randomIndex);
                    board[j][i] = numberForSlot;
                    fifthColumn.remove(randomIndex);
                }
            }
        }
        return board;
    }

    public void fillNumberArray(){
        BingoGame bingoGame = new BingoGame();
        bingoGame.setNumbers(numbers, 1, 75);
    }

    public int chooseRandomNumber(){
        Random random = new Random();
        int randomIndex = random.nextInt(numbers.size());
        int randomNumber = numbers.get(randomIndex);
        numbers.remove(randomIndex);
        return randomNumber;
    }

    public int checkFiveInARow(int[][] board){
        // Checks the rows
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    counter++;
                }
            }
            if (counter == 5) {
                break;
            } else {
                counter = 0;
            }
        }
        // Checks the columns
        if (counter == 0){
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[j][i] == 0) {
                        counter++;
                    }
                }
                if (counter == 5) {
                    break;
                } else {
                    counter = 0;
                }
            }
        }
        // Checks the topLeft to bottomRight diagonal
        if (counter == 0){
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[j][j] == 0) {
                        counter++;
                    }
                }
                if (counter == 5) {
                    break;
                } else {
                    counter = 0;
                }
            }
        }
        // Checks the topRight to bottomLeft diagonal
        if (counter == 0){
            for (int i = 4; i >= 0; i--) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 0) {
                        counter++;
                    }
                    i--;
                }
                if (counter == 5) {
                    break;
                } else {
                    counter = 0;
                }
            }
        }
        return counter;
    }

    public int[][] updateBoard(int[][] board, int numberChosen){
        int[][] updatedBoard = board;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (board[i][j] == numberChosen){
                    updatedBoard[i][j] = 0;
                }
            }
        }
        return updatedBoard;
    }

    public String formatChosenNumber(int numberChosen){
        if (numberChosen < 16){
            return "B-" + numberChosen;
        }
        if (numberChosen > 15 && numberChosen < 31){
            return "I-" + numberChosen;
        }
        if (numberChosen > 30 && numberChosen < 46){
            return "N-" + numberChosen;
        }
        if (numberChosen > 45 && numberChosen < 61){
            return "G-" + numberChosen;
        }
        if (numberChosen > 60 && numberChosen < 76){
            return "O-" + numberChosen;
        }
        return "Unreachable statement?";
    }

    public void outputBoard(int[][] board){

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                System.out.print(board[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public boolean playerWins(int winningCombo){
        return winningCombo == 5;
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }


}
