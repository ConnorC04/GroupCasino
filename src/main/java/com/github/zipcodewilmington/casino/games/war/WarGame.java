package com.github.zipcodewilmington.casino.games.war;

import java.util.*;


class WarGame {
    private List<Integer> deck;
    private Queue<Integer> playerDeck;
    private Queue<Integer> dealerDeck;
    private Scanner scanner;
    public WarGame() {
        scanner = new Scanner (System.in);
        deck = createdDeck();
        playerDeck = new LinkedList<>();
        dealerDeck = new LinkedList<>();
        dealCards();
    }
//creating 1 deck of 52 cards, values 2-14 and 2 to Ace
    private List<Integer> createdDeck() {
        List<Integer> deck = new ArrayList<>();
        for (int i = 2; i <= 14;i++ ){
            for(int j = 0; j <4; j++) {
                deck.add(i);
            }}
        Collections.shuffle(deck);
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
        System.out.println("Welcome to the war game!");
        System.out.println("Press enter to draw a card and play.");
        while ( !dealerDeck.isEmpty()) {
            System.out.println("Enter you card(2-14):");
            int playerCard = getUserCard();
           if (playerCard == -1) {
               System.out.println("Wrong Input");
           }
                //command for player input
            drawCard(playerCard);
        }
        System.out.println(declareWinner());
    }

    private int declareWinner() {
        return 0;
    }

    //get input
    private int getUserCard() {
        try {
            int card = Integer.parseInt(scanner.nextLine());
            if (card >= 2 && card <= 14) {
                return card;
            }
        } catch (NumberFormatException e)  {

        }
        return -1;
    }

    //draw cards for both
    private void drawCard(int playerCard) {
        if (dealerDeck.isEmpty()) return;

        int dealerCard = dealerDeck.poll();

        System.out.println("You Draw :" + cardName (playerCard));
        System.out.println("Dealer Draws:" + cardName(dealerCard));
        if (playerCard > dealerCard) {
            playerDeck.add(playerCard);
            playerDeck.add(dealerCard);
            System.out.println("Player wins!"); }
        else if  (dealerCard > playerCard) {
            dealerDeck.add(dealerCard);
            dealerDeck.add(playerCard);
            System.out.println("Dealer wins");
        } else { System.out.println ("It is a tie");

        }
        }
        //now making readable string
    private String cardName (int value) {
        switch (value) {
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            case 14:
                return "Ace";
            default:
                return
                        String.valueOf(value);
        }
    }

    //start the game
    public static void main (String [] args) {
        WarGame game = new WarGame();
        game.playGame();












    }



}
