package com.github.zipcodewilmington.casino.games.gofish;


import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.Deck;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.*;

public class GoFishGame implements GameInterface {

    //DeckOfCards newDeck = new DeckOfCards();
    Deck newDeck = new Deck();
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);
    private boolean playersTurn = true;
    private boolean dealersTurn = false;
    private int player4KindCount;
    private int dealer4KindCount;
    private boolean gameRunning = false;
    private ArrayList<String> playerHand = new ArrayList<>();
    private ArrayList<String> dealerHand = new ArrayList<>();
    private ArrayList<String> drawStack = new ArrayList<>();
    private ArrayList<String> currentDeck = new ArrayList<>();

    public GoFishGame() {
        // System.out.println(newDeck.size());
        newDeck.shuffle();
        for (int i = 0; i < 52; i++) {
            currentDeck.add(String.valueOf(newDeck.drawCard()));
        }
        //System.out.println(currentDeck.toString());
    }

    public GoFishGame(ArrayList<String> array) {
        currentDeck = array;
    }

    public ArrayList<String> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<String> getDealerHand() {
        return dealerHand;
    }

    public ArrayList<String> getDrawStack() {
        return drawStack;
    }

    public void startGame() {
        gameRunning = true;
        dealDeck();
        printHand(playerHand);
        playerTurns();
        //dealerTurns();
//        if (currentDeck.isEmpty()) {
//            winningHand();
//        }
    }

    public String getUserInput(String string) {
        System.out.println(string);
        return sc.nextLine();
    }

    public void updatePlayer(String string) {
        System.out.println(string);
    }

    public boolean isUserInputAllowed(String userInput) {
        if (userInput.equals("exit")) {
            updatePlayer("Thanks for Playing");
            gameRunning = false;
            return true;
        }
        for (int stringIndex = 0; stringIndex < currentDeck.size(); stringIndex++) {
            if (userInput.equals(currentDeck.get(stringIndex).split(" ")[0].toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    //give 7 cards to each player and bank the rest.
    public void dealDeck() {
        for (int i = 0; i < currentDeck.size(); i++) {
            if (i < 7) {
                dealerHand.add(currentDeck.get(i));
            } else if (i < 14) {
                playerHand.add(currentDeck.get(i));
            } else {
                drawStack.add(currentDeck.get(i));
            }
        }
    }

    public void printHand(ArrayList<String> currentHand) {
        for (int i = 0; i < currentHand.size(); i++) {
            System.out.println("|" + currentHand.get(i) + " | ");
            playerHand.get(i).split(" ")[0].toLowerCase();
        }
    }

    public int checkDealerDeckForCard(String userInput) {
        int count = 0;
        ArrayList<String> copyOfHandToCompare = new ArrayList<>(dealerHand);
        for (int i = 0; i < copyOfHandToCompare.size(); i++) {
            if (Objects.equals(copyOfHandToCompare.get(i).split(" ")[0].toLowerCase(), userInput)) {
                playerHand.add(copyOfHandToCompare.get(i));
                dealerHand.remove(copyOfHandToCompare.get(i));
                count++;
            }
        }
        return count;
    }

    public int checkPlayerDeckForCard(String userInput) {
        int count = 0;
        ArrayList<String> copyOfHandToCompare = new ArrayList<>(playerHand);
        for (int i = 0; i < copyOfHandToCompare.size(); i++) {
            if (Objects.equals(copyOfHandToCompare.get(i).split(" ")[0].toLowerCase(), userInput)) {
                dealerHand.add(copyOfHandToCompare.get(i));
                playerHand.remove(copyOfHandToCompare.get(i));
                count++;
            }
        }
        return count;
    }

    public void playerTurns() {
        while (playersTurn && gameRunning) {
            //player asks for number
            String userInput = getUserInput("What card do you want from dealer").toLowerCase();
            if (!isUserInputAllowed(userInput)) {
                updatePlayer("Error: Invalid Card");
                continue;
            }
            int count = 0;
            count = checkDealerDeckForCard(userInput);
            if (isFourOfAKind(playerHand, userInput)) {
                removeFourKindFromHand(playerHand, userInput);
                player4KindCount++;
            }
            updatePlayer("You got " + count + " Cards from dealer");
            if (count == 0) {
                //endGame("player");
                playersTurn = false;
                dealersTurn = true;
                updatePlayer("No match - player goes Fishing");
                goFish("player");
                dealerTurns();
                break;
            }
            System.out.println(dealerHand);
            System.out.println(playerHand);
            endGame();
        }
    }

    private void dealerTurns() {
        while (dealersTurn && gameRunning) {
            String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Jack", "Queen", "King"};
            int num = rand.nextInt(ranks.length);
            String stringToMatch = ranks[num].toLowerCase();
            //dealer asks for number
            updatePlayer("Dealer is asking for an " + stringToMatch + " card ");
            String userInput = getUserInput("[1] - Yes, [2] - No");
            int count = 0;
            try {
                if (userInput.equals("1")) {
                    count = checkPlayerDeckForCard(stringToMatch);
                    if (isFourOfAKind(dealerHand, stringToMatch)) {
                        removeFourKindFromHand(dealerHand, stringToMatch);
                        dealer4KindCount++;
                    }
                    updatePlayer("You got " + count + " Cards from player ");
                } else if (userInput.equals("2")) {
                    dealersTurn = false;
                    playersTurn = true;
                    updatePlayer("No match - goes Fishing");
                    goFish("dealer");
                    playerTurns();
                    break;
                }
            } catch (IllegalArgumentException e) {
                updatePlayer("Error: " + e.getMessage());
            }
            System.out.println(dealerHand);
            System.out.println(playerHand);
            endGame();
        }
    }

    public void removeFourKindFromHand(ArrayList<String> array, String string) {
        ArrayList<String> copyOfHandToCompare = new ArrayList<>(array);
        for (int i = 0; i < copyOfHandToCompare.size(); i++) {
            if (Objects.equals(copyOfHandToCompare.get(i).split(" ")[0].toLowerCase(), string)) {
                array.remove(copyOfHandToCompare.get(i));
            }
        }
    }


    public void winningHand() {
        if (player4KindCount > dealer4KindCount) {
            System.out.println("Dealer Wins");
        } else if (player4KindCount < dealer4KindCount) {
            System.out.println("Player Wins");
        } else {
            System.out.println("Look like a tie");
        }
    }

    public void endGame() {
        if (playerHand.isEmpty() || dealerHand.isEmpty() || drawStack.isEmpty()) {
            winningHand();
            String userInput = getUserInput("Do you want to keep playing? [1]- Yes [2]- No");
            if (userInput.equals("1")) {
                resetGame();
            } else if (userInput.equals("2")) {
                updatePlayer("Thanks for Playing");
                gameRunning = false;
            } else {
                updatePlayer("Wrong button, exit game:");
                gameRunning = false;
            }
        }
    }

    public void resetGame() {
        playerHand.clear();
        dealerHand.clear();
        drawStack.clear();
        player4KindCount = 0;
        dealer4KindCount = 0;

        GoFishGame gf = new GoFishGame();
        gf.run();

    }


    public void goFish(String s) {
        if (s.equals("player")) {
            playerHand.add(drawStack.get(0));
            drawStack.remove(drawStack.get(0));
        }
        if (s.equals("dealer")) {
            dealerHand.add(drawStack.get(0));
            drawStack.remove(drawStack.get(0));
        }
//        System.out.println(playerHand);
//        System.out.println(dealerHand);
    }

    public boolean isFourOfAKind(ArrayList<String> array, String string) {
        int count = 0;
        for (int i = 0; i < array.size(); i++) {
            if (Objects.equals(array.get(i).split(" ")[0].toLowerCase(), string.toLowerCase())) {
                count++;
            }
        }
        return count == 4;
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {

        GoFishGame gf = new GoFishGame();
        gf.updatePlayer("Welcome to Go - Fish");
        gf.startGame();


//        else {
//            updatePlayer("Game is already in progress");
//        }

    }
}

