package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws  Exception {

        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression to be calculated: \n");
        input = scanner.nextLine();
        scanner.close();

        System.out.println("Calc result = " + calc(input));
    }

    public static String calc(String input) throws Exception {

        Expression expression = new Expression(input);
        System.out.println("Expression = " + expression);
        return expression.getResult();
    }
}
