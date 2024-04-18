package org.example;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UserHandling {

    public static char moveType(String s) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println(s);
                char x = sc.next().charAt(0);

                if (x != 'r' && x != 'f') {
                    System.out.println("Invalid input. Press 'r' to reveal or 'f' to flag.");
                    continue;
                }

                return x;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Press 'r' to reveal or 'f' to flag.");
                sc.nextLine();
            }
        }
    }

    public static int revealMove(String s) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println(s);
                int x = sc.nextInt();

                if (x<0 || x>8) {
                    System.out.println("Number out of range. Please enter an integer between 0-8.");
                    continue;
                }

                return x;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer between 0-8.");
                sc.nextLine();
            }
        }
    }
}
