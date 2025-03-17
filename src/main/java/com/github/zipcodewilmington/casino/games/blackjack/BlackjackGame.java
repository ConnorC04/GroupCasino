package com.github.zipcodewilmington.casino.games.blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackjackGame {
     ArrayList<String>  dealerHand;
     ArrayList<String>  playerHand;
    Scanner input = new Scanner(System.in);
    Random random = new Random();
   Object[] deck ;

 public void playBlackJack(){
     deck = new Object[] {2,3,4,5,6,7,8,9,10,"J","Q","K","A"};
     intro();

 }


    private void intro() {
        System.out.println("Welcome to BlackJack");

    }
    public  Object getDeck(){
     return deck;
    }
}
