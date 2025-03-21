package com.github.zipcodewilmington.casino.games.slots;
import  java.util.Scanner;
/**
 * Created by leon on 7/21/2020.
 */
public class SlotsPlayer {
    /*
    About the player: Find how much the player has remaining after playing other games.


    Find out if the user has enough money to bet slots.
    Then add the bet (wager). Whatever money they have minus the wager.
    "Are you ready? Pull the trigger to begin!!!" --> SlotsGame class becomes active
    */

    public void player(){
        Scanner scanner = new Scanner(System.in);
        double walletBalance = wallet linked to player account.
        double moneyToPLaySlots = walletBalance - moneyFromWallet(walletBalance);
        Transfer to SlotsGame.java
    }

    public String getUserInput(String s){
        System.out.println(s);
        Scanner addMoney = new Scanner(System.in);
        return addMoney.nextLine();
    }

    public double moneyFromWallet(double walletAccountFromPlayer){
        double slotsPlayerWallet = Double.parseDouble(getUserInput("Hi! Welcome to SLOTS! How much money do you want to play for SLOTS?"));
        return (slotsPlayerWallet);
    }
}