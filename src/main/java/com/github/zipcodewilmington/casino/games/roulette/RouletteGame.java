package com.github.zipcodewilmington.casino.games.roulette;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class RouletteGame {
    private static Random random = new Random(); //var from superclass casino needed? - casinoWallet
    private static Scanner scan = new Scanner(System.in);
    private boolean playGame = true;
    private boolean addBets = true;
    private int currentSpinVal;
    private String currentColor;
    private String oddOrEven;
    private HashMap<Enum, Double> allBetsMade = new HashMap<>(); //need to decide what type of hashmap would be best for storing bets
    private HashMap<Enum, Double> betOdds = new HashMap<>();
    private ArrayList<Integer> currentNumsBetOn = new ArrayList<>();
    private enum betsAvailable {STRAIGHT, SPLIT, STREET, CORNER, RED, BLACK, ODD, EVEN, LOW, HIGH, DOZEN, ROW}

    private Double betAmount;
    private String rouletteTable = "   |        1-12       |       13-24       |      25--36  \n" +
            "   |  3 |  6 |  9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | ROW 1\n" +
            " 0 |  2 |  5 |  8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | ROW 2\n" +
            "   |  1 |  4 |  7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | ROW 3\n" +
            "   |LOW  1-18|  EVEN   |  REDS   |  BLACK  |  ODDS   |19-36 HIGH|";

    public RouletteGame() {
        betOdds.put(betsAvailable.STRAIGHT, 35.0);
        betOdds.put(betsAvailable.SPLIT, 17.0);
        betOdds.put(betsAvailable.STREET, 11.0);
        betOdds.put(betsAvailable.CORNER, 8.0);
        betOdds.put(betsAvailable.RED, 1.0);
        betOdds.put(betsAvailable.BLACK, 1.0);
        betOdds.put(betsAvailable.ODD, 1.0);
        betOdds.put(betsAvailable.EVEN, 1.0);
        betOdds.put(betsAvailable.LOW, 1.0);
        betOdds.put(betsAvailable.HIGH, 1.0);
        betOdds.put(betsAvailable.DOZEN, 2.0);
        betOdds.put(betsAvailable.ROW, 2.0);
    }

    private void runGame() {
        welcomeMessage();
        while (playGame) {
            allBetsMade.clear();
            currentNumsBetOn.clear();
            printTableLayout();
            while (addBets) {
                askForBets();
                askForAmountBet();
                askForNextNumber();
                askToConfirmBets();
            }
            spinWheel();
            //check all bets made against number from wheel
            //calculate wins/losses
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
                System.out.println("\"" + scan.next() + "\" isn't a valid wager");
            }
        }
    }

    int getInteger(String message) {
        while (true) {
            System.out.println(message);
            try {
                return scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scan.next() + "\" isn't a valid number to bet on");
            }
        }
    }


    public void welcomeMessage() {
        System.out.println("Welcome to Roulette!");
    }

    public String getTableLayout() {
        return rouletteTable;
    }

    public void printTableLayout() {
        System.out.println(rouletteTable);
    }

    public boolean makeBet(betsAvailable bet, double amount) {
        allBetsMade.put(bet, amount);
        return true;
    }

    private boolean askForBets() {
        betsAvailable bet = this.askWhichBet();
        Double amount = this.askForAmountBet();
        return makeBet(bet, amount);
    }

    private betsAvailable askWhichBet() {
        String askBetType = getString("What kind of bet would you like to make: ( INSIDE ) ( OUTSIDE )");
        betsAvailable betType = null;
        if (askBetType.equalsIgnoreCase("Inside")) {
            String inside = getString("Which type of inside bet would you like to make:\n" +
                "( 1-STRAIGHT ) ( 2-SPLIT VERTICAL ) ( 3-STREET ) ( 4-CORNER )");
            switch(inside) {
                case("1"):
                    betType = betsAvailable.STRAIGHT;
                    break;
                case("2"):
                    betType = betsAvailable.SPLIT;
                    break;
                case("3"):
                    betType = betsAvailable.STREET;
                    break;
                case("4"):
                    betType = betsAvailable.CORNER;
                    break;
            }
        } else if (askBetType.equalsIgnoreCase("Outside")) {
            String outside = getString("Which type of outside bet would you like to make:\n" +
                "( 1-RED ) ( 2-BLACK ) ( 3-ODD ) ( 4-EVEN )\n" +
                    "( 5-LOW ) ( 6-HIGH ) ( 7-DOZEN ) ( 8-ROW )");
            switch(outside) {
                case("1"):
                    betType = betsAvailable.RED;
                    break;
                case("2"):
                    betType = betsAvailable.BLACK;
                    break;
                case("3"):
                    betType = betsAvailable.ODD;
                    break;
                case("4"):
                    betType = betsAvailable.EVEN;
                    break;
                case("5"):
                    betType = betsAvailable.LOW;
                    break;
                case("6"):
                    betType = betsAvailable.HIGH;
                    break;
                case("7"):
                    betType = betsAvailable.DOZEN;
                    break;
                case("8"):
                    betType = betsAvailable.ROW;
                    break;
            }
        }
        return betType;
    }

    private Double askForAmountBet() {
        Double amount = getDouble("How much would you like to wager?");
        return amount;
    }

    public boolean addToNumsBetOn(int number) {
        currentNumsBetOn.add(number);
        return true;
    }

    private boolean askForNextNumber() {
        int number = getInteger("Which number would you like to bet on?");
        return addToNumsBetOn(number);
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
            this.oddOrEven = "Neither odd, nor even";
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

    //check inside and outside bets against number from spin
    public boolean checkBets(betsAvailable bet, int number, ArrayList<Integer> array) {
        for (int numbers : array) {
            if (bet == betsAvailable.STRAIGHT && number == numbers) return true;
            if (bet == betsAvailable.SPLIT && number == numbers) return true;
            if (bet == betsAvailable.STREET && number == numbers) return true;
            if (bet == betsAvailable.CORNER && number == numbers) return true;
        }
        if (bet == betsAvailable.EVEN && this.oddOrEven.equals("Even")) return true;
        if (bet == betsAvailable.ODD && this.oddOrEven.equals("Odd")) return true;
        if (bet == betsAvailable.RED && this.currentColor.equals("Red")) return true;
        if (bet == betsAvailable.BLACK && this.currentColor.equals("Black")) return true;
        return false;
    }

    //calculate winnings/losses

    public void printSpinSummary(int number, String modValue, String color) {
        System.out.println(String.format("The lucky number was...%s!", number));
        System.out.println(String.format("%s is %s, and the color is %s.", number, modValue, color));
    }

    public boolean exitGame() {
        String askPlayAgain = getString("Would you like to play again? ( YES ) ( NO )");
        if (askPlayAgain.equalsIgnoreCase("No")) {
            playGame = false;
        }
        return playGame;
    }


}
