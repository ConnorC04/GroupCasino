package com.github.zipcodewilmington.casino.games.gofish;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GoFishGame {

    DeckOfCards newDeck = new DeckOfCards();
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    boolean playerTurn = true;
    boolean dealerTurn = false;
    int currentCard;
    ArrayList<String> playerHand = new ArrayList<>();
    ArrayList<String> dealerHand = new ArrayList<>();
    ArrayList<String> drawStack = new ArrayList<>();
    ArrayList<String> currentDeck = new ArrayList<>();

    public void startGame() {
        currentDeck = newDeck.createDeck();
        System.out.println(newDeck.getRank());
        System.out.println(newDeck.shuffleDeck(currentDeck));
        dealDeck();
        printPlayerHand();
        playerTurns();
    }

    public String getUserInput(String string) {
        System.out.println(string);
        return sc.nextLine();
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

    public void printPlayerHand() {
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(playerHand.get(i));
        }

    }

    public void playerTurns() {
        while (playerTurn) {
            //player asks for number
            String userInput = getUserInput("What card do you want from dealer");
            for (int i = 0; i < dealerHand.size(); i++) {
                if (Objects.equals(dealerHand.get(i).split("")[0], userInput)) {
                    System.out.println(dealerHand.get(i));
                }
            }
        }
    }


    public static void main(String[] args) {
        GoFishGame gf = new GoFishGame();
        gf.startGame();
    }

    //initialize game


    //make the card deck
//   DeckOfCards card = new DeckOfCards(11,1);

    //    public void createDeck(){
//        int index = 0;
//        for (int i = 0; i <= 3; i++) {
//            for (int j = 0; j <= 13; j++) {
//                card[i] = new Card(rank, suit);
//                index++;
//            }
//
//        }
//    }

    //shuffle the deck
    public void shuffleDeck() {

    }
}

