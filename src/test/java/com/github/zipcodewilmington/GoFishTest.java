package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.gofish.DeckOfCards;
import com.github.zipcodewilmington.casino.games.gofish.GoFishGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoFishTest {

    @Test
    public void dealDeckTest_splitDeckIntoThree(){
        GoFishGame goFishGame = new GoFishGame();
        goFishGame.dealDeck();
        ArrayList<String> playerHand = goFishGame.getPlayerHand();
        ArrayList<String> dealerHand = goFishGame.getDealerHand();
        ArrayList<String> drawStack = goFishGame.getDrawStack();

        Assert.assertEquals(7, playerHand.size());
        Assert.assertEquals(7, dealerHand.size());
        Assert.assertEquals(38, drawStack.size());
    }

    @Test
    public void checkDealerDeckForCard_returnsHowManyMatchesInTheDeck(){
        ArrayList<String> arrayToTest = createTestDeck();
        GoFishGame goFishGame = new GoFishGame(arrayToTest);
        //creates a new deck to populate the player and dealer hands.
        goFishGame.dealDeck();
        int numberOfCardsFound = goFishGame.checkDealerDeckForCard("ace");
        System.out.println(arrayToTest);
        Assert.assertEquals(4, numberOfCardsFound);
    }

    @Test
    public void isFourOfAKind_shouldReturnTrue(){
        GoFishGame goFishGame = new GoFishGame();
        ArrayList<String> arrayToTest = createTestDeck();
        boolean isFourKindInArray = goFishGame.isFourOfAKind(arrayToTest, "ace");

        Assert.assertTrue(isFourKindInArray);
    }

    @Test
    public void isFourOfAKind_shouldReturnFalse(){
        GoFishGame goFishGame = new GoFishGame();
        ArrayList<String> arrayToTest = createTestDeck();
        boolean isFourKindInArray = goFishGame.isFourOfAKind(arrayToTest, "rice");

        Assert.assertFalse(isFourKindInArray);
    }


    private ArrayList<String> createTestDeck(){
        ArrayList<String> deckOfCards = new ArrayList<>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for (int rank = 0; rank < ranks.length; rank++) {
            for (int suit = 0; suit < suits.length; suit++) {
                String card= ranks[rank] + " of " + suits[suit];;
                deckOfCards.add(card);
            }
        }
        return deckOfCards;
    }


}
