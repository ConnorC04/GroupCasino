package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.games.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {Collections.shuffle(cards);}

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty.");
        }
        return cards.remove(cards.size() - 1);
    }

    public int size() {
        return cards.size();
    }

    //added getter and took it out added a main. trying to figure out why I am getting 56 cards
//    public static void main(String[] args) {
//        Deck newDeck = new Deck();
//        System.out.println(newDeck.size());
//    }
}

