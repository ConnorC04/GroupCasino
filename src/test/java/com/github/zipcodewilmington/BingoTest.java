package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.bingo.BingoGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BingoTest {




    @Test
    public void testResetNumbers(){
        BingoGame bingo = new BingoGame();
        ArrayList<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 75; i++){
            expected.add(i);
        }
        ArrayList<Integer> actual = new ArrayList<>();
        bingo.setNumbers(actual, 1, 75);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMakeBoard(){
        BingoGame bingoGame = new BingoGame();
        int[][] board = bingoGame.makeBoard();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (i == 0){
                    Assert.assertTrue(board[i][j] >= 1 && board[i][j] <= 15);
                }
                if (i == 1){
                    Assert.assertTrue(board[i][j] >= 16 && board[i][j] <= 30);
                }
                if (i == 2){
                    Assert.assertTrue(board[i][j] >= 31 && board[i][j] <= 45);
                }
                if (i == 3){
                    Assert.assertTrue(board[i][j] >= 46 && board[i][j] <= 60);
                }
                if (i == 4){
                    Assert.assertTrue(board[i][j] >= 61 && board[i][j] <= 75);
                }
            }
        }
    }

    @Test
    public void testRandomChoice(){
        BingoGame bingoGame = new BingoGame();

    }
}
