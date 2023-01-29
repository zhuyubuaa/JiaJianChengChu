import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player extends User {
    public String ansPattern = "(-?\\d+)(\\s*(\\d+)?)?";

    public Pattern pattern = Pattern.compile(ansPattern);

    public Player(Cards mainCards, String name) {
        super(mainCards, name);
    }

    public int put(int top, Cards mainCards) {
        ArrayList<Integer> myCards = getMyCards();
        head(top, myCards);
        String ans = MainClass.SCANNER.nextLine();
        if(ans.equals("e")){
            return -1;
        }else{
            return analyze(ans, mainCards, top);
        }
    }

    public int analyze(String ans, Cards mainCards, int top) {
        Matcher m = pattern.matcher(ans);
        if(ans.equals("h")){
            help();
            ans = MainClass.SCANNER.nextLine();
            return analyze(ans, mainCards, top);
        }
        while (!m.find()) {
            retypeWarning();
            ans = MainClass.SCANNER.nextLine();
            m = pattern.matcher(ans);
        }
        String itemA = m.group(1);
        String itemB = m.group(3);
        if (Integer.parseInt(itemA) == -1 && (itemB == null || itemB.isEmpty())) {
            return pickCard(mainCards, top);
        } else if (((itemB == null || itemB.isEmpty()) && (oneLegal(itemA, top))) ||
                !(itemB == null || itemB.isEmpty()) && twoLegal(itemA, itemB, top)) {
            correct(itemA, itemB);
            return Integer.parseInt(itemA);
        } else {
            retypeWarning();
            ans = MainClass.SCANNER.nextLine();
            return analyze(ans, mainCards, top);
        }
    }

    public void correct(String itemA, String itemB) {
        myCards.remove(Integer.valueOf(itemA));
        if (!(itemB == null || itemB.isEmpty())) {
            myCards.remove(Integer.valueOf(itemB));
        }
        PrintLine();
        System.out.println("Smart! Now let's see how the next one will deal with it!");
    }


    public int pickCard(Cards mainCards, int top) {
        int newCard = pickOne(mainCards);
        PrintLine();
        System.out.println("You get a \"" + newCard + "\".");
        return put(top, mainCards);
    }

    public boolean itemIsLegal(String item) {
        return item.matches("\\d+") && Integer.parseInt(item) >= 1 && Integer.parseInt(item) <= 13;
    }

    public void head(int top, ArrayList<Integer> myCards) {
        PrintLine();
        System.out.println(
                "Dear " + name + ":\n"
                        + "The top card is " + top + ".\n"
                        + "Now you have: "
                        + myCards
                        + " Please select 1 or 2 cards to put on the top of mainCards."
        );
        System.out.println("Type in \"h\" to get help.");
        PrintLine();
        System.out.println("Your answer or operation: ");
    }

    public void help(){
        System.out.println("1. You can type in \"-1\" to pick one more card from the mainCards.");
        System.out.println("2. The first number you type in will be the next top number. ");
        System.out.println("3. Please type in one line.");
        PrintLine();
        System.out.println("Your answer or operation: ");
    }
    public void retypeWarning() {
        PrintLine();
        System.out.println(
                "It seems that you put the cards illegally... :(\n"
                        + "Please put your cards again.");
        PrintLine();
        System.out.print("Your answer or operation: ");
    }

    public boolean oneLegal(String a, int top) {
        if (itemIsLegal(a)) {
            int item = Integer.parseInt(a);
            return item == top && myCards.contains(item);
        } else {
            return false;
        }
    }

    public boolean twoLegal(String a, String b, int top) {
        if (itemIsLegal(a) && itemIsLegal(b)) {
            int itemA = Integer.parseInt(a);
            int itemB = Integer.parseInt(b);
            if (calLegal(itemA, itemB, top)) {
                if (itemA == itemB) {
                    return myCards.indexOf(itemA) != myCards.lastIndexOf(itemA);
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

