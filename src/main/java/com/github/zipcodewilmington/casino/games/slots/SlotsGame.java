package com.github.zipcodewilmington.casino.games.slots;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;



/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame {
    double currentBalance = 1000.00;
    int slot1 = 0;
    int slot2 = 0;
    int slot3 = 0;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SlotsGame sg = new SlotsGame();
        sg.play();

    }

        public String getUserInput(String s){
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
            if (userInput.equalsIgnoreCase("Yes")) {
                while (readyPlayer) {
                    bet(currentBalance);
                    System.out.println("Do you want to bet again? (Yes/No): ");
                    choiceToPlayAgain = scanner.nextLine();

                    if (choiceToPlayAgain.equalsIgnoreCase("Yes")) {
                        if (currentBalance <= 0){
                            System.out.println("Sorry, but you don't have the balance to bet.\n" +
                                    "You will be escorted back to the Casino.");
                            break;
                        }
                    } else {
                        System.out.println("Thank you for coming! You'll be escorted back to the Casino");
                        break;
                    }
                }
            }else {
                System.out.println("You will be escorted back to the Casino!");
            }
        }

        public double bet(double balance){
            System.out.println("Let's Play!!");
            double amountToBet = Double.parseDouble(getUserInput("How much would you like to bet?"));
            double balanceAfterBet = balance - amountToBet;
            double winnings;
            double winningMultiplier;
            pullTrigger();

            if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
                winningMultiplier = 1.4;
                winnings = (winningMultiplier * amountToBet);
                currentBalance = (winnings + balanceAfterBet);
                System.out.println(String.format("You've won!! Here's your winnings: %.2f " +
                        "\nYour new balance is ", winnings, currentBalance));

                return currentBalance;
            }
            if (slot1 == slot2 && slot2 == slot3) {
                winningMultiplier = 2;
                winnings = (winningMultiplier * amountToBet);
                currentBalance = winnings + balanceAfterBet;
                System.out.println(String.format("You've won!! Here's your winnings: %.2f " +
                        "Your new balance is %.2f ", winnings, currentBalance));
                return currentBalance;
            } else {
                currentBalance = balanceAfterBet;
                System.out.println(String.format("Sorry, try again. Your current balance is %.2f", currentBalance));
                return currentBalance;
            }
        }

        private void pullTrigger () {
            /*Cycle through a series of unicode to express the characters on the slots*/
            SlotsGame sg = new SlotsGame();
            String[] slotCharacters = new String[]{"\u2660", "\u2665", "\u2663", "\u2666", "\u26C0", "\u26C1", "\u26C2", "\u26C3", "\u265B", "\u277C"};
            slot1 = sg.randomColumn();
            slot2 = sg.randomColumn();
            slot3 = sg.randomColumn();
            String[] column = new String[]{slotCharacters[slot1], slotCharacters[slot2], slotCharacters[slot3]};
            System.out.println(Arrays.toString(column));
        }

        public int randomColumn () {
            Random rand = new Random();
            return rand.nextInt(10);
        }
    }
