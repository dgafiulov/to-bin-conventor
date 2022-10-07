import java.util.Locale;
import java.util.Scanner;

public class Main {

    static private Scanner scan = new Scanner(System.in);
    static private boolean flag;

    static {
        flag = false;
    }

    public static void main(String[] args) {

        coolHello();

        while (!flag) {
            showMainQuestion();
            scannerInit(Locale.ENGLISH);
            try {
                chooseAnalysis(scan.nextInt());
            } catch (Exception IOException) {
                String s = scan.nextLine();
                wrongAnswer();
            }
        }
    }

    public static void coolHello() {
        System.out.println(" ");
        System.out.println(" _          _     _                                        _             ");
        System.out.println("| |_ ___   | |__ (_)_ __     ___ ___  _ ____   _____ _ __ | |_ ___  _ __ ");
        System.out.println("| __/ _ \\  | '_ \\| | '_ \\   / __/ _ \\| '_ \\ \\ / / _ \\ '_ \\| __/ _ \\| '__|");
        System.out.println("| || (_) | | |_) | | | | | | (_| (_) | | | \\ V /  __/ | | | || (_) | |   ");
        System.out.println(" \\__\\___/  |_.__/|_|_| |_|  \\___\\___/|_| |_|\\_/ \\___|_| |_|\\__\\___/|_|   ");
        System.out.println(" ");
    }

    private static void scannerInit(Locale locale) {
        Scanner scan = new Scanner(System.in).useLocale(locale);
    }

    private static void showMainQuestion() {
        System.out.println(" ");
        System.out.println("Input number:");
        System.out.println("Press \"1\". Integer number");
        System.out.println("Press \"2\". Float number");
        System.out.println("Press \"3\". Exit program");
        System.out.printf("Number: ");
    }

    private static void chooseAnalysis(int choice) {

        switch (choice) {
            case 1:
                break;
            case 2:
                inputFloatNumber();
                break;
            case 3:
                flag = true;
                break;
        }
    }

    private static void wrongAnswer() {
        System.out.println(" /              \\");
        System.out.println("|  Wrong answer  |");
        System.out.println(" \\              /");
    }

    private static void wrongFloat() {
        System.out.println(" /             \\");
        System.out.println("|  Not a float  |");
        System.out.println(" \\             /");
    }

    private static void inputFloatNumber() {
        System.out.println("Input float number: ");
        double input = 0.0;
        boolean flagDouble = false;
        while (!flagDouble) {
            try {
                scannerInit(Locale.ENGLISH);
                input = scan.nextDouble();
                flagDouble = true;
            } catch (Exception e) {
                try {
                    scannerInit(Locale.FRENCH);
                    input = scan.nextDouble();
                    flagDouble = true;
                } catch (Exception err) {
                    String s = scan.nextLine();
                    wrongFloat();
                }
            }
        }
        System.out.println("Number is " + input);
        flag = true;
    }
}