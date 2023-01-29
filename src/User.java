import java.util.ArrayList;

public class User {
    protected ArrayList<Integer> myCards;

    protected String name;

    public String getName() {
        return name;
    }

    public User(Cards mainCards, String name) {
        this.myCards = mainCards.fivePicked();
        this.name = name;
    }

    public ArrayList<Integer> getMyCards() {
        return myCards;
    }

    public int pickOne(Cards mainCards) {
        int card = mainCards.onePicked();
        myCards.add(card);
        return card;
    }

    public boolean allUsed() {
        return myCards.isEmpty();
    }

    public boolean calLegal(int itemA, int itemB, int top) {
        return (itemA + itemB == top) ||
                (Math.abs(itemA - itemB) == top) ||
                (itemA * itemB == top) ||
                (top * itemA == itemB) ||
                (top * itemB == itemA);
    }
    protected void PrintLine(){
        System.out.println("------------------------------------------------------------------------------------");
    }
}
