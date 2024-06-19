import java.util.HashMap;

public class Cards {
    private final HashMap<String,Integer> mainCards;
    private final HashMap<String,Integer> additionalCards;

    public Cards() {
        this.mainCards = new HashMap<>();
        this.additionalCards = new HashMap<>();
        initializeMainCards();
        initializeAdditionalCards();
    }

    public HashMap<String, Integer> initializeMainCards() {
        for (int i=1; i <= 10; i++){
            mainCards.put(String.valueOf(i),i);
        }
        return mainCards;
    }

    public HashMap<String, Integer> initializeAdditionalCards() {
        for (int i=1; i <= 10; i++){
            additionalCards.put(String.valueOf(i),i);
            additionalCards.put(String.valueOf(-i),-i);
        }
        return additionalCards;
    }

    public HashMap<String, Integer> getMainCards() {
        return new HashMap<>(mainCards);
    }

    public HashMap<String, Integer> getAdditionalCards() {
        return new HashMap<>(additionalCards);
    }

}
