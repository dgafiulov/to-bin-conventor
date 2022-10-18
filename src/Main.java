//Mr. Hecker plz dont heck my code

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static private final Scanner scan = new Scanner(System.in);
    static private boolean mainFlag;
    static private double input_d;
    static private long input_i;
    static public int move;
    static private int sign;
    static public int amountOfZeros = 0;
    static public int amountOfSame = 0;

    static {
        mainFlag = false;
    }

    public static void main(String[] args) {

        //System.out.println(toTypicalBinWithDot(5.652));

        coolHello();

        while (!mainFlag) {
            showMainQuestion();
            scannerInit(Locale.ENGLISH);
            try {
                chooseAnalysis(scan.nextInt());
            } catch (Exception IOException) {
                wrong();
                String s = scan.nextLine();
            }
        }
    }

    //"hello" block

    public static void coolHello() {
        System.out.println("ToBinConverter by Mr. Dwarf. Protected by MrDwarfSecurity");
        System.out.println(" ");
        System.out.println(" _____     ____  _        ____                          _            ");
        System.out.println("|_   _|__ | __ )(_)_ __  / ___|___  _ ____   _____ _ __| |_ ___ _ __ ");
        System.out.println("  | |/ _ \\|  _ \\| | '_ \\| |   / _ \\| '_ \\ \\ / / _ \\ '__| __/ _ \\ '__|");
        System.out.println("  | | (_) | |_) | | | | | |__| (_) | | | \\ V /  __/ |  | ||  __/ |   ");
        System.out.println("  |_|\\___/|____/|_|_| |_|\\____\\___/|_| |_|\\_/ \\___|_|   \\__\\___|_|   ");
        System.out.println(" ");
    }

    private static void scannerInit(Locale locale) {
        Scanner scan = new Scanner(System.in).useLocale(locale);
    }

    private static void showMainQuestion() {
        System.out.println(" ");
        System.out.println("Input number:");
        System.out.println("Type \"1\". Integer number");
        System.out.println("Type \"2\". Float number");
        System.out.println("Type \"3\". Settings");
        System.out.println("Type \"4\". Exit program");
        System.out.print("Number: ");
    }

    private static void chooseAnalysis(int choice) {
        switch (choice) {
            case 1 -> inputIntegerNumber();
            case 2 -> inputFloatNumber();
            case 3 -> settings();
            case 4 -> mainFlag = true;
        }
    }

    private static void wrong() {
        System.out.println();
        System.out.println(" /          \\");
        System.out.println("|   Error!   |");
        System.out.println(" \\          /");
    }

    private static void settings() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Type the number of the setting to change it");
        System.out.println();
        System.out.println("Settings:");
        System.out.println("1 Spaces between bytes: false");
        System.out.println("2 exit");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        boolean settingsFlag = false;
        while (!settingsFlag) {
            String answer = scan.nextLine();
            if (Objects.equals(answer, "2")) settingsFlag = true;
            if (Objects.equals(answer, "1")) {
                System.out.println();
                System.out.println("You must have premium account to use this.");
                System.out.println("type: \"buy\" to buy premium account.");
                System.out.println();
            }
            if (Objects.equals(answer, "buy")) {
                System.out.println();
                System.out.println("You will have an ability to buy premium account in release 2.0. Maybe.");
                System.out.println();
            }
        }
    }

    //input block

    private static void inputFloatNumber() {
        boolean flagDouble = false;
        boolean inputChange;
        String baseInput = scan.nextLine();

        while (!flagDouble) {
            System.out.println();
            System.out.print("Input float number: ");
            baseInput = scan.nextLine();
            inputChange = readFloatNumber(baseInput, Locale.CANADA);
            if (!inputChange)
                inputChange = readFloatNumber(baseInput, Locale.FRANCE);
            if (inputChange) {
                System.out.println("float: " + toCorrectBinFormat32bit(input_d));
                System.out.println("double: " + toCorrectBinFormat64bit(input_d));
                flagDouble = true;
            }
            else wrong();
        }
    }

    private static boolean readFloatNumber(String input, Locale locale) {
        boolean inputChange = false;
        Scanner sc_ca = new Scanner(input).useLocale(locale);
        try {
            input_d = sc_ca.nextDouble();
            inputChange = true;
        } catch (Exception ignored) {
        }
        return inputChange;
    }

    private static void inputIntegerNumber() {
        boolean intFlag = false;
        while (!intFlag) {
            System.out.println();
            System.out.print("Input integer number: ");
            try {
                input_i = scan.nextInt();
                intFlag = true;
                amountOfSame = 0;
            } catch (Exception e) {
                amountOfSame++;
                if (amountOfSame == 3) {
                    System.out.println();
                    System.out.println("Да я смотрю, ты упорно не хочешь следовать инструкции.");
                    System.out.println();
                }
                if (amountOfSame == 5) {
                    System.out.println();
                    System.out.println("Че, вот прям вообще не хочется? Ну тут же написано, целое число. Это когда без дроби. Без циферок после запятой");
                    System.out.println();
                }
                if (amountOfSame == 7) {
                    System.out.println();
                    System.out.println("Серьезно? Это уже 7 раз подряд!");
                    System.out.println();
                }
                if (amountOfSame == 9) {
                    System.out.println();
                    System.out.println("Девять раз!!!");
                    System.out.println();
                    mainFlag = false;
                    amountOfSame = 0;
                }
                wrong();
                String s = scan.nextLine();
            }
        }
        toCorrectBinaryInteger();
    }

    //decoding block

    private static String mantissa(double num, int length) {
        String big = (toBinaryInteger((long) num));
        move = big.length() - 1;
        int coefficientSmall = (int) Math.pow(10, (String.valueOf(num).length() - String.valueOf((int) num).length() - 1));
        String small = "";
        if ((num - (int) num) != 0) small = toBinaryFractional((int) Math.round((num % 1) * coefficientSmall));
        String result = big.substring(1) + small;
        while (result.length() < length) {
            result += "0";
        }
        if (result.length() > length) result = result.substring(0, length - 1);
        return result;
    }

    public static String toBinaryInteger(long num) {
        gettingSign(num);
        if (sign == 1) num *= -1;
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            res.insert(0, num % 2);
            num = num / 2;
        }
        return res.toString();
    }

    public static void toCorrectBinaryInteger() {
        long orig = input_i;
        StringBuilder result = new StringBuilder(String.valueOf(Long.parseLong(toBinaryInteger(input_i))));
        amountOfZeros += result.length();
        gettingSign(input_i);
        System.out.println(orig);
        if (orig >= -128 && orig < 127) {
            System.out.println("byte:" + sign + addZeros(8, result));
        } if (orig >= -32768 && orig < 32767) {
            System.out.println("short:" + sign + addZeros(16, result));
        } if (orig >= -2147483648 && orig < 2147483647) {
            System.out.println("int:" + sign + addZeros(32, result));
        } if (orig < 9223372036854775807L) {
            System.out.println("long:" + sign + addZeros(64, result));
        } if (orig > 3.4E-38) {
            System.out.println("float: " + toCorrectBinFormat32bit(Double.parseDouble(String.valueOf(orig))));
        }
        System.out.println("double: " + toCorrectBinFormat64bit(Double.parseDouble(String.valueOf(orig))));
    }

    public static StringBuilder addZeros(int size, StringBuilder result) {
        size--;
        int amountOfIterations = size - amountOfZeros;
        for (int i = 0; i < amountOfIterations; i++) {
            result.insert(0, "0");
            amountOfZeros++;
        }
        return result;
    }

    public static String toBinaryFractional(int origNum) {
        //int amountOfIterations = 0;
        double num = Double.parseDouble("0." + origNum);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < 23 - move; i++) {
            res.append(String.valueOf(num * 2).charAt(0));
            num = num * 2;
            num = num - (int) num;
        }
        return res.toString();
    }


    private static String toCorrectBinFormat32bit(double input) {

        gettingSign(Double.parseDouble(String.valueOf(input)));
        if (sign == 1) {
            input *= -1;
        }
        StringBuilder mantissa = new StringBuilder(mantissa(input, 23));
        String exponent = toBinaryInteger(127 + move);

        return sign + " " + exponent + " " + mantissa;
    }

    private static String toCorrectBinFormat64bit(double input) {

        gettingSign(Double.parseDouble(String.valueOf(input)));
        if (sign == 1) {
            input *= -1;
        }
        StringBuilder mantissa = new StringBuilder(mantissa(input, 52));
        String exponent = toBinaryInteger(1023 + move);

        return sign + " " + exponent + " " + mantissa;
    }

    private static void gettingSign(double num) {
        sign = num < 0 ? 1: 0;
    }
}