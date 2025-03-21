package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to bet.
 */
public class CasinoAccount extends CasinoAccountManager{
    CasinoAccountManager cm= new CasinoAccountManager();

    String accountPassword;
    String accountName;

    public CasinoAccount() {

    }

    public CasinoAccount(String accountName, String accountPassword) {
        this.accountName=accountName;
        this.accountPassword=accountPassword;
    }
    public String getAccountPassword(){
        return accountPassword;
    }
    public String getAccountName(){
        return accountName;
    }
    public void registerPlayer(String accountName, String accountPassword){
        this.accountName=accountName;
        this.accountPassword=accountPassword;
    }
}
