package com.github.zipcodewilmington.casino.games.blackjack;
import com.github.zipcodewilmington.casino.Hand;
import com.github.zipcodewilmington.casino.games.Card;

import java.awt.*;
import java.util.*;


public class BlackjackGame {
    private ArrayList<Card> deck;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealerHand;
    private Scanner input;


    public BlackjackGame() {
        input = new Scanner(System.in);
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        Random random;
        intitializeDeck();
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        boolean playAgain = true;
//
//        while (playAgain) {
//            BlackjackGame game = new BlackjackGame();
//            game.playBlackJack();
//
//            // Ask if the player wants to play again
//            System.out.print("\nDo you want to play again? (yes/no): ");
//            String response = scanner.nextLine().trim().toLowerCase();
//
//            if (!response.equals("yes")) {
//                playAgain = false;
//                System.out.println("Thanks for playing! Goodbye (Door slamming sound plays.");
//            }
//        }
//        scanner.close();
//    }

    private void intitializeDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank, suit));
            }

        }
        Collections.shuffle(deck, new Random());
    }

    public int handValue(ArrayList<Card> hand) {
        int total = 0;
        int aceCount = 0;
        for (Card card : hand) {
            total += card.getValue();
            if (card.rank == Card.Rank.ACE) {
                aceCount++;
            }
        }
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }

    public void playBlackJack() {
        intro();
        //Drawing cards
        playerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        dealerHand.add(drawCard());


        int playerTotal = handValue(playerHand);
        int dealerTotal = handValue(dealerHand);


        System.out.println("Dealer is showing: " + dealerHand.get(0) + ", ?");

        System.out.println("Your hand is: " + playerHand + playerHand + " your total: " + playerTotal);

        if (dealerTotal == 21) {
            System.out.println("Dealer hand: " + dealerHand + "Total: 21");
            System.out.println("Dealer has Blackjack!");

            if (playerTotal == 21) {
                System.out.println("It's a push!");
            } else {
                System.out.println("Dealer wins. . .");
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
                    System.out.println("Bust! Over 21. Dealer wins.");
                    break;
                }
            } else if (decision.equals("stay")) {
                break;
            }
        }
        System.out.println("Dealer reveals their hand " + dealerHand + "Total: " + dealerTotal);

        while (dealerTotal < 17) {  // Dealer must draw until their total is at least 17
            Card newCard = drawCard();
            dealerHand.add(newCard);
            dealerTotal = handValue(dealerHand);

            System.out.println("Dealer drew: " + newCard);
            System.out.println("New total = " + dealerTotal);

            if (dealerTotal > 21) {
                System.out.println("Dealer busts! You win.");
                return;  // Ends game if dealer busts
            }
        }

        // Determine winner
        if (playerTotal > dealerTotal) {
            System.out.println("Winner, winner, chicken dinner!");//add in currentbet*2
        } else if (playerTotal < dealerTotal) {
            System.out.println("Loser, loser, pay up!");//subtract bet from total
        } else {
            System.out.println("Push, keep your bet.");//give money back
        }
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

            if (!response.equals("yes")) {
                playAgain = false;
                System.out.println("Thanks for playing! Goodbye (Door slamming sound plays.");
            }
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

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

}
//private void placeBet(){
//    while(true){
//        System.out.println("Enter your bet amount: $");
//        currentBet=Double.parseDouble(input.nextline());
//        if(currentBet>playerBalance){
//            System.out.println("Get your money up, not your funny up");
//        }else if (currentBet<=0){
//            System.out.println("Bet must be greater than zero, take your monopoly money somewhere else.");
//        }else{
//            break;
//        }
//    }
//}