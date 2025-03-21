package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.games.blackjack.BlackjackPlayer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccountManager` stores, manages, and retrieves `ArcadeAccount` objects
 * it is advised that every instruction in this class is logged
 */
public class CasinoAccountManager {
    private Map<String, CasinoAccount> accountMap = new HashMap<>();//need to store the account somewhere

    /**
     * @param accountName     name of account to be returned
     * @param accountPassword password of account to be returned
     * @return `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount getAccount(String accountName, String accountPassword) {
        CasinoAccount account = accountMap.get(accountName);//stores input
        if (accountMap.containsKey(accountName)) {
            System.out.println("Account already exists");
        }
        if (account != null && account.getAccountPassword().equals(accountPassword)) {

        }return account;
    }

    /**
     * logs & creates a new `ArcadeAccount`
     *
     * @param accountName     name of account to be created
     * @param accountPassword password of account to be created
     * @return new instance of `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount createAccount(String accountName, String accountPassword) {
        // Check if the account already exists
        if (accountMap.containsKey(accountName)) {
            throw new RuntimeException("Account already exists with this name.");
        }
        CasinoAccount account = new CasinoAccount(accountName, accountPassword);
        accountMap.put(accountName, account);
        return account;
    }

    /**
     * logs & registers a new `CasinoAccount` to `this.getArcadeAccountList()`
     *
     * @param casinoAccount the `CasinoAccount` to be added to `this.accountMap`
     */
    public void registerAccount(CasinoAccount casinoAccount) {
        accountMap.put(casinoAccount.getAccountName(), casinoAccount);
    }
}
    /**
     * logs & registers a new `ArcadeAccount` to `this.getArcadeAccountList()`
     *
     * @param casinoAccount the arcadeAccount to be added to `this.getArcadeAccountList()`
     */
//    public void registerAccount(CasinoAccount casinoAccount) {
//        String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
//        String currentClassName = getClass().getName();
//        String errorMessage = "Method with name [ %s ], defined in class with name [ %s ] has  not yet been implemented";
//        throw new RuntimeException(String.format(errorMessage, currentMethodName, currentClassName));
//    }
