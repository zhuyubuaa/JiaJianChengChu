import javax.swing.*;
import java.awt.*;
public class User {
    protected Cards myCards;

    protected JPanel myCardsGUI;

    protected String name;

    protected Mode mode;

    public String getName() {
        return name;
    }

    public User(Mode mode, String name) {
        this.mode = mode;
        this.myCards = new Cards();
        mode.getMainCards().fivePicked(myCards, mode.getLeftCards());
        this.name = name;
    }

    public JPanel getMyCardsGUI() {
        return myCardsGUI;
    }

    public User(GameTable gameTable) {   //for GUI
        this.myCards = new Cards();
        gameTable.getMainCards().fivePicked(myCards, gameTable.getLeftCards());
        myCardsGUI = new JPanel(new FlowLayout());
        for (Card item : myCards) {
            myCardsGUI.add(item.getCardButton());
        }
        myCardsGUI.setBackground(Color.CYAN);
        myCardsGUI.setPreferredSize(new Dimension(800, 800));
        myCardsGUI.setVisible(true);
    }

    public Cards getMyCards() {
        return myCards;
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

    protected void PrintLine() {
        System.out.println("------------------------------------------------------------------------------------");
    }
}
