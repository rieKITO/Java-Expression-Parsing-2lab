package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VariableResolver {
    private final Scanner scanner;

    public VariableResolver() {
        this.scanner = new Scanner(System.in);
    }

    public Map<String, Double> resolveVariables(List<String> tokens) {
        Map<String, Double> variables = new HashMap<>();

        for (String token : tokens) {
            if (isVariable(token) && !variables.containsKey(token)) {
                System.out.print("Enter a value for the " + token + ": ");
                double value = scanner.nextDouble();
                variables.put(token, value);
            }
        }

        return variables;
    }

    private boolean isVariable(String token) {
        return token.matches("[a-zA-Z]+");
    }
}
