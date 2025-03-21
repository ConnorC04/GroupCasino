package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.roulette.RouletteGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;

public class RouletteTest {

    RouletteGame roulette;
    boolean about(int expected, int actual) {
        return actual > 0.90 * expected && actual < 1.10 * expected;
    }

    @BeforeEach
    public void setUpRoulette() {
        roulette = new RouletteGame();
    }

    @Test
    public void testRouletteTableLayout() {
        String expected = "   |        1-12       |       13-24       |      25--36  \n" +
                "   |  3 |  6 |  9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | ROW1 1\n" +
                " 0 |  2 |  5 |  8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | ROW1 2\n" +
                "   |  1 |  4 |  7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | ROW1 3\n" +
                "   |LOW  1-18|  EVEN   |  REDS   |  BLACK  |  ODDS   |19-36 HIGH|";
        String actual = roulette.getTableLayout();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testWheelSpinRange() {
        roulette.spinWheel();
        int randomNumber = roulette.getCurrentNum();
        assertTrue(-1 < randomNumber && randomNumber < 37);
    }

    @Test
    public void test1WheelSpinChances() {
        int numOfHitsFor13 = 0;
        for (int i = 0; i < 37000; i++) {
            roulette.spinWheel();
            int luckyNum13 = roulette.getCurrentNum();
            if (luckyNum13 == 13) {
                numOfHitsFor13++;
            }
        }
        System.out.println(numOfHitsFor13);
        assertTrue(about(1000, numOfHitsFor13)); //if between 900 and 1100, should come up true
    }

    @Test
    public void test2WheelSpinChances() {
        int numOfHitsFor0 = 0;
        for (int i = 0; i < 37000; i++) {
            roulette.spinWheel();
            int luckyNum0 = roulette.getCurrentNum();
            if (luckyNum0 == 0) {
                numOfHitsFor0++;
            }
        }
        System.out.println(numOfHitsFor0);
        assertTrue(about(1000, numOfHitsFor0));
    }

    @Test
    public void test1OddOrEven() {
        roulette.isOddOrEven(0);
        String expected = "Neither odd, nor even";
        String actual = roulette.getOddOrEven();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test2OddOrEven() {
        roulette.isOddOrEven(28);
        String expected = "Even";
        String actual = roulette.getOddOrEven();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test3OddOrEven() {
        roulette.isOddOrEven(19);
        String expected = "Odd";
        String actual = roulette.getOddOrEven();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test1RedOrBlack() {
        roulette.isRedOrBlack(0);
        String expected = "Green";
        String actual = roulette.getCurrentColor();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test2RedOrBlack() {
        roulette.isRedOrBlack(14);
        String expected = "Red";
        String actual = roulette.getCurrentColor();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test3RedOrBlack() {
        roulette.isRedOrBlack(22);
        String expected = "Black";
        String actual = roulette.getCurrentColor();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testGetOdd() {
        double[] odds = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        odds[0] = roulette.calcWinnings(RouletteGame.BetsAvailable.STRAIGHT, 1.0);
        odds[1] = roulette.calcWinnings(RouletteGame.BetsAvailable.SPLIT, 1.0);
        odds[2] = roulette.calcWinnings(RouletteGame.BetsAvailable.STREET, 1.0);
        odds[3] = roulette.calcWinnings(RouletteGame.BetsAvailable.CORNER, 1.0);
        odds[4] = roulette.calcWinnings(RouletteGame.BetsAvailable.EVEN, 1.0);
        odds[5] = roulette.calcWinnings(RouletteGame.BetsAvailable.ODD, 1.0);
        odds[6] = roulette.calcWinnings(RouletteGame.BetsAvailable.RED, 1.0);
        odds[7] = roulette.calcWinnings(RouletteGame.BetsAvailable.BLACK, 1.0);
        odds[8] = roulette.calcWinnings(RouletteGame.BetsAvailable.LOW, 1.0);
        odds[9] = roulette.calcWinnings(RouletteGame.BetsAvailable.HIGH, 1.0);
        odds[10] = roulette.calcWinnings(RouletteGame.BetsAvailable.DOZEN1, 1.0);
        odds[11] = roulette.calcWinnings(RouletteGame.BetsAvailable.ROW1, 1.0);
        double[] expected = {35, 17, 11, 8, 1, 1, 1, 1, 1, 1, 2, 2};
        for (int i = 0; i < odds.length; i++) {
            assertEquals(expected[i], odds[i], 0.001);
        }
    }


    @Test
    public void test1MakeBet() {
        roulette.makeBet(RouletteGame.BetsAvailable.STRAIGHT, 1.0);
        RouletteGame.BetsAvailable expectedBet = RouletteGame.BetsAvailable.STRAIGHT;
        double expectedAmount = 1.0;
        assertEquals(roulette.currentBet, expectedBet);
        assertEquals(roulette.currentAmtBet, expectedAmount, 0.001);
    }

    @Test
    public void test2MakeBet() {
        roulette.makeBet(RouletteGame.BetsAvailable.SPLIT, 1.0);
        RouletteGame.BetsAvailable expectedBet = RouletteGame.BetsAvailable.SPLIT;
        double expectedAmount = 1.0;
        assertEquals(roulette.currentBet, expectedBet);
        assertEquals(roulette.currentAmtBet, expectedAmount, 0.001);
    }

    @Test
    public void test3MakeBet() {
        roulette.makeBet(RouletteGame.BetsAvailable.STREET, 1.0);
        RouletteGame.BetsAvailable expectedBet = RouletteGame.BetsAvailable.STREET;
        double expectedAmount = 1.0;
        assertEquals(roulette.currentBet, expectedBet);
        assertEquals(roulette.currentAmtBet, expectedAmount, 0.001);
    }

}
