import java.util.ArrayList;

public class Cards extends ArrayList<Card> {

    private GameTable gameTable;

    public Cards(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public Cards() {
        super();
    }

    public void fillMain() {
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                this.add(new Card(i,gameTable));
            }
        }
    }

    public Card onePicked(Cards target, Cards leftCards) {
        if (size() == 0) {
            System.out.println(666666666);
            if (leftCards.isEmpty() || leftCards.size() == 1) {
                System.out.println("Seems that all cards are picked. You should restart the game.");
                System.exit(0);
            } else {
                this.addAll(leftCards);
                for (Card item : leftCards
                ) {
                    leftCards.remove(item);
                }
            }
        }
        int random = (int) (Math.random() * this.size());
        Card result = this.get(random);
        remove(result, target);
        return result;
    }

    public Card onePickedGUI(Cards target){
        int random = (int) (Math.random() * this.size());
        Card result = this.get(random);
        result.getCardButton().setVisible(true);
        remove(result, target);
        return result;
    }

    public void remove(Card o, Cards leftCards) {
        leftCards.add(o);
        this.remove(o);
    }

    public void fivePicked(Cards UserCards, Cards leftCards) { //only beginning
        for (int i = 0; i < 5; i++) {
            onePicked(UserCards, leftCards);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card item : this) {
            stringBuilder.append(item.getValue()).append(" ");
        }
        return stringBuilder.toString();
    }

    public Card contains(int o) {
        for (Card item : this) {
            if (o == item.getValue()) {
                return item;
            }
        }
        return null;
    }

    public int indexOf(int o) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getValue() == o)
                return i;
        }
        return -1;
    }

    public int lastIndexOf(int o) {
        for (int i = this.size() - 1; i >= 0; i--) {
            if (this.get(i).getValue() == o)
                return i;
        }
        return -1;
    }
}
