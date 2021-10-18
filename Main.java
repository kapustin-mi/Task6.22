package cs.vsu.ru.kapustin;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        int numberOfMembers = readNumberOfMembers();
        double degree = readDegree();

        double sumOfNTerms = findSumOfNTerms(numberOfMembers, degree);
        double sumOfNumbersGreaterThanE = findSumOfNumbersGreaterThanE(numberOfMembers, degree);
        double sumOfNumbersGreaterThanTenthOfE = findSumOfNumbersGreaterThanTenthOfE(numberOfMembers, degree);

        double eToDegree = findEToDegree(degree);

        printResults(sumOfNTerms, sumOfNumbersGreaterThanE, sumOfNumbersGreaterThanTenthOfE, eToDegree,
                numberOfMembers, degree);
    }

    private static int readNumberOfMembers() {
        Scanner scn = new Scanner(System.in);
        int num;
        System.out.print("Enter the number of members to be counted: ");

        while (!scn.hasNextInt()) {
            System.out.println("Invalid value! Please, try again: ");
            scn.next();
        }
        num = scn.nextInt();

        if (num < 1) {
            System.out.println("Error! The number must be greater than 1!");
            System.out.println("Please, try again...");
            return readNumberOfMembers();
        }

        return num;
    }

    private static double readDegree() {
        Scanner scn = new Scanner(System.in);
        double num;
        System.out.print("Enter the degree of the number e: ");

        while (!scn.hasNextDouble()) {
            System.out.print("Invalid value! Please try again...");
            scn.next();
        }
        num = scn.nextDouble();

        if (num < 0) {
            System.out.println("Error! The number must be greater than 0!");
            System.out.println("Please, try again...");
            return readDegree();
        }

        return num;
    }

    private static double findSumOfNTerms(int numberOfMembers, double degree) {
        double sum = 1;
        int divider = 1;

        for (int i = 1; i < numberOfMembers; i++) {
            divider *= i;
            if (i % 2 == 1) {
                sum -= Math.pow(degree, i) / divider;
            } else {
                sum += Math.pow(degree, i) / divider;
            }
        }

        return sum;
    }

    private static double findSumOfNumbersGreaterThanE(int numberOfMembers, double degree) {
        double sum = 0;
        int divider = 1;
        double newNumber;

        for (int i = 0; i < numberOfMembers; i++) {
            if (i == 0) {
                newNumber = Math.pow(degree, i);
            } else {
                divider *= i;
                newNumber = Math.pow(degree, i) / divider;
            }

            if (newNumber > Math.E) {
                sum += newNumber;
            }
        }

        return sum;
    }

    private static double findSumOfNumbersGreaterThanTenthOfE(int numberOfMembers, double degree) {
        double sum = 0;
        int divider = 1;
        double newNumber;

        for (int i = 0; i < numberOfMembers; i++) {
            if (i == 0) {
                newNumber = Math.pow(degree, i);
            } else {
                divider *= i;
                newNumber = Math.pow(degree, i) / divider;
            }

            if (newNumber > Math.E / 10) {
                sum += newNumber;
            }
        }

        return sum;
    }

    private static double findEToDegree(double degree) {
        return Math.pow(Math.E, -degree);
    }

    private static void printResults(double sumOfNTerms, double sumOfNumbersGreaterThanE,
                                     double sumOfNumbersGreaterThanTenthOfE, double eToDegree, int numberOfMembers,
                                     double degree) {
        System.out.printf("1) The sum of the first %s numbers = %2$.3f\n", numberOfMembers, sumOfNTerms);
        System.out.printf("2) The sum of the first %s numbers greater than e = ", numberOfMembers);
        System.out.printf("%1$.3f\n", sumOfNumbersGreaterThanE);
        System.out.printf("3) The sum of the first %s numbers grater than a tenth of e = ", numberOfMembers);
        System.out.printf("%1$.3f\n", sumOfNumbersGreaterThanTenthOfE);
        System.out.printf("4) The number r to the degree %1$.3f = %2$.3f", degree, eToDegree);
    }
}
