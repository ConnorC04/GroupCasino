package com.github.zipcodewilmington.casino.games.slots;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
/**
 * Created by leon on 7/21/2020.
 */
public class SlotsPlayer implements PlayerInterface {
    Double wallet = 1000000.0;
    String accountName;
    String accountPassword;

    public SlotsPlayer() {

    }

    public SlotsPlayer(String accountName, String accountPassword) {
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
