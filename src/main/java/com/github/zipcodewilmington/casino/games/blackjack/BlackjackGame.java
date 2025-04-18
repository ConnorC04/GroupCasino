package com.github.zipcodewilmington.casino.games.blackjack;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.Hand;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.Card;

import java.awt.*;
import java.util.*;


public class BlackjackGame implements GameInterface {
    private ArrayList<Card> deck;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealerHand;
    private Scanner input;
    private double currentBet;
    private double wallet;


    public BlackjackGame() {
        input = new Scanner(System.in);
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        Random random;
        intitializeDeck();
        this.wallet=1000.0;
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            BlackjackGame game = new BlackjackGame();
            game.playBlackJack();

            // Ask if the player wants to play again
            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                playAgain = true;
            } else if (response.equals("no")) {
                playAgain=false;
                System.out.println("Thank you for playing.");
            }else{
                System.out.println("Please enter 'yes' or 'no': ");
                scanner.nextLine();}
        }
        scanner.close();
    }

    private void intitializeDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank, suit));
            }

        }
        Collections.shuffle(deck, new Random());
    }

    public int handValue(ArrayList<Card> hand) {
        //checks to hand for amount of aces
        int total = 0;
        int aceCount = 0;
        for (Card card : hand) {
            total += card.getValue();
            //counts the amount of aces in the players hand
            if (card.rank == Card.Rank.ACE) {
                aceCount++;
            }
        }
        //If ace is in hand and the hand total is greater than 21, amount will be adjusted.
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }

    public void playBlackJack() {
        intro();
        placeBet();
        //Drawing cards
        playerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        dealerHand.add(drawCard());

        //total of cards in hand
        int playerTotal = handValue(playerHand);
        int dealerTotal = handValue(dealerHand);

        //shows one of the dealers cards
        System.out.println("Dealer is showing: " + dealerHand.get(0) + ", ?");
        // shows player cards
        System.out.println("Your hand is: " + playerHand + playerHand + " your total: " + playerTotal);

        if (dealerTotal == 21) {
            System.out.println("Dealer hand: " + dealerHand + "Total: 21");
            System.out.println("Dealer has Blackjack!");

            if (playerTotal == 21) {
                System.out.println("It's a push!");
            } else {
                System.out.println("Dealer wins. . .");
                addOccurenceToWallet(-currentBet);
                System.out.println("You have "+ walletBalance()+ " left to play with");
            }
            return;
        }
        while (playerTotal < 21) {
            String decision = userInput();

            if (decision.equals("hit")) {
                Card newCard = drawCard();
                playerHand.add(newCard);
                playerTotal = handValue(playerHand);
                System.out.println("You drew: " + newCard);
                System.out.println("New total = " + playerTotal);

                if (playerTotal > 21) {
                    //If player is over 21 they lose and the loop ends
                    System.out.println("Bust! Over 21. Dealer wins.");
                    addOccurenceToWallet(-currentBet);

                    return;
                }
            } else if (decision.equals("stay")) {
                break;
            }
            walletBalance(); // Show the updated balance

        }
        System.out.println("Dealer reveals their hand " + dealerHand + "Total: " + dealerTotal);

        while (dealerTotal < 17) {  // Dealer must draw until their total is at least 17
            Card newCard = drawCard();
            dealerHand.add(newCard);
            dealerTotal = handValue(dealerHand);
            //draw cards after hit
            System.out.println("Dealer drew: " + newCard);
            System.out.println("New total = " + dealerTotal);

            if (dealerTotal > 21) {
                System.out.println("Dealer busts! You win."+currentBet*2);
                addOccurenceToWallet(currentBet);
                return;
            }
            walletBalance();

        }

        // Determine winner
        if (playerTotal > dealerTotal) {
            //when player total is <=21 && more than dealer total
            addOccurenceToWallet(currentBet*2);
            walletBalance(); // Show the updated balance

        } else if (playerTotal < dealerTotal) {
            addOccurenceToWallet(-currentBet);
            walletBalance();
        }
           else{System.out.println("Push, keep your bet.");//give money back
            addOccurenceToWallet(currentBet);
            walletBalance();

        }
        walletBalance();

    }

    private void intro() {
        System.out.println("Welcome to BlackJack!");
    }

    public Card drawCard() {
        Random random = new Random();
        return deck.get(random.nextInt(deck.size()));
    }

    private void blackJack() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            BlackjackGame game = new BlackjackGame();
            game.playBlackJack();

            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("no")) {
                playAgain = false;
                System.out.println("Thanks for playing! Goodbye (Door slamming sound plays.");
            }else{
                System.out.println("Please type 'yes' or 'no': ");}
                scanner.nextLine();
        }
        scanner.close();
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
    private void placeBet() {
        while (true) {
            System.out.print("Enter your bet amount: $");
            try {
                double betAmount = Double.parseDouble(input.nextLine().trim());

                if (betAmount <= 0 ){
                    System.out.println("Bet must be greater than zero");
                } else if (wallet < betAmount) {
                    System.out.println("Bet must be greater than zero, take your monopoly money somewhere else.");
                } else {
                        wallet-=betAmount;
                        currentBet = betAmount;
                    System.out.println("\n bet placed : $");
                        break;
                    }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");        }
    }
}

    public void addOccurenceToWallet(double amount){
        wallet+=Math.abs(amount);
        //added statement here so wallet balance is adjusted every hand
        //don't need to manually add statements after every hand
        System.out.println("Your new wallet balance is :$"+ walletBalance());
    }
    public double walletBalance(){
        return wallet;

    }




    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

}
