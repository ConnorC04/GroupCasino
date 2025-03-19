package com.github.zipcodewilmington.casino.games.roulette;
import java.util.Random;


public class RouletteGame {
    private static Random random = new Random(); //var from superclass casino needed? - casinoWallet
    private int currentSpinVal;
    private String currentColor;
    private String oddOrEven;
    //private ??? currentBets; //need to decide what type of hashmap would be best for storing bets
    private boolean playGame = true;
    private enum betTypes{INSIDE, OUTSIDE}
    private enum insideBets{SINGLE}
    private enum outsideBets{RED, BLACK, ODD, EVEN}
    private boolean addBets = true;
    private double betAmount;
    private String rouletteTable = "   |        1-12       |       13-24       |      25--36  \n" +
            "   |  3 |  6 |  9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | ROW 1\n" +
            " 0 |  2 |  5 |  8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | ROW 2\n" +
            "   |  1 |  4 |  7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | ROW 3\n" +
            "   |LOW  1-18|  EVEN   |  REDS   |  BLACK  |  ODDS   |19-36 HIGH|";
//change 1-18 to have LOW in the block, and 19-36 to have HIGH for clarity
    public RouletteGame() {
    }

    private void runGame() {
        while (playGame) {
            tableLayout();
            while (addBets) {
                makeBets();
                askToConfirmBets();
            }
            spinWheel();


        }
    }

    //opening message/display of board?

    public String getTableLayout() {
        return rouletteTable;
    }

    private void tableLayout() {
        System.out.println(rouletteTable);
    }

    private void makeBets() {
        //prompt user to select betType, for now only have single number

    }

    private boolean askToConfirmBets() {
        boolean makeMoreBets = true;
        //ask user if they want to continue making bets, if yes then return true, if no return false;
        return this.addBets = makeMoreBets;
    }

    public void spinWheel() {
        this.currentSpinVal = random.nextInt(37); //may need to set origin to -1, will test to be sure
    }
    public int getCurrentNum() {
        return currentSpinVal;
    }
    public void isOddOrEven(int randomNumber) {
        if (randomNumber == 0) {
            this.oddOrEven = "Neither";
        } else if (randomNumber % 2 == 0) {
            this.oddOrEven = "Even";
        } else {
            this.oddOrEven = "Odd";
        }
    }
    public String getOddOrEven() {
        return oddOrEven;
    }
    public void isRedOrBlack(int randomNumber) {
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
    public String getCurrentColor() {
        return currentColor;
    }
    //matchingNum (checks if currentNum matches user's guess),
    //exitGame (closes game)


}
