import java.util.*;


class WarGame {
    private List<Integer> deck;
    private Queue<Integer> playerDeck;
    private Queue<Integer> dealerDeck;
    public WarGame() {
        deck = createdDeck();
        playerDeck = new LinkedList<>();
        dealerDeck = new LinkedList<>();
        dealCards();
    }
//creating 1 deck of 52 cards, values 2-14 and 2 to Ace
    private List<Integer> createdDeck() {
        List<Integer> deck = new ArrayList<>();
        for (int i = 2; i <= 14;i++ ){
            for(int j = 0; j <4; j++) {
                deck.add(i);
            }}
        Collections.shuffle(deck);
        return deck;
            }
            //splitting the deck between player and computer (dealer)
    private void dealCards() {
        for(int i = 0; i < deck.size(); i++) {
            if (i % 2 == 0) {
                playerDeck.add(deck.get(i));
            }else{
                dealerDeck.add(deck.get(i));
            }
        }
    }
    //playing game until winner is decided and no more cards are left
    public void playGame() {
        while (playerDeck.size() > 1 && dealerDeck.size() > 1){
            drawCard();
        }
        System.out.println(declareWinner());
    }

    private int declareWinner() {
        return 0;
    }

    //draw cards for both
    private void drawCard() {
        if (playerDeck.isEmpty() || dealerDeck.isEmpty()) return;
        int playerCard = playerDeck.poll();
        int dealerCard = dealerDeck.poll();

        System.out.println("Player Draws :" + cardName (playerCard));
        System.out.println("Dealer Draws:" + cardName(dealerCard));
        if (playerCard > dealerCard) {
            playerDeck.add(playerCard);
            playerDeck.add(dealerCard);
            System.out.println("Player wins!"); }
        else if  (dealerCard > playerCard) {
            dealerDeck.add(dealerCard);
            dealerDeck.add(playerCard);
            System.out.println("Dealer wins");
        } else { System.out.println ("It is a tie");

        }
        }
        //now making readable string
    private String cardName (int value) {
        switch (value) {
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            case 14:
                return "Ace";
            default:
                return
                        String.valueOf(value);
        }
    }

    //start the game
    public static void main (String [] args) {
        WarGame game = new WarGame();
        game.playGame();












    }



}
