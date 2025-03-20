package com.github.zipcodewilmington.casino.games.gofish;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

    public static void main(String[] args) {
            DeckOfCards card = new DeckOfCards();

        System.out.println(card.createDeck());
        System.out.println(card.shuffleDeck(card.getDeckOfCards()));
        System.out.println(card.getRank());
    }


    private int rank;
    private int suit;
    private ArrayList<String> deckOfCards = new ArrayList<>();
    String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    String [] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8","9", "Jack", "Queen", "King"};

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

    public String toString(){
        return ranks[this.rank] + " of " + suits[this.suit];
    }

    public ArrayList<String> createDeck(){

        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                this.rank = i;
                this.suit = j;
                DeckOfCards newDeck = new DeckOfCards(i,j);
                deckOfCards.add(newDeck.toString());
            }
        }
        return deckOfCards;
    }
    public ArrayList<String> shuffleDeck(ArrayList<String> deck){
        DeckOfCards newDeck = new DeckOfCards();
        Collections.shuffle(deckOfCards);;
//        while (shuffledDeck.size() >0) {
//            Random index = new Random(deckOfCards.size());
//            shuffledDeck.add(deckOfCards[index]);
//        }
        return deck;
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
