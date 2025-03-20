package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.Card;
import com.github.zipcodewilmington.casino.games.blackjack.BlackjackGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {
    private BlackjackGame game;

    @BeforeEach
    public void setUp() {
        game = new BlackjackGame();

    }

    @Test
    public void handSinAces() {
        // Given:
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(Card.Rank.EIGHT, Card.Suit.CLUBS));
        hand.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        // When:
        int value = game.handValue(hand);

        // Then
        assertEquals(19, value, "Hand value should sum without Ace adjustments");
    }
    @Test
    public void handWithAces() {
        // Given
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        hand.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        // WHEN
        int value = game.handValue(hand);

        //then
        assertEquals(12, value, "Hand value should sum with Ace adjustments");
    }

    @Test
    public void testPlayerBust() {
        // Test to ensure player busts when total exceeds 21
        game.getPlayerHand().clear();
        game.getPlayerHand().add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        game.getPlayerHand().add(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));
        game.getPlayerHand().add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));

        boolean playerBusted = game.handValue(game.getPlayerHand()) > 21;
        assertTrue(playerBusted, "Player's hand should bust over 21.");
    }

    @Test
    public void testDealerBust() {
        // Test to ensure dealer busts when total exceeds 21
        game.getDealerHand().clear();
        game.getDealerHand().add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        game.getDealerHand().add(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));
        game.getDealerHand().add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));

        boolean dealerBusted = game.handValue(game.getDealerHand()) > 21;
        assertTrue(dealerBusted, "Dealer's hand should bust over 21.");
    }
}