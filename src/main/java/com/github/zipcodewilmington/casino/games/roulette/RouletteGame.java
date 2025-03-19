package com.github.zipcodewilmington.casino.games.roulette;
import java.util.Random;


public class RouletteGame {
    Random rand = new Random();
    //var from superclass casino needed? - casinoWallet
    int currentNum;
    String currentColor;
    String oddOrEven;
    double currentBet;
    boolean playGame = true;
    String[][] rouletteTable = {{"  ", "|    ", "   1-", "-12  ", "     ", "|    ", "  13-", "-24  ", "     ", "|    ", "  25-", "-36  "},
            {"  ", "|  3 ", "|  6 ", "|  9 ", "| 12 ", "| 15 ", "| 18 ", "| 21 ", "| 24 ", "| 27 ", "| 30 ", "| 33 ", "| 36 ", "|2 to 1"},
            {" 0", "|  2 ", "|  5 ", "|  8 ", "| 11 ", "| 14 ", "| 17 ", "| 20 ", "| 23 ", "| 26 ", "| 29 ", "| 32 ", "| 35 ", "|2 to 1"},
            {"  ", "|  1 ", "|  4 ", "|  7 ", "| 10 ", "| 13 ", "| 16 ", "| 19 ", "| 22 ", "| 25 ", "| 28 ", "| 31 ", "| 34 ", "|2 to 1"},
            {"  ", "|  1-", "-18  ", "|  EV", "EN   ", "|  RE", "DS   ", "|  BL", "ACK  ", "|  OD", "DS   ", "| 19-", "-36  "}};

    public void runGame() {
        //holds order of methods when game is running
//        while (playGame) {
//
//
//
//        }
    }

    //opening message/display of board?
    private void tableLayout() {
        System.out.println(rouletteTable);
        //add 2d array of the board, hardcode it in?
    }
    private void spinWheel() {
        this.currentNum = rand.nextInt(0, 37); //may need to set origin to -1, will test to be sure
    }
    private int getCurrentNum() {
        return this.currentNum;
    }
    private void isOddOrEven(int randomNumber) {
        if (randomNumber == 0) {
            this.oddOrEven = "Neither";
        } else if (randomNumber % 2 == 0) {
            this.oddOrEven = "Even";
        } else {
            this.oddOrEven = "Odd";
        }
    }
    private String getOddOrEven() {
        return this.oddOrEven;
    }
    private void isRedOrBlack(int randomNumber) {
        if (randomNumber % 2 == 0) {
            if (randomNumber > 0 && randomNumber < 11 ||
                    randomNumber > 18 && randomNumber < 29) {
                this.currentColor = "Black";
            } else if (randomNumber > 10 && randomNumber < 19 ||
                    randomNumber > 28 && randomNumber < 37) {
                this.currentColor = "Red";
            } else {
                this.currentColor = "Green";
            }
        }
    }
    private String getCurrentColor() {
        return this.currentColor;
    }
    //matchingNum (checks if currentNum matches user's guess),
    //exitGame (closes game)


}
