package com.github.zipcodewilmington.casino.games.gofish;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

    public static void main(String[] args) {
        DeckOfCards cards = new DeckOfCards();
        ArrayList<String> deck = cards.createDeck();
        System.out.println(deck);
        cards.shuffleDeck(deck);
        System.out.println(deck.size());

    }


    private int rank;
    private int suit;
    private ArrayList<String> deckOfCards = new ArrayList<>();
    String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9","10", "Jack", "Queen", "King"};

    public DeckOfCards(int rank, int suit, ArrayList<String> deck) {
        this.rank = rank;
        this.suit = suit;
    }

    public DeckOfCards(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public DeckOfCards() {

    }

    public String buildCard(int rank, int suit) {
        return ranks[rank] + " of " + suits[suit];
    }

    public ArrayList<String> createDeck() {
        for (int rank = 0; rank < ranks.length; rank++) {
            for (int suit = 0; suit < suits.length; suit++) {
                String card= buildCard(rank,suit);
                deckOfCards.add(card);
            }
        }
        shuffleDeck(deckOfCards);
        return deckOfCards;
    }

    public void shuffleDeck(ArrayList<String> deck) {
       // DeckOfCards newDeck = new DeckOfCards();
        Collections.shuffle(deck);

    }

    public ArrayList<String> getDeckOfCards() {

        return deckOfCards;
    }

    public int getRank() {
        return rank;
    }

    public void setDeckOfCards(ArrayList<String> deckOfCards) {

        this.deckOfCards = deckOfCards;
    }
}
