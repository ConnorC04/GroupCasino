package com.github.zipcodewilmington.casino.games.roulette;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class RouletteGame {
    private static Random random = new Random(); //var from superclass casino needed? - casinoWallet
    private static Scanner scan = new Scanner(System.in);
    private int currentSpinVal;
    private String currentColor;
    private String oddOrEven;
    private HashMap<Enum, Double> currentBets = new HashMap<>(); //need to decide what type of hashmap would be best for storing bets
    private boolean playGame = true;
    private enum insideBets{STRAIGHT, SPLIT, STREET, CORNER}
    private enum outsideBets{RED, BLACK, ODD, EVEN, LOW, HIGH, DOZEN1, DOZEN2, DOZEN3, ROW1, ROW2, ROW3}
    private boolean addBets = true;
    private double betAmount;
    private String rouletteTable = "   |        1-12       |       13-24       |      25--36  \n" +
            "   |  3 |  6 |  9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | ROW 1\n" +
            " 0 |  2 |  5 |  8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | ROW 2\n" +
            "   |  1 |  4 |  7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | ROW 3\n" +
            "   |LOW  1-18|  EVEN   |  REDS   |  BLACK  |  ODDS   |19-36 HIGH|";

    public RouletteGame() {
    }

    private void runGame() {
        welcomeMessage();
        while (playGame) {
            printTableLayout();
            while (addBets) {
                makeBets();
                askToConfirmBets();
            }
            spinWheel();


         exitGame();
        }
    }

    String getString(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scan.next();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scan.next() + "\" isn't a valid answer");
            }
        }
    }

    double getDouble(String message) {
        while (true) {
            System.out.println(message);
            try {
                return scan.nextDouble();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scan.nextDouble() + "\" isn't a valid bet");
            }
        }
    }


    public void welcomeMessage() {
        System.out.println("Welcome to Roulette!");
        //make a boolean so players can leave from here just in case?
    }

    public String getTableLayout() {
        return rouletteTable;
    }

    public void printTableLayout() {
        System.out.println(rouletteTable);
    }

    public void makeBets() {
        String betType = getString("What kind of bet would you like to make: ( INSIDE ) ( OUTSIDE )");

        if (betType.equalsIgnoreCase("Inside")) {
            String insideBet = getString("Which type of inside bet would you like to make: ( STRAIGHT ) ( SPLIT ) ( STREET ) ( CORNER )");
        } else if (betType.equalsIgnoreCase("Outside")) {
            String outsideBet = getString("Which type of outside bet would you like to make:\n( RED ) ( BLACK ) ( ODD ) ( EVEN ) ( LOW ) ( HIGH )\n" +
                    "( FIRST DOZEN ) ( SECOND DOZEN ) ( THIRD DOZEN )\n( ROW 1 ) ( ROW 2 ) ( ROW 3 )");
        }
        //prompt user to select betType, for now only have single number
    }

    public boolean askToConfirmBets() {
        String betAgain = getString("Would you like to place another bet? ( YES ) ( NO )");
        if (betAgain.equalsIgnoreCase("No")) {
            addBets = false;
        }
        return addBets;
    }

    public void spinWheel() {
        this.currentSpinVal = random.nextInt(37);
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
    public boolean exitGame() {
        String askPlayAgain = getString("Would you like to play again? ( YES ) ( NO )");
        if (askPlayAgain.equalsIgnoreCase("No")) {
            playGame = false;
        }
        return playGame;
    }


}
