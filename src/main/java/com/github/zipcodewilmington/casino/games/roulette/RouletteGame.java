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

    void runGame() {
        while (playGame) {



        }
    }


    //opening message/display of board


    //methods - spinWheel (sets currentNum to randomly generated num between -1 and 37),
    private void spinWheel() {
        this.currentNum = rand.nextInt(-1, 37);
    }
    private int getCurrentNum() {
        return currentNum;
    }
    //oddOrEven (check if number is odd or even),
    private void isOddOrEven() {
        if (this.currentNum == 0) {
            this.oddOrEven = "Neither";
        } else if (this.currentNum % 2 == 0) {
            this.oddOrEven = "Even";
        } else {
            this.oddOrEven = "Odd";
        }
    }
    private String getOddOrEven() {
        return this.oddOrEven;
    }
    //redOrBlack (uses vals from oddOrEven and randomNum to determine if red or black),
    private void isRedOrBlack() {
        if (this.currentNum % 2 == 0) {
            if (this.currentNum > 0 && this.currentNum < 11 ||
                    this.currentNum > 18 && this.currentNum < 29) {
                this.currentColor = "Black";
            } else if (this.currentNum > 10 && this.currentNum < 19 ||
                    this.currentNum > 28 && this.currentNum < 37) {
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
