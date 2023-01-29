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
                    Mode.cvc();
                    break;
                case "e":
                    exit = true;
                    break;
                default:
                    System.out.println("Seems that you have typed illegally. " +
                            "Please retype your operation.");
                    break;
            }
        } while (!exit);
        System.out.println("See you next time!");
    }

    public static void beginning() {
        System.out.println("Welcome to JiaJianChengChu!");
        System.out.println("Type \"1\" to play with computer");
        System.out.println("Type \"2\" to read the tutorial");
        System.out.println("Type \"3\" to see how computer plays this game with itself, " +
                "which may help you understand this game better");
        System.out.println("Type \"e\" to exit this game");
    }
}
