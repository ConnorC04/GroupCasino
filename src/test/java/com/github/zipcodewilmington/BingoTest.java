package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.bingo.BingoGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BingoTest {

    @Test
    public void testResetNumbers(){
        ArrayList<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 75; i++){
            expected.add(i);
        }
        BingoGame bingo = new BingoGame();
        ArrayList<Integer> actual = new ArrayList<>();
        bingo.setNumbers(actual, 1, 75);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRandomChoice(){

    }
}
