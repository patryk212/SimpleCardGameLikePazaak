import java.util.*;

public class GameLogic {
    Cards cards;
    private final int scoreToWin = 20;

    public GameLogic(Cards cards) {
        this.cards = cards;
    }

    public void setupPlayerHands(Scanner scanner, HashMap<String, Integer> playerOneMainDeck){
        HashMap<String, Integer> temporaryPlayerOneMainDeck = new HashMap<>();
        while (temporaryPlayerOneMainDeck.size() < 5) {
            System.out.println("Choose 5 cards to your hand:");
            displayAvailableCard(cards.getAdditionalCards());

            String choiceCards = scanner.nextLine();
            addCardToHand(temporaryPlayerOneMainDeck, choiceCards, cards.getAdditionalCards());

            displayPickedCards(temporaryPlayerOneMainDeck);
        }
        finalizePlayerHand(playerOneMainDeck, temporaryPlayerOneMainDeck);
        displayFinalHand(playerOneMainDeck);
    }

    public boolean playRound(Scanner scanner, HashMap<String, Integer> playerOneMainDeck){
        int yourScore = 0;
        boolean playerHasLastMove = false;
        HashMap<String, Integer> mainDeck = cards.getMainCards();

        while (true) {
            System.out.println("Round in progress!");

            if (!playerHasLastMove) {
                yourScore = drawCardFromMainDeck(mainDeck,yourScore);
                if (checkWinOrLoose(yourScore)) return true;
                if (yourScore > scoreToWin) playerHasLastMove = true;
            }
            yourScore = playerMove(scanner, playerOneMainDeck, yourScore);
            if (checkWinOrLoose(yourScore)) return true;
            if (playerHasLastMove) return false;
        }
    }
    private int drawCardFromMainDeck(HashMap<String, Integer> mainDeck, int currentScore){
        List<String> keysMainDeck = new ArrayList<>(mainDeck.keySet());
        Collections.shuffle(keysMainDeck);
        String keyValue = keysMainDeck.get(0);
        System.out.println("Drawed card from main deck: " + keyValue);
        currentScore += mainDeck.get(keyValue);
        System.out.println("Current score: " + currentScore + " / " + scoreToWin);
        return currentScore;
    }
    private boolean checkWinOrLoose(int score){
        if (score == scoreToWin){
            System.out.println("You win this round!");
            return true;
        }else if (score > scoreToWin){
            System.out.println("Your score exceeded 20!");
            return false;
        }
        return false;
    }
    private int playerMove(Scanner scanner, HashMap<String, Integer> playerOneMainDeck, int currentScore){
        System.out.println("Choose card to play or press Enter to pass:");
        for (String card : playerOneMainDeck.keySet()) {
            System.out.print(card + " ");
        }
        System.out.println();
        String playChoice = scanner.nextLine();

        if (playerOneMainDeck.containsKey(playChoice)) {
            System.out.println("Your choice: " + playChoice);
            currentScore += playerOneMainDeck.get(playChoice);
            System.out.println("Actual score: " + currentScore + " / " + scoreToWin);
            playerOneMainDeck.remove(playChoice);
        } else {
            System.out.println("Passed!");
        }
        return currentScore;
    }

    private void finalizePlayerHand(HashMap<String, Integer> playerOneMainDeck, HashMap<String, Integer> temporaryHand){
        List<String> keys = new ArrayList<>(temporaryHand.keySet());
        for (int i=0; i < 5; i++){
            String keyValue = keys.get(i);
            playerOneMainDeck.put(keyValue, temporaryHand.get(keyValue));
        }
        System.out.println("Full hand, now we can start!");
    }
    private void displayFinalHand(HashMap<String, Integer> playerOneMainDeck){
        System.out.println("Your main deck after draw: ");
        for (String card : playerOneMainDeck.keySet()){
            System.out.print(card + " ");
        }
        System.out.println();
    }
    private void addCardToHand(HashMap<String, Integer> hand, String card, HashMap<String, Integer> sourceDeck){
        if (sourceDeck.containsKey(card)){
            hand.put(card,sourceDeck.get(card));
            sourceDeck.remove(card);
            System.out.println("Your picks: ");
        }else {
            System.out.println("Wrong pick!");
        }

    }
    private void displayPickedCards(HashMap<String, Integer> hand){
        for (String card : hand.keySet()){
            System.out.print(card + " ");
        }
        System.out.println();
    }

    private void displayAvailableCard(HashMap<String, Integer> additionalCards){
        for (String card : additionalCards.keySet()){
            System.out.print(card + " ");
        }
        System.out.println();
    }
}
