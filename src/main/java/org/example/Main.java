package org.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String expression = scanner.nextLine();

        ExpressionParser parser = new ExpressionParser(expression);
        List<String> tokens = parser.parse();

        VariableResolver resolver = new VariableResolver();
        Map<String, Double> variables = resolver.resolveVariables(tokens);

        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
        try {
            double result = evaluator.evaluate(tokens);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}