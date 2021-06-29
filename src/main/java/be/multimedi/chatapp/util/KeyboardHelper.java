package be.multimedi.chatapp.util;

import java.time.LocalDate;
import java.util.Scanner;

public class KeyboardHelper {

    public static int askForNumber(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        return scanner.nextInt();
    }

    public static LocalDate askForDate(String text) {
        System.out.print(text);
        //TODO ask for date
        return LocalDate.of(2021, 3, 18);
    }

    public static String askForText(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        return scanner.nextLine();
    }
}