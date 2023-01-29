import java.util.ArrayList;

//电脑方法：优先打一张，否则从小到大遍历打两张，再不行摸一张牌，以此类推
public class Computer extends User {

    public Computer(Cards mainCards, String name) {
        super(mainCards, name);
    }

    public int put(int top, Cards mainCards, boolean detail) {
        ArrayList<Integer> myCards = getMyCards();
        System.out.println("The top one is " + top + ".");
        showCards(detail);
        if (myCards.contains(top)) {
            myCards.remove(Integer.valueOf(top));
            PrintLine();
            System.out.println(name + " put \"" + top + "\" on the top.");
            System.out.println(name + " now has " + myCards.size() + " cards.");
            showCards(detail);
            return top;
        } else {
            Integer itemA;
            Integer itemB;
            for (int i = 0; i < myCards.size(); i++) {
                itemA = myCards.get(i);
                for (int j = i + 1; j < myCards.size(); j++) {
                    itemB = myCards.get(j);
                    if (i != j && calLegal(itemA, itemB, top)) {
                        myCards.remove(itemA);
                        myCards.remove(itemB);
                        PrintLine();
                        System.out.println(
                                name + " put " +
                                        "\"" + itemA + "\"" +
                                        " and " +
                                        "\"" + itemB + "\""
                                        + " on the top."
                                        + " The top one is "
                                        + Math.max(itemA, itemB) + "."
                        );
                        System.out.println(name + " now has " + myCards.size() + " cards.");
                        showCards(detail);
                        PrintLine();
                        return Math.max(itemA, itemB);
                    }
                }
            }
            pickOne(mainCards);
            System.out.println("--- " + name + " pick one card from the mainCards.");
            return put(top, mainCards, detail);
        }
    }

    public void showCards(boolean detail) {
        if (detail) {
            System.out.println("(" + name + " now has " + myCards + ")");
        }
    }
}
