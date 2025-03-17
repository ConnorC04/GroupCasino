package com.github.zipcodewilmington.casino.games.slots;
import java.util.Scanner;


import java.util.Random;



/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame {
/*
    columnone runs, then stops at a number or object, then columntwo runs, and so on...
    Once all three stop, then they are compared to find out if they are matching..
    If all three match, then the player wins double their winnings. player's account + (wager * 2).

 */
    int slot1 = 0;
    int slot2 = 0;
    int slot3 = 0;


private double getUserInput(String s) {
    System.out.println(s);
    Scanner bet = new Scanner(System.in);
    return bet.nextDouble();
}

 public void bet(double balance){
     System.out.println("Let's Play!!");
     double amountToBet = getUserInput("How much would you like to bet?");
     pullTrigger();
     System.out.println(slot1);
     System.out.println(slot2);
     System.out.println(slot3);
 }

    private void pullTrigger() {
        slot1 = randomColumn();
        slot2 = randomColumn();
        slot3 = randomColumn();
    }

    public int randomColumn() {
    Random num = new Random();
    return num.nextInt(7);
    }

    public static void main(String[] args) {
        SlotsGame sg = new SlotsGame();
        sg.bet(1000.00);
    }

}
