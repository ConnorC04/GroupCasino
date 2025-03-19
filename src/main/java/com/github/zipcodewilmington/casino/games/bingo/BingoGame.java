package com.github.zipcodewilmington.casino.games.bingo;

import java.util.ArrayList;
import java.util.Random;

public class BingoGame {

    private Random random = new Random();
    private int numberChosen;
    private ArrayList<Integer> numbers;

    public BingoGame(){
    }

    public ArrayList<Integer> setNumbers(ArrayList<Integer> num, int start, int end){
        for (int i = start; i < end; i++){
             num.add(i);
        }
        return num;
    }

    public int[][] makeBoard(){

        ArrayList<Integer> firstColumn = new ArrayList<>();
        ArrayList<Integer> secondColumn = new ArrayList<>();
        ArrayList<Integer> thirdColumn = new ArrayList<>();
        ArrayList<Integer> fourthColumn = new ArrayList<>();
        ArrayList<Integer> fifthColumn = new ArrayList<>();

        BingoGame bingoGame = new BingoGame();

        bingoGame.setNumbers(firstColumn, 1, 15);
        bingoGame.setNumbers(secondColumn, 16, 30);

        int[][] board = new int[5][5];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (i == 0){

                }
            }
        }
        return null;
    }

    public int chooseRandomNumber(ArrayList<Integer> num){

//        int randomChoice = random.nextInt(1, 75);
//        num.remove(randomChoice);
//        return randomChoice;
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
