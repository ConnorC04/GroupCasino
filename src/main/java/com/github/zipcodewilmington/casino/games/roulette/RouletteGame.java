package com.github.zipcodewilmington.casino.games.roulette;
import java.util.*;


public class RouletteGame {
    private static Random random = new Random(); //var from superclass casino needed? - casinoWallet
    private static Scanner scan = new Scanner(System.in);
    private boolean playGame = true;
    private boolean addBets = true;
    private int currentSpinVal;
    private String currentColor;
    private String oddOrEven;
    //private HashMap<BetsAvailable, Double> allBetsMade = new HashMap<>(); //need to decide what type of hashmap would be best for storing bets
    private HashMap<BetsAvailable, Double> betOdds = new HashMap<>();
    //private ArrayList<Integer> currentNumsBetOn = new ArrayList<>();
    private BetsAvailable currentBet;
    private int currentNumBetOn;
    private double currentAmtBet;
    public enum BetsAvailable {STRAIGHT, SPLIT, STREET, CORNER, RED, BLACK, ODD, EVEN, LOW, HIGH, DOZEN1, ROW1};

    private Double betAmount;
    private String rouletteTable = "   |        1-12       |       13-24       |      25--36  \n" +
            "   |  3 |  6 |  9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | ROW1 1\n" +
            " 0 |  2 |  5 |  8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | ROW1 2\n" +
            "   |  1 |  4 |  7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | ROW1 3\n" +
            "   |LOW  1-18|  EVEN   |  REDS   |  BLACK  |  ODDS   |19-36 HIGH|";

    public RouletteGame() {
        betOdds.put(BetsAvailable.STRAIGHT, 35.0);
        betOdds.put(BetsAvailable.SPLIT, 17.0);
        betOdds.put(BetsAvailable.STREET, 11.0);
        betOdds.put(BetsAvailable.CORNER, 8.0);
        betOdds.put(BetsAvailable.RED, 1.0);
        betOdds.put(BetsAvailable.BLACK, 1.0);
        betOdds.put(BetsAvailable.ODD, 1.0);
        betOdds.put(BetsAvailable.EVEN, 1.0);
        betOdds.put(BetsAvailable.LOW, 1.0);
        betOdds.put(BetsAvailable.HIGH, 1.0);
        betOdds.put(BetsAvailable.DOZEN1, 2.0);
        betOdds.put(BetsAvailable.ROW1, 2.0);
    }

    public void runGame() {
        welcomeMessage();
        while (playGame) {
            //allBetsMade.clear();
            //currentNumsBetOn.clear();
            printTableLayout();
            while (addBets) {
                askForBets();
                askForAmountBet();
                askForNextNumber();
                askToConfirmBets();
            }
            spinWheel();
            printSpinSummary(this.currentSpinVal, this.oddOrEven, this.currentColor);
            checkBets(this.currentBet, this.currentNumBetOn);//check all bets made against number from wheel
            calcChanges(this.currentSpinVal);
            exitGame();
        }
    }

