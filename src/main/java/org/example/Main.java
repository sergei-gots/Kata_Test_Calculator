package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws  Exception {

        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression to be calculated: \n");
        input = scanner.nextLine();
        scanner.close();

        calc(input);
    }

    public static String calc(String input) throws Exception {
        String result;
        Expression expression = new Expression(input);

        System.out.println("Expression = " + expression);

        result = expression.getResult();

        System.out.println("Calc result = " + result);

        return result;
    }
}
