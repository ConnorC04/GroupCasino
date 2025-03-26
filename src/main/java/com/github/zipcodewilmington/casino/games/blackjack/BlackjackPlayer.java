package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class BlackjackPlayer implements PlayerInterface {
    private Double wallet;
    private String accountName;
    private String accountPassword;

    // Default constructor
    public BlackjackPlayer() {
        this.wallet = 1000000.0;
    }

    // Constructor with parameters
    public BlackjackPlayer(String accountName, String accountPassword, Double wallet) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.wallet = wallet;
    }



    @Override
    public CasinoAccount getArcadeAccount() {
        // Implement the Arcade account method if required
        return null;
    }

    @Override
    public String getAccountName() {
        return this.accountName;
    }

    @Override
    public String getAccountPassword() {
        return this.accountPassword;
    }

    @Override
    public Double getAccountBalance() {
        return this.wallet; // Return current wallet balance
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        // Implement the play method as needed
        return null;
    }
}