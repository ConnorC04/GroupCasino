package com.github.zipcodewilmington.casino.games.slots;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotsGameTest {

    @Test
    void testBalanceAfterBet() {
        SlotsGame slotsGame = new SlotsGame();

        String actual = slotsGame.getUserInput("Yes");
        assertEquals(expected, actual);
    }




}

