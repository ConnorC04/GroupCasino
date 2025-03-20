package com.github.zipcodewilmington.casino.games.gofish;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GoFishGame {

    DeckOfCards newDeck = new DeckOfCards();
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    boolean playersTurn = true;
    boolean dealersTurn = false;
    int player4KingCount;
    int dealer4KingCount;
    ArrayList<String> playerHand = new ArrayList<>();
    ArrayList<String> dealerHand = new ArrayList<>();
    ArrayList<String> drawStack = new ArrayList<>();
    ArrayList<String> currentDeck = new ArrayList<>();

    public void startGame() {
        currentDeck = newDeck.createDeck();
//        System.out.println(newDeck.getRank());
//        System.out.println(newDeck.shuffleDeck(currentDeck));
        dealDeck();
        printHand(playerHand);
        playerTurns();
        dealerTurns();
    }



    public String getUserInput(String string) {
        System.out.println(string);
        return sc.nextLine();
    }

    public void updatePlayer(String string) {
        System.out.println(string);
    }

    //give 7 cards to each player and bank the rest.
    public void dealDeck() {
        for (int i = 0; i < currentDeck.size(); i++) {
            if (i < 7) {
                playerHand.add(currentDeck.get(i));
            } else if (i < 14) {
                dealerHand.add(currentDeck.get(i));
            } else {
                drawStack.add(currentDeck.get(i));
            }
        }


    }

    public void printHand(ArrayList<String> currentHand) {
        for (int i = 0; i < currentHand.size(); i++) {
            System.out.println("|" + currentHand.get(i) + " | ");
        }

    }

    public void playerTurns() {
        while (playersTurn) {
            //player asks for number
            String userInput = getUserInput("What card do you want from dealer").toLowerCase();
            int count = 0;
            for (int i = 0; i < dealerHand.size(); i++) {
               // System.out.println(dealerHand.get(i).split(" ")[0]);
                if (Objects.deepEquals(dealerHand.get(i).split("")[0].toLowerCase(), userInput)) {
                    playerHand.add(dealerHand.get(i));
                    dealerHand.remove(dealerHand.get(i));
                    count++;
                }

            }
            player4KingCount = countFourOfAKind(dealerHand, userInput)? player4KingCount++ : player4KingCount;
            updatePlayer("you got " + count + " Cards from dealer");
            if (count== 0){
                playersTurn = false;
                dealersTurn = true;
                updatePlayer("No match - go Fish");
                goFish("player");
                dealerTurns();
                break;
            }
          count = 0;
        }

    }

    private void dealerTurns() {

        while (dealersTurn) {
            String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "Jack", "Queen", "King"};
            int num = rand.nextInt(ranks.length);
            String stringToMatch = ranks[num];
            //dealer asks for number
            updatePlayer("Dealer is asking for an " + stringToMatch + " card ");
            String userInput = getUserInput("[1] - Yes, [2] - No");
            int count = 0;
            if (userInput.equals("1")) {
                userInput = getUserInput("How many " + stringToMatch + " cards are you giving up?");
                count = Integer.parseInt(userInput);
                for (int i = 0; i < playerHand.size(); i++) {
                    if (Objects.equals(playerHand.get(i).split("")[0].toLowerCase(), stringToMatch.toLowerCase())) {
                        dealerHand.add(playerHand.get(i));
                        playerHand.remove(playerHand.get(i));
                    }
                }
                countFourOfAKind(dealerHand, stringToMatch);
                updatePlayer("you got " + count + " Cards from player");
                if (count == 0) {
                    dealersTurn = false;
                    playersTurn = true;
                    updatePlayer("No match - go Fish");
                    goFish("dealer");
                    playerTurns();
                    break;
                }
            }
        }
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
//        printHand(playerHand);
//        printHand(dealerHand);
    }

    public boolean countFourOfAKind(ArrayList<String> array, String string) {
        int count = 0;
        for (int i = 0; i < array.size(); i++) {
            if (Objects.equals(array.get(i).split("")[0].toLowerCase(), string.toLowerCase())) {
                count++;
            }
        }
        return count == 4;
    }


    public static void main(String[] args) {
        GoFishGame gf = new GoFishGame();
        gf.startGame();
    }

}

