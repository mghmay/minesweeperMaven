package mghmay.minesweeper;

import java.util.Arrays;
import java.util.Scanner;

public class Display {
    public static int validateInteger(int greaterThan, int lessThan) {
        Scanner scanner = new Scanner(System.in);

        int number;
        do {
            System.out.print("Please enter a number between " + greaterThan + " and " + lessThan + ":" );
            while (!scanner.hasNextInt()) {
                scanner.next();
//                String input =
//                System.out.printf(input + " is not a valid number.");
                System.out.print("Please enter a number between " + greaterThan + " and " + lessThan + ":" );
            }
            number = scanner.nextInt();
        } while (number < greaterThan || number > lessThan);
        return number;
    }
}