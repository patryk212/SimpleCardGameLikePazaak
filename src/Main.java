import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Cards cards = new Cards();
        GameLogic gameLogic = new GameLogic(cards);

        Scanner scanner = new Scanner(System.in);

        int winRound = 0;
        int loseRound = 0;
        final int totalRound = 3;

        while (true) {
            System.out.println("1. Play");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 2) {
                break;
            }

            HashMap<String, Integer> playerOneMainDeck = new HashMap<>();
            gameLogic.setupPlayerHands(scanner, playerOneMainDeck);

            System.out.println("OK. Now we can start play.");
            System.out.println("1. Start");
            System.out.println("2. Exit");
            int start = scanner.nextInt();
            scanner.nextLine();
            if (start == 2) {
                break;
            }

            if (start == 1) {
                for (int roundCount = 1; roundCount <= totalRound; roundCount++) {
                    System.out.println("Round " + roundCount);
                    System.out.println("1. Start " + roundCount + " round");
                    System.out.println("2. Exit");

                    int choiceR = scanner.nextInt();
                    scanner.nextLine();
                    if (choiceR == 2) {
                        break;
                    }

                    if (choiceR == 1) {
                        if (gameLogic.playRound(scanner, playerOneMainDeck)) {
                            winRound++;
                        } else {
                            loseRound++;
                        }
                    }
                }
                System.out.println("Score: " + winRound + " : " + loseRound);
                winRound = 0;
                loseRound = 0;
            }
        }
        scanner.close();
    }
}