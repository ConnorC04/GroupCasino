package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.Card;
import com.github.zipcodewilmington.casino.games.blackjack.BlackjackGame;
import com.github.zipcodewilmington.casino.games.blackjack.BlackjackPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {
    private BlackjackGame game;
    private BlackjackPlayer player;

    @BeforeEach
    public void setUp() {
        // Initialize the game and player with a balance
        game = new BlackjackGame();
        player = new BlackjackPlayer("Player1", "password123", 1000.0);
        // You might need to set up the player in the game if it's required
    }

    @Test
    public void handSinAces() {
        // Given: Hand without any Aces
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(Card.Rank.EIGHT, Card.Suit.CLUBS));
        hand.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        // When: Calculating hand value
        int value = game.handValue(hand);

        // Then: Assert the total sum of the hand
        assertEquals(19, value, "Hand value should sum without Ace adjustments.");
    }

    @Test
    public void handWithAces() {
        // Given: Hand with an Ace
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        hand.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        // When: Calculating hand value
        int value = game.handValue(hand);

        // Then: Assert the total sum with Ace adjustments
        assertEquals(12, value, "Hand value should sum with Ace adjustments.");
    }

    @Test
    public void testPlayerBust() {
        // Test to ensure player busts when total exceeds 21
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        playerHand.add(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));
        playerHand.add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
        game.getPlayerHand().clear();
        game.getPlayerHand().addAll(playerHand);

        boolean playerBusted = game.handValue(game.getPlayerHand()) > 21;
        assertTrue(playerBusted, "Player's hand should bust over 21.");
    }

    @Test
    public void testDealerBust() {
        // Test to ensure dealer busts when total exceeds 21
        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        dealerHand.add(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));
        dealerHand.add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
        game.getDealerHand().clear();
        game.getDealerHand().addAll(dealerHand);

        boolean dealerBusted = game.handValue(game.getDealerHand()) > 21;
        assertTrue(dealerBusted, "Dealer's hand should bust over 21.");
    }

//    @Test
//    public void testPlaceBet() {
//        // Test that a bet is placed correctly
//        double initialBalance = player.getAccountBalance();
//        double betAmount = 100.0;
//        boolean betPlaced = player.placeBet(betAmount);
//
//        assertTrue(betPlaced, "Bet should be placed successfully.");
//        assertEquals(initialBalance - betAmount, player.getAccountBalance(), "Player's account balance should decrease by the bet amount.");
//    }
    @Test
    public void walletDecreaseOnLoss(){
        double initialWallet =game.walletBalance();
        double betAmount=100.0;
        game.addOccurenceToWallet(-betAmount);

        double expected=initialWallet-betAmount;

        assertEquals(expected,game.walletBalance(),.01, "Wallet should decrease on a player loss");
    }
    @Test
    public void walletIncreaseOnWin(){
        double initialWallet =game.walletBalance();
        double betAmount=100.0;
        game.addOccurenceToWallet(betAmount*2);

        double expected=initialWallet+(betAmount*2);

        assertEquals(expected,game.walletBalance(),.01, "Wallet should increase on a player loss");
    }
    @Test
    public void testNoOverchargeOnLoss() {
        // Given
        BlackjackGame game = new BlackjackGame();
        double startingWallet = game.walletBalance();
        double betAmount = 20.0;

        // Simulate a bad loss
        game.addOccurenceToWallet(-50.0); // Remove more than 20 to simulate error

        // Then
        double amountLost = startingWallet - game.walletBalance();
        Assertions.assertTrue(amountLost <= betAmount,
                "Error: Player lost more than their bet, this should not happen!");
    }

}