    private String getString(String message) {
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

    private double getDouble(String message) {
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

    private int getInteger(String message) {
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

    public boolean makeBet(BetsAvailable bet, double amount) {
        //allBetsMade.put(bet, amount);
        currentBet = bet;
        currentAmtBet = amount;
        return true;
    }

    public boolean askForBets() {
        BetsAvailable bet = this.askWhichBet();
        Double amount = this.askForAmountBet();
        return makeBet(bet, amount);
    }

    public BetsAvailable askWhichBet() {
        String askBetType = getString("What kind of bet would you like to make: ( INSIDE ) ( OUTSIDE )");
        BetsAvailable betType = null;
        if (askBetType.equalsIgnoreCase("Inside")) {
            String inside = getString("Which type of inside bet would you like to make:\n" +
                "( 1-STRAIGHT ) ( 2-SPLIT VERTICAL ) ( 3-STREET ) ( 4-CORNER )");
            switch(inside) {
                case("1"):
                    betType = BetsAvailable.STRAIGHT;
                    break;
                case("2"):
                    betType = BetsAvailable.SPLIT;
                    break;
                case("3"):
                    betType = BetsAvailable.STREET;
                    break;
                case("4"):
                    betType = BetsAvailable.CORNER;
                    break;
            }
        } else if (askBetType.equalsIgnoreCase("Outside")) {
            String outside = getString("Which type of outside bet would you like to make:\n" +
                "( 1-RED ) ( 2-BLACK ) ( 3-ODD ) ( 4-EVEN )\n" +
                    "( 5-LOW ) ( 6-HIGH ) ( 7-DOZEN1 ) ( 8-ROW1 )");
            switch(outside) {
                case("1"):
                    betType = BetsAvailable.RED;
                    break;
                case("2"):
                    betType = BetsAvailable.BLACK;
                    break;
                case("3"):
                    betType = BetsAvailable.ODD;
                    break;
                case("4"):
                    betType = BetsAvailable.EVEN;
                    break;
                case("5"):
                    betType = BetsAvailable.LOW;
                    break;
                case("6"):
                    betType = BetsAvailable.HIGH;
                    break;
                case("7"):
                    betType = BetsAvailable.DOZEN1;
                    break;
                case("8"):
                    betType = BetsAvailable.ROW1;
                    break;
            }
        }
        return betType;
    }

    public Double askForAmountBet() {
        Double amount = getDouble("How much would you like to wager?");
        return amount;
    }

    public boolean addToNumsBetOn(int number) {
        //currentNumsBetOn.add(number);
        currentNumBetOn = number;
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

    public double getBetOdds(BetsAvailable bet) {
        return betOdds.get(bet);
    }

    //check inside and outside bets against number from spin
    public boolean checkBets(BetsAvailable bet, int guess) {
        if (bet == BetsAvailable.STRAIGHT && this.currentSpinVal == guess) return true;
        if (bet == BetsAvailable.SPLIT && this.currentSpinVal == guess) return true;
        if (bet == BetsAvailable.STREET && this.currentSpinVal == guess) return true;
        if (bet == BetsAvailable.CORNER && this.currentSpinVal == guess) return true;
        if (bet == BetsAvailable.EVEN && this.oddOrEven.equals("Even")) return true;
        if (bet == BetsAvailable.ODD && this.oddOrEven.equals("Odd")) return true;
        if (bet == BetsAvailable.RED && this.currentColor.equals("Red")) return true;
        if (bet == BetsAvailable.BLACK && this.currentColor.equals("Black")) return true;
        if (bet == BetsAvailable.DOZEN1 && this.currentSpinVal < 13) return true;
        if (bet == BetsAvailable.ROW1 && this.currentSpinVal % 3 == 0) return true;
        return false;
    }

    //calculate winnings/losses

    public Double calcWinnings(BetsAvailable bet, Double amount) {
        return (this.getBetOdds(bet) * amount);

    }

    private Double calcChanges(int randNum) {
        Double total = 0.0;
        if (this.checkBets(this.currentBet, this.currentSpinVal)) {
            total += this.calcWinnings(this.currentBet, this.currentAmtBet);
        } else {
            total -= this.currentAmtBet;
        }
//        for (Map.Entry<Enum, Double> entry : allBetsMade.entrySet()) {
//            Enum bet = entry.getKey();
//            Double amount = entry.getValue();
//            int guess = this.currentNumBetOn;
//            if (this.checkBets((BetsAvailable) bet, guess)) {
//                total += this.calcWinnings(bet, amount);
//            } else {
//                total -= amount;
//            }
//        }
        return total;
    }

    private void printSpinSummary(int number, String modValue, String color) {
        System.out.println(String.format("The lucky number was...%s!", number));
        System.out.println(String.format("%s is %s, and the color is %s.", number, modValue, color));
    }

    private boolean exitGame() {
        String askPlayAgain = getString("Would you like to play again? ( YES ) ( NO )");
        if (askPlayAgain.equalsIgnoreCase("No")) {
            playGame = false;
        }
        return playGame;
    }


}
