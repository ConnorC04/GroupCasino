package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.bingo.BingoGame;
import com.github.zipcodewilmington.casino.games.bingo.BingoPlayer;
import com.github.zipcodewilmington.casino.games.blackjack.BlackjackGame;
import com.github.zipcodewilmington.casino.games.blackjack.BlackjackPlayer;
import com.github.zipcodewilmington.casino.games.gofish.GoFishGame;
import com.github.zipcodewilmington.casino.games.gofish.GoFishPlayer;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.casino.games.roulette.RouletteGame;
import com.github.zipcodewilmington.casino.games.roulette.RoulettePlayer;
import com.github.zipcodewilmington.casino.games.war.WarGame;
import com.github.zipcodewilmington.casino.games.war.WarPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */

public class Casino implements Runnable {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);

    @Override
    public void run() {
        String arcadeDashBoardInput;
        CasinoAccountManager casinoAccountManager = new CasinoAccountManager();
        do {
            arcadeDashBoardInput = getArcadeDashboardInput();
            if ("select-game".equals(arcadeDashBoardInput) || "2".equals(arcadeDashBoardInput)) {
                String accountName = console.getStringInput("Enter your account name:");
                String accountPassword = console.getStringInput("Enter your account password:");
                CasinoAccount casinoAccount = casinoAccountManager.getAccount(accountName, accountPassword);
                boolean isValidLogin = casinoAccount != null;
                if (isValidLogin) {
                    String gameSelectionInput = getGameSelectionInput().toUpperCase();
                    if (gameSelectionInput.equals("SLOTS")) {
                        play(new SlotsGame(), new SlotsPlayer());
                    } else if (gameSelectionInput.equals("WAR")) {
                        play(new WarGame(), new WarPlayer());
                    } else if (gameSelectionInput.equals("GOFISH")) {
                        play(new GoFishGame(), new GoFishPlayer());
                    } else if (gameSelectionInput.equals("BLACKJACK")) {
//                        BlackjackPlayer player=new BlackjackPlayer(accountName,accountPassword);//made player here
                        play(new BlackjackGame(), new BlackjackPlayer());
                    } else if (gameSelectionInput.equals("BINGO")) {
                        //BingoPlayer = new BingoPlayer(accountName, accountPassword);
                        play(new BingoGame(), new BingoPlayer());
                    } else if (gameSelectionInput.equals("ROULETTE")) {
                        play(new RouletteGame(), new RoulettePlayer());
                    } else {
                        // TODO - implement better exception handling
                        String errorMessage = "[ %s ] is an invalid game selection";
                        throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
                    }
                } else {
                    // TODO - implement better exception handling
                    String errorMessage = "No account found with name of [ %s ] and password of [ %s ]";
                    throw new RuntimeException(String.format(errorMessage, accountPassword, accountName));
                }
            } else if ("create-account".equals(arcadeDashBoardInput) || "1".equals(arcadeDashBoardInput)) {
                console.println("Welcome to the account-creation screen.");
                String accountName = console.getStringInput("Enter your account name:");
                String accountPassword = console.getStringInput("Enter your account password:");
                CasinoAccount newAccount = casinoAccountManager.createAccount(accountName, accountPassword);
                casinoAccountManager.registerAccount(newAccount);
            }
        } while (!"logout".equals(arcadeDashBoardInput));
    }

    private String getArcadeDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Arcade Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t(1) - [ create-account ], (2) - [ select-game ]")
                .toString());
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ SLOTS ], [ ROULETTE ], [ BLACKJACK ], [ BINGO ], [ GOFISH ], [ WAR ]")
                .toString());
    }

    private void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface) gameObject;
        PlayerInterface player = (PlayerInterface) playerObject;
        game.add(player);
        game.run();
    }
}
