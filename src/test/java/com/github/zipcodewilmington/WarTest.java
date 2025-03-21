package com.github.zipcodewilmington;
import java.util.List;
import org.junit.Test;
import com.github.zipcodewilmington.casino.games.war.WarGame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class WarTest {
    private WarGame game;
//test for checking deck has 52 cards
@Test
public void testcreatedDeck() {

    //given
    WarGame warGame = new WarGame();
    //when
    List<Integer> deck = warGame.createdDeck();
    //then
    assertEquals(52, deck.size());
}

@Test
public void testCreatedShuffle() {

    //given
    WarGame warGame = new WarGame();
    List<Integer> deck1 = warGame.createdDeck();
    List<Integer> deck2 = warGame.createdDeck();

    //when
    boolean areEqual = deck1.equals(deck2);

    //then
    assertFalse(areEqual);

}



}
