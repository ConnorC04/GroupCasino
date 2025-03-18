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



 public double bet(double balance){
     System.out.println("Let's Play!!");
     double amountToBet = getUserInput("How much would you like to bet?");
     double balanceAfterBet = balance - amountToBet;
     double winnings;
     double winningMultiplier;
     double balanceAfterPlay;
     pullTrigger();
     System.out.print(slot1 + " ");
     System.out.print(slot2 + " ");
     System.out.println(slot3);
     if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
         winningMultiplier = 1.4;
         winnings = (winningMultiplier * amountToBet);
         balanceAfterPlay = (winnings + balanceAfterBet);
         System.out.println("You've won!! Here's your winnings: " + winnings + ".\n" +
                 "Your new balance is " + balanceAfterPlay);

         return balanceAfterPlay;
     }
     if (slot1 == slot2 && slot2 == slot3){
         winningMultiplier = 2;
         winnings = (winningMultiplier * amountToBet);
         balanceAfterPlay = winnings + balanceAfterBet;
         System.out.println("You've won!! Here's your winnings: " + winnings  + ".\n" +
                 "Your new balance is " + balanceAfterPlay);
         return balance;
     } else {
         balanceAfterPlay = balanceAfterBet;
         System.out.println("Sorry, try again. Your current balance is " + balanceAfterPlay);
         return balanceAfterPlay;
     }
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

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        double currentBalance = 1000.00;
        SlotsGame sg = new SlotsGame();
        //Scanner choiceToPlayAgain;
        //Casino currentBalance = new Casino(wallet);
        String choiceToPlayAgain;
        do {
            sg.bet(currentBalance);
            System.out.println("Do you want to play again? (Yes/No): ");
            choiceToPlayAgain = scanner.nextLine();
        }
        while(choiceToPlayAgain.equalsIgnoreCase("Yes"));
    }

}
