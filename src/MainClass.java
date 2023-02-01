import java.util.Scanner;

public class MainClass {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        do {
            beginning();
            String op = SCANNER.nextLine();
            switch (op) {
                case "1":
                    exit = Mode.pvc();
                    break;
                case "2":
                    Mode.tutorial();
                    break;
                case "3":
                    System.out.println("How many conputers do you want to imitate?");
                    int pcNum = SCANNER.nextInt();
                    Mode.cvc(pcNum);
                    break;
                case "s":
                    statisticsTips();
                    int n = SCANNER.nextInt();
                    System.out.println("How many conputers do you want to imitate?");
                    pcNum = SCANNER.nextInt();
                    Mode.statistics(n, pcNum);
                    break;
                case "e":
                    exit = true;
                    break;
                default:
                    illegalIn();
                    break;
            }
        } while (!exit);
        System.out.println("See you next time!");
    }

    private static void illegalIn() {
        System.out.println("Seems that you have typed illegally. " +
                "Please retype your operation.");
    }

    private static void statisticsTips() {
        PrintLine();
        System.out.println("It is statistic mode. \n" +
                "Card A's usedCnt counts how many times A was used for calculate\n" +
                "and its easyCnt counts how many times A was put on the top." +
                "\n" +
                "This statistic may help you find some interesting laws about this game" +
                " and computer's logic.\n" +
                "Your computer will play this game with its logic for any time you set below.\n" +
                "And finally statistics result will be shown on the screen.");
        PrintLine();
        System.out.print("How many times do you want to set? ");
    }

    private static void PrintLine() {
        System.out.println("------------------------------------------------------------------------------------");

    }

    public static void beginning() {
        System.out.println("Welcome to JiaJianChengChu!");
        System.out.println("Type \"1\" to play with computer");
        System.out.println("Type \"2\" to read the tutorial");
        System.out.println("Type \"3\" to see how computer plays this game with itself, " +
                "which may help you understand this game better");
        System.out.println("Type \"s\" to enter statistics mode");
        System.out.println("Type \"e\" to exit this game");
    }
}
