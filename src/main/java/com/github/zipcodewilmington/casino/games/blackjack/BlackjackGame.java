package com.github.zipcodewilmington.casino.games.blackjack;


import java.util.Random;
import java.util.Scanner;

public class BlackjackGame {
    private int dealerCard1;
    private int dealerCard2;
    private int playerCard1;
    private int playerCard2;
    private int playerHand;
    private int dealerHand;
    private Scanner input;
    private Random random;
    private Integer[] deck;

    public BlackjackGame() {
        input = new Scanner(System.in);
        random = new Random();
        deck = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11}; // 11 represents Ace
    }

    public void playBlackJack() {
        intro();
        //Drawing cards
        dealerCard1= drawCard();
        dealerCard2= drawCard();
        dealerHand = dealerCard1+dealerCard2;

        playerCard1= drawCard();
        playerCard2= drawCard();
        playerHand = (playerCard1+playerCard2);

        System.out.println("Dealer is showing: " + dealerCard1+", ?");
        System.out.println("Your hand is: " +playerCard1+playerCard2+ " your total: "+playerHand);








    }



    private void intro() {
        System.out.println("Welcome to BlackJack!");
    }

    public int drawCard() {
        return deck[random.nextInt(deck.length)]; // Draws a random card from the deck
    }

    private String userInput() {
        while (true) {
            System.out.print("Would you like to Hit or Stay? Type 'Hit' or 'Stay': ");
            String decision = input.nextLine().trim().toLowerCase();

            if (decision.equals("hit") || decision.equals("stay")) {
                return decision;
            } else {
                System.out.println("Invalid input. Please type 'Hit' or 'Stay'.");
            }
        }
    }

}