public class Mode {

    private Cards mainCards;

    public Cards getMainCards() {
        return mainCards;
    }

    public Cards getLeftCards() {
        return leftCards;
    }

    private final Cards leftCards;

    public Mode() {
        this.mainCards = new Cards();
        this.mainCards.fillMain();
        this.leftCards = new Cards();
    }

    public void cvc(int pcNum) {
        Computer[] computers = generateComputers(mainCards, pcNum);
        int top = start(mainCards);
        for (int i = 0; i < pcNum; i++) {
            computers[i].showCards(true);
        }
        boolean endPlay = false;
        do {
            for (int j = 0; j < pcNum; j++) {
                top = computers[j].put(top, true);
                if (computers[j].allUsed()) {
                    System.out.println(computers[j].name + " wins!");
                    PrintLine();
                    endPlay = true;
                    break;
                }
            }
        } while (!endPlay);
        System.out.println("Press enter to return to beginning.");
        MainClass.SCANNER.nextLine();
    }

    public boolean pvc() {
        System.out.print("First, please type in your name: ");
        Player player = new Player(this, MainClass.SCANNER.nextLine());
        Computer computer = new Computer(this, "Your PC friend");
        int top = start(mainCards);
        while (true) {
            System.out.println(leftCards);
            top = player.put(top);
            if (top == -1) {
                return true;
            } else if (player.allUsed()) {
                PrintLine();
                System.out.println(player.name + " win! Good job!");
                PrintLine();
                break;
            }
            top = computer.put(top, false);
            if (computer.allUsed()) {
                PrintLine();
                System.out.println("You lose... But I believe you will win next time!");
                PrintLine();
                break;
            }
        }
        System.out.println("Press enter to return to beginning.");
        MainClass.SCANNER.nextLine();
        return false;
    }

    public int start(Cards mainCards) {
        int top = mainCards.onePicked(leftCards,leftCards).getValue();
        PrintLine();
        System.out.println("It's time to begin our game! :)");
        return top;
    }

    private void PrintLine() {
        System.out.println("------------------------------------------------------------------------------------");
    }

    public void tutorial() {
        PrintLine();
        System.out.println("从1－13各四张一副牌，开局抽5张。\n" +
                "使用一张或者两张牌通过加减乘除四则运算计算凑出牌堆顶部的牌大小则可打出，\n" +
                "若手牌无法凑出牌堆顶牌，则摸牌，直到可以打出，当牌打光时获胜\n" +
                "                                       --coward");
        PrintLine();
        System.out.println("Press enter to return to beginning.");
        MainClass.SCANNER.nextLine();
    }

    public void statistics(int n, int pcNum) {
        int[] usedCnt = new int[20];
        int[] easyCnt = new int[20];
        Computer[] computers;
        int[] WinTimes = new int[pcNum];
        int roundTimes = 0;
        for (int i = 0; i < n; i++) {
            boolean endPlay = false;
            mainCards = new Cards();
            computers = generateComputers(mainCards, pcNum);
            int top = mainCards.onePicked(leftCards,leftCards).getValue();
            boolean init = true;
            do {
                for (int j = init ? i % pcNum : 0; j < pcNum; j++) {
                    top = computers[j].noPrintPut(top, usedCnt, easyCnt);
                    roundTimes++;
                    if (computers[j].allUsed()) {
                        WinTimes[j]++;
                        endPlay = true;
                        break;
                    }
                }
                init = false;
            } while (!endPlay);
        }
        for (int i = 1; i <= 13; i++) {
            System.out.println(i + "'s usedCnt is " + usedCnt[i] + " and " + i + "'s easyCnt is " + easyCnt[i]);
        }
        char name;
        for (int i = 0; i < pcNum; i++) {
            name = (char) ('A' + i);
            System.out.println(name + " wins " + WinTimes[i] + " times.");
        }
        System.out.println("Average round time is " + roundTimes / n + ".");
        PrintLine();
        MainClass.SCANNER.nextLine();
        System.out.println("Press enter to return to beginning.");
        MainClass.SCANNER.nextLine();
    }

    public Computer[] generateComputers(Cards mainCards, int pcNum) {
        Computer[] computers = new Computer[pcNum];
        for (int i = 0; i < pcNum; i++) {
            char name = (char) ('A' + i);
            computers[i] = new Computer(this, Character.toString(name));
        }
        return computers;
    }

}
