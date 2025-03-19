package com.github.zipcodewilmington.casino.games.bingo;

import java.util.ArrayList;
import java.util.Random;

public class BingoGame {


    private int numberChosen;
    private ArrayList<Integer> numbers;

    public BingoGame() {
    }

    public void runGame(){

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
                    board[i][j] = numberForSlot;
                    firstColumn.remove(randomIndex);
                }
                if (i == 1){
                    randomIndex = random.nextInt(secondColumn.size());
                    numberForSlot = secondColumn.get(randomIndex);
                    board[i][j] = numberForSlot;
                    secondColumn.remove(randomIndex);
                }
                if (i == 2){
                    randomIndex = random.nextInt(thirdColumn.size());
                    numberForSlot = thirdColumn.get(randomIndex);
                    board[i][j] = numberForSlot;
                    thirdColumn.remove(randomIndex);
                }
                if (i == 3){
                    randomIndex = random.nextInt(fourthColumn.size());
                    numberForSlot = fourthColumn.get(randomIndex);
                    board[i][j] = numberForSlot;
                    fourthColumn.remove(randomIndex);
                }
                if (i == 4){
                    randomIndex = random.nextInt(fifthColumn.size());
                    numberForSlot = fifthColumn.get(randomIndex);
                    board[i][j] = numberForSlot;
                    fifthColumn.remove(randomIndex);
                }
            }
        }
        return board;
    }

    public int chooseRandomNumber(ArrayList<Integer> num){
        return 0;
    }

    public int checkFiveInARow(){
        return 0;
    }

    public int[][] updateBoard(){
        return null;
    }

    public String formatChosenNumber(){
        return null;
    }

    public int freeSpace(){
        return 0;
    }

    public String outputBoard(){
        return null;
    }
}
