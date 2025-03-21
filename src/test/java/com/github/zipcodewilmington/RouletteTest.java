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
                "   |  3 |  6 |  9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | ROW 1\n" +
                " 0 |  2 |  5 |  8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | ROW 2\n" +
                "   |  1 |  4 |  7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | ROW 3\n" +
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

//        int[] odds = {35, 17, 11, 8, 1, 1, 1, 1, 1, 1, 2, 2};
//        odds[0] = (int) roulette.getBetOdds(insideBets.STRAIGHT, 35.0);
//        odds[1];
//        odds[2];
//        odds[3];
//        odds[4];
//        odds[5];
//        odds[6];
//        odds[7];
//        odds[8];
//        odds[9];
//        odds[10] = (int) roulette.getBetOdds(outsideBets.DOZEN, 2.0);
//        odds[11] = (int) roulette.getBetOdds(outsideBets.ROW, 2.0);
    }


    @Test
    public void test1MakeInsideBets() {
        //roulette.makeInsideBets();
    }

    @Test
    public void test2MakeInsideBets() {

    }

    @Test
    public void test3MakeInsideBets() {

    }

    @Test
    public void test4MakeInsideBets() {

    }

    @Test
    public void test5MakeInsideBets() {

    }

}
