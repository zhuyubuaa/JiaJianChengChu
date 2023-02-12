public class Computer extends User {


    public Computer(Mode mode, String name) {
        super(mode, name);
    }

    public Computer(GameTable gameTable) {
        super(gameTable);
    }

    public int put(int top, boolean detail) {
        Cards myCards = getMyCards();
        System.out.println("The top one is " + top + ".");
        showCards(detail);
        Card tmp;
        if ((tmp = myCards.contains(top)) != null) {
            myCards.remove(tmp, mode.getLeftCards());
            PrintLine();
            System.out.println(name + " put \"" + top + "\" on the top.");
            System.out.println(name + " now has " + myCards.size() + " cards.");
            showCards(detail);
            return top;
        } else {
            Card itemA;
            Card itemB;
            for (int i = 0; i < myCards.size(); i++) {
                itemA = myCards.get(i);
                for (int j = i + 1; j < myCards.size(); j++) {
                    itemB = myCards.get(j);
                    if (i != j && calLegal(itemA.getValue(), itemB.getValue(), top)) {
                        PrintLine();
                        int maxAB = Math.max(itemA.getValue(), itemB.getValue());
                        System.out.println(
                                name + " put " +
                                        "\"" + itemA + "\"" +
                                        " and " +
                                        "\"" + itemB + "\""
                                        + " on the top."
                                        + " The top one is "
                                        + maxAB + "."
                        );
                        myCards.remove(itemA, mode.getLeftCards());
                        myCards.remove(itemB, mode.getLeftCards());
                        System.out.println(name + " now has " + myCards.size() + " cards.");
                        showCards(detail);
                        PrintLine();
                        return maxAB;
                    }
                }
            }
            mode.getMainCards().onePicked(myCards, mode.getLeftCards());
            System.out.println("--- " + name + " pick one card from the mainCards.");
            return put(top, detail);
        }
    }

    public Card putGUI(int top, GameTable gameTable) throws Exception {
        BeginningFrame.checkMain(gameTable);
        Card tmp;
        if ((tmp = myCards.contains(top)) != null) {
            myCards.remove(tmp, gameTable.getLeftCards());
            myCardsGUI.remove(tmp.getCardButton());
            myCardsGUI.updateUI();
            return tmp;
        } else {
            Card itemA;
            Card itemB;
            for (int i = 0; i < myCards.size(); i++) {
                itemA = myCards.get(i);
                for (int j = i + 1; j < myCards.size(); j++) {
                    itemB = myCards.get(j);
                    if (i != j && calLegal(itemA.getValue(), itemB.getValue(), top)) {
                        myCards.remove(itemA, gameTable.getLeftCards());
                        myCards.remove(itemB, gameTable.getLeftCards());
                        myCardsGUI.remove(itemA.getCardButton());
                        myCardsGUI.remove(itemB.getCardButton());
                        myCardsGUI.updateUI();
                        return itemA;
                    }
                }
            }
            try {
                Card picked = gameTable.getMainCards().onePickedGUI(myCards);
                picked.getCardButton().setEnabled(false);
                picked.getCardButton().setVisible(true);
                picked.getCardButton().setText("");
                myCardsGUI.add(picked.getCardButton());
                myCardsGUI.updateUI();
            } catch (Exception e) {
                new BeginningFrame.NoCardError();
            }
            return putGUI(top, gameTable);  //不知道会不会有bug
        }
    }

    public int noPrintPut(int top, int[] usedCnt, int[] easyCnt) {
        Cards myCards = getMyCards();
        Card tmp;
        if ((tmp = myCards.contains(top)) != null) {
            myCards.remove(tmp, mode.getLeftCards());
            easyCnt[top]++;
            return top;
        } else {
            int itemA;
            int itemB;
            for (int i = 0; i < myCards.size(); i++) {
                itemA = myCards.get(i).getValue();
                for (int j = i + 1; j < myCards.size(); j++) {
                    itemB = myCards.get(j).getValue();
                    if (i != j && calLegal(itemA, itemB, top)) {
                        myCards.remove(myCards.get(i), mode.getLeftCards());
                        myCards.remove(myCards.get(j), mode.getLeftCards());
                        easyCnt[Math.max(itemA, itemB)]++;
                        usedCnt[itemA]++;
                        usedCnt[itemB]++;
                        return Math.max(itemA, itemB);
                    }
                }
            }
            mode.getMainCards().onePicked(myCards, mode.getLeftCards());
            return noPrintPut(top, usedCnt, easyCnt);
        }
    }

    public void showCards(boolean detail) {
        if (detail) {
            System.out.println("(" + name + " now has " + myCards + ")");
        }
    }
}
