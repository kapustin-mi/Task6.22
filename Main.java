package cs.vsu.ru.kapustin;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        int numberOfMembers = (int) readNumber("number of members: ");
        double degree = readNumber("degree of the number e: ");
        double epsilon = readNumber("epsilon: ");

        double sumOfNTerms = findSumOfNTerms(numberOfMembers, degree);
        double sumOfNumbersGreaterThanE = findSumOfNumbersGreaterThanE(numberOfMembers, degree, epsilon);
        double sumOfNumbersGreaterThanTenthOfE = findSumOfNumbersGreaterThanE(numberOfMembers, degree, epsilon/10);

        double eToDegree = findEToDegree(degree);

        printResults(sumOfNTerms, sumOfNumbersGreaterThanE, sumOfNumbersGreaterThanTenthOfE, eToDegree,
                numberOfMembers, degree, epsilon);
    }

    private static double readNumber(String typeOfNumber) {
        Scanner scn = new Scanner(System.in);
        double num;
        System.out.print("Enter the " + typeOfNumber);

        if (typeOfNumber.equals("number of members: ")) {
            while (!scn.hasNextInt()) {
                System.out.print("Invalid value! Please, try again: ");
                scn.next();
            }
            num = scn.nextInt();

            if (isNumberWrong(num, typeOfNumber)) {
                return readNumber("number of members: ");
            }
        } else {
            while (!scn.hasNextDouble()) {
                System.out.print("Invalid value! Please, try again: ");
                scn.next();
            }
            num = scn.nextDouble();

            if (isNumberWrong(num, typeOfNumber)) {
               if (typeOfNumber.equals("degree of the number e: ")) {
                   return readNumber("degree of the number e: ");
               } else {
                   return readNumber("epsilon: ");
               }
            }
        }

        return num;
    }

    private static boolean isNumberWrong(double number, String typeOfNumber) {
        if (typeOfNumber.equals("number of members: ")) {
            if (number < 1) {
                System.out.println("Error! The number must be greater than 1!");
                System.out.println("Please, try again...");

                return true;
            }
        } else if (typeOfNumber.equals("degree of the number e: ")) {
            if (number < 0) {
                System.out.println("Error! The number must be greater than 0!");
                System.out.println("Please, try again...");

                return true;
            }
        } else {
            if (number < 0 || number > 1) {
                System.out.println("Error! Epsilon must be positive and less than 1!");
                System.out.println("Please, try again...");

                return true;
            }
        }

        return false;
    }

    private static double findSumOfNTerms(int numberOfMembers, double degree) {
        double sum = 1;
        int divider = 1;

        for (int i = 1; i < numberOfMembers; i++) {
            divider *= i;
            sum += Math.pow(-1, i) * Math.pow(degree, i) / divider;
        }

        return sum;
    }

    private static double findSumOfNumbersGreaterThanE(int numberOfMembers, double degree, double epsilon) {
        double sum = 0;
        int divider = 1;
        double newNumber;

        for (int i = 0; i < numberOfMembers; i++) {
            if (i == 0) {
                newNumber = Math.pow(degree, i);
            } else {
                divider *= i;
                newNumber = Math.pow(-1, i) * Math.pow(degree, i) / divider;
            }

            if (newNumber > epsilon) {
                if (i % 2 == 0) {
                    sum += newNumber;
                } else {
                    sum -= newNumber;
                }
            }
        }

        return sum;
    }

    private static double findEToDegree(double degree) {
        return Math.pow(Math.E, -degree);
    }

    private static void printResults(double sumOfNTerms, double sumOfNumbersGreaterThanE,
                                     double sumOfNumbersGreaterThanTenthOfE, double eToDegree, int numberOfMembers,
                                     double degree, double e) {
        System.out.printf("1) The sum of the first %s numbers = %2$.3f\n", numberOfMembers, sumOfNTerms);
        System.out.printf("2) The sum of the first %s numbers greater than epsilon (%2$.3f) = ", numberOfMembers, e);
        System.out.printf("%1$.3f\n", sumOfNumbersGreaterThanE);
        System.out.printf("3) The sum of the first %s numbers grater than a tenth of ", numberOfMembers);
        System.out.printf("epsilon (%1$.3f) = %2$.3f\n", e/10, sumOfNumbersGreaterThanTenthOfE);
        System.out.printf("4) The number e to the degree %1$.3f = %2$.3f", degree, eToDegree);
    }
}
