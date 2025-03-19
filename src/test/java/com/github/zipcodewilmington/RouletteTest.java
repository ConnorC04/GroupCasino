package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.roulette.RouletteGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class RouletteTest {

    RouletteGame roulette;

    @BeforeEach
    public void setUpRoulette() {
        roulette = new RouletteGame();
    }

    @Test
    public void testRouletteTableLayout() {
        String expected = "   |        1-12       |       13-24       |      25--36  \n" +
                "   |  3 |  6 |  9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 |2 to 1\n" +
                " 0 |  2 |  5 |  8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 |2 to 1\n" +
                "   |  1 |  4 |  7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 |2 to 1\n" +
                "   |   1-18  |  EVEN   |  REDS   |  BLACK  |  ODDS   |  19-36  |";
        String actual = roulette.getTableLayout();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testWheelSpinRange() {
        roulette.spinWheel();
        int randomNumber = roulette.getCurrentNum();
        Assertions.assertTrue(-1 < randomNumber && randomNumber < 37);
    }


}
