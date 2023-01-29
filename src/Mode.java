public class Mode {
    public static void cvc() {
        Cards mainCards = new Cards();
        Computer computerA = new Computer(mainCards, "A");
        Computer computerB = new Computer(mainCards, "B");

        int top = start(mainCards);
        computerA.showCards(true);
        computerB.showCards(true);
        while (true) {
            top = computerA.put(top, mainCards, true);
            if (computerA.allUsed()) {
                System.out.println(computerA.name + " win!");
                break;
            }
            top = computerB.put(top, mainCards, true);
            if (computerB.allUsed()) {
                System.out.println(computerB.name + " win!");
                break;
            }
        }
        System.out.println("Press enter to return to beginning.");
        MainClass.SCANNER.nextLine();
    }

    public static boolean pvc() {
        Cards mainCards = new Cards();
        System.out.print("First, please type in your name: ");
        Player player = new Player(mainCards, MainClass.SCANNER.nextLine());
        Computer computer = new Computer(mainCards, "Your PC friend");
        int top = start(mainCards);
        while (true) {
            top = player.put(top, mainCards);
            if (top == -1) {
                return true;
            } else if (player.allUsed()) {
                PrintLine();
                System.out.println(player.name + " win! Good job!");
                PrintLine();
                break;
            }
            top = computer.put(top, mainCards, false);
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

    public static int start(Cards mainCards) {
        int top = mainCards.onePicked();
        PrintLine();
        System.out.println("It's time to begin our game! :)");
        return top;
    }

    private static void PrintLine() {
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static void tutorial() {
System.out.println("It's simple. ");
    }
}
