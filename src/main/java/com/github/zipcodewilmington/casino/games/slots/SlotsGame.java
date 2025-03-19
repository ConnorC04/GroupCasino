package com.github.zipcodewilmington.casino.games.slots;

import java.util.Arrays;
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
    double currentBalance = 1000.00;
    int slot1 = 0;
    int slot2 = 0;
    int slot3 = 0;
    Scanner scanner = new Scanner(System.in);
    SlotsGame sg = new SlotsGame();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        SlotsGame sg = new SlotsGame();
        sg.play();
    }

    private String getUserInput(String s) {
    System.out.println(s);
    Scanner bet = new Scanner(System.in);
    return bet.nextLine();
}

public void play(){
    String choiceToPlayAgain;
    boolean startGame = true;
    boolean readyPlayer = true;
//    System.out.println("Are you ready to play?");
//    String userInput = scanner.nextLine();
    String userInput = getUserInput("Are You ready to play?");
    if(userInput.equalsIgnoreCase("Yes")){
        while(readyPlayer){
            sg.bet(sg.currentBalance);
            System.out.println("Do you want to bet again? (Yes/No): ");
            choiceToPlayAgain = scanner.nextLine();
            if(choiceToPlayAgain.equalsIgnoreCase("Yes")) {
                if (sg.currentBalance <= 0) {
                    System.out.println("Sorry, but you don't have the balance to bet.\n");
                    break;
                }
            }
        }
    } else if(userInput.equalsIgnoreCase("No")){
        System.out.println("Thank you for coming! You'll be escorted back to the Casino");
    } else{

    }
}


 public double bet(double balance){
     System.out.println("Let's Play!!");
     double amountToBet = Double.parseDouble(getUserInput("How much would you like to bet?"));
     double balanceAfterBet = balance - amountToBet;
     double winnings;
     double winningMultiplier;
     pullTrigger();
//     System.out.print(slot1 + " ");
//     System.out.print(slot2 + " ");
//     System.out.println(slot3);
     if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
         winningMultiplier = 1.4;
         winnings = (winningMultiplier * amountToBet);
         currentBalance = (winnings + balanceAfterBet);
         System.out.println("You've won!! Here's your winnings: " + winnings + ".\n" +
                 "Your new balance is " + currentBalance);

         return currentBalance;
     }
     if (slot1 == slot2 && slot2 == slot3){
         winningMultiplier = 2;
         winnings = (winningMultiplier * amountToBet);
         currentBalance = winnings + balanceAfterBet;
         System.out.println("You've won!! Here's your winnings: " + winnings  + ".\n" +
                 "Your new balance is " + currentBalance);
         return currentBalance;
     } else {
         currentBalance = balanceAfterBet;
         System.out.println("Sorry, try again. Your current balance is " + currentBalance);
         return currentBalance;
     }
 }

    private void pullTrigger() {
       /*Cycle through a series of unicode to express the characters on the slots*/
        SlotsGame sg = new SlotsGame();
        String[] slotCharacters = new String[]{"\u2660", "\u2665", "\u2663", "\u2666", "\u26C0", "\u26C1", "\u26C2", "\u26C3", "\u265B", "\u277C"};
        slot1 = sg.randomColumn();
        slot2 = sg.randomColumn();
        slot3 = sg.randomColumn();
        String[] column = new String[]{slotCharacters[slot1], slotCharacters[slot2], slotCharacters[slot3]};
        System.out.println(Arrays.toString(column));
    }

    public int randomColumn() {
    Random rand = new Random();
    return rand.nextInt(10);
    }


}
