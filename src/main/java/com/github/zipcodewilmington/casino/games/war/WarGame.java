package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.*;


public class WarGame implements GameInterface {
    //class variables
    private List<Integer> deck;
    private Queue<Integer> playerDeck; //FIFO
    private Queue<Integer> dealerDeck;
    private Scanner scanner;
    //constructor
    public WarGame() {
        scanner = new Scanner (System.in); //console
        deck = createdDeck(); // shuffle
        playerDeck = new LinkedList<>();//adding & removing cards from the beginning of the list
        dealerDeck = new LinkedList<>();
        dealCards();
    }
//creating 1 deck of 52 cards, values 2-14 and 2 to Ace
    public List<Integer> createdDeck() {
        List<Integer> deck = new ArrayList<>();
        for (int i = 2; i <= 14;i++ ) {    //iterates through card values 2-14
            for (int j = 0; j < 4; j++) {   // iterates 4 times through suits
                deck.add(i);
            }
        }
        Collections.shuffle(deck);//shuffles the deck randomly
        return deck;
            }
            //splitting the deck between player and computer (dealer)
    private void dealCards() {
        for(int i = 0; i < deck.size(); i++) {
            if (i % 2 == 0) {
                playerDeck.add(deck.get(i));
            }else{
                dealerDeck.add(deck.get(i));
            }
        }
    }
    //playing game until winner is decided and no more cards are left
    public void playGame() {
        System.out.println("Welcome King to the war game!");
        System.out.println("Press enter to draw a card and play.");
        while ( !dealerDeck.isEmpty()) {
            System.out.println("Enter you card(2-14):");
            int playerCard = getUserCard(); //here asking for user input
           if (playerCard == -1) {
               System.out.println("I think I told you to put numbers from 2-14 so do that");
           }
                //command for player input
            drawCard(playerCard);
        }
        System.out.println(declareWinner());
    }

    private int declareWinner() {
        return 0;
    }

    //get input and handles errors
    private int getUserCard() {
        try {
            int card = Integer.parseInt(scanner.nextLine());
            if (card >= 2 && card <= 14) {
                return card;
            }
        } catch (NumberFormatException e) //something that cannot be converted to integer
        {

        }
        return -1;
    }

    //draw cards for both
    private void drawCard(int playerCard) {
        if (dealerDeck.isEmpty()) return;

        int dealerCard = dealerDeck.poll(); //using fifo

        System.out.println("You Draw :" + cardName (playerCard));
        System.out.println("Dealer Draws:" + cardName(dealerCard));
        if (playerCard > dealerCard) {
            playerDeck.add(playerCard);
            playerDeck.add(dealerCard);
            System.out.println("you won but don't be overconfident!"); }
        else if  (dealerCard > playerCard) {
            dealerDeck.add(dealerCard);
            dealerDeck.add(playerCard);
            System.out.println("You lost the game boo!");
        } else { System.out.println ("It is a tie");

        }
        }
        //now making readable string
    private String cardName (int value) {
        return switch (value) {
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Ace";
            default -> String.valueOf(value);
        };
    }

    //start the game
    @Override
    public void run () {
        WarGame game = new WarGame();
        game.playGame();
    }


    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }
}
