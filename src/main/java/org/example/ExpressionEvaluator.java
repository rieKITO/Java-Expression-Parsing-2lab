package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class ExpressionEvaluator {
    private final Map<String, Double> variables;

    public ExpressionEvaluator(Map<String, Double> variables) {
        this.variables = variables;
    }

    public double evaluate(List<String> tokens) {
        Deque<Double> operands = new ArrayDeque<>();
        Deque<String> operators = new ArrayDeque<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                operands.push(Double.parseDouble(token));
            } else if (isVariable(token)) {
                if (!variables.containsKey(token)) {
                    throw new IllegalArgumentException("The value of the variable " + token + " x is not set.");
                }
                operands.push(variables.get(token));
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(token) <= precedence(operators.peek())) {
                    applyOperation(operators.pop(), operands);
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    applyOperation(operators.pop(), operands);
                }
                String topOperator = operators.peek();
                if (topOperator != null && topOperator.equals("(")) {
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Incorrect placement of brackets.");
                }
            } else {
                throw new IllegalArgumentException("Incorrect token in the expression: " + token);
            }
        }

        while (!operators.isEmpty()) {
            applyOperation(operators.pop(), operands);
        }

        return operands.pop();
    }

    private boolean isNumber(String token) {
        return token.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isVariable(String token) {
        return token.matches("[a-zA-Z]+");
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int precedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else {
            return 0;
        }
    }

    private void applyOperation(String operator, Deque<Double> operands) {
        double b = operands.pop();
        double a = operands.pop();
        switch (operator) {
            case "+":
                operands.push(a + b);
                break;
            case "-":
                operands.push(a - b);
                break;
            case "*":
                operands.push(a * b);
                break;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                operands.push(a / b);
                break;
            default:
                throw new IllegalArgumentException("Incorrect operator: " + operator);
        }
    }
}
