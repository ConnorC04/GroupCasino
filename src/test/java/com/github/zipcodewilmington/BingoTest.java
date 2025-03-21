package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.bingo.BingoGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BingoTest {

    @Test
    public void testResetNumbers() {
        BingoGame bingo = new BingoGame();
        ArrayList<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 75; i++) {
            expected.add(i);
        }
        ArrayList<Integer> actual = new ArrayList<>();
        bingo.setNumbers(actual, 1, 75);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMakeBoard() {
        BingoGame bingoGame = new BingoGame();
        int[][] board = bingoGame.makeBoard();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0) {
                    Assert.assertTrue(board[i][j] >= 1 && board[i][j] <= 15);
                }
                if (i == 1) {
                    Assert.assertTrue(board[i][j] >= 16 && board[i][j] <= 30);
                }
                if (i == 2) {
                    Assert.assertTrue(board[i][j] >= 31 && board[i][j] <= 45);
                }
                if (i == 3) {
                    Assert.assertTrue(board[i][j] >= 46 && board[i][j] <= 60);
                }
                if (i == 4) {
                    Assert.assertTrue(board[i][j] >= 61 && board[i][j] <= 75);
                }
            }
        }
    }

    @Test
    public void testColumnChecker() {
        BingoGame bingoGame = new BingoGame();
        int[][] firstColumn = {
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };
        int[][] secondColumn = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1}
        };
        int[][] thirdColumn = {
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1}
        };
        int[][] fourthColumn = {
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1}
        };
        int[][] fifthColumn = {
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0}
        };
        Assert.assertEquals(5, bingoGame.checkFiveInARow(firstColumn));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(secondColumn));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(thirdColumn));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(fourthColumn));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(fifthColumn));
    }

    @Test
    public void testRowChecker() {
        BingoGame bingoGame = new BingoGame();
        int[][] firstRow = {
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] secondRow = {
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] thirdRow = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] fourthRow = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int[][] fifthRow = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };
        Assert.assertEquals(5, bingoGame.checkFiveInARow(firstRow));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(secondRow));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(thirdRow));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(fourthRow));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(fifthRow));
    }

    @Test
    public void testDiagonalChecker() {
        BingoGame bingoGame = new BingoGame();
        int[][] firstDiagonal = {
                {0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0}
        };
        int[][] secondDiagonal = {
                {1, 1, 1, 1, 0},
                {1, 1, 1, 0, 1},
                {1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };
        Assert.assertEquals(5, bingoGame.checkFiveInARow(firstDiagonal));
        Assert.assertEquals(5, bingoGame.checkFiveInARow(secondDiagonal));
    }

    @Test
    public void testRandomChoice() {
        BingoGame bingoGame = new BingoGame();
        int number = bingoGame.chooseRandomNumber();
        Assert.assertTrue(number >= 1 && number <= 75);
    }

    @Test
    public void testFormatNumber() {
        BingoGame bingoGame = new BingoGame();
        Assert.assertEquals("B-1", bingoGame.formatChosenNumber(1));
        Assert.assertEquals("I-16", bingoGame.formatChosenNumber(16));
        Assert.assertEquals("N-31", bingoGame.formatChosenNumber(31));
        Assert.assertEquals("G-46", bingoGame.formatChosenNumber(46));
        Assert.assertEquals("O-61", bingoGame.formatChosenNumber(61));
    }

    @Test
    public void testUpdateBoard() {
        BingoGame bingoGame = new BingoGame();
        int[][] original = {
                {10, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] expected = {
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        Assert.assertEquals(expected, bingoGame.updateBoard(original, 10));
    }

    @Test
    public void testPlayerWins() {
        BingoGame bingoGame = new BingoGame();
        int[][] firstColumn = {
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };
        int winning = bingoGame.checkFiveInARow(firstColumn);
        Assert.assertTrue(bingoGame.playerWins(winning));
    }

    @Test
    public void testOutputBoard() {
        BingoGame bingoGame = new BingoGame();
        int[][] board = {
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };
        bingoGame.outputBoard(board);
    }

//    @Test
//    public void runGame(){
//        BingoGame bingoGame = new BingoGame();
//        bingoGame.runGame();
//    }
}

