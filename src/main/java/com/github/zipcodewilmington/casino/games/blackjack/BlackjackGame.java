package com.github.zipcodewilmington.casino.games.blackjack;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackjackGame {
    Integer[] dealerCard1;
    Integer[] dealerCard2;
    Integer[] dealerHand;
    Integer[]  playerCard1;
    Integer[]  playerCard2;
    //    ArrayList<Integer> ace= new ArrayList<>();
//    ace.add(11);
    Scanner input = new Scanner(System.in);
    Random random = new Random();
    Integer[] deck ;

    public void playBlackJack(){
        deck = new Integer[] {2,3,4,5,6,7,8,9,10,10,10,10,};
        intro();
        int i =random.nextInt();
        dealerCard1= new Integer[]{deck[i]};
        dealerCard2= new Integer[]{deck[i]};
        playerCard1= new Integer[]{deck[i]};
        playerCard2= new Integer[]{deck[i]};
        System.out.println("Dealer hand: "+ dealerCard1);

        System.out.println("Playler hand: "+ playerCard1+playerCard2);







    }


    private void intro() {
        System.out.println("Welcome to BlackJack");

    }
//    public  String[] getDeck(){
//        return deck;
//    }
//    private String userInput(String message){
//        while()
//        return message;
//    ???????
}