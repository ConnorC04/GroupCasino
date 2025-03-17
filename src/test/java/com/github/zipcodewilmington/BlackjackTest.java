package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.blackjack.BlackjackGame;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlackjackTest {
    private BlackjackGame game;
    @BeforeEach
    public void setUp(){
        game= new BlackjackGame();
    }
    @Test
    public void testDrawCardReturnsValidValue() {
        BlackjackGame game = new BlackjackGame();
        int card = game.drawCard();

        // Deck contains values 2-10 (including face cards represented as 10)
        Integer[] validCards = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        assertTrue(java.util.Arrays.asList(validCards).contains(card),
                "Drawn card should be within the valid deck values");
    }

    @Test
    public void testDealerTotalInValidRange() {
        BlackjackGame game = new BlackjackGame();

        int dealerCard1 = game.drawCard();
        int dealerCard2 = game.drawCard();
        int dealerTotal = dealerCard1 + dealerCard2;

        assertTrue(dealerTotal >= 4 && dealerTotal <= 20,
                "Dealer total should be between 4 and 20");
    }

    @Test
    public void testPlayerTotalInValidRange() {
        BlackjackGame game = new BlackjackGame();

        int playerCard1 = game.drawCard();
        int playerCard2 = game.drawCard();
        int playerTotal = playerCard1 + playerCard2;

        assertTrue(playerTotal >= 4 && playerTotal <= 20,
                "Player total should be between 4 and 20");
    }

}
