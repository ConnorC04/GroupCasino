package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;



/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements GameInterface {
    double currentBalance = 1000.00;
    SlotsPlayer wb = new SlotsPlayer();


//    int slot1 = 0;
//    int slot2 = 0;
//    int slot3 = 0;
    int slots[] = {0 ,0 ,0};


    Scanner scanner = new Scanner(System.in);

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    public void run() {
        SlotsGame sg = new SlotsGame();
        sg.play();
    }

    public String getUserInput(String s){
        System.out.println(s);
        Scanner response = new Scanner(System.in);
        return response.nextLine();
    }

    public void play(){
        boolean readyPlayer = true;
        String userInput = getUserInput("Are You ready to play?");
        runGame(userInput, readyPlayer);
    }

    public void runGame(String userInput, boolean readyPlayer) {
        String choiceToPlayAgain;
        if (userInput.equalsIgnoreCase("Yes")) {
            while (readyPlayer) {
                System.out.println("Let's Play!!");
                bet(wb.wallet);
                System.out.println("Do you want to bet again? (Yes/No): ");
                choiceToPlayAgain = scanner.nextLine();

                if (choiceToPlayAgain.equalsIgnoreCase("Yes")) {
                    if (wb.wallet <= 0){
                        System.out.println("Sorry, but you don't have the balance to bet.\n" +
                                "You will be escorted back to the Casino.");
                        break;
                    }
                } else {
                    System.out.println("Thank you for coming! You'll be escorted back to the Casino");
                    break;
                }
            }
        } else {
            System.out.println("You will be escorted back to the Casino!");
        }
    }

    public double bet(double balance){
        double amountToBet = Double.parseDouble(getUserInput("How much would you like to bet?"));
        double balanceAfterBet = balance - amountToBet;
        pullTrigger(this.slots);
        return checkForWinOrLose(amountToBet, balanceAfterBet);
    }

    public double checkForWinOrLose(double amountToBet, double balanceAfterBet) {
        double winnings;
        double winningMultiplier;
        if (slots[0] == slots[1] || slots[0] == slots[2] || slots[1] == slots[2]) {
            winningMultiplier = 1.4;
            winnings = (winningMultiplier * amountToBet);
            wb.wallet = (winnings + balanceAfterBet);
            System.out.println((String.format("You've won!! Here's your winnings: %.2f\n", winnings)) +
                    (String.format("Your new balance is: %.2f ", wb.wallet)));

            return wb.wallet;
        }
        if (slots[0] == slots[1] && slots[1] == slots[2]) {
            winningMultiplier = 2;
            winnings = (winningMultiplier * amountToBet);
            wb.wallet = winnings + balanceAfterBet;
            System.out.println((String.format("You've won!! Here's your winnings: %.2f\n", winnings)) +
                    (String.format("Your new balance is: %.2f ", wb.wallet)));
            return wb.wallet;
        } else {
            wb.wallet = balanceAfterBet;
            System.out.println(String.format("Sorry, try again. Your current balance is %.2f", wb.wallet));
            return wb.wallet;
        }
    }

    private String pullTrigger (int[] s) {
            /*Cycle through a series of unicode to express the characters on the slots*/
            SlotsGame sg = new SlotsGame();
            String[] slotCharacters = new String[]{"\u2660", "\u2665", "\u2663", "\u2666", "\u26C0", "\u26C1", "\u26C2", "\u26C3", "\u265B", "\u277C"};
            s[0] = sg.randomColumn();
            s[1] = sg.randomColumn();
            s[2] = sg.randomColumn();
            String[] column = new String[]{slotCharacters[s[0]], slotCharacters[s[1]], slotCharacters[s[2]]};
            String printColumns = Arrays.toString(column);
            System.out.println(printColumns);
            return printColumns;
        }

    public int randomColumn () {
        Random rand = new Random();
        return rand.nextInt(10);
    }
}
