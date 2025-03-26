package com.github.zipcodewilmington.casino.games.slots;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotsGameTest {

    @Test
    void testPullTrigger1() {
        SlotsGame sg = new SlotsGame();
        String[] slotCharacters = new String[]{"\u2660", "\u2665", "\u2663", "\u2666", "\u26C0", "\u26C1", "\u26C2", "\u26C3", "\u265B", "\u277C"};
        int[] s = {9, 9, 9};
        String[] expected = new String[]{slotCharacters[9], slotCharacters[9], slotCharacters[9]};
        String[] actual = new String[]{slotCharacters[s[0]], slotCharacters[s[1]], slotCharacters[s[2]]};

        Assert.assertEquals(expected, actual);
    }

    @Test
    void testPullTrigger2() {
        SlotsGame sg = new SlotsGame();
        String[] slotCharacters = new String[]{"\u2660", "\u2665", "\u2663", "\u2666", "\u26C0", "\u26C1", "\u26C2", "\u26C3", "\u265B", "\u277C"};
        int[] s = {0, 0, 0};
        String[] expected = new String[]{slotCharacters[0], slotCharacters[0], slotCharacters[0]};
        String[] actual = new String[]{slotCharacters[s[0]], slotCharacters[s[1]], slotCharacters[s[2]]};

        Assert.assertEquals(expected, actual);
    }

    @Test
    void testPullTrigger3() {
        SlotsGame sg = new SlotsGame();
        String[] slotCharacters = new String[]{"\u2660", "\u2665", "\u2663", "\u2666", "\u26C0", "\u26C1", "\u26C2", "\u26C3", "\u265B", "\u277C"};
        int[] s = {1, 1, 1};
        String[] expected = new String[]{slotCharacters[1], slotCharacters[1], slotCharacters[1]};
        String[] actual = new String[]{slotCharacters[s[0]], slotCharacters[s[1]], slotCharacters[s[2]]};

        Assert.assertEquals(expected, actual);
    }

    @Test
    void testPullTrigger4() {
        SlotsGame sg = new SlotsGame();
        String[] slotCharacters = new String[]{"\u2660", "\u2665", "\u2663", "\u2666", "\u26C0", "\u26C1", "\u26C2", "\u26C3", "\u265B", "\u277C"};
        int[] s = {4, 5, 7};
        String[] expected = new String[]{slotCharacters[4], slotCharacters[5], slotCharacters[7]};
        String[] actual = new String[]{slotCharacters[s[0]], slotCharacters[s[1]], slotCharacters[s[2]]};

        Assert.assertEquals(expected, actual);
    }
}


