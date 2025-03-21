package com.github.zipcodewilmington.casino.games.roulette;


import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.CasinoAccount;


public class RoulettePlayer implements PlayerInterface {
    Double wallet = 1000.00;
    String accountName;
    String accountPassword;

    public RoulettePlayer() {
    }

    public RoulettePlayer(String accountName, String accountPassword) {
        this.wallet = wallet;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    @Override
    public CasinoAccount getArcadeAccount() {
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
        return this.wallet;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

}
