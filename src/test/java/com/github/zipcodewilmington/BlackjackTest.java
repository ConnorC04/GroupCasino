package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.blackjack.BlackjackGame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        Integer[] validCards = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        assertTrue(java.util.Arrays.asList(validCards).contains(card));
    }

    @Test
    public void testDealerTotalInValidRange() {
        BlackjackGame game = new BlackjackGame();

        int dealerCard1 = game.drawCard();
        int dealerCard2 = game.drawCard();
        int dealerTotal = dealerCard1 + dealerCard2;

        assertTrue(dealerTotal >= 4 && dealerTotal <= 20);
    }

    @Test
    public void testPlayerTotalInValidRange() {
        BlackjackGame game = new BlackjackGame();

        int playerCard1 = game.drawCard();
        int playerCard2 = game.drawCard();
        int playerTotal = playerCard1 + playerCard2;

        assertTrue(playerTotal >= 4 && playerTotal <= 20);
    }
    @Test
    public void testPlayerBust(){
        BlackjackGame game = new BlackjackGame();

        //Given
        game.setPlayerHand(15);
        game.getHit(10);
        //When
        boolean playerBusted = game.getPlayerTotal() > 21;
        //Then
        assertTrue(playerBusted,"Player's hand is over 21");
    }
    @Test
    public void testDealerBust(){
        BlackjackGame game = new BlackjackGame();

        //Given
        game.setDealerHand(15);
        game.getHit(10);
        //When
        boolean dealerBusted = game.getDealerTotal() > 21;
        //Then
        assertTrue(dealerBusted,"Dealer's hand is over 21");
    }

//    @Test Need to check this tomorrow!!!!
//    public void playerHands(){
//        int playerCard1 = game.drawCard();
//        int playerCard2 = game.drawCard();
//        int expectedTotal = playerCard1 + playerCard2;
//        assertEquals(expectedTotal,playerCard1+playerCard2);
//    }

}
