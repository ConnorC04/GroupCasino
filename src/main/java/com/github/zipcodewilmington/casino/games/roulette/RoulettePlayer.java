package com.github.zipcodewilmington.casino.games.roulette;


import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.utils.IOConsole;
import java.util.ArrayList;


public class RoulettePlayer {
    //vars from superclass CasinoAccount needed? - accountName, playerWallet
    //instance vars - currentGuess (only single number for now,
    ArrayList<Integer> currentGuess;
    // will need to account for both inside bets like splits, streets, or corners,
    // and outside bets like first or last 18, red or black, even or odd, or first/second/third dozen later on)
    double currentBets; //(total amt player has bet)


    //methods - getGuess (prompts player to make a guess),
    private ArrayList<Integer> getGuess() {
        return null;
    }
    //setGuess (assigns their guess to the instance var),
    //getBet (asks player to place bet, amt must be more than 0
    // but less than how much they currently have, otherwise prompts user for new input)
    //setBet (subtracts bet from player, saves it to currentBet)
    //isPlayerWalletEmpty (boolean, checks that player's wallet > 0, if false ends game)
}